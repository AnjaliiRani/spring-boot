-- Seed users (idempotent)

INSERT INTO users (name, email ,password ,role)
SELECT 'Alice' AS name, 'alice@example.com' AS email,
'$2a$10$DKttTHsOiJXYOpD0ulREqOke67Nh.XGmYmDT9eqQ46iNcB1V6Drm.' AS password, 'USER' AS role
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'alice@example.com'
);

INSERT INTO users (name, email,password ,role)
SELECT 'Bob' AS name, 'bob@example.com' AS email,
'$2a$10$DKttTHsOiJXYOpD0ulREqOke67Nh.XGmYmDT9eqQ46iNcB1V6Drm.' AS password, 'USER' AS role
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'bob@example.com'
);

INSERT INTO users (name, email,password ,role)
SELECT 'Charlie' AS name, 'charlie@example.com' AS email,
'$2a$10$DKttTHsOiJXYOpD0ulREqOke67Nh.XGmYmDT9eqQ46iNcB1V6Drm.' AS password, 'USER' AS role

WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'charlie@example.com'
);
--ADMIN USER

INSERT INTO users (name, email,password ,role)
SELECT 'Admin' AS name, 'admin@example.com' AS email,
'$2a$10$O0bjOCPiMIBrJpPT0mRloux3P3YGAhYarsysvRxdUXO9Q36Ass3wW' AS password, 'ADMIN' AS role

WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'admin@example.com'
);
-- PRODUCTS (catalog)

INSERT INTO products (name, price, active)
SELECT 'Keyboard', 75.00, true
WHERE NOT EXISTS (SELECT 1 FROM products WHERE name = 'Keyboard');

INSERT INTO products (name, price, active)
SELECT 'Mouse', 25.00, true
WHERE NOT EXISTS (SELECT 1 FROM products WHERE name = 'Mouse');

INSERT INTO products (name, price, active)
SELECT 'Laptop', 999.00, true
WHERE NOT EXISTS (SELECT 1 FROM products WHERE name = 'Laptop');

INSERT INTO products (name, price, active)
SELECT 'Monitor', 299.00, true
WHERE NOT EXISTS (SELECT 1 FROM products WHERE name = 'Monitor');

INSERT INTO products (name, price, active)
SELECT 'Headphones', 149.00, true
WHERE NOT EXISTS (SELECT 1 FROM products WHERE name = 'Headphones');


-- ORDERS (product_id + price_at_purchase snapshot)

-- Alice: Keyboard — snapshot price differs from current catalog (75.00) for demo
INSERT INTO orders (user_id, product_id, price_at_purchase)
SELECT u.id, p.id, 65.00
FROM users u
JOIN products p ON p.name = 'Keyboard'
WHERE u.email = 'alice@example.com'
AND NOT EXISTS (
    SELECT 1 FROM orders o
    WHERE o.user_id = u.id AND o.product_id = p.id
);

INSERT INTO orders (user_id, product_id, price_at_purchase)
SELECT u.id, p.id, p.price
FROM users u
JOIN products p ON p.name = 'Mouse'
WHERE u.email = 'alice@example.com'
AND NOT EXISTS (
    SELECT 1 FROM orders o
    WHERE o.user_id = u.id AND o.product_id = p.id
);

-- Bob orders
INSERT INTO orders (user_id, product_id, price_at_purchase)
SELECT u.id, p.id, p.price
FROM users u
JOIN products p ON p.name = 'Laptop'
WHERE u.email = 'bob@example.com'
AND NOT EXISTS (
    SELECT 1 FROM orders o
    WHERE o.user_id = u.id AND o.product_id = p.id
);

INSERT INTO orders (user_id, product_id, price_at_purchase)
SELECT u.id, p.id, p.price
FROM users u
JOIN products p ON p.name = 'Monitor'
WHERE u.email = 'bob@example.com'
AND NOT EXISTS (
    SELECT 1 FROM orders o
    WHERE o.user_id = u.id AND o.product_id = p.id
);

-- Charlie orders
INSERT INTO orders (user_id, product_id, price_at_purchase)
SELECT u.id, p.id, p.price
FROM users u
JOIN products p ON p.name = 'Headphones'
WHERE u.email = 'charlie@example.com'
AND NOT EXISTS (
    SELECT 1 FROM orders o
    WHERE o.user_id = u.id AND o.product_id = p.id
);


