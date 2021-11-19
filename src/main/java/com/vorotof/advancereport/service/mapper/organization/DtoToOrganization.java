package com.vorotof.advancereport.service.mapper.organization;

import com.vorotof.advancereport.domain.Organization;
import com.vorotof.advancereport.service.dto.organization.OrganizationDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DtoToOrganization implements Mapper<OrganizationDto, Organization> {
    @Override
    public Organization map(OrganizationDto input) {
        return new Organization()
                .setId(input.getId())
                .setName(input.getName())
                .setInn(input.getInn())
                .setDeleted(input.getDeleted());
    }
}
