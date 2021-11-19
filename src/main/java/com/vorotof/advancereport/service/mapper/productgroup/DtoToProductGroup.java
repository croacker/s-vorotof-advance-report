package com.vorotof.advancereport.service.mapper.productgroup;

import com.vorotof.advancereport.domain.ProductGroup;
import com.vorotof.advancereport.service.dto.productgroup.ProductGroupDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class DtoToProductGroup implements Mapper<ProductGroupDto, ProductGroup> {

    @Override
    public ProductGroup map(ProductGroupDto input) {
        return new ProductGroup()
                .setId(input.getId())
                .setName(input.getName())
                .setDeleted(input.getDeleted());
    }

}
