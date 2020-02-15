package ru.xpendence.jooqexample.repository.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.xpendence.jooqexample.domain.tables.Countries;
import ru.xpendence.jooqexample.dto.Country;
import ru.xpendence.jooqexample.repository.CrudRepository;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 15.02.2020
 */
@Repository
@RequiredArgsConstructor
public class CountryRepository implements CrudRepository<Country> {

    private final DSLContext dsl;

    @Override
    public Country insert(Country country) {
        return dsl.insertInto(Countries.COUNTRIES)
                .set(dsl.newRecord(Countries.COUNTRIES, country))
                .returning()
                .fetchOne()
                .into(Country.class);
    }

    @Override
    public Country update(Country country) {
        return null;
    }

    @Override
    public Country find(Long id) {
        return null;
    }

    @Override
    public Country findAll(Condition condition) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
