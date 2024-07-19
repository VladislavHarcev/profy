CREATE TABLE profy.revinfo (
	rev int4 NOT NULL,
	revtstmp int8 NULL,
	CONSTRAINT revinfo_pkey PRIMARY KEY (rev)
);

CREATE TABLE profy.bank_balance_history (
	id uuid NOT NULL,
	rev int4 NOT NULL,
	revtype int2 NULL,
	balance numeric(12, 2) NULL,
	customer_id uuid NULL,
	executor_id uuid NULL,
	CONSTRAINT bank_balance_history_pkey PRIMARY KEY (rev, id),
	CONSTRAINT balance_to_revinfo_key FOREIGN KEY (rev) REFERENCES profy.revinfo(rev)
);