package com.vorotof.advancereport.service.mapper.shop;

import com.vorotof.advancereport.TestConfiguration;
import com.vorotof.advancereport.domain.Organization;
import com.vorotof.advancereport.domain.Shop;
import com.vorotof.advancereport.service.dto.shop.ShopDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class ShopToDtoTest {

    private ShopToDto mapper;

    private final static LocalDateTime NOW = LocalDateTime.now();

    @BeforeEach
    void setup() {
        mapper = new ShopToDto();
    }

    @Test
    void shouldMapEntity() {
        //given
        var given = createEntity();
        var expected = createDto();

        // when
        var actual = mapper.map(given);

        // then
        assertTrue(new ReflectionEquals(expected).matches(actual),
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private Shop createEntity() {
        var organization = new Organization()
                .setId(0L)
                .setName("test_organization")
                .setInn("test_inn")
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
        return new Shop()
                .setId(0L)
                .setName("test_shop")
                .setAddress("test_address")
                .setOrganization(organization)
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }

    private ShopDto createDto() {
        return new ShopDto()
                .setId(0L)
                .setName("test_shop")
                .setAddress("test_address")
                .setOrganizationId(0L)
                .setCreatedAt(NOW)
                .setUpdatedAt(NOW)
                .setDeleted(false);
    }


}