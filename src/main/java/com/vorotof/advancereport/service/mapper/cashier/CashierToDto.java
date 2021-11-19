package com.vorotof.advancereport.service.mapper.cashier;

import com.vorotof.advancereport.domain.Cashier;
import com.vorotof.advancereport.service.dto.cashier.CashierDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class CashierToDto implements Mapper<Cashier, CashierDto> {

    @Override
    public CashierDto map(Cashier input) {
        return new CashierDto()
                .setId(input.getId())
                .setName(input.getName())
                .setShopId(input.getShop().getId())
                .setCreatedAt(input.getCreatedAt())
                .setUpdatedAt(input.getUpdatedAt())
                .setDeleted(input.getDeleted());
    }

}
