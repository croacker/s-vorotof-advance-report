package com.vorotof.advancereport.telegram;

import com.vorotof.advancereport.config.TelegramConfiguration;
import com.vorotof.advancereport.service.locale.LocaleService;
import com.vorotof.advancereport.service.telegram.request.TelegramRequestType;
import com.vorotof.advancereport.service.telegram.request.TelegramCallback;
import com.vorotof.advancereport.service.telegram.request.TelegramCommand;
import com.vorotof.advancereport.service.telegram.request.TelegramFile;
import com.vorotof.advancereport.service.telegram.request.TelegramMessage;
import com.vorotof.advancereport.service.telegram.request.TelegramQuery;
import com.vorotof.advancereport.service.telegram.TelegramMessageServiceImpl;
import com.vorotof.advancereport.telegram.updateprocessor.MessageDispatcher;
import com.vorotof.advancereport.telegram.updateprocessor.MessageProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class IrkBuyersClubBot extends TelegramLongPollingBot {

    private final int RECONNECT_PAUSE = 10000;

    private final TelegramBotsApi telegramBotsApi;

    private final LocaleService localeService;

    private final TelegramConfiguration configuration;

    private final MessageDispatcher messageDispatcher;

    private final TelegramMessageServiceImpl telegramMessageService;

    @Override
    public String getBotUsername() {
        return configuration.getUsername();
    }

    @Override
    public String getBotToken() {
        return configuration.getToken();
    }

    @PostConstruct
    public void init() {
        botConnect();
    }

    @Override
    public void onUpdateReceived(Update update) {
        var telegramMessage = toTelegramMessage(update);
        getInprocessMessage(telegramMessage).ifPresent(this::sendResponse);
        Mono.just(telegramMessage).flatMap(this::process).subscribe(this::sendResponse);
    }

    /**
     * Сообщение о том что запрос обрабатывается.
     * @param update сообщение от пользователя
     * @return ответ
     */
    private Optional<SendMessage> getInprocessMessage(TelegramMessage update) {
        return switch (update.getTelegramRequestType()){
            case FILE -> Optional.of(fileInprocess(update));
            case QUERY -> Optional.of(queryInprocess(update));
            default -> Optional.empty();
        };
    }

    /**
     * Отправить сообщение.
     * @param response сообщение
     */
    private void sendResponse(SendMessage response) {
        try {
            execute(response);
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
        }
    }

    private Mono<SendMessage> process(TelegramMessage telegramMessage) {
        return getProcessor(telegramMessage).process();
    }

    private void botConnect() {
        try {
            telegramBotsApi.registerBot(this);
            log.info("TelegramAPI started. Look for messages");
        } catch (TelegramApiRequestException e) {
            log.info("Cant Connect. Pause " + RECONNECT_PAUSE / 1000 + "sec and try again. Error: " + e.getMessage());
            try {
                Thread.sleep(RECONNECT_PAUSE);
            } catch (InterruptedException e1) {
                log.error(e1.getMessage(), e1);
                return;
            }
            botConnect();
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
        }
    }

    private MessageProcessor getProcessor(TelegramMessage telegramMessage){
        return messageDispatcher.getProcessor(telegramMessage);
    }

    private TelegramRequestType getMessageType(Update update){
        return telegramMessageService.getMessageType(update);
    }

    // TODO to service
    private SendMessage fileInprocess(TelegramMessage telegramMessage){
        var text = getString("response.file.inprocess", telegramMessage.getLanguageCode());
        return getResponse(telegramMessage.getChatId(), text);
    }

    // TODO to service
    private SendMessage queryInprocess(TelegramMessage telegramMessage){
        var text = getString("response.query.inprocess", telegramMessage.getLanguageCode());
        return getResponse(telegramMessage.getChatId(), text);
    }

    // TODO to service
    private SendMessage getResponse(String chatId, String responseText) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        sendMessage.setText(responseText);
        return sendMessage;
    }

    private String getString(String key, String languageCode){
        return localeService.getString(key, languageCode);
    }

    private TelegramMessage toTelegramMessage(Update update) {
        var type = getMessageType(update);
        return switch (type) {
            case FILE -> new TelegramFile(type, update);
//            case COMMAND -> new TelegramCommand(type, update);
//            case CALLBACK -> new TelegramCallback(type, update);
            default -> new TelegramQuery(type, update);
        };
    }

}
