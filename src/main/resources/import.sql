-- INSERT SOME KITCHENS
INSERT INTO algafood.cozinha (id, nome) VALUES (1, 'Tailandesa');
INSERT INTO algafood.cozinha (id, nome) VALUES (2, 'Indiana');

-- INSERT SOME RESTAURANTS
INSERT INTO algafood.restaurante (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id) values ('Panela de Barro', 7.89, TRUE, TRUE, NOW(), NOW(), 1);
INSERT INTO algafood.restaurante (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id) values ('Comida Caseira', 8.19, TRUE, FALSE, NOW(), NOW(), 2);

-- INSERT SOME STATES
INSERT INTO algafood.estado (id, nome) VALUES (1, 'Maranhão');
INSERT INTO algafood.estado (id, nome) VALUES (2, 'Pernambuco');
INSERT INTO algafood.estado (id, nome) VALUES (3, 'Tocantins');

-- INSERT SOME CITIES
INSERT INTO algafood.cidade(nome, estado_id) VALUES ('São Luís', 1);
INSERT INTO algafood.cidade(nome, estado_id) VALUES ('Recife', 2);
INSERT INTO algafood.cidade(nome, estado_id) VALUES ('Palmas', 3);
INSERT INTO algafood.cidade(nome, estado_id) VALUES ('Araguaína', 3);