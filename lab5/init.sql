CREATE TABLE customers
(
    id        BIGSERIAL PRIMARY KEY NOT NULL,
    full_name VARCHAR(256)          NOT NULL,
    country   VARCHAR(64)           NOT NULL,
    state     VARCHAR(64)           NOT NULL,
    city      VARCHAR(64)           NOT NULL,
    zip       VARCHAR(16)           NOT NULL
);

INSERT INTO customers(full_name, country, state, city, zip)
VALUES ('Andrei Dziamyanchyk', 'Belarus', 'Minsk', 'Minsk', '220007'),
       ('Maxim Sobaleu', 'Belarus', 'Minsk', 'Minsk', '220095');