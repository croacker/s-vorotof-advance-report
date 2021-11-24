package com.vorotof.advancereport.controller;

import com.vorotof.advancereport.service.AdvanceReportViewService;
import com.vorotof.advancereport.service.dto.advancereport.AdvanceReportViewDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/advance-report")
@AllArgsConstructor
@Slf4j
public class AdvanceReportController implements AdvanceReportOperations{

    private final AdvanceReportViewService service;

    @Override
    public Flux<AdvanceReportViewDto> getAdvanceReport(LocalDate beginDate, LocalDate endDate) {
        return Flux.fromIterable(service.advanceReport(beginDate, endDate));
    }

}
