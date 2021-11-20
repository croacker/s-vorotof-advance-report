package com.vorotof.advancereport.repo;

import com.vorotof.advancereport.domain.CashCheck;
import com.vorotof.advancereport.service.dto.check.CashCheckDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CheckRepo extends CrudRepository<CashCheck, Long> {

    List<CashCheck> findByDeletedIsFalse(Pageable pageable);

    Optional<CashCheck> findByKktRegIdAndFiscalDriveNumberAndFiscalDocumentNumber(String kktRegId,
                                                                                     String fiscalDriveNumber, String fiscalDocumentNumber1);
}

