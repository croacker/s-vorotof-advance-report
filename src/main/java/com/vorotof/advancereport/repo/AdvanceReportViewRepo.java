package com.vorotof.advancereport.repo;

import com.vorotof.advancereport.domain.AdvanceReportView;
import com.vorotof.advancereport.domain.ProductPriceView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AdvanceReportViewRepo extends CrudRepository<AdvanceReportView, Long> {

    List<AdvanceReportView> findAllByCheckDateBetweenOrderByCheckDate(LocalDateTime beginDate, LocalDateTime endDate);

}
