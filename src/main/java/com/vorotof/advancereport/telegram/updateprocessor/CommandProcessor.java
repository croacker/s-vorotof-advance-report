package com.vorotof.advancereport.telegram.updateprocessor;

import com.vorotof.advancereport.service.locale.LocaleService;
import com.vorotof.advancereport.service.telegram.request.TelegramMessage;
import com.vorotof.advancereport.telegram.keyboard.ReplyKeyboardBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
public class CommandProcessor implements MessageProcessor {

    private final TelegramMessage message;

    private final LocaleService localeService;

    @Override
    public Mono<SendMessage> process() {
        return startMenu();
    }

    public Mono<SendMessage> startMenu() {
        var builder = new ReplyKeyboardBuilder();
        builder.newButton(getString("menu.start.data"));
        builder.newButton(getString("menu.start.reports"));
        var sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(getString("message.choosetype"));
        sendMessage.setReplyMarkup(builder.build());
        return Mono.just(sendMessage);
    }

    private String getString(String key) {
        var languageCode = message.getLanguageCode();
        return localeService.getString(key, languageCode);
    }
}
