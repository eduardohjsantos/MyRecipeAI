
CREATE TABLE food_items(
    id BIGINT AUTO_INCREMENT PRIMARYKEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    category VARCHAR(255) NOT NULL,
    amount INT NOT NULL,
    expiration DATE
    );