-- INSERT SOME KITCHENS
INSERT INTO algafood.cozinha (id, nome) VALUES (1, 'Tailandesa');
INSERT INTO algafood.cozinha (id, nome) VALUES (2, 'Indiana');

-- INSERT SOME RESTAURANTS
INSERT INTO algafood.restaurante (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id) values ("Panela de Barro", 7.89, TRUE, TRUE, NOW(), NOW(), 1);
INSERT INTO algafood.restaurante (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id) values ("Comida Caseira", 8.19, TRUE, FALSE, NOW(), NOW(), 2);

-- INSERT SOME STATES
INSERT INTO algafood.estado (nome) VALUES ("Maranhão");
INSERT INTO algafood.estado (nome) VALUES ("Pernambuco");
INSERT INTO algafood.estado (nome) VALUES ("Tocantins");