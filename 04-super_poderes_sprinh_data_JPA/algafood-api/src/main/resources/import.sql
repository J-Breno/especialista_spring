INSERT INTO tb_kitchen(id, name) VALUES(1, 'Tailandesa');
INSERT INTO tb_kitchen(id, name) VALUES(2, 'Indiana');

INSERT INTO tb_payment_method(id, description) VALUES(1, 'Cartão de crédito');
INSERT INTO tb_payment_method(id, description) VALUES(2, 'Cartão de débito');
INSERT INTO tb_payment_method(id, description) VALUES(3, 'dinheiro');

INSERT INTO tb_restaurant(name, shipping_fee, kitchen_id, payments_method_id) VALUES('Thai Gourmet', 10, 1, 2);
INSERT INTO tb_restaurant(name, shipping_fee, kitchen_id, payments_method_id) VALUES('Thai Delivery', 9.50, 1, 3);
INSERT INTO tb_restaurant(name, shipping_fee, kitchen_id, payments_method_id) VALUES('Tuk Tuk Comida Indiana', 15, 2, 1);

INSERT INTO tb_permission(id, name, description) VALUES(1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO tb_permission(id, name, description) VALUES(2, 'EDITAR_COZINHAS', 'pode atualizar pedidos');

INSERT INTO tb_state(id, name) VALUES(1, 'Minas Gerais');
INSERT INTO tb_state(id, name) VALUES(2, 'São Paulo');
INSERT INTO tb_state(id, name) VALUES(3, 'Ceará');

INSERT INTO tb_city(id, name, state_id) VALUES(1, 'Uberlândia', 1);
INSERT INTO tb_city(id, name, state_id) VALUES(2, 'Belo Horizonte', 1);
INSERT INTO tb_city(id, name, state_id) VALUES(3, 'Fortaleza', 3);
INSERT INTO tb_city(id, name, state_id) VALUES(4, 'São Paulo', 2);
INSERT INTO tb_city(id, name, state_id) VALUES(5, 'Campinas', 2);

INSERT INTO tb_restaurant_payment_method(restauarnt_id, payment_method_id) VALUES(1, 1), (1,2), (1, 3),(2,3), (3, 2), (3, 3);