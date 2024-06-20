CREATE TABLE public.executor (
	"date" int8 NULL,
	id uuid NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT executor_pkey PRIMARY KEY (id)
);