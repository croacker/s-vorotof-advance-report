package com.vorotof.advancereport.service.mapper.cashier;

import com.vorotof.advancereport.domain.Cashier;
import com.vorotof.advancereport.service.dto.cashier.AddCashierDto;
import com.vorotof.advancereport.service.dto.cashier.CashierDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class DtoToCashier implements Mapper<CashierDto, Cashier> {

    @Override
    public Cashier map(CashierDto input) {
        return new Cashier()
                .setId(input.getId())
                .setName(input.getName())
                .setDeleted(input.getDeleted());
    }

}
