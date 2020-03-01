package ru.xpendence.jooqexample.repository.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.springframework.stereotype.Repository;
import ru.xpendence.jooqexample.domain.tables.Cities;
import ru.xpendence.jooqexample.domain.tables.Countries;
import ru.xpendence.jooqexample.domain.tables.records.CountriesRecord;
import ru.xpendence.jooqexample.dto.Country;
import ru.xpendence.jooqexample.mapper.CountryRecordMapper;
import ru.xpendence.jooqexample.mapper.CountryRecordUnmapper;
import ru.xpendence.jooqexample.repository.CrudRepository;

import java.util.List;
import java.util.Objects;

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
    private final CountryRecordMapper countryRecordMapper;
    private final CountryRecordUnmapper countryRecordUnmapper;

    @Override
    public Country insert(Country country) {
        return dsl.insertInto(Countries.COUNTRIES)
                .set(dsl.newRecord(Countries.COUNTRIES, country))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new DataAccessException("Error inserting entity: " + country.getId()))
                .into(Country.class);
    }

    public Country insertWithUnmapper(Country country) {
        return dsl.insertInto(Countries.COUNTRIES)
                .set(countryRecordUnmapper.unmap(country))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new DataAccessException("Error inserting entity: " + country.getId()))
                .into(Country.class);
    }

    public Country insertSettingEachField(Country country) {
        return dsl.insertInto(Countries.COUNTRIES)
                .set(Countries.COUNTRIES.NAME, country.getName())
                .set(Countries.COUNTRIES.POPULATION, country.getPopulation())
                .set(Countries.COUNTRIES.GOVERNMENT_FORM, nameOrNull(country.getGovernmentForm()))
                .returning()
                .fetchOne()
                .into(Country.class);
    }

    public Long insertAndReturnId(Country country) {
        return dsl.insertInto(Countries.COUNTRIES)
                .set(dsl.newRecord(Countries.COUNTRIES, country))
                .returning(Countries.COUNTRIES.ID)
                .fetchOptional()
                .orElseThrow(() -> new DataAccessException("Error inserting entity: " + country.getId()))
                .get(Countries.COUNTRIES.ID);
    }

    @Override
    public Country update(Country country) {
        return dsl.update(Countries.COUNTRIES)
                .set(dsl.newRecord(Countries.COUNTRIES, country))
                .where(Countries.COUNTRIES.ID.eq(country.getId()))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new DataAccessException("Error updating entity: " + country.getId()))
                .into(Country.class);
    }

    public Country updateEachField(Country country) {
        return dsl.update(Countries.COUNTRIES)
                .set(Countries.COUNTRIES.NAME, country.getName())
                .set(Countries.COUNTRIES.POPULATION, country.getPopulation())
                .set(Countries.COUNTRIES.GOVERNMENT_FORM, nameOrNull(country.getGovernmentForm()))
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

    public Country findWithCustomMapper(Long id) {
        return dsl.selectFrom(Countries.COUNTRIES)
                .where(Countries.COUNTRIES.ID.eq(id))
                .fetchAny()
                .map(r -> countryRecordMapper.map((CountriesRecord) r));
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

    public static <T> String nameOrNull(T enumeration) {
        return Objects.isNull(enumeration) ? null : enumeration instanceof Enum ? ((Enum) enumeration).name() : null;
    }
}
