package com.vorotof.advancereport.service;

import com.vorotof.advancereport.TestConfiguration;
import com.vorotof.advancereport.domain.Organization;
import com.vorotof.advancereport.repo.OrganizationRepo;
import com.vorotof.advancereport.service.dto.organization.AddOrganizationDto;
import com.vorotof.advancereport.service.dto.organization.OrganizationDto;
import com.vorotof.advancereport.service.mapper.organization.AddDtoToOrganization;
import com.vorotof.advancereport.service.mapper.organization.DtoToOrganization;
import com.vorotof.advancereport.service.mapper.organization.OrganizationToDto;
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
class OrganizationServiceTest {

    private OrganizationService service;

    @MockBean
    private OrganizationRepo repo;

    private OrganizationToDto toDtoMapper;

    private DtoToOrganization toEntityMapper;

    private AddDtoToOrganization addToEntityMapper;

    private final TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @BeforeEach
    void setup() {
        toDtoMapper = new OrganizationToDto();
        toEntityMapper = new DtoToOrganization();
        addToEntityMapper = new AddDtoToOrganization();
        service = new OrganizationServiceImpl(repo, toDtoMapper, toEntityMapper, addToEntityMapper);
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
        when(repo.findById(1L)).thenReturn(Optional.of(createEntity(1L)));
        var expected = createDto(1L);

        // when
        var actual = service.findOne(1L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void findByInn() {
        // given
        when(repo.findByInn("test_inn_1")).thenReturn(Optional.of(createEntity(1L)));
        var expected = createDto(1L);

        // when
        var actual = service.findByInn("test_inn_1");

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void getOrganizations() {
        // given
        var given = PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt");
        when(repo.findByNameContainingIgnoreCase("name", given)).thenReturn(createEntitiesList());
        var expected = createDtosList();

        // when
        var actual = service.getOrganizations("name", given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void save() {
        // given
        var given = createAddOrganizationDto(1L);
        when(repo.save(any())).thenReturn(createEntity(1L));
        var expected = createDto(1L);

        // when
        var actual = service.save(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void update() {
        // given
        var given = createDto(1L);
        when(repo.save(any())).thenReturn(createEntity(1L));
        var expected = createDto(1L);

        // when
        var actual = service.update(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void delete() {
        // given
        var given = createDto(1L);
        when(repo.findById(any())).thenReturn(Optional.of(createEntity(1L)));
        when(repo.save(any())).thenReturn(createEntity(1L).setDeleted(true));
        var expected = createDto(1L).setDeleted(true);

        // when
        var actual = service.delete(1L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private List<Organization> createEntitiesList() {
        return Arrays.asList(
                createEntity(1L),
                createEntity(2L),
                createEntity(3L),
                createEntity(4L),
                createEntity(5L)
        );
    }

    private List<OrganizationDto> createDtosList() {
        return Arrays.asList(
                createDto(1L),
                createDto(2L),
                createDto(3L),
                createDto(4L),
                createDto(5L)
        );
    }

    private Organization createEntity(long id) {
        return testEntitiesProducer.createOrganization(id);
    }

    private OrganizationDto createDto(long id) {
        return testEntitiesProducer.createOrganizationDto(id);
    }

    private AddOrganizationDto createAddOrganizationDto(long id) {
        return testEntitiesProducer.createAddOrganizationDto(id);
    }
}