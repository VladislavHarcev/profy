
CREATE TABLE profy.status (
	id uuid DEFAULT gen_random_uuid() NOT NULL,
	title text NOT NULL,
	CONSTRAINT state_unique_1 UNIQUE (title),
	CONSTRAINT status_pk PRIMARY KEY (id)
);

COMMENT on TABLE profy.status IS 'Таблица статусов';
COMMENT ON COLUMN profy.status.id IS 'Ключевое поле статусов';
COMMENT ON COLUMN profy.status.title IS 'Наименование статуса';


CREATE TABLE profy.customer (
	id uuid NOT NULL,
	title text NOT NULL,
	create_date int8 NOT NULL,
	CONSTRAINT customer_pkey PRIMARY KEY (id)
);

COMMENT on TABLE profy.customer IS 'Таблица заказчиков';
COMMENT ON COLUMN profy.customer.id IS 'Ключевое поле таблицы заказчиков';
COMMENT ON COLUMN profy.customer.title IS 'Наименование заказчика';
COMMENT ON COLUMN profy.customer.create_date IS 'Дата создания заказчика';



CREATE TABLE profy.executor (
	create_date int8 NULL,
	id uuid NOT NULL,
	title text NOT NULL,
	CONSTRAINT executor_pkey PRIMARY KEY (id)
);
COMMENT on TABLE profy.executor IS 'Таблица исполнителей';
COMMENT ON COLUMN profy.executor.id IS 'Ключевое поле таблицы исполнителей';
COMMENT ON COLUMN profy.executor.title IS 'Наименование исполнителя';
COMMENT ON COLUMN profy.executor.create_date IS 'Дата создания исполнителя';



CREATE TABLE profy.orders (
	id uuid DEFAULT gen_random_uuid() NOT NULL,
	title text NOT NULL,
    descript text NOT NULL,
	executor_id uuid NULL,
	customer_id uuid NULL,
	create_date int8 NOT NULL,
	state_id uuid NOT NULL,
	CONSTRAINT order_pk PRIMARY KEY (id),
	CONSTRAINT order_status_fk FOREIGN KEY (state_id) REFERENCES profy.status(id)
);

COMMENT on TABLE profy.orders IS 'Таблица заказов';
COMMENT ON COLUMN profy.orders.id IS 'Ключевое поле таблицы заказов';
COMMENT ON COLUMN profy.orders.title IS 'Наименование заказа';
COMMENT ON COLUMN profy.orders.descript IS 'Описание заказа';
COMMENT ON COLUMN profy.orders.executor_id IS 'Id исполнителя заказа';
COMMENT ON COLUMN profy.orders.customer_id IS 'Id заказчика';
COMMENT ON COLUMN profy.orders.create_date IS 'Дата создания заказа';
COMMENT ON COLUMN profy.orders.state_id IS 'Id статуса заказа';


CREATE INDEX order_id_idx ON profy.orders USING btree (id, create_date, title, descript, state_id, executor_id, customer_id);