package com.vorotof.advancereport.service.mapper.telegram;

import com.vorotof.advancereport.service.dto.organization.OrganizationDto;
import com.vorotof.advancereport.service.dto.telegram.TelegramProductPriceDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class TelegramOrganizationDtoToString implements Mapper<OrganizationDto, String> {

    @Override
    public String map(OrganizationDto input) {
        return "[" + input.getName() + ", " + input.getInn() + "]";
    }

}
