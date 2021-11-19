package com.vorotof.advancereport.service;

import com.vorotof.advancereport.service.dto.organization.AddOrganizationDto;
import com.vorotof.advancereport.service.dto.organization.OrganizationDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;

/**
 * Сервис организаций.
 */
public interface OrganizationService {

    List<OrganizationDto> findAll(Pageable pageable);

    Mono<Long> getCount();

    OrganizationDto findOne(Long id);

    OrganizationDto save(AddOrganizationDto dto);

    OrganizationDto update(OrganizationDto dto);

    OrganizationDto delete(Long id);

    OrganizationDto findByInn(String inn);

    List<OrganizationDto> getOrganizations(String expression, Pageable pageable);
}
