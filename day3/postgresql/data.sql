INSERT INTO customers (name, email) VALUES 
('Hi', 'hello@gmail.com'),
('Bye', 'ntmy@gmail.com');

INSERT INTO products (name, price) VALUES
('apple', '320'),
('orange', '100'),
('stawberry', '80');

INSERT INTO orders (customer_id) VALUES
(1),
(2);

INSERT INTO order_items (order_id, product_id, quantity) VALUES
(1, 1, 6),
(2, 2, 10),
(2, 3, 1);


