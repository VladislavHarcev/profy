ALTER TABLE profy.orders RENAME COLUMN state_id TO state;
ALTER TABLE profy.orders ALTER COLUMN state TYPE text USING state::text;
UPDATE profy.orders
SET state = 'CREATED'
WHERE state IS NOT NULL;