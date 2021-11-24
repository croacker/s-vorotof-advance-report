package com.vorotof.advancereport.service;

import com.vorotof.advancereport.service.dto.advancereport.AdvanceReportViewDto;

import java.time.LocalDate;
import java.util.List;

public interface AdvanceReportViewService {

    List<AdvanceReportViewDto> advanceReport(LocalDate beginDate, LocalDate endDate);

}
