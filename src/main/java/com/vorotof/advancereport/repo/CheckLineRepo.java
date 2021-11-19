package com.vorotof.advancereport.repo;

import com.vorotof.advancereport.domain.CashCheckLine;
import org.springframework.data.repository.CrudRepository;

public interface CheckLineRepo extends CrudRepository<CashCheckLine, Long> {
}

