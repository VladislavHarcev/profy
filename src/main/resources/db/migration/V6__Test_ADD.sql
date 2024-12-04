INSERT INTO profy.customer (title)
VALUES ('Дима');
INSERT INTO profy.executor (title)
VALUES ('Денис');
INSERT INTO profy.status (title)
VALUES ('готов');
INSERT INTO profy.status (title)
VALUES ('ожидает');
INSERT INTO profy.status (title)
VALUES ('в процессе');
INSERT INTO profy.orders (title, descript, executor_id, customer_id, state_id)
VALUES (
  'Название заказа',
  'Описание заказа',
  (SELECT id FROM executor WHERE title = 'Денис'),
  (SELECT id FROM customer WHERE title = 'Дима'),
  (SELECT id FROM status WHERE title = 'готов')
);
