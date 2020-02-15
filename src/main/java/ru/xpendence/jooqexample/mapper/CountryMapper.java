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
 * @since 15.02.2020
 */
@Component
@RequiredArgsConstructor
public class CountryMapper implements RecordMapper<CountriesRecord, Country> {

//    private final RecordMapper<CountriesRecord, Country> mapper;
    private final CityRepository cityRepository;

    @Override
    public Country map(CountriesRecord countriesRecord) {
        Country country = new Country();
        country.setCities(cityRepository.findAll(Cities.CITIES.COUNTRY_ID.eq(country.getId())));
        return country;
    }
}
