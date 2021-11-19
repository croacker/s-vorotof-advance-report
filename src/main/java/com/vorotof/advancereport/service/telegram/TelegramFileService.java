package com.vorotof.advancereport.service.telegram;

import com.vorotof.advancereport.service.dto.check.CashCheckDto;
import com.vorotof.advancereport.service.telegram.request.TelegramMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

/**
 * Сервис работы с файлами telegram.
 */
public interface TelegramFileService {

    /**
     * Обработать файл, при наличии.
     *
     * @param message сообщение
     */
    Mono<String> processFile(TelegramMessage message);

    /**
     * Получить идентификатор файла.
     *
     * @param message сообщение
     * @return идентификатор файла
     */
    Optional<String> getFileId(Message message);
}
