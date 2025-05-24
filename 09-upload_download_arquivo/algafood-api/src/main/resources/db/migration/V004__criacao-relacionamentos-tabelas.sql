CREATE TABLE tb_payment_method (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE tb_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    registration_date DATETIME NOT NULL
);

CREATE TABLE tb_permission (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE tb_group (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE tb_group_permission (
    group_id BIGINT NOT NULL,
    permision_id BIGINT NOT NULL,
    PRIMARY KEY (group_id, permision_id),
    CONSTRAINT fk_group FOREIGN KEY (group_id) REFERENCES tb_group(id),
    CONSTRAINT fk_permission FOREIGN KEY (permision_id) REFERENCES tb_permission(id)
);

CREATE TABLE tb_user_group (
    user_id BIGINT NOT NULL,
    group_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, group_id),
    CONSTRAINT fk_tb_user_group_user FOREIGN KEY (user_id) REFERENCES tb_user(id),
    CONSTRAINT fk_tb_user_group_group FOREIGN KEY (group_id) REFERENCES tb_group(id)
);

CREATE TABLE tb_restaurant (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    shipping_fee DECIMAL(19,2) NOT NULL,
    kitchen_id BIGINT NOT NULL,
    registration_date DATETIME NOT NULL,
    update_date DATETIME NOT NULL,
    CONSTRAINT fk_restaurant_kitchen FOREIGN KEY (kitchen_id) REFERENCES tb_kitchen(id)
);

CREATE TABLE tb_restaurant_payment_method (
    restaurant_id BIGINT NOT NULL,
    payment_method_id BIGINT NOT NULL,
    PRIMARY KEY (restaurant_id, payment_method_id),
    CONSTRAINT fk_restaurant FOREIGN KEY (restaurant_id) REFERENCES tb_restaurant(id),
    CONSTRAINT fk_payment_method FOREIGN KEY (payment_method_id) REFERENCES tb_payment_method(id)
);

CREATE TABLE tb_order (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    subtotal DECIMAL(19,2) NOT NULL,
    shipping_fee DECIMAL(19,2) NOT NULL,
    total_value DECIMAL(19,2) NOT NULL,
    
    date_created DATETIME NOT NULL,
    confirmation_date DATETIME,
    cancelletion_date DATETIME,
    delivery_date DATETIME,
    
    payment_method_id BIGINT NOT NULL,
    restaurant_id BIGINT NOT NULL,
    client_id BIGINT NOT NULL,

    delivery_address_street VARCHAR(255) NOT NULL,
    delivery_address_number VARCHAR(20) NOT NULL,
    delivery_address_complement VARCHAR(255) NOT NULL,
    delivery_address_neighborhood VARCHAR(255),
    delivery_address_cep VARCHAR(30) NOT NULL,
    delivery_address_city_id BIGINT NOT NULL,

    status VARCHAR(50) NOT NULL,
    
    CONSTRAINT fk_tb_order_payment_method FOREIGN KEY (payment_method_id) REFERENCES tb_payment_method(id),
    CONSTRAINT fk_tb_order_restaurant FOREIGN KEY (restaurant_id) REFERENCES tb_restaurant(id),
    CONSTRAINT fk_tb_order_client FOREIGN KEY (client_id) REFERENCES tb_user(id),
    CONSTRAINT fk_tb_order_city FOREIGN KEY (delivery_address_city_id) REFERENCES tb_city(id)
);