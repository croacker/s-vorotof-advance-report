package com.vorotof.advancereport.service.dto.organization;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Организация.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "Организация")
public class OrganizationDto {
    /**
     * Идентификатор.
     */
    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    /**
     * Наименование.
     */
    @Schema(description = "Наименование", example = "ООО 'Наименование'")
    private String name;

    /**
     * ИНН.
     */
    @Schema(description = "ИНН", example = "4835354717")
    private String inn;

    /**
     * Создан.
     */
    @Schema(description = "Создан")
    private LocalDateTime createdAt;

    /**
     * Обновлен.
     */
    @Schema(description = "Обновлен")
    private LocalDateTime updatedAt;

    /**
     * Пометка на удаление.
     */
    @Schema(description = "Пометка на удаление", example = "false")
    private Boolean deleted;
}
