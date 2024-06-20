CREATE SCHEMA IF NOT EXISTS migrations;
CREATE TABLE public."order" (
	executor_id uuid NULL,
	id uuid DEFAULT gen_random_uuid() NOT NULL,
	"date" int8 NOT NULL,
	customer_id uuid NULL,
	state_id uuid NOT NULL,
	title varchar NOT NULL,
	descript varchar NOT NULL,
	CONSTRAINT order_pk PRIMARY KEY (id),
	CONSTRAINT order_status_fk FOREIGN KEY (state_id) REFERENCES public.status(id)
);
CREATE INDEX order_id_idx ON public."order" USING btree (id, date, title, descript, state_id, executor_id, customer_id);