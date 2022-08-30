package com.vorotof.advancereport.controller;

import com.vorotof.advancereport.service.ScannedDocumentService;
import com.vorotof.advancereport.service.dto.scanneddocument.AddScannedDocumentDto;
import com.vorotof.advancereport.service.dto.scanneddocument.ScannedDocumentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

/**
 * РаспознанныйДокумент из 1С
 */
@RestController
@RequestMapping("/api/v1/scanned-document")
@RequiredArgsConstructor
@Slf4j
public class ScannedDocumentController implements ScannedDocumentOperations {

    private final ScannedDocumentService service;

    @Override
    public Flux<ScannedDocumentDto> getAllScannedDocuments(int page, int size, String sort, Sort.Direction direction) {
        return service.findAll(PageRequest.of(page, size, direction, sort));
    }

    @Override
    public Mono<Long> getScannedDocumentsCount() {
        return service.getCount();
    }

    @Override
    public Mono<ScannedDocumentDto> getScannedDocument(Long id) {
        return service.findOne(id);
    }

    @Override
    public Mono<ScannedDocumentDto> getScannedDocumentByDescriptionDoc(String descriptionDoc) {
        return service.findByDescriptionDoc(descriptionDoc);
    }

    @Override
    public Flux<ScannedDocumentDto> getBetweenDate(LocalDate beginDate, LocalDate endDate) {
        return service.getBetweenDate(beginDate, endDate);
    }

    @Override
    public Flux<Long> getIdsBetweenDate(LocalDate beginDate, LocalDate endDate) {
        return service.getIdsBetweenDate(beginDate, endDate);
    }

    @Override
    public Mono<ScannedDocumentDto> createScannedDocument(AddScannedDocumentDto dto) {
        return service.save(dto);
    }

    @Override
    public Mono<ScannedDocumentDto> updateScannedDocument(ScannedDocumentDto dto) {
        return service.update(dto);
    }

    @Override
    public Mono<ScannedDocumentDto> deleteOrganization(Long id) {
        return service.delete(id);
    }
}
