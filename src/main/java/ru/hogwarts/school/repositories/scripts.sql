 -- 1. Получить всех студентов, возраст которых находится между 10 и 20
SELECT * FROM student WHERE age BETWEEN 10 AND 20;
-- 2. Получить всех студентов, но отобразить только список их имен
SELECT name FROM student;
 -- 3. Получить всех студентов, у которых в имени присутствует буква О (или любая другая).
 SELECT * FROM student WHERE name  ILIKE  '%Г%';
--4. Получить всех студентов, у которых возраст меньше идентификатора
 SELECT * FROM student WHERE age < id;
--5. Получить всех студентов упорядоченных по возрасту (по возрастанию)
 SELECT * FROM student ORDER BY age;
select s. *from faculty as f, student as s
where s.faculty_id = f.id;

/* DELETE FROM faculty WHERE id in (52,2,3,4,5,6,7,8,9,102) удаление строки */

 SELECT COUNT(s) FROM Student s
