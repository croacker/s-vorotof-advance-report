package com.vorotof.advancereport.service.mapper.telegram;

import com.vorotof.advancereport.TestConfiguration;
import com.vorotof.advancereport.service.dto.shop.ShopDto;
import com.vorotof.advancereport.service.dto.telegram.TelegramProductPriceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class TelegramShopDtoToStringTest {

    private TelegramShopDtoToString mapper;

    @BeforeEach
    void setup() {
        mapper = new TelegramShopDtoToString();
    }

    @Test
    void shouldMapEntity() {
        //given
        var given = createDto();
        var expected = createString();

        // when
        var actual = mapper.map(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private String createString() {
        return "[test_name, test_address]";
    }

    private ShopDto createDto() {
        return new ShopDto()
                .setName("test_name")
                .setAddress("test_address");
    }

}