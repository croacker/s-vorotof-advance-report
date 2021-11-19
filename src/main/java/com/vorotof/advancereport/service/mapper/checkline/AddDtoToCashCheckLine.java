package com.vorotof.advancereport.service.mapper.checkline;

import com.vorotof.advancereport.domain.CashCheckLine;
import com.vorotof.advancereport.service.dto.checkline.AddCashCheckLineDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class AddDtoToCashCheckLine implements Mapper<AddCashCheckLineDto, CashCheckLine> {

    @Override
    public CashCheckLine map(AddCashCheckLineDto input) {
        return new CashCheckLine()
                .setPrice(input.getPrice())
                .setQuantity(input.getQuantity())
                .setTotalSum(input.getTotalSum());
    }

}
