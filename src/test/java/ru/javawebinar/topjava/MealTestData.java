package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final Meal MEAL1 = new Meal(START_SEQ + 2, LocalDateTime.of(2017,
            Month.JUNE, 1, 8, 30), "Админ ланч", 650);

    public static final Meal MEAL2 = new Meal(START_SEQ + 3, LocalDateTime.of(2017,
            Month.JUNE, 1, 12, 30), "Админ обед", 800);

    public static final Meal MEAL3 = new Meal(START_SEQ + 4, LocalDateTime.of(2017,
            Month.JUNE, 1, 20, 30), "Админ ужин2", 700);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}
