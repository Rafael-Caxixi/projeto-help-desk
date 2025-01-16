create table chamados (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  cliente_id BIGINT NOT NULL,
  consultor_id BIGINT NULL,
  categoria VARCHAR(25) NOT NULL,
  foreign key (cliente_id) references clientes(id),
  foreign key (consultor_id) references consultores(id),
  descricao VARCHAR(500) NOT NULL,
  aberto BOOLEAN);