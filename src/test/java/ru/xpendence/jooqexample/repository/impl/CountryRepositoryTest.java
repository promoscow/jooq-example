package ru.xpendence.jooqexample.repository.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.xpendence.jooqexample.domain.tables.Countries;
import ru.xpendence.jooqexample.dto.Country;
import ru.xpendence.jooqexample.dto.type.GovernmentForm;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 01.03.2020
 */
@SpringBootTest
class CountryRepositoryTest {

    @Autowired
    private CountryRepository repository;

    @Test
    void insert() {
        Country country = repository.insert(createCountry());
        assertNotNull(country.getId());
    }

    @Test
    void insertWithUnmapper() {
        Country country = repository.insertWithUnmapper(createCountry());
        assertNotNull(country.getId());
    }

    @Test
    void insertSettingEachField() {
        Country country = repository.insertSettingEachField(createCountry());
        assertNotNull(country.getId());
    }

    @Test
    void update() {
        Country country = repository.insert(createCountry());
        country.setPopulation(100500);
        Country updated = repository.update(country);
        assertEquals(country.getPopulation(), updated.getPopulation());
    }

    @Test
    void updateEachField() {
        Country country = repository.insert(createCountry());
        country.setPopulation(100500);
        Country updated = repository.updateEachField(country);
        assertEquals(country.getPopulation(), updated.getPopulation());
    }

    @Test
    void find() {
        Country country = repository.insert(createCountry());
        Country found = repository.find(country.getId());
        assertEquals(country.getId(), found.getId());
    }

    @Test
    void findWithCustomMapper() {
        Country country = repository.insert(createCountry());
        Country found = repository.findWithCustomMapper(country.getId());
        assertEquals(country.getId(), found.getId());
    }

    @Test
    void findAll() {
        Country toCreate = createCountry();
        toCreate.setPopulation(100);
        repository.insert(toCreate);
        List<Country> countries = repository.findAll(Countries.COUNTRIES.POPULATION.lessOrEqual(100));
        assertFalse(countries.isEmpty());
    }

    @Test
    void delete() {
        Country country = repository.insert(createCountry());
        assertTrue(repository.delete(country.getId()));
    }

    private Country createCountry() {
        Country country = new Country();
        country.setName(RandomStringUtils.randomAlphabetic(16));
        country.setGovernmentForm(Arrays.stream(GovernmentForm.values()).findAny().orElse(GovernmentForm.FEDERATE));
        country.setPopulation(new Random().nextInt(1000000000));
        return country;
    }
}