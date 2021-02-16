-- INSERT SOME KITCHENS
INSERT INTO algafood.cozinha (id, nome) VALUES (1, 'Tailandesa');
INSERT INTO algafood.cozinha (id, nome) VALUES (2, 'Indiana');

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
INSERT INTO algafood.restaurante (id, nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) VALUES (1, 'Panela de Barro', 7.89, TRUE, TRUE, UTC_TIMESTAMP(), UTC_TIMESTAMP(), 1, '77023526', 'Quadra 1004 Sul Alameda 14', '144', 'No centro da quadra', 'Plano Diretor Norte', 3);
INSERT INTO algafood.restaurante (id, nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id) VALUES (2, 'Comida Caseira', 8.19, TRUE, FALSE, UTC_TIMESTAMP(), UTC_TIMESTAMP(), 2);

-- INSERT SOME PAYMENT METHODS
INSERT INTO algafood.forma_pagamento (id, descricao) VALUES (1, 'Cartão de Crédito');
INSERT INTO algafood.forma_pagamento (id, descricao) VALUES (2, 'Cartão de Débito');
INSERT INTO algafood.forma_pagamento (id, descricao) VALUES (3, 'Dinheiro');

-- INSERT SOME RELATIONSHIPS BETWEEN RESTAURANT AND PAYMENT METHOD
INSERT INTO algafood.restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1), (1, 2), (1, 3), (2, 3);