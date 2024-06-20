CREATE TABLE test.status (
	id uuid DEFAULT gen_random_uuid() NOT NULL,
	"name" varchar NOT NULL,
	CONSTRAINT state_unique_1 UNIQUE (name),
	CONSTRAINT status_pk PRIMARY KEY (id)
);