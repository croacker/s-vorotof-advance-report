package com.vorotof.advancereport.service.mapper.check;

import com.vorotof.advancereport.domain.CashCheck;
import com.vorotof.advancereport.service.dto.check.CashCheckDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class CashCheckToDto implements Mapper<CashCheck, CashCheckDto> {

    @Override
    public CashCheckDto map(CashCheck input) {
        return new CashCheckDto()
                .setId(input.getId())
                .setCashierId(input.getCashier().getId())
                .setRequestNumber(input.getRequestNumber())
                .setShiftNumber(input.getShiftNumber())
                .setKktRegId(input.getKktRegId())
                .setFiscalDriveNumber(input.getFiscalDriveNumber())
                .setFiscalDocumentNumber(input.getFiscalDocumentNumber())
                .setTotalSum(input.getTotalSum())
                .setCashSum(input.getCashSum())
                .setEcashSum(input.getEcashSum())
                .setCheckDate(input.getCheckDate())
                .setTelegramUserId(input.getTelegramUser().getId())
                .setCreatedAt(input.getCreatedAt())
                .setUpdatedAt(input.getUpdatedAt())
                .setDeleted(input.getDeleted());
    }

}
