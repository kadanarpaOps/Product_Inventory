CREATE TABLE products
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(15, 2) NOT NULL,
    operation VARCHAR(255) NOT NULL,
    date_event TIMESTAMP
);