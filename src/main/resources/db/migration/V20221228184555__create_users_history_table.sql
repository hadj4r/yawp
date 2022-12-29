CREATE TABLE users_history
(
    id         serial PRIMARY KEY,
    user_id    uuid REFERENCES users (id),
    first_name varchar(255),
    last_name  varchar(255),
    login      varchar(255),
    password   varchar(255),
    birthday   date,
    about      text,
    updated_at timestamptz DEFAULT NOW()
);
