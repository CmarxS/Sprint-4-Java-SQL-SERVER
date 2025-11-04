-- Zonas
INSERT INTO tb_zona_virtual (nome, descricao, tipo) VALUES
('Patio A', 'Área principal', 'OPERACIONAL'),
('Visitantes', 'Estacionamento de visitantes', 'PUBLICO');

-- Motos
INSERT INTO tb_moto (placa, modelo, status) VALUES
('ABC1D23', 'Honda CG 160', 'No pátio'),
('DEF4G56', 'Yamaha Fazer 250', 'Em movimento');

-- Histórico
INSERT INTO tb_historico_movimentacao (moto_id, zona_id, data_hora, observacao) VALUES
(1, 1, GETDATE(), 'Entrada pelo portão 1'),
(2, 2, GETDATE(), 'Estacionada visitantes');
