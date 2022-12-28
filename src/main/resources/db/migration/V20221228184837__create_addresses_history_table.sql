CREATE TABLE addresses_history (
    id serial PRIMARY KEY,
    address_id uuid REFERENCES addresses(id),
    address text NOT NULL,
    updated_at timestamptz DEFAULT NOW()
);