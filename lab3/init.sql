CREATE TABLE customers
(
    id        BIGSERIAL PRIMARY KEY NOT NULL,
    full_name VARCHAR(256)          NOT NULL,
    country   VARCHAR(64)           NOT NULL,
    state     VARCHAR(64)           NOT NULL,
    address   VARCHAR(64)           NOT NULL,
    telephone VARCHAR(16)           NOT NULL
);

INSERT INTO customers(full_name, country, state, address, telephone)
VALUES ('Maxim Sobaleu', 'Belarus', 'Minsk', 'Plekhanova', '29-389-76-11'),
	   ('Kiryl Bahushevich', 'Belarus', 'Minsk', 'Vostochnaya', '29-123-34-56'),
       ('Andrey Demyan', 'Belarus', 'Minsk', 'Aerodromnaya', '29-234-56-78');