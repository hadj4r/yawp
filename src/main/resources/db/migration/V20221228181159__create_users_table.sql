CREATE TABLE users
(
    id         uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    login      varchar(255) NOT NULL,
    password   varchar(255) NOT NULL,
    birthday   date         NOT NULL,
    about      text
);

