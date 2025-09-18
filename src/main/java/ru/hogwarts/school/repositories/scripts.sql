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

 /*SELECT COUNT(s) FROM Student s*/


/*дз
 */
 /*CREATE TABLE employee ( id SERIAL PRIMARY KEY, name TEXT NOT NULL, department TEXT NOT NULL, salary NUMERIC(12,2));
iNSERT INTO employee (name, department, salary) VALUES ('Ivan Petrov', 'Sales', 50000.00), ('Maria Ivanova', 'Sales', 55000.00), ('Petr Sidorov', 'IT', 70000.00), ('Olga Smirnova', 'HR', 40000.00), ('Anna K', 'IT', 72000.00), ('Sergey L', 'Legal', NULL);
 SELECT department, MAX(salary) AS max_salary, AVG(salary) AS avg_salary FROM employee GROUP BY department HAVING COUNT(*) > 1;*/
DROP TABLE employee