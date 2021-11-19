package com.vorotof.advancereport.service.mapper.shop;

import com.vorotof.advancereport.domain.Shop;
import com.vorotof.advancereport.service.dto.shop.AddShopDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class AddDtoToShop implements Mapper<AddShopDto, Shop> {

    @Override
    public Shop map(AddShopDto input) {
        return new Shop()
                .setName(input.getName())
                .setAddress(input.getAddress());
    }

}
