CREATE OR REPLACE FUNCTION update_user_log()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO users_history (user_id, name, surname, login, password, birthday, about)
    VALUES (OLD.id, OLD.name, OLD.surname, OLD.login, OLD.password, OLD.birthday, OLD.about);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_users_history
    AFTER UPDATE ON users
    FOR EACH ROW
    EXECUTE PROCEDURE update_user_log();