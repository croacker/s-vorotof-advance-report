package com.vorotof.advancereport.service.mapper.shop;

import com.vorotof.advancereport.domain.Shop;
import com.vorotof.advancereport.service.dto.shop.ShopDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class ShopToDto implements Mapper<Shop, ShopDto> {

    @Override
    public ShopDto map(Shop input) {
        return new ShopDto()
                .setId(input.getId())
                .setName(input.getName())
                .setAddress(input.getAddress())
                .setOrganizationId(input.getOrganization().getId())
                .setCreatedAt(input.getCreatedAt())
                .setUpdatedAt(input.getUpdatedAt())
                .setDeleted(input.getDeleted());
    }

}