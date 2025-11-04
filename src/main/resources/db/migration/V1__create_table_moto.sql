-- Drop table if exists (for idempotency)
IF OBJECT_ID('dbo.tb_moto', 'U') IS NOT NULL
    DROP TABLE dbo.tb_moto;

CREATE TABLE tb_moto (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    placa VARCHAR(7) NOT NULL UNIQUE,
    modelo VARCHAR(100) NOT NULL,
    status VARCHAR(50) NOT NULL
);
