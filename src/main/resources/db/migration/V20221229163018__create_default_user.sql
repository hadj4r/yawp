INSERT INTO public.users (id, first_name, last_name, login, "password", birthday, about)
VALUES ('f9512f28-ad2c-4b0a-a909-6897d9bb4b66', 'John', 'Doe', 'john',
        '$2a$10$DzkWAGq/XaomuMCp2ahPqudUUmQzl9FkUJArKNk6KRmI7nVJ9Ld86', '1968-11-23', NULL);

INSERT INTO public.addresses (id, user_id, address)
VALUES ('2fd3d2b8-1825-4ce4-a8a4-4382f5586834', 'f9512f28-ad2c-4b0a-a909-6897d9bb4b66', 'USA, NY, Wall Street 210A');
