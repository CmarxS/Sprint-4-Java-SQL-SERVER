CREATE TABLE tb_role (
  id BIGINT IDENTITY(1,1) PRIMARY KEY,
  nome VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE tb_usuario (
  id BIGINT IDENTITY(1,1) PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(120) NOT NULL UNIQUE,
  senha VARCHAR(255) NOT NULL,
  enabled BIT NOT NULL DEFAULT 1
);

CREATE TABLE tb_usuario_role (
  usuario_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  CONSTRAINT pk_usuario_role PRIMARY KEY (usuario_id, role_id),
  CONSTRAINT fk_usuario_role_user FOREIGN KEY (usuario_id) REFERENCES tb_usuario(id) ON DELETE CASCADE,
  CONSTRAINT fk_usuario_role_role FOREIGN KEY (role_id) REFERENCES tb_role(id) ON DELETE CASCADE
);
