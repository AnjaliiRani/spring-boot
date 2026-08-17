-- Seed users (idempotent)

INSERT INTO users (name, email ,password ,role)
SELECT 'Alice' AS name, 'alice@example.com' AS email
'' AS password, 'USER' AS role
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'alice@example.com'
);

INSERT INTO users (name, email,password ,role)
SELECT 'Bob' AS name, 'bob@example.com' AS email
'' AS password, 'USER' AS role
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'bob@example.com'
);

INSERT INTO users (name, email,password ,role)
SELECT 'Charlie' AS name, 'charlie@example.com' AS email
'' AS password, 'USER' AS role

WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'charlie@example.com'
);

INSERT INTO users (name, email,password ,role)
SELECT 'Admin' AS name, 'admin@example.com' AS email,
'' AS password, 'ADMIN' AS role

WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'charlie@example.com'
);


-- Seed orders for Alice (idempotent)

INSERT INTO orders (product_name, user_id)
SELECT 'Keyboard' AS product_name, u.id AS user_id
FROM users u
WHERE u.email = 'alice@example.com'
  AND NOT EXISTS (
      SELECT 1
      FROM orders o
      WHERE o.user_id = u.id
        AND o.product_name = 'Keyboard'
  );

INSERT INTO orders (product_name, user_id)
SELECT 'Mouse' AS product_name, u.id AS user_id
FROM users u
WHERE u.email = 'alice@example.com'
  AND NOT EXISTS (
      SELECT 1
      FROM orders o
      WHERE o.user_id = u.id
        AND o.product_name = 'Mouse'
  );


-- Seed orders for Bob (idempotent)

INSERT INTO orders (product_name, user_id)
SELECT 'Laptop' AS product_name, u.id AS user_id
FROM users u
WHERE u.email = 'bob@example.com'
  AND NOT EXISTS (
      SELECT 1
      FROM orders o
      WHERE o.user_id = u.id
        AND o.product_name = 'Laptop'
  );

INSERT INTO orders (product_name, user_id)
SELECT 'Monitor' AS product_name, u.id AS user_id
FROM users u
WHERE u.email = 'bob@example.com'
  AND NOT EXISTS (
      SELECT 1
      FROM orders o
      WHERE o.user_id = u.id
        AND o.product_name = 'Monitor'
  );


-- Seed orders for Charlie (idempotent)

INSERT INTO orders (product_name, user_id)
SELECT 'Headphones' AS product_name, u.id AS user_id
FROM users u
WHERE u.email = 'charlie@example.com'
  AND NOT EXISTS (
      SELECT 1
      FROM orders o
      WHERE o.user_id = u.id
        AND o.product_name = 'Headphones'
  );