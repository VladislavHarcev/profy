ALTER TABLE profy.orders
ADD COLUMN price DECIMAL(12,2) not null CHECK (price >= 0) DEFAULT 0;
COMMENT ON COLUMN profy.orders.price IS 'Цена заказа';

CREATE TABLE profy.bank_balance(
	id uuid DEFAULT gen_random_uuid() NOT NULL,
	executor_id uuid,
	customer_id uuid,
	balance decimal(12,2) not null CHECK (balance >= 0) DEFAULT 0,
	create_date bigint NULL,
	CONSTRAINT bank_balance_pk PRIMARY KEY (id),
	CONSTRAINT bank_balance_executor_fk FOREIGN KEY (executor_id) REFERENCES profy.executor(id),
	CONSTRAINT bank_balance_customer_fk FOREIGN KEY (customer_id) REFERENCES profy.customer(id)
);

COMMENT ON COLUMN profy.bank_balance.id IS 'Идентификатор баланса';
COMMENT ON COLUMN profy.bank_balance.executor_id IS 'Идетификатор исполнителя';
COMMENT ON COLUMN profy.bank_balance.customer_id IS 'Идентификатор заказчика';
COMMENT ON COLUMN profy.bank_balance.balance IS 'Баланс';
COMMENT ON COLUMN profy.bank_balance.create_date IS 'дата создания заказа';

CREATE UNIQUE INDEX unique_customer_id ON profy.bank_balance(customer_id);
CREATE UNIQUE INDEX unique_executor_id ON profy.bank_balance(executor_id);

INSERT INTO profy.bank_balance (customer_id)
SELECT c.id
FROM profy.customer c
ON CONFLICT (customer_id) DO NOTHING;

INSERT INTO profy.bank_balance (executor_id)
SELECT c.id
FROM profy.executor c
ON CONFLICT (executor_id) DO NOTHING;