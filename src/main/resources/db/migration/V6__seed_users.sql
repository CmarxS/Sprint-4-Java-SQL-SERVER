INSERT INTO tb_role (nome) VALUES ('ADMIN'), ('OPERADOR');

-- usuários:
INSERT INTO tb_usuario (nome, email, senha, enabled) VALUES
('Admin',   'admin@mottomap.com',   '{noop}admin123',  1),
('Operador','oper@mottomap.com',   '{noop}oper123',   1);

-- vínculos de perfil:
INSERT INTO tb_usuario_role (usuario_id, role_id)
SELECT u.id, r.id FROM tb_usuario u, tb_role r WHERE u.email='admin@mottomap.com' AND r.nome='ADMIN';
INSERT INTO tb_usuario_role (usuario_id, role_id)
SELECT u.id, r.id FROM tb_usuario u, tb_role r WHERE u.email='oper@mottomap.com' AND r.nome='OPERADOR';
