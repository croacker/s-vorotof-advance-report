package com.vorotof.advancereport.service.mapper.telegram;

import com.vorotof.advancereport.service.format.DateTimeService;
import com.vorotof.advancereport.service.dto.check.CashCheckDto;
import com.vorotof.advancereport.service.dto.telegram.TelegramFileProcessResult;
import com.vorotof.advancereport.service.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CashCheckDtoToTelegramFileProcessResult implements Mapper<CashCheckDto, TelegramFileProcessResult> {

    private final DateTimeService service;

    @Override
    public TelegramFileProcessResult map(CashCheckDto input) {
        return new TelegramFileProcessResult()
                .setCheckInfo(toString(input.getCheckDate()) + " " + input.getFiscalDocumentNumber());
    }

    private String toString(LocalDateTime dateTime){
        return service.localDateTimeToString(dateTime);
    }

}
