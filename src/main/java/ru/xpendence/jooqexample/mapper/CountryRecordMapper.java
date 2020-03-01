package ru.xpendence.jooqexample.mapper;

import lombok.RequiredArgsConstructor;
import org.jooq.RecordMapper;
import org.springframework.stereotype.Component;
import ru.xpendence.jooqexample.domain.tables.Cities;
import ru.xpendence.jooqexample.domain.tables.records.CountriesRecord;
import ru.xpendence.jooqexample.dto.Country;
import ru.xpendence.jooqexample.repository.impl.CityRepository;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 01.03.2020
 */
@RequiredArgsConstructor
@Component
public class CountryRecordMapper implements RecordMapper<CountriesRecord, Country> {

    private final CityRepository cityRepository;

    @Override
    public Country map(CountriesRecord record) {
        Country country = record.into(Country.class);
        country.setCities(cityRepository.findAll(Cities.CITIES.COUNTRY_ID.eq(country.getId())));
        return country;
    }
}
