package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

public interface MealsDao {
    public List<MealWithExceed> getMeals(int caloriesPerDay);
}
