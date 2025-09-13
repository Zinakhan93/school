-- Создание таблицы car (машина)
CREATE TABLE car (
                     id BIGSERIAL PRIMARY KEY,

                     brand VARCHAR(255) NOT NULL,
                     model VARCHAR(255) NOT NULL,
                     cost NUMERIC NOT NULL
);


-- Создание таблицы person (человек)
CREATE TABLE person (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        age INTEGER NOT NULL,
                        has_license BOOLEAN NOT NULL,
                        car_id BIGINT REFERENCES car(id)
);