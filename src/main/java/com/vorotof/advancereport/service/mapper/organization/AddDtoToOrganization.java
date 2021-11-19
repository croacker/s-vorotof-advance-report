package com.vorotof.advancereport.service.mapper.organization;

import com.vorotof.advancereport.domain.Organization;
import com.vorotof.advancereport.service.dto.organization.AddOrganizationDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AddDtoToOrganization implements Mapper<AddOrganizationDto, Organization> {
    @Override
    public Organization map(AddOrganizationDto input) {
        return new Organization()
                .setName(input.getName())
                .setInn(input.getInn());
    }
}
