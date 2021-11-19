package com.vorotof.advancereport.service.mapper.organization;

import com.vorotof.advancereport.domain.Organization;
import com.vorotof.advancereport.service.dto.organization.OrganizationDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class OrganizationToDto implements Mapper<Organization, OrganizationDto> {
    @Override
    public OrganizationDto map(Organization input) {
        return new OrganizationDto()
                .setId(input.getId())
                .setName(input.getName())
                .setInn(input.getInn())
                .setCreatedAt(input.getCreatedAt())
                .setUpdatedAt(input.getUpdatedAt())
                .setDeleted(input.getDeleted());
    }
}
