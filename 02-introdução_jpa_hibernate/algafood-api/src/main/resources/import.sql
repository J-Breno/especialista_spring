INSERT INTO tb_kitchen(id, name) VALUES(1, 'Tailandesa');
INSERT INTO tb_kitchen(id, name) VALUES(2, 'Indiana');

INSERT INTO tb_payment_method(id, description) VALUES(1, 'pix');
INSERT INTO tb_payment_method(id, description) VALUES(2, 'cartão');
INSERT INTO tb_payment_method(id, description) VALUES(3, 'dinheiro');

INSERT INTO tb_restaurant(name, shipping_fee, kitchen_id, payments_method_id) VALUES('Thai Gourmet', 10, 1, 2);
INSERT INTO tb_restaurant(name, shipping_fee, kitchen_id, payments_method_id) VALUES('Thai Delivery', 9.50, 1, 3);
INSERT INTO tb_restaurant(name, shipping_fee, kitchen_id, payments_method_id) VALUES('Tuk Tuk Comida Indiana', 15, 2, 1);

INSERT INTO tb_permission(id, name, description) VALUES(1, 'Consultar pedidos', 'pode consultar pedidos');
INSERT INTO tb_permission(id, name, description) VALUES(2, 'Atualizar pedidos', 'pode atualizar pedidos');
INSERT INTO tb_permission(id, name, description) VALUES(3, 'Excluir pedidos', 'pode excluir pedidos');

INSERT INTO tb_state(id, name) VALUES(1, 'Ceará');
INSERT INTO tb_state(id, name) VALUES(2, 'Paraná');
INSERT INTO tb_state(id, name) VALUES(1, 'Ceará');

INSERT INTO tb_city(id, name, state_id) VALUES(1, 'Fortaleza', 1);
INSERT INTO tb_city(id, name, state_id) VALUES(2, 'Caucaia', 1);
INSERT INTO tb_city(id, name, state_id) VALUES(3, 'Curitiba', 2);
