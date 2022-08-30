package com.vorotof.advancereport.repo;

import com.vorotof.advancereport.domain.ScannedDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScannedDocumentRepo extends CrudRepository<ScannedDocument, Long> {

    List<ScannedDocument> findByIdIsNotNull(Pageable pageable);

    List<ScannedDocument> findAllByDateBetweenOrderByDate(LocalDateTime beginDate, LocalDateTime endDate);

    @Query("select id from ScannedDocument where date between :beginDate and :endDate")
    List<Long> findAllIdByDateBetweenOrderByDate(LocalDateTime beginDate, LocalDateTime endDate);

    Optional<ScannedDocument> findFirstByDescriptionDoc(String descriptionDoc);

}
