-- DDL
ALTER TABLE horario DROP CONSTRAINT fk_horario_gerenciavel_id;
ALTER TABLE horario ADD CONSTRAINT fk_horario_gerenciavel_id FOREIGN KEY (gerenciavel_id) REFERENCES gerenciavel (id) ON DELETE CASCADE ON UPDATE CASCADE;

-- DDL
ALTER TABLE horario DROP CONSTRAINT fk_horario_dia_aula_id;
ALTER TABLE horario ADD CONSTRAINT fk_horario_dia_aula_id FOREIGN KEY (dia_aula_id) REFERENCES diaaula (id) ON DELETE RESTRICT ON UPDATE CASCADE;

-- DLL
INSERT INTO usuario (id, login, senha, nome) VALUES (1, 'admin', '123', 'Administrador');
INSERT INTO gerenciavel (id, dtype) VALUES (1, 'E');
INSERT INTO escola (id, nome, duracaoaula, inicioaula, periodocorrente, semanasletivas) VALUES (1, 'Escola', 45, '07:00:00', 2017, 41);