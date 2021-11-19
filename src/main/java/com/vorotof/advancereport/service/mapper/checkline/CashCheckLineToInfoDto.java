package com.vorotof.advancereport.service.mapper.checkline;

import com.vorotof.advancereport.domain.CashCheckLine;
import com.vorotof.advancereport.service.dto.checkline.CashCheckLineInfoDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class CashCheckLineToInfoDto implements Mapper<CashCheckLine, CashCheckLineInfoDto> {
    @Override
    public CashCheckLineInfoDto map(CashCheckLine input) {
        return new CashCheckLineInfoDto()
                .setId(input.getId())
                .setProductId(input.getProduct().getId())
                .setProductName(input.getProduct().getName())
                .setPrice(input.getPrice())
                .setQuantity(input.getQuantity())
                .setTotalSum(input.getTotalSum());
    }
}
