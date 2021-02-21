create table forma_pagamento (
  id bigint not null auto_increment,
  descricao varchar(60) not null,

  primary key(id)
) engine=InnoDB default charset=utf8mb4;

create table grupo (
  id bigint not null auto_increment,
  nome varchar(80) not null,

  primary key(id)
) engine=InnoDB default charset=utf8mb4;

create table permissao (
  id bigint not null auto_increment,
  nome varchar(60) not null,
  descricao varchar(100) not null,

  primary key(id)
) engine=InnoDB default charset=utf8mb4;

create table grupo_permissao (
  grupo_id bigint not null,
  permissao_id bigint not null,

  constraint fk_grupo_id foreign key (grupo_id) references grupo (id),
  constraint fk_permissao_id foreign key (permissao_id) references permissao (id)
) engine=InnoDB default charset=utf8mb4;

create table restaurante (
  id bigint not null auto_increment,
  data_cadastro datetime not null,
  data_atualizacao datetime not null,
  nome varchar(60) not null,
  taxa_frete decimal(10,2) not null,
  ativo bool not null default false,
  aberto bool not null default false,
  cozinha_id bigint not null,
  endereco_cep varchar(15),
  endereco_logradouro varchar(120),
  endereco_numero varchar(10),
  endereco_complemento varchar(100),
  endereco_bairro varchar(80),
  endereco_cidade_id bigint,

  primary key(id),
  constraint fk_restaurante_cozinha foreign key (cozinha_id) references cozinha (id),
  constraint fk_restaurante_cidade foreign key (endereco_cidade_id) references cidade (id)
) engine=InnoDB default charset=utf8mb4;

create table restaurante_forma_pagamento (
  restaurante_id bigint not null,
  forma_pagamento_id bigint not null,

  constraint fk_restaurante_forma_pagamento_id foreign key (restaurante_id) references restaurante (id),
  constraint fk_forma_pagamento_restaurante_id foreign key (forma_pagamento_id) references forma_pagamento (id)
) engine=InnoDB default charset=utf8mb4;

create table produto (
  id bigint not null auto_increment,
  nome varchar(60) not null,
  descricao varchar(255) not null,
  preco decimal(10,2) not null,
  ativo bool not null default true,
  restaurante_id bigint,

  primary key(id),
  constraint fk_produto_restaurante_id foreign key (restaurante_id) references restaurante (id)
) engine=InnoDB default charset=utf8mb4;

create table usuario (
  id bigint not null auto_increment,
  data_cadastro datetime not null,
  data_atualizacao datetime not null,
  nome varchar(60) not null,
  email varchar(100) not null,
  senha varchar(100) not null,

  primary key(id)
) engine=InnoDB default charset=utf8mb4;

create table usuario_grupo (
  usuario_id bigint not null,
  grupo_id bigint not null,

  constraint fk_usuario_grupo_id foreign key (usuario_id) references usuario (id),
  constraint fk_grupo_usuario_id foreign key (grupo_id) references grupo (id)
) engine=InnoDB default charset=utf8mb4;