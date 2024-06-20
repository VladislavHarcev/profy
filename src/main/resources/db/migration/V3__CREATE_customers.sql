CREATE TABLE test.customer (
	"date" int8 NULL,
	id uuid NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT customer_pkey PRIMARY KEY (id)
);