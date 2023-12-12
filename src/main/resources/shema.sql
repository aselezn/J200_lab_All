CREATE DATABASE clients_db;

USE clients_db;

CREATE TABLE clients (
                         clientid INT NOT NULL AUTO_INCREMENT,
                         client_name VARCHAR(100) NOT NULL,
                         type VARCHAR(20) NOT NULL,
                         added DATE NOT NULL DEFAULT (CURRENT_DATE),
                         PRIMARY KEY (clientid)
);

CREATE TABLE addresses (
                           addressid INT NOT NULL AUTO_INCREMENT,
                           ip VARCHAR(25) NOT NULL,
                           mac VARCHAR(20) NOT NULL,
                           model VARCHAR(100) NOT NULL,
                           address VARCHAR(200) NOT NULL,
                           clientid INT NOT NULL,
                           FOREIGN KEY (clientid) REFERENCES clients (clientid),
                           PRIMARY KEY (addressid)
);


INSERT INTO clients (client_name, type)
VALUES
    ('Иванов Иван Иванович', 'Физическое лицо'),
    ('Петров Петр Петрович', 'Физическое лицо'),
    ('Сидоров Сидор Сидорович', 'Физическое лицо'),
    ('ООО Альфа', 'Юридическое лицо'),
    ('ООО Бета', 'Юридическое лицо'),
    ('ООО Гамма', 'Юридическое лицо'),
    ('ООО Омега', 'Юридическое лицо'),
    ('ИП Васильев Анатолий Викторович', 'Юридическое лицо'),
    ('Ерохин Ероха Ерохович', 'Физическое лицо'),
    ('Маскович Илон Петрович', 'Физическое лицо'),
    ('Винзор Елизавета Васильевна', 'Физическое лицо'),
    ('ИП Геральт из Ривии', 'Юридическое лицо'),
    ('Барби Барбовна Барбовна', 'Юридическое лицо'),
    ('Айм Джаст Кен', 'Физическое лицо');


# Create some addresses for each client
INSERT INTO addresses (ip, mac, model, address, clientid)
VALUES ('192.168.1.1', 'AA:BB:CC:DD:EE:1F', 'Dell XPS 13', 'г. Москва, ул. Ленина, д. 1', 1),
       ('192.168.1.2', 'FF:EE:DD:CC:2B:AA', 'Apple MacBook Pro', 'г. Санкт-Петербург, пр. Невский, д. 100', 1),
       ('192.168.1.3', 'CC:BB:AA:3D:EE:FF', 'Asus ZenBook', 'г. Воронеж, ул. Комсомольская, д. 5', 1),
       ('192.168.1.4', 'BB:AA:4D:CC:EE:FF', 'HP Spectre x360', 'г. Краснодар, ул. Красная, д. 100', 2),
       ('192.168.1.5', 'EE:5D:CC:BB:AA:FF', 'Lenovo ThinkPad', 'г. Ростов-на-Дону, ул. Большая Садовая, д. 1', 2),
       ('192.168.1.6', '6D:CC:BB:AA:EE:FF', 'Microsoft Surface', 'г. Самара, ул. Чапаевская, д. 100', 3),
       ('192.168.1.7', 'CC:B7:AA:DD:EE:FF', 'Acer Nitro 5', 'г. Уфа, ул. Ленина, д. 1', 4),
       ('192.168.1.8', 'BB:AA:D8:CC:EE:FF', 'Samsung Galaxy Book', 'г. Казань, ул. Баумана, д. 1', 5),
       ('192.168.1.9', 'AA:BB:CC:D9:EE:FF', 'Dell XPS 13', 'г. Москва, ул. Ленина, д. 2', 6),
       ('192.168.1.10', 'FF:EE:DD:CC:B1:AA', 'Apple MacBook Pro', 'г. Санкт-Петербург, пр. Невский, д. 101', 6),
       ('192.168.1.11', 'CC:BB:AA:DD:EE:2F', 'Asus ZenBook', 'г. Воронеж, ул. Комсомольская, д. 6', 6),
       ('192.168.1.12', 'BB:AA:DD:CC:3E:FF', 'HP Spectre x360', 'г. Краснодар, ул. Красная, д. 102', 7),
       ('192.168.1.13', 'EE:DD:4C:BB:AA:FF', 'Lenovo ThinkPad', 'г. Ростов-на-Дону, ул. Большая Садовая, д. 2', 7),
       ('192.168.1.14', 'DD:5C:BB:AA:EE:FF', 'Microsoft Surface', 'г. Самара, ул. Чапаевская, д. 101', 8),
       ('192.168.1.15', 'C6:BB:AA:DD:EE:FF', 'Acer Nitro 5', 'г. Уфа, ул. Ленина, д. 2', 9),
       ('192.168.1.16', 'BB:A7:DD:CC:EE:FF', 'Samsung Galaxy Book', 'г. Казань, ул. Баумана, д. 2', 10),
       ('192.168.1.17', 'EE:DD:7C:BB:AA:JJ', 'Lenovo ThinkPad', 'г. Ростов-на-Дону, ул. Большая Садовая, д. 8', 11),
       ('192.168.1.18', 'DD:CC:B7:AA:EE:FF', 'Microsoft Surface', 'г. Уфа, ул. Чапаевская, д. 10', 12),
       ('192.168.1.19', 'CC:BB:AA:D8:EE:FF', 'Acer Nitro 5', 'г. Уфа, ул. Ленина, д. 45', 13),
       ('192.168.1.20', 'BB:AA:DD:6C:EE:FF', 'Samsung Galaxy Book', 'г. Самара, ул. Баумана, д. 67', 14);
