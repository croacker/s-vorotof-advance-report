package com.vorotof.advancereport.service;

import com.vorotof.advancereport.domain.ScannedDocument;
import com.vorotof.advancereport.repo.ScannedDocumentRepo;
import com.vorotof.advancereport.service.dto.scanneddocument.AddScannedDocumentDto;
import com.vorotof.advancereport.service.dto.scanneddocument.ScannedDocumentDto;
import com.vorotof.advancereport.service.mapper.scanneddocument.AddDtoToScannedDocument;
import com.vorotof.advancereport.service.mapper.scanneddocument.DtoToScannedDocument;
import com.vorotof.advancereport.service.mapper.scanneddocument.ScannedDocumentToDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScannedDocumentServiceImpl implements ScannedDocumentService {

    private final ScannedDocumentRepo repo;

    private final ScannedDocumentToDto toDtoMapper;

    private final DtoToScannedDocument toEntityMapper;

    private final AddDtoToScannedDocument addToEntityMapper;

    @Override
    public Flux<ScannedDocumentDto> findAll(Pageable pageable) {
        return Flux.fromIterable(repo.findByIdIsNotNull(pageable)).map(toDtoMapper);
    }

    @Override
    public Mono<Long> getCount() {
        return Mono.just(repo.count());
    }

    @Override
    public Mono<ScannedDocumentDto> findOne(Long id) {
        return repo.findById(id).map(toDtoMapper)
                .map(Mono::just)
                .orElse(Mono.empty());

    }

    @Override
    public Mono<ScannedDocumentDto> findByDescriptionDoc(String descriptionDoc) {
        return repo.findFirstByDescriptionDoc(descriptionDoc).map(toDtoMapper)
                .map(Mono::just)
                .orElse(Mono.empty());
    }

    @Override
    public Flux<ScannedDocumentDto> getBetweenDate(LocalDate beginDate, LocalDate endDate) {
        log.info("Request ScannedDocument from {} to {}", beginDate, endDate);
        LocalDateTime begin = beginDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(LocalTime.MAX);
        return Flux.fromIterable(repo.findAllByDateBetweenOrderByDate(begin, end))
                .map(toDtoMapper);
    }

    @Override
    public Flux<Long> getIdsBetweenDate(LocalDate beginDate, LocalDate endDate) {
        log.info("Request ScannedDocument from {} to {}", beginDate, endDate);
        LocalDateTime begin = beginDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(LocalTime.MAX);
        return Flux.fromIterable(repo.findAllIdByDateBetweenOrderByDate(begin, end));
    }

    @Override
    public Mono<ScannedDocumentDto> save(AddScannedDocumentDto dto) {
        ScannedDocument document = addToEntityMapper.map(dto);
        return Mono.just(toDtoMapper.map(repo.save(document)));
    }

    @Override
    public Mono<ScannedDocumentDto> update(ScannedDocumentDto dto) {
        ScannedDocument document = toEntityMapper.map(dto);
        return Mono.just(toDtoMapper.map(repo.save(document)));
    }

    @Override
    public Mono<ScannedDocumentDto> delete(Long id) {
//        return repo.findById(id).map(scannedDocument -> {
//                    scannedDocument = repo.save(scannedDocument);
//                    return toDtoMapper.map(scannedDocument);
//                })
//                .map(Mono::just)
//                .orElse(Mono.empty());
        throw new UnsupportedOperationException();
    }
}
