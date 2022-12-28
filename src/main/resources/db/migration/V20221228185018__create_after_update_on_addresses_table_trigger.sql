CREATE OR REPLACE FUNCTION update_address_log()
    RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO addresses_history (address_id, address)
    VALUES (OLD.id, OLD.address);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_addresses_history
    AFTER UPDATE ON addresses
    FOR EACH ROW
EXECUTE PROCEDURE update_address_log();
