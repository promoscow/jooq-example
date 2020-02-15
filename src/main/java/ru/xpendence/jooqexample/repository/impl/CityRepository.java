package ru.xpendence.jooqexample.repository.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.xpendence.jooqexample.domain.tables.Cities;
import ru.xpendence.jooqexample.dto.City;
import ru.xpendence.jooqexample.repository.CrudRepository;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 15.02.2020
 */
@Repository
@RequiredArgsConstructor
public class CityRepository implements CrudRepository<City> {

    private final DSLContext dsl;

    @Override
    public City insert(City city) {
        return dsl.insertInto(Cities.CITIES)
                .set(dsl.newRecord(Cities.CITIES, city))
                .returning()
                .fetchOne()
                .into(City.class);
    }

    @Override
    public City update(City city) {
        return null;
    }

    @Override
    public City find(Long id) {
        return null;
    }

    @Override
    public City findAll(Condition condition) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
