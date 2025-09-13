-- Получение информации о всех студентах (имя и возраст) вместе с названиями факультетов
SELECT s.name, s.age, f.name AS faculty_name
FROM student s
         LEFT JOIN faculty f ON s.faculty_id = f.id;

-- Получение только тех студентов, у которых есть аватарки
SELECT s.name
FROM student s
         INNER JOIN avatar a ON s.id = a.student_id;