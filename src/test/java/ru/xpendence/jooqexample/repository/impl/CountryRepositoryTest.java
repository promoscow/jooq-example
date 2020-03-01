package ru.xpendence.jooqexample.repository.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.xpendence.jooqexample.JooqExampleApplicationTests;
import ru.xpendence.jooqexample.dto.Country;
import ru.xpendence.jooqexample.dto.type.GovernmentForm;

import java.util.Arrays;
import java.util.Random;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 15.02.2020
 */
class CountryRepositoryTest extends JooqExampleApplicationTests {

    @Autowired
    private CountryRepository repository;

    @Test
    void insert() {
        Country country = repository.insertWithUnmapper(createCountry());
        System.out.println();
    }

    @Test
    void update() {
        Country country = repository.insert(createCountry());
        Country toUpdate = new Country();
        toUpdate.setId(country.getId());
        toUpdate.setGovernmentForm(
                Arrays
                        .stream(GovernmentForm.values())
                        .filter(e -> !e.equals(country.getGovernmentForm()))
                        .findAny()
                        .orElse(GovernmentForm.UNITARY)
        );
        Country updated = repository.update(toUpdate);
        System.out.println();
    }

    @Test
    void find() {
        Country country = repository.insert(createCountry());
        Country found = repository.findWithCustomMapper(country.getId());
        System.out.println();
    }

    @Test
    void findAll() {
    }

    @Test
    void delete() {
    }

    private Country createCountry() {
        Country country = new Country();
        country.setName(RandomStringUtils.randomAlphabetic(16));
        country.setGovernmentForm(Arrays.stream(GovernmentForm.values()).findAny().orElse(GovernmentForm.FEDERATE));
        country.setPopulation(new Random().nextInt(1000000000));
        return country;
    }
}