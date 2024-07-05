ALTER TABLE profy.status ADD create_date bigint NULL;
COMMENT ON COLUMN profy.status.create_date IS 'Дата создания статуса';