CREATE TABLE tb_product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price DECIMAL(19,2) NOT NULL,
    active BOOLEAN NOT NULL,
    restaurant_id BIGINT NOT NULL,
    
    CONSTRAINT fk_tb_product_restaurant FOREIGN KEY (restaurant_id) REFERENCES tb_restaurant(id)
);

CREATE TABLE tb_order_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity INT NOT NULL,
    unit_price DECIMAL(19,2) NOT NULL,
    total_price DECIMAL(19,2) NOT NULL,
    observation VARCHAR(255),
    
    product_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    
    CONSTRAINT fk_tb_order_item_product FOREIGN KEY (product_id) REFERENCES tb_product(id),
    CONSTRAINT fk_tb_order_item_order FOREIGN KEY (order_id) REFERENCES tb_order(id)
);