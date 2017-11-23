DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO meals (date_time, description, calories, user_id) VALUES
  ('2017-07-01 08:30', 'Админ ланч', 650, 100001),
  ('2017-07-01 12:30', 'Админ обед', 800, 100001),
  ('2017-07-01 18:30', 'Админ ужин', 600, 100001),
  ('2017-07-01 07:00', 'завтрак', 600, 100000),
  ('2017-07-01 12:00', 'обед', 800, 100000);

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);