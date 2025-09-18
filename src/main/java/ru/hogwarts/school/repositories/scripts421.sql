--- Возраст студента не может быть меньше 16 лет.
ALTER TABLE student ADD CONSTRAINT chk_student_age CHECK (age >= 16);
---Имена студентов должны быть уникальными и не равны нулю.
ALTER TABLE student ALTER COLUMN name SET NOT NULL;
ALTER TABLE student ADD CONSTRAINT uk_student_name UNIQUE (name);
--- Пара “значение названия” - “цвет факультета” должна быть уникальной.
ALTER TABLE faculty ADD CONSTRAINT uk_faculty_name_color UNIQUE (name, color);
---При создании студента без возраста ему автоматически должно присваиваться 20 лет.
ALTER TABLE student ALTER COLUMN age SET DEFAULT 20