
CREATE TABLE food_item(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    category VARCHAR(100) NOT NULL,
    amount INT NOT NULL,
    expiration DATE NOT NULL
    );