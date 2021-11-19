package com.vorotof.advancereport.service.mapper.productgroup;

import com.vorotof.advancereport.domain.ProductGroup;
import com.vorotof.advancereport.service.dto.productgroup.AddProductGroupDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class AddDtoToProductGroup implements Mapper<AddProductGroupDto, ProductGroup> {

    @Override
    public ProductGroup map(AddProductGroupDto input) {
        return new ProductGroup()
                .setName(input.getName());
    }

}
