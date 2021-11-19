package com.vorotof.advancereport.service.mapper.shop;

import com.vorotof.advancereport.domain.Shop;
import com.vorotof.advancereport.service.dto.shop.ShopDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class DtoToShop implements Mapper<ShopDto, Shop> {

    @Override
    public Shop map(ShopDto input) {
        return new Shop()
                .setId(input.getId())
                .setName(input.getName())
                .setAddress(input.getAddress())
                .setDeleted(input.getDeleted());
    }

}
