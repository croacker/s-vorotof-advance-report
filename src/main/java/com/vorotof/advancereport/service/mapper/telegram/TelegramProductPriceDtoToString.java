package com.vorotof.advancereport.service.mapper.telegram;

import com.vorotof.advancereport.service.dto.telegram.TelegramProductPriceDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class TelegramProductPriceDtoToString implements Mapper<TelegramProductPriceDto, String> {

    @Override
    public String map(TelegramProductPriceDto input) {
        return input.getPrice() + " руб. - " + input.getProductName();
    }

}
