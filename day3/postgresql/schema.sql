CREATE TABLE customers (

    customer_id SMALLSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE products (
    product_id SMALLSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price NUMERIC(10, 2) NOT NULL
);

CREATE TABLE orders (
    order_id SMALLSERIAL PRIMARY KEY,
    customer_id SMALLINT NOT NULL REFERENCES customers(customer_id),
    order_date TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE order_items (
    order_item_id SMALLSERIAL PRIMARY KEY,
    order_id SMALLINT NOT NULL REFERENCES orders(order_id) ON DELETE CASCADE,
    product_id SMALLINT NOT NULL REFERENCES products(product_id),
    quantity INT NOT NULL CHECK (quantity > 0)
);