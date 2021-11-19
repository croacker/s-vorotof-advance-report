package com.vorotof.advancereport.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * Магазин.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Shop {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * Наименование.
     */
    private String name;

    /**
     * Адрес.
     */
    private String address;

    /**
     * Организация.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    /**
     * Создан.
     */
    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    /**
     * Обновлен.
     */
    @LastModifiedDate
    private LocalDateTime updatedAt;

    /**
     * Пометка на удаление.
     */
    private Boolean deleted;
}
