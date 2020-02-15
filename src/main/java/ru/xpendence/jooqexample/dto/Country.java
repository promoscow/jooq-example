package ru.xpendence.jooqexample.dto;

import lombok.Data;
import ru.xpendence.jooqexample.dto.type.GovernmentForm;

import java.util.List;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 15.02.2020
 */
@Data
public class Country {

    private Long id;
    private String name;
    private GovernmentForm governmentForm;
    private Integer population;

    private List<City> cities;
}
