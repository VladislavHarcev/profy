ALTER TABLE profy.executor ALTER COLUMN id SET DEFAULT gen_random_uuid();
ALTER TABLE profy.customer ALTER COLUMN id SET DEFAULT gen_random_uuid();

ALTER TABLE profy.orders ADD CONSTRAINT order_customer_fk FOREIGN KEY (customer_id) REFERENCES profy.customer(id);
ALTER TABLE profy.orders ADD CONSTRAINT order_executor_fk FOREIGN KEY (executor_id) REFERENCES profy.executor(id);
