package com.vorotof.advancereport.repo;

import com.vorotof.advancereport.domain.ScannedDocument;
import com.vorotof.advancereport.domain.Shop;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ScannedDocumentRepo extends CrudRepository<ScannedDocument, Long> {

    List<ScannedDocument> findByDeletedIsFalse(Pageable pageable);

    Optional<ScannedDocument> findFirstByDescriptionDoc(String descriptionDoc);

}
