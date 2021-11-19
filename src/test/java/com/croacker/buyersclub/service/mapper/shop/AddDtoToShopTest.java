package com.vorotof.advancereport.service.mapper.shop;


import com.vorotof.advancereport.TestConfiguration;
import com.vorotof.advancereport.domain.Shop;
import com.vorotof.advancereport.service.dto.shop.AddShopDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class AddDtoToShopTest {

    private AddDtoToShop mapper;

    @BeforeEach
    void setup() {
        mapper = new AddDtoToShop();
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

    private Shop createEntity() {
        return new Shop()
                .setName("test_shop")
                .setAddress("test_address");
    }

    private AddShopDto createDto() {
        return new AddShopDto()
                .setName("test_shop")
                .setAddress("test_address");
    }

}
