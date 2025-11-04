CREATE TABLE tb_historico_movimentacao (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    moto_id BIGINT NOT NULL,
    zona_id BIGINT,
    data_hora DATETIME2 NOT NULL,
    observacao VARCHAR(255),

    CONSTRAINT fk_hist_moto
        FOREIGN KEY (moto_id) REFERENCES tb_moto (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_hist_zona
        FOREIGN KEY (zona_id) REFERENCES tb_zona_virtual (id)
        ON DELETE SET NULL
);
