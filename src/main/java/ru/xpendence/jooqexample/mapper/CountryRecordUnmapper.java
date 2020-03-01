package ru.xpendence.jooqexample.mapper;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.RecordUnmapper;
import org.jooq.exception.MappingException;
import org.springframework.stereotype.Component;
import ru.xpendence.jooqexample.domain.tables.Countries;
import ru.xpendence.jooqexample.domain.tables.records.CountriesRecord;
import ru.xpendence.jooqexample.dto.Country;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 01.03.2020
 */
@Component
@RequiredArgsConstructor
public class CountryRecordUnmapper implements RecordUnmapper<Country, CountriesRecord> {

    private final DSLContext dsl;

    @Override
    public CountriesRecord unmap(Country country) throws MappingException {
        CountriesRecord record = dsl.newRecord(Countries.COUNTRIES, country);
        record.setPopulation(-1);
        return record;
    }
}
