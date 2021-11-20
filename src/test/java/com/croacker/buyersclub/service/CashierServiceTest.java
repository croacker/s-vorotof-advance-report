package com.vorotof.advancereport.service;

import com.vorotof.advancereport.TestConfiguration;
import com.vorotof.advancereport.domain.Cashier;
import com.vorotof.advancereport.domain.Shop;
import com.vorotof.advancereport.repo.CashierRepo;
import com.vorotof.advancereport.repo.ShopRepo;
import com.vorotof.advancereport.service.dto.cashier.AddCashierDto;
import com.vorotof.advancereport.service.dto.cashier.CashierDto;
import com.vorotof.advancereport.service.mapper.cashier.AddDtoToCashier;
import com.vorotof.advancereport.service.mapper.cashier.CashierToDto;
import com.vorotof.advancereport.service.mapper.cashier.DtoToCashier;
import com.croacker.tests.TestEntitiesProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class CashierServiceTest {

    private CashierService service;

    @MockBean
    private CashierRepo repo;

    @MockBean
    private ShopRepo shopRepo;

    private CashierToDto toDtoMapper;

    private DtoToCashier toEntityMapper;

    private AddDtoToCashier addToEntityMapper;

    private final TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @BeforeEach
    void setup(){
        toDtoMapper = new CashierToDto();
        toEntityMapper = new DtoToCashier();
        addToEntityMapper = new AddDtoToCashier();
        service = new CashierServiceImpl(repo, shopRepo, toDtoMapper, toEntityMapper, addToEntityMapper);
    }

    @Test
    void findAll(){
        // given
        var given = PageRequest.of(0, 20, Sort.Direction.ASC, "createdAt");
        var cashiers = createEntitiesList();
        when(repo.findByDeletedIsFalse(given)).thenReturn(cashiers);
        var expected = createDtosList();

        // when
        var actual = service.findAll(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void findOne(){
        // given
        var given = 1L;
        var cashier = createEntity(1L);
        when(repo.findById(given)).thenReturn(Optional.of(cashier));
        var expected = createDto(1L);

        // when
        var actual = service.findOne(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void findByName(){
        // given
        var given = "test_cashier_1";
        var cashier = createEntity(1L);
        when(repo.findByNameAndShopId(given, 1L)).thenReturn(Optional.of(cashier));
        var expected = createDto(1L);

        // when
        var actual = service.findByNameAndShopId(given, 1L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void save(){
        // given
        var given = createAddDto(0L);
        var cashier = createEntity(0L);
        var shop = createShop(0L);
        when(repo.save(any())).thenReturn(cashier);
        when(shopRepo.findById(0L)).thenReturn(Optional.of(shop));
        var expected = createDto(0L);

        // when
        var actual = service.save(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void update(){
        // given
        var given = createDto(0L);
        var cashier = createEntity(0L);
        var shop = createShop(0L);
        when(repo.save(any())).thenReturn(cashier);
        when(shopRepo.findById(0L)).thenReturn(Optional.of(shop));
        var expected = createDto(0L);

        // when
        var actual = service.update(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void delete(){
        // given
        var cashier = createEntity(1L);
        var deletedCashier = createEntity(1L).setDeleted(true);
        when(repo.findById(1L)).thenReturn(Optional.of(cashier));
        when(repo.save(any())).thenReturn(deletedCashier);
        var expected = createDto(1L).setDeleted(true);

        // when
        var actual = service.delete(1L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private List<Cashier> createEntitiesList() {
        return Arrays.asList(
                createEntity(1L),
                createEntity(2L),
                createEntity(3L),
                createEntity(4L),
                createEntity(5L)
        );
    }

    private List<CashierDto> createDtosList() {
        return Arrays.asList(
                createDto(1L),
                createDto(2L),
                createDto(3L),
                createDto(4L),
                createDto(5L)
        );
    }

    private Cashier createEntity(long id){
        return testEntitiesProducer.createCashier(id);
    }

    private CashierDto createDto(long id){
        return testEntitiesProducer.createCashierDto(id);
    }

    private AddCashierDto createAddDto(long id){
        return testEntitiesProducer.createAddCashierDto(id);
    }

    private Shop createShop(long id){
        return testEntitiesProducer.createShop(id);
    }
}