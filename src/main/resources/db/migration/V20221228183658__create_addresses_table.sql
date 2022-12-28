CREATE TABLE addresses (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id uuid REFERENCES users(id),
    address text NOT NULL
);
