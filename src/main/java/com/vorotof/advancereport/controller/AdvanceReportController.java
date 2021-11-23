package com.vorotof.advancereport.controller;

import com.vorotof.advancereport.domain.AdvanceReportView;
import com.vorotof.advancereport.service.TelegramUserService;
import com.vorotof.advancereport.service.dto.advancereport.AdvanceReportDto;
import com.vorotof.advancereport.service.dto.telegramuser.TelegramUserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/advance-report")
@AllArgsConstructor
@Slf4j
public class AdvanceReportController implements AdvanceReportOperations{

    private final AdvanceReportService service;

    @Override
    public Flux<AdvanceReportDto> getAdvanceReport(LocalDate beginDate, LocalDate endDate) {
        return Flux.fromIterable(service.advanceReport(beginDate, endDate));
    }

}
