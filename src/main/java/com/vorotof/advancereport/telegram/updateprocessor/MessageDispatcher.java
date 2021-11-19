package com.vorotof.advancereport.telegram.updateprocessor;

import com.vorotof.advancereport.service.telegram.request.TelegramMessage;

public interface MessageDispatcher {

    MessageProcessor getProcessor(TelegramMessage update);

}
