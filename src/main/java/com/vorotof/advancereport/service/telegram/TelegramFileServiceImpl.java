package com.vorotof.advancereport.service.telegram;

import com.vorotof.advancereport.client.TelegramWebClient;
import com.vorotof.advancereport.service.OfdCheckServiceImpl;
import com.vorotof.advancereport.service.dto.check.CashCheckDto;
import com.vorotof.advancereport.service.mapper.ofd.OfdCheckExcerptToOfdCheck;
import com.vorotof.advancereport.service.ofd.OfdCheck;
import com.vorotof.advancereport.service.ofd.excerpt.Excerpt;
import com.vorotof.advancereport.service.telegram.request.TelegramMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис работы с файлами telegram.
 */
@Service
@Slf4j
@AllArgsConstructor
public class TelegramFileServiceImpl implements TelegramFileService {

    private final TelegramWebClient client;

    private final OfdCheckServiceImpl ofdCheckService;

    private final OfdCheckExcerptToOfdCheck mapper;

    @Override
    public Mono<String> processFile(TelegramMessage telegramMessage) {
        var userId = telegramMessage.getFrom().getId();
        return getFileId(telegramMessage.getMessage()).map(fileId -> client.getFileContent(fileId)
                .map(this::toOfdChecks)
                .map(ofdChecks -> processChecks(ofdChecks, userId))
        ).orElseGet(()->Mono.just("Ошибка"));
    }

    /**
     * Получить идентификатор файла.
     *
     * @param message сообщение
     * @return идентификатор файла
     */
    @Override
    public Optional<String> getFileId(Message message) {
        return getDocument(message).map(Document::getFileId);
    }

    /**
     * Строку в набор ОФД чеков
     *
     * @param str строка
     * @return
     */
    private List<OfdCheck> toOfdChecks(String str) {
        List<OfdCheck> ofdChecks;
        var objectMapper = new ObjectMapper();
        if (isExcerpt(str)) {
            ofdChecks = readAsExcerpt(str, objectMapper);
        } else {
            if (isMultipleChecks(str)) {
                ofdChecks = readAsChecks(str, objectMapper);
            } else {
                ofdChecks = readAsCheck(str, objectMapper);
            }
        }
        log.info("OfdChecks:{}", ofdChecks);
        return ofdChecks;
    }

    /**
     * Прочитать как чеки.
     *
     * @param str          строка
     * @param objectMapper транслятор
     * @return чеки
     */
    private List<OfdCheck> readAsChecks(String str, ObjectMapper objectMapper) {
        List<OfdCheck> result = Collections.emptyList();
        try {
            result = objectMapper.readValue(str, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Прочитать как чек.
     *
     * @param str          строка
     * @param objectMapper транслятор
     * @return чек
     */
    private List<OfdCheck> readAsCheck(String str, ObjectMapper objectMapper) {
        List<OfdCheck> result = Collections.emptyList();
        try {
            result = List.of(objectMapper.readValue(str, OfdCheck.class));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Прочитать как чеки.
     *
     * @param str          строка
     * @param objectMapper транслятор
     * @return чеки
     */
    private List<OfdCheck> readAsExcerpt(String str, ObjectMapper objectMapper) {
        List<OfdCheck> ofdChecks = Collections.emptyList();
        List<Excerpt> excerpts = Collections.emptyList();
        try {
            excerpts = objectMapper.readValue(str, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        if (!excerpts.isEmpty()){
            ofdChecks = excerpts.stream()
                    .map(excerpt -> excerpt.getTicket().getDocument().getOfdCheck())
                    .map(mapper)
                    .collect(Collectors.toList());
        }
        return ofdChecks;
    }

    /**
     * Обработать ОФД чеки
     *  @param ofdChecks ОФД чеки
     * @param userId
     * @return
     */
    private String processChecks(List<OfdCheck> ofdChecks, Long userId) {
        return ofdChecks.stream()
                .map(ofdCheck -> ofdCheckService.process(ofdCheck, userId))
                .map(telegramFileProcessResult -> telegramFileProcessResult.getCheckInfo())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Получить документ.
     *
     * @param message сообщение
     * @return документ
     */
    private Optional<Document> getDocument(Message message) {
        Optional<Document> result = Optional.empty();
        if (message != null) {
            result = Optional.ofNullable(message.getDocument());
        }
        return result;
    }

    /**
     * Это выписка по чекам за период.
     * @param str json-строка
     * @return
     */
    private boolean isExcerpt(String str){
        return StringUtils.isNotEmpty(str) && str.contains("\"claims\"");
    }

    /**
     * Это несколько чеков.
     * @param str json-строка
     * @return
     */
    private boolean isMultipleChecks(String str) {
        return str.startsWith("[");
    }

}
