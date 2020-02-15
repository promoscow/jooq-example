package ru.xpendence.jooqexample.repository.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.springframework.stereotype.Repository;
import ru.xpendence.jooqexample.domain.tables.Cities;
import ru.xpendence.jooqexample.domain.tables.Countries;
import ru.xpendence.jooqexample.dto.Country;
import ru.xpendence.jooqexample.repository.CrudRepository;

import java.util.List;

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
    private final CityRepository cityRepository;

    @Override
    public Country insert(Country country) {
        return dsl.insertInto(Countries.COUNTRIES)
                .set(dsl.newRecord(Countries.COUNTRIES, country))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new DataAccessException("Error inserting entity: " + country.getId()))
                .into(Country.class);
    }

    @Override
    public Country update(Country country) {
        Country saved = find(country.getId());
        return dsl.update(Countries.COUNTRIES)
                .set(dsl.newRecord(Countries.COUNTRIES, country))
                .where(Countries.COUNTRIES.ID.eq(country.getId()))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new DataAccessException("Error updating entity: " + country.getId()))
                .into(Country.class);
    }

    @Override
    public Country find(Long id) {
        return dsl.selectFrom(Countries.COUNTRIES)
                .where(Countries.COUNTRIES.ID.eq(id))
                .fetchAny()
                .map(r -> {
                    Country country = r.into(Country.class);
                    country.setCities(cityRepository.findAll(Cities.CITIES.COUNTRY_ID.eq(country.getId())));
                    return country;
                });
    }

    @Override
    public List<Country> findAll(Condition condition) {
        return dsl.selectFrom(Countries.COUNTRIES)
                .where(condition)
                .fetch()
                .map(r -> {
                    Country country = r.into(Country.class);
                    country.setCities(cityRepository.findAll(Cities.CITIES.COUNTRY_ID.eq(country.getId())));
                    return country;
                });
    }

    @Override
    public Boolean delete(Long id) {
        return dsl.deleteFrom(Countries.COUNTRIES)
                .where(Countries.COUNTRIES.ID.eq(id))
                .execute() == SUCCESS_CODE;
    }
}
