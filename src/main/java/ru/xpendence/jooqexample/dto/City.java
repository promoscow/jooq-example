package ru.xpendence.jooqexample.dto;

import lombok.Data;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 15.02.2020
 */
@Data
public class City {

    private Long id;
    private Long countryId;
    private String name;
}
