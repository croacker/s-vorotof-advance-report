package com.vorotof.advancereport.service.mapper.check;

import com.vorotof.advancereport.domain.CashCheck;
import com.vorotof.advancereport.domain.CashCheckLine;
import com.vorotof.advancereport.domain.TelegramUser;
import com.vorotof.advancereport.service.dto.check.CashCheckInfoDto;
import com.vorotof.advancereport.service.dto.checkline.CashCheckLineInfoDto;
import com.vorotof.advancereport.service.dto.telegramuser.TelegramUserDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import com.vorotof.advancereport.service.mapper.checkline.CashCheckLineToInfoDto;
import com.vorotof.advancereport.service.mapper.telegramuser.TelegramUserToDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CashCheckToInfoDto implements Mapper<CashCheck, CashCheckInfoDto> {

    private final CashCheckLineToInfoDto lineMapper;

    private final TelegramUserToDto telegramUserToDto;

    @Override
    public CashCheckInfoDto map(CashCheck input) {
        return new CashCheckInfoDto()
                .setId(input.getId())
                .setCashierId(input.getCashier().getId())
                .setCashierName(input.getCashier().getName())
                .setCheckLines(toCheckLines(input.getCheckLines()))
                .setRequestNumber(input.getRequestNumber())
                .setShiftNumber(input.getShiftNumber())
                .setKktRegId(input.getKktRegId())
                .setFiscalDriveNumber(input.getFiscalDriveNumber())
                .setFiscalDocumentNumber(input.getFiscalDocumentNumber())
                .setTotalSum(input.getTotalSum())
                .setCashSum(input.getCashSum())
                .setEcashSum(input.getEcashSum())
                .setCheckDate(input.getCheckDate())
                .setTelegramUser(telegramUserDto(input.getTelegramUser()))
                .setCreatedAt(input.getCreatedAt())
                .setUpdatedAt(input.getUpdatedAt())
                .setDeleted(input.getDeleted());
    }

    private List<CashCheckLineInfoDto> toCheckLines(List<CashCheckLine> checkLines) {
        return checkLines.stream().map(lineMapper).collect(Collectors.toList());
    }

    private TelegramUserDto telegramUserDto(TelegramUser telegramUser){
        return telegramUserToDto.map(telegramUser);
    }

}
