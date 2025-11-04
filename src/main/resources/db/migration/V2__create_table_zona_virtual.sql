-- Drop table if exists (for idempotency)
IF OBJECT_ID('dbo.tb_zona_virtual', 'U') IS NOT NULL
    DROP TABLE dbo.tb_zona_virtual;

CREATE TABLE tb_zona_virtual (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao VARCHAR(255),
    tipo VARCHAR(50)
);
