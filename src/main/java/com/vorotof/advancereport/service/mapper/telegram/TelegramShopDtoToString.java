package com.vorotof.advancereport.service.mapper.telegram;

import com.vorotof.advancereport.service.dto.shop.ShopDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class TelegramShopDtoToString implements Mapper<ShopDto, String> {

    @Override
    public String map(ShopDto input) {
        return "[" + input.getName() + ", " + input.getAddress() + "]";
    }

}
