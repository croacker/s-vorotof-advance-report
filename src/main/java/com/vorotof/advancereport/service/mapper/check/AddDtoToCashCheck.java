package com.vorotof.advancereport.service.mapper.check;

import com.vorotof.advancereport.domain.CashCheck;
import com.vorotof.advancereport.service.dto.check.AddCashCheckDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class AddDtoToCashCheck implements Mapper<AddCashCheckDto, CashCheck> {

    @Override
    public CashCheck map(AddCashCheckDto input) {
        return new CashCheck()
                .setRequestNumber(input.getRequestNumber())
                .setShiftNumber(input.getShiftNumber())
                .setKktRegId(input.getKktRegId())
                .setFiscalDriveNumber(input.getFiscalDriveNumber())
                .setFiscalDocumentNumber(input.getFiscalDocumentNumber())
                .setTotalSum(input.getTotalSum())
                .setCashSum(input.getCashSum())
                .setEcashSum(input.getEcashSum())
                .setCheckDate(input.getCheckDate());
    }

}
