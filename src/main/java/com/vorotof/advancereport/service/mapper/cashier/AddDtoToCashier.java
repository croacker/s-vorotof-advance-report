package com.vorotof.advancereport.service.mapper.cashier;

import com.vorotof.advancereport.domain.Cashier;
import com.vorotof.advancereport.service.dto.cashier.AddCashierDto;
import com.vorotof.advancereport.service.dto.cashier.CashierDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class AddDtoToCashier implements Mapper<AddCashierDto, Cashier> {

    @Override
    public Cashier map(AddCashierDto input) {
        return new Cashier()
                .setName(input.getName());
    }

}
