package com.vorotof.advancereport.repo;

import com.vorotof.advancereport.domain.Organization;
import com.vorotof.advancereport.domain.Product;
import com.vorotof.advancereport.service.dto.organization.OrganizationDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepo extends CrudRepository<Organization, Long> {

    List<Organization> findByDeletedIsFalse(Pageable pageable);

    Optional<Organization> findByInn(String inn);

    List<Organization> findByNameContainingIgnoreCase(String expression, Pageable pageable);
}
