-- Active: 1695563421178@@127.0.0.1@3306

DROP DATABASE IF EXISTS clothingstore;

SET GLOBAL max_allowed_packet = 16777216;

CREATE DATABASE
    clothingstore CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE clothingstore;

CREATE TABLE
    users (
        id INT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(255) NOT NULL,
        password VARCHAR(255) NOT NULL,
        email VARCHAR(255) NOT NULL,
        name VARCHAR(255) NOT NULL,
        phone VARCHAR(255) NOT NULL,
        gender INT,
        image VARCHAR(255) NOT NULL,
        role_id INT,
        address LONGTEXT,
        status ENUM('ACTIVE', 'INACTIVE', 'BANNED') NOT NULL DEFAULT 'ACTIVE'
    );

CREATE TABLE
    roles (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name ENUM('ADMIN', 'MANAGER', 'EMPLOYEE') NOT NULL DEFAULT 'EMPLOYEE'
    );

CREATE TABLE
    permissions (
        id INT AUTO_INCREMENT PRIMARY KEY,
        permission_name VARCHAR(255)
    );

CREATE TABLE role_permissions (role_id INT, permission_id INT);

CREATE TABLE
    customers (
        id INT AUTO_INCREMENT PRIMARY KEY,
        customer_name VARCHAR(255) NOT NULL,
        phone VARCHAR(255) NOT NULL,
        email VARCHAR(255) DEFAULT NULL
    );

CREATE TABLE
    purchases (
        id INT AUTO_INCREMENT PRIMARY KEY,
        customer_id INT,
        purchase_date TIMESTAMP,
        total_amount DOUBLE
    );

CREATE TABLE
    points (
        id INT AUTO_INCREMENT PRIMARY KEY,
        customer_id INT,
        points_earned INT NOT NULL DEFAULT 0,
        points_used INT NOT NULL DEFAULT 0
    );

CREATE TABLE
    point_transactions (
        id INT AUTO_INCREMENT PRIMARY KEY,
        customer_id INT,
        transaction_date TIMESTAMP,
        points_earned INT NOT NULL DEFAULT 0,
        points_used INT NOT NULL DEFAULT 0
    );

CREATE TABLE
    conversion_rate (
        id INT AUTO_INCREMENT PRIMARY KEY,
        points INT NOT NULL,
        price INT NOT NULL
    );

CREATE TABLE
    orders (
        id INT AUTO_INCREMENT PRIMARY KEY,
        customer_id INT,
        user_id INT,
        order_date TIMESTAMP NOT NULL,
        total_amount INT NOT NULL
    );

CREATE TABLE
    order_items (
        id INT AUTO_INCREMENT PRIMARY KEY,
        order_id INT,
        product_id INT,
        quantity INT,
        price INT
    );

CREATE TABLE
    payment_methods (
        id INT AUTO_INCREMENT PRIMARY KEY,
        method_name VARCHAR(255)
    );

CREATE TABLE
    payments (
        id INT AUTO_INCREMENT PRIMARY KEY,
        order_id INT,
        method_id INT,
        payment_date TIMESTAMP,
        total_price INT
    );

CREATE TABLE
    products (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        category VARCHAR(255) NOT NULL,
        image VARCHAR(255) NOT NULL,
        gender INT NOT NULL,
        rating FLOAT DEFAULT NULL
    );

CREATE TABLE
    imports (
        id INT AUTO_INCREMENT PRIMARY KEY,
        import_date TIMESTAMP,
        total_cost INT
    );

CREATE TABLE
    import_items (
        id INT AUTO_INCREMENT PRIMARY KEY,
        import_id INT,
        product_id INT,
        quantity INT,
        price INT
    );

CREATE TABLE
    sizes (
        id INT AUTO_INCREMENT PRIMARY KEY,
        size VARCHAR(255) NOT NULL
    );

CREATE TABLE
    size_items (
        id INT AUTO_INCREMENT PRIMARY KEY,
        product_id INT,
        size_id INT,
        quantity INT NOT NULL DEFAULT 0,
    );

ALTER TABLE users
ADD
    CONSTRAINT fk_users_roles FOREIGN KEY (role_id) REFERENCES roles(id);

ALTER TABLE role_permissions
ADD
    CONSTRAINT fk_role_permissions_roles FOREIGN KEY (role_id) REFERENCES roles(id);

ALTER TABLE role_permissions
ADD
    CONSTRAINT fk_role_permissions_permissions FOREIGN KEY (permission_id) REFERENCES permissions(id);

ALTER TABLE purchases
ADD
    CONSTRAINT fk_purchases_customers FOREIGN KEY (customer_id) REFERENCES customers(id);

ALTER TABLE points
ADD
    CONSTRAINT fk_points_customers FOREIGN KEY (customer_id) REFERENCES customers(id);

ALTER TABLE point_transactions
ADD
    CONSTRAINT fk_point_transactions_customers FOREIGN KEY (customer_id) REFERENCES customers(id);

ALTER TABLE orders
ADD
    CONSTRAINT fk_orders_customers FOREIGN KEY (customer_id) REFERENCES customers(id);

ALTER TABLE orders
ADD
    CONSTRAINT fk_orders_users FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE order_items
ADD
    CONSTRAINT fk_order_items_orders FOREIGN KEY (order_id) REFERENCES orders(id);

ALTER TABLE payments
ADD
    CONSTRAINT fk_payments_orders FOREIGN KEY (order_id) REFERENCES orders(id);

ALTER TABLE payments
ADD
    CONSTRAINT fk_payments_payment_methods FOREIGN KEY (method_id) REFERENCES payment_methods(id);

ALTER TABLE order_items
ADD
    CONSTRAINT fk_order_items_products FOREIGN KEY (product_id) REFERENCES products(id);

ALTER TABLE import_items
ADD
    CONSTRAINT fk_import_items_imports FOREIGN KEY (import_id) REFERENCES imports(id);

ALTER TABLE import_items
ADD
    CONSTRAINT fk_import_items_products FOREIGN KEY (product_id) REFERENCES products(id);

ALTER TABLE size_items
ADD
    CONSTRAINT fk_size_items_products FOREIGN KEY (product_id) REFERENCES products(id);

ALTER TABLE size_items
ADD
    CONSTRAINT fk_size_items_sizes FOREIGN KEY (size_id) REFERENCES sizes(id);