package com.vorotof.advancereport.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Авансовый отчет, sql-view.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
public class AdvanceReportView {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
}
