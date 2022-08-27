package com.vorotof.advancereport.service;

import com.vorotof.advancereport.service.dto.scanneddocument.AddScannedDocumentDto;
import com.vorotof.advancereport.service.dto.scanneddocument.ScannedDocumentDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ScannedDocumentService {

    Flux<ScannedDocumentDto> findAll(Pageable pageable);

    Mono<Long> getCount();

    Mono<ScannedDocumentDto> findOne(Long id);

    Mono<ScannedDocumentDto> findByDescriptionDoc(String name);

    Mono<ScannedDocumentDto> save(AddScannedDocumentDto dto);

    Mono<ScannedDocumentDto> update(ScannedDocumentDto dto);

    Mono<ScannedDocumentDto> delete(Long id);

}
