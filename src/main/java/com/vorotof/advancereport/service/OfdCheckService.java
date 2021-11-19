package com.vorotof.advancereport.service;

import com.vorotof.advancereport.service.dto.telegram.TelegramFileProcessResult;
import com.vorotof.advancereport.service.ofd.OfdCheck;

/**
 * Сервис обработки поступающих чеков ОФД.
 */
public interface OfdCheckService {

    TelegramFileProcessResult process(OfdCheck ofdCheck, Long userId);

}
