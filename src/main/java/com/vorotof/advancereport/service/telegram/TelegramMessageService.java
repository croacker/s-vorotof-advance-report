package com.vorotof.advancereport.service.telegram;

import com.vorotof.advancereport.service.telegram.request.TelegramRequestType;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramMessageService {

    /**
     * Тип сообщения.
     * @param update telegram-сообщение
     * @return тип сообщения
     */
    TelegramRequestType getMessageType(Update update);

}
