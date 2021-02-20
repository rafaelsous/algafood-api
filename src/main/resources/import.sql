-- INSERT SOME KITCHENS
INSERT INTO algafood.cozinha (id, nome) VALUES (1, 'Tailandesa');
INSERT INTO algafood.cozinha (id, nome) VALUES (2, 'Indiana');
INSERT INTO cozinha (id, nome) VALUES (3, 'Argentina');
INSERT INTO cozinha (id, nome) VALUES (4, 'Brasileira');

-- INSERT SOME STATES
INSERT INTO algafood.estado (id, nome) VALUES (1, 'Maranhão');
INSERT INTO algafood.estado (id, nome) VALUES (2, 'Pernambuco');
INSERT INTO algafood.estado (id, nome) VALUES (3, 'Tocantins');

-- INSERT SOME CITIES
INSERT INTO algafood.cidade(id, nome, estado_id) VALUES (1, 'São Luís', 1);
INSERT INTO algafood.cidade(id, nome, estado_id) VALUES (2, 'Recife', 2);
INSERT INTO algafood.cidade(id, nome, estado_id) VALUES (3, 'Palmas', 3);
INSERT INTO algafood.cidade(id, nome, estado_id) VALUES (4, 'Araguaína', 3);

-- INSERT SOME RESTAURANTS
INSERT INTO algafood.restaurante (id, nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) VALUES (1, 'Panela de Barro', 7.89, TRUE, TRUE, UTC_TIMESTAMP, UTC_TIMESTAMP, 1, '77023526', 'Quadra 1004 Sul Alameda 14', '144', 'No centro da quadra', 'Plano Diretor Norte', 3);
INSERT INTO algafood.restaurante (id, nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id) VALUES (2, 'Comida Caseira', 8.19, TRUE, FALSE, UTC_TIMESTAMP, UTC_TIMESTAMP, 2);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) VALUES (3, 'Java Steakhouse', 12, 3, UTC_TIMESTAMP, UTC_TIMESTAMP);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) VALUES (4, 'Lanchonete do Tio Sam', 11, 4, UTC_TIMESTAMP, UTC_TIMESTAMP);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) VALUES (5, 'Bar da Maria', 6, 4, UTC_TIMESTAMP, UTC_TIMESTAMP);

-- INSERT SOME PAYMENT METHODS
INSERT INTO algafood.forma_pagamento (id, descricao) VALUES (1, 'Cartão de Crédito');
INSERT INTO algafood.forma_pagamento (id, descricao) VALUES (2, 'Cartão de Débito');
INSERT INTO algafood.forma_pagamento (id, descricao) VALUES (3, 'Dinheiro');

-- INSERT SOME RELATIONSHIPS BETWEEN RESTAURANT AND PAYMENT METHOD
INSERT INTO algafood.restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (5, 3);

-- INSERT SOME PRODUCTS
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);

INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);

INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);

INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);

INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
