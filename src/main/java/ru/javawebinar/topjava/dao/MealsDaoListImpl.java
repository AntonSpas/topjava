package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class MealsDaoListImpl implements MealsDao{

    List<Meal> meals = new ArrayList<>(Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    ));

    @Override
    public List<MealWithExceed> getMeals(int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
                );

        return meals.stream()
                .map(meal -> createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(toList());
    }

    @Override
    public void deleteMeal(int id) {
        for (int i = 0; i<=id; i++)
            if (meals.get(i).getId() == id)
            {
                meals.remove(i);
                break;
            }
    }

    @Override
    public Meal getMealById(int id) {
        Meal meal = null;
        for (int i = 0; i<=id; i++)
            if (meals.get(i).getId() == id)
            {
                meal = meals.get(i);
                break;
            }
        return meal;
    }

    @Override
    public void updateMeal(Meal meal) {
        int id = meal.getId();
        for (int i = 0; i<=id; i++)
            if (meals.get(i).getId() == id)
            {
                meals.remove(i);
                break;
            }
        meals.add(id, meal);
    }

    @Override
    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    public static MealWithExceed createWithExceed(Meal meal, boolean exceeded) {
        return new MealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), meal.getId(), exceeded);
    }
}
