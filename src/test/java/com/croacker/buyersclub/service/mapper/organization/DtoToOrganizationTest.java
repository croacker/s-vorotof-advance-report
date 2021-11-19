package com.vorotof.advancereport.service.mapper.organization;


import com.vorotof.advancereport.TestConfiguration;
import com.vorotof.advancereport.domain.Organization;
import com.vorotof.advancereport.service.dto.organization.OrganizationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class DtoToOrganizationTest {

    private DtoToOrganization mapper;

    @BeforeEach
    void setup() {
        mapper = new DtoToOrganization();
    }

    @Test
    void shouldMapDto() {
        //given
        var given = createDto();
        var expected = createEntity();

        // when
        var actual = mapper.map(given);

        // then
        assertTrue(new ReflectionEquals(expected).matches(actual),
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private Organization createEntity() {
        return new Organization()
                .setId(0L)
                .setName("test_organization")
                .setInn("test_inn")
                .setDeleted(false);
    }

    private OrganizationDto createDto() {
        return new OrganizationDto()
                .setId(0L)
                .setName("test_organization")
                .setInn("test_inn")
                .setDeleted(false);
    }

}
