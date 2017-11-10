package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

public interface MealsDao {
    List<MealWithExceed> getMeals(int caloriesPerDay);
    void deleteMeal(int id);
    Meal getMealById (int id);
    void updateMeal(Meal meal);
    void addMeal(Meal meal);
}
