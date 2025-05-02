SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM tb_order_item;
DELETE FROM tb_order;
DELETE FROM tb_restaurant_products;
DELETE FROM tb_product;
DELETE FROM tb_restaurant_payment_method;
DELETE FROM tb_user_group;
DELETE FROM tb_group_permission;
DELETE FROM tb_user;
DELETE FROM tb_group;
DELETE FROM tb_permission;
DELETE FROM tb_payment_method;
DELETE FROM tb_restaurant;
DELETE FROM tb_city;
DELETE FROM tb_state;
DELETE FROM tb_kitchen;

SET FOREIGN_KEY_CHECKS = 1;

ALTER TABLE tb_city AUTO_INCREMENT = 1;
ALTER TABLE tb_kitchen AUTO_INCREMENT = 1;
ALTER TABLE tb_state AUTO_INCREMENT = 1;
ALTER TABLE tb_payment_method AUTO_INCREMENT = 1;
ALTER TABLE tb_permission AUTO_INCREMENT = 1;
ALTER TABLE tb_group AUTO_INCREMENT = 1;
ALTER TABLE tb_user AUTO_INCREMENT = 1;
ALTER TABLE tb_restaurant AUTO_INCREMENT = 1;
ALTER TABLE tb_order AUTO_INCREMENT = 1;
ALTER TABLE tb_product AUTO_INCREMENT = 1;
ALTER TABLE tb_order_item AUTO_INCREMENT = 1;

ALTER TABLE tb_product DROP FOREIGN KEY fk_tb_product_restaurant;
ALTER TABLE tb_product DROP INDEX fk_tb_product_restaurant;
ALTER TABLE tb_product DROP COLUMN restaurant_id;

ALTER TABLE tb_restaurant ADD COLUMN open BOOLEAN NOT NULL DEFAULT TRUE;

CREATE TABLE IF NOT EXISTS tb_restaurant_products (
    restaurant_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (restaurant_id, product_id),
    FOREIGN KEY (restaurant_id) REFERENCES tb_restaurant(id),
    FOREIGN KEY (product_id) REFERENCES tb_product(id)
);

INSERT INTO tb_kitchen(id, name) VALUES(1, 'Tailandesa');
INSERT INTO tb_kitchen(id, name) VALUES(2, 'Indiana');

INSERT INTO tb_state(id, name) VALUES(1, 'Minas Gerais');
INSERT INTO tb_state(id, name) VALUES(2, 'São Paulo');
INSERT INTO tb_state(id, name) VALUES(3, 'Ceará');

INSERT INTO tb_city(id, name, state_id) VALUES(1, 'Uberlândia', 1);
INSERT INTO tb_city(id, name, state_id) VALUES(2, 'Belo Horizonte', 1);
INSERT INTO tb_city(id, name, state_id) VALUES(3, 'Fortaleza', 3);
INSERT INTO tb_city(id, name, state_id) VALUES(4, 'São Paulo', 2);
INSERT INTO tb_city(id, name, state_id) VALUES(5, 'Campinas', 2);

INSERT INTO tb_restaurant(id, name, shipping_fee, kitchen_id, registration_date, update_date, active, open, address_city_id, address_cep, address_street, address_number, address_neighborhood, address_complement) 
VALUES
(1, 'Thai Gourmet', 10, 1, UTC_TIMESTAMP(), UTC_TIMESTAMP(), true, true, 1, '38499-00', 'Rua João Pìnheiro', '1000', 'Centro', 'Perto dali'),
(2, 'Thai Delivery', 9.50, 1, UTC_TIMESTAMP(), UTC_TIMESTAMP(), true, false, 1, '38455-00', 'Rua Padre Pìnheiro', '1623', 'Aldeota', 'Perto de cá'),
(3, 'Tuk Tuk Comida Indiana', 15, 2, UTC_TIMESTAMP(), UTC_TIMESTAMP(), true, true, 1, '32699-00', 'Rua João Moura', '1712', 'Meireles', 'Perto de lá');

INSERT INTO tb_product(id, name, description, price, active) 
VALUES
(1, 'Pizza', 'Pizza de frango com bacon', 50.3, true),
(2, 'Pizza', 'Pizza de Calabresa', 50.3, true),
(3, 'Pizza', 'Pizza de Chocolate', 50.3, true);

INSERT INTO tb_restaurant_products(restaurant_id, product_id) 
VALUES
(1, 1),
(3, 2),
(2, 3);

INSERT INTO tb_payment_method(id, description) 
VALUES
(1, 'Cartão de crédito'),
(2, 'Cartão de débito'),
(3, 'Dinheiro');

INSERT INTO tb_permission(id, name, description) 
VALUES
(1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas'),
(2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

INSERT INTO tb_restaurant_payment_method(restaurant_id, payment_method_id) 
VALUES
(1, 2),
(1, 3),
(2, 3),
(3, 2),
(3, 3);

INSERT INTO tb_group(id, name) 
VALUES
(1, 'Gerente'), 
(2, 'Vendedor'), 
(3, 'Secretária'), 
(4, 'Cadastrador');

INSERT INTO tb_user(id, name, email, password, registration_date) 
VALUES
(1, 'João Breno', 'joaobreno@hotmail.com', '1234', UTC_TIMESTAMP()),
(2, 'João Melo', 'joaomelo@hotmail.com', '1234', UTC_TIMESTAMP()),
(3, 'João Souza', 'joaosouza@hotmail.com', '1234', UTC_TIMESTAMP()),
(4, 'Breno Souza', 'brenosouza@hotmail.com', '1234', UTC_TIMESTAMP());