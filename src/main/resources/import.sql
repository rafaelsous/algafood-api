INSERT INTO algafood.cozinha (id, nome) VALUES (1, 'Tailandesa');
INSERT INTO algafood.cozinha (id, nome) VALUES (2, 'Indiana');

INSERT INTO algafood.restaurante (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id) values ("Panela de Barro", 7.89, TRUE, TRUE, NOW(), NOW(), 1);
INSERT INTO algafood.restaurante (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id) values ("Comida Caseira", 8.19, TRUE, FALSE, NOW(), NOW(), 2);