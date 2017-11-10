package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealsDao;
import ru.javawebinar.topjava.dao.MealsDaoListImpl;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsServlet extends HttpServlet {
    private static final Logger log = getLogger(MealsServlet.class);

    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_MEALS = "/meals.jsp";

    MealsDao dao = new MealsDaoListImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        request.setAttribute("localDateTimeFormat", new SimpleDateFormat("yyyy-MM-dd'T'hh:mm"));

        if (action.equalsIgnoreCase("delete")){
            log.debug("forward to delete");
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            dao.deleteMeal(mealId);
            response.sendRedirect("meals?action=mealsList");
        } else if (action.equalsIgnoreCase("edit")){
            log.debug("forward to edit");
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = dao.getMealById(mealId);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
        } else if (action.equalsIgnoreCase("mealsList")){
            log.debug("forward to meals");
            request.setAttribute("mealsList", dao.getMeals(2000));
            request.getRequestDispatcher(LIST_MEALS).forward(request, response);
        } else {
            log.debug("forward to add");
            request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
        }

//        response.sendRedirect("meals.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LocalDateTime dateTime = null;
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(request.getParameter("dateTime"));
            dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        Meal meal = (Meal) request.getAttribute("meal");

        if(meal == null)
        {
            log.debug("add action");
            meal = new Meal(dateTime, description, calories);
            dao.addMeal(meal);
        }
        else
        {
            log.debug("edit action");
            meal.setDateTime(dateTime);
            meal.setDescription(description);
            meal.setCalories(calories);
            dao.updateMeal(meal);
        }

        request.setAttribute("mealsList", dao.getMeals(2000));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
