package com.vorotof.advancereport.service;

import com.vorotof.advancereport.repo.AdvanceReportViewRepo;
import com.vorotof.advancereport.service.dto.advancereport.AdvanceReportViewDto;
import com.vorotof.advancereport.service.mapper.advancereport.AdvanceReportViewToDtoMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис авансового отчета.
 */
@Service
@AllArgsConstructor
@Slf4j
public class AdvanceReportViewServiceImpl implements AdvanceReportViewService {

    private final AdvanceReportViewRepo repo;

    private final AdvanceReportViewToDtoMapper mapper;

    @Override
    public List<AdvanceReportViewDto> advanceReport(LocalDate beginDate, LocalDate endDate) {
        LocalDateTime begin = beginDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(LocalTime.MAX);
        return repo.findAllByCheckDateBetweenOrderByCheckDate(begin, end).stream()
                .map(mapper).collect(Collectors.toList());
    }

}
