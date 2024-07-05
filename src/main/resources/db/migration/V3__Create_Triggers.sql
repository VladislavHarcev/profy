CREATE OR REPLACE FUNCTION set_current_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.create_date := EXTRACT(EPOCH FROM now())::BIGINT;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_set_timestamp_customer
BEFORE INSERT ON profy.customer
FOR EACH ROW
EXECUTE FUNCTION set_current_timestamp();

CREATE TRIGGER trigger_set_timestamp_orders
BEFORE INSERT ON profy.orders
FOR EACH ROW
EXECUTE FUNCTION set_current_timestamp();

CREATE TRIGGER trigger_set_timestamp_executor
BEFORE INSERT ON profy.executor
FOR EACH ROW
EXECUTE FUNCTION set_current_timestamp();

CREATE TRIGGER trigger_set_timestamp_status
BEFORE INSERT ON profy.status
FOR EACH ROW
EXECUTE FUNCTION set_current_timestamp();