-- liquibase formatted sql

-- changeset ZinaKhan:1
--Индекс для поиска по имени студента.
CREATE INDEX student_name_index ON student (name);

-- changeset ZinaKhan:2
--Индекс для поиска по названию и цвету факультета
CREATE INDEX faculty_nc_index ON faculty (name, color);