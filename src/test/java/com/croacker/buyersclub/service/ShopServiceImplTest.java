package com.vorotof.advancereport.service;

import com.vorotof.advancereport.TestConfiguration;
import com.vorotof.advancereport.domain.Organization;
import com.vorotof.advancereport.domain.Shop;
import com.vorotof.advancereport.repo.OrganizationRepo;
import com.vorotof.advancereport.repo.ShopRepo;
import com.vorotof.advancereport.service.dto.shop.AddShopDto;
import com.vorotof.advancereport.service.dto.shop.ShopDto;
import com.vorotof.advancereport.service.mapper.shop.AddDtoToShop;
import com.vorotof.advancereport.service.mapper.shop.DtoToShop;
import com.vorotof.advancereport.service.mapper.shop.ShopToDto;
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
class ShopServiceImplTest {

    private ShopService service;

    @MockBean
    private ShopRepo repo;

    @MockBean
    private OrganizationRepo organizationRepo;

    private ShopToDto toDtoMapper;

    private DtoToShop toShopMapper;

    private AddDtoToShop addToEntityMapper;

    private final TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @BeforeEach
    void setup(){
        toDtoMapper = new ShopToDto();
        toShopMapper = new DtoToShop();
        addToEntityMapper = new AddDtoToShop();
        service = new ShopServiceImpl(repo, organizationRepo,toDtoMapper, toShopMapper, addToEntityMapper);
    }

    @Test
    void findAll() {
        // given
        var given = PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt");
        when(repo.findByDeletedIsFalse(given)).thenReturn(createEntitiesList());
        var expected = createDtosList();

        // when
        var actual = service.findAll(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void findOne() {
        // given
        when(repo.findById(0L)).thenReturn(Optional.of(createEntity(0L)));
        var expected = createDto(0L);

        // when
        var actual = service.findOne(0L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void findByName() {
        // given
        when(repo.findFirstByName("test_shop_0")).thenReturn(Optional.of(createEntity(0L)));
        var expected = createDto(0L);

        // when
        var actual = service.findByName("test_shop_0");

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);

    }

    @Test
    void findByAddress() {
        // given
        when(repo.findFirstByAddress("test_address_0")).thenReturn(Optional.of(createEntity(0L)));
        var expected = createDto(0L);

        // when
        var actual = service.findByAddress("test_address_0");

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void save() {
        // given
        var given = createAddDto(0L);
        when(repo.save(any())).thenReturn(createEntity(0L));
        when(organizationRepo.findById(0L)).thenReturn(Optional.of(createOrganization(0L)));
        var expected = createDto(0L);

        // when
        var actual = service.save(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void update() {
        // given
        var given = createDto(0L);
        when(repo.save(any())).thenReturn(createEntity(0L));
        when(organizationRepo.findById(0L)).thenReturn(Optional.of(createOrganization(0L)));
        var expected = createDto(0L);

        // when
        var actual = service.update(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void delete() {
        // given
        var given = createDto(0L);
        when(repo.findById(any())).thenReturn(Optional.of(createEntity(0L)));
        when(repo.save(any())).thenReturn(createEntity(0L).setDeleted(true));
        var expected = createDto(0L).setDeleted(true);

        // when
        var actual = service.delete(0L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void getShops() {
        // given
        var pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt");
        when(repo.findByNameContainingIgnoreCase("test_expression", pageable)).thenReturn(createEntitiesList());
        var expected = createDtosList();

        // when
        var actual = service.getShops("test_expression", pageable);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private List<Shop> createEntitiesList() {
        return Arrays.asList(
                createEntity(1L),
                createEntity(2L),
                createEntity(3L),
                createEntity(4L),
                createEntity(5L)
        );
    }

    private Shop createEntity(long id) {
        return testEntitiesProducer.createShop(id);
    }

    private List<ShopDto> createDtosList() {
        return Arrays.asList(
                createDto(1L),
                createDto(2L),
                createDto(3L),
                createDto(4L),
                createDto(5L)
        );
    }

    private ShopDto createDto(long id) {
        return testEntitiesProducer.createShopDto(id);
    }

    private AddShopDto createAddDto(long id) {
        return testEntitiesProducer.createAddShopDto(id);
    }

    private Organization createOrganization(long id) {
        return testEntitiesProducer.createOrganization(id);
    }

}