CREATE TABLE profy.calendar_data (
	id uuid DEFAULT gen_random_uuid() NOT NULL,
	calendar_year int NOT NULL,
	months jsonb NULL,
	transitions jsonb NULL,
	statistic jsonb NULL,
    create_date bigint NULL,
	CONSTRAINT newtable_pk PRIMARY KEY (id)
);
COMMENT ON COLUMN profy.calendar_data.id IS 'Идентификатор календарного года';
COMMENT ON COLUMN profy.calendar_data.calendar_year IS 'Год';
COMMENT ON COLUMN profy.calendar_data.months IS 'Месяц';
COMMENT ON COLUMN profy.calendar_data.transitions IS 'Переход';
COMMENT ON COLUMN profy.calendar_data.statistic IS 'Статистика';