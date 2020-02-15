package ru.xpendence.jooqexample.repository;

import org.jooq.Condition;

import java.util.List;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 15.02.2020
 */
public interface CrudRepository<T> {

    Integer SUCCESS_CODE = 1;

    T insert(T t);

    T update(T t);

    T find(Long id);

    List<T> findAll(Condition condition);

    Boolean delete(Long id);
}
