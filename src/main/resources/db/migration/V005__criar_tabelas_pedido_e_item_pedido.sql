create table pedido (
  id bigint not null auto_increment,
  data_criacao datetime not null,
  subtotal decimal(10, 2) not null,
  taxa_frete decimal(10, 2) not null,
  valor_total decimal(10, 2) not null,
  data_confirmacao datetime not null,
  data_entrega datetime not null,
  data_cancelamento datetime not null,
  forma_pagamento_id bigint not null,
  restaurante_id bigint not null,
  usuario_cliente_id bigint not null,
  endereco_cep varchar(15),
  endereco_logradouro varchar(120),
  endereco_numero varchar(10),
  endereco_complemento varchar(100),
  endereco_bairro varchar(80),
  endereco_cidade_id bigint,
  status varchar(10) not null,

  primary key (id),
  constraint fk_pedido_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id),
  constraint fk_pedido_restaurante foreign key (restaurante_id) references restaurante (id),
  constraint fk_pedido_usuario_cliente foreign key (usuario_cliente_id) references usuario (id),
  constraint fk_pedido_endereco_cidade foreign key (endereco_cidade_id) references cidade (id)
) engine=InnoDB default charset=utf8mb4;

create table item_pedido (
  id bigint not null auto_increment,
  preco_unitario decimal(10, 2) not null,
  quantidade smallint not null,
  preco_total decimal(10, 2) not null,
  observacao varchar(255),
  produto_id bigint not null,
  pedido_id bigint not null,

  primary key(id),
  unique key uk_item_pedido_produto (pedido_id, produto_id),
  constraint fk_item_pedido_produto foreign key (produto_id) references produto (id),
  constraint fk_item_pedido_pedido foreign key (pedido_id) references pedido (id)
) engine=InnoDB default charset=utf8mb4;