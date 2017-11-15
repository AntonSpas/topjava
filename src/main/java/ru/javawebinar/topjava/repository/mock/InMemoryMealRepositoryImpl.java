package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {}, with userId={}", id, userId);
        if (repository.containsKey(id)) {
            if (repository.get(id).getUserId() == userId) {
                repository.remove(id);
                return true;
            } else return false;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {}, with userId={}", id, userId);
        if (repository.containsKey(id))
            return userId == repository.get(id).getUserId() ? repository.get(id) : null;
        else return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        log.info("getAll {}", userId);

        return repository.values()
                .stream()
                .filter(m -> m.getUserId() == userId)
                .sorted(Comparator.nullsLast(
                        (m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime())))
                .collect(toList());
    }
}

