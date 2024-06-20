CREATE UNIQUE INDEX executor_pkey ON public.executor USING btree (id);
CREATE UNIQUE INDEX state_unique_1 ON public.status USING btree (name);
CREATE UNIQUE INDEX status_pk ON public.status USING btree (id);
CREATE INDEX order_id_idx ON public."order" USING btree (id, date, title, descript, state_id, executor_id, customer_id);
CREATE UNIQUE INDEX order_pk ON public."order" USING btree (id);
CREATE UNIQUE INDEX customer_pkey ON public.customer USING btree (id);