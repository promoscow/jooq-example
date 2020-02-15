package ru.xpendence.jooqexample.repository.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.xpendence.jooqexample.JooqExampleApplicationTests;
import ru.xpendence.jooqexample.dto.City;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 15.02.2020
 */
class CityRepositoryTest extends JooqExampleApplicationTests {

    @Autowired
    private CityRepository repository;

    @Test
    void insert() {
        City city = repository.insert(createCity());
        System.out.println();
    }

    private City createCity() {
        City city = new City();
        city.setName("Washington");
        return city;
    }

    @Test
    void update() {
    }

    @Test
    void find() {
    }

    @Test
    void findAll() {
    }

    @Test
    void delete() {
    }
}