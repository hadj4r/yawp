CREATE OR REPLACE FUNCTION update_user_log()
    RETURNS TRIGGER AS
$$
BEGIN
    INSERT INTO users_history (user_id, first_name, last_name, login, password, birthday, about)
    VALUES (OLD.id, OLD.first_name, OLD.last_name, OLD.login, OLD.password, OLD.birthday, OLD.about);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_users_history
    AFTER UPDATE
    ON users
    FOR EACH ROW
EXECUTE PROCEDURE update_user_log();