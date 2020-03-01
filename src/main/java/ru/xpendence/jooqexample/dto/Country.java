package ru.xpendence.jooqexample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.xpendence.jooqexample.dto.type.GovernmentForm;

import java.util.List;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 15.02.2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    private Long id;
    private String name;
    private GovernmentForm governmentForm;
    private Integer population;

    private List<City> cities;
}
