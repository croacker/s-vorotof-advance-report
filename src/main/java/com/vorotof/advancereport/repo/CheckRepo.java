package com.vorotof.advancereport.repo;

import com.vorotof.advancereport.domain.CashCheck;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CheckRepo extends CrudRepository<CashCheck, Long> {

    List<CashCheck> findByDeletedIsFalse(Pageable pageable);

}

