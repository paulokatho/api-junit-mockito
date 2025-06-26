-- Insere dados na tabela tb_users (se você renomeou sua entidade para UserEntity e a tabela para tb_users)
-- src/main/resources/import.sql
INSERT INTO tb_users (name, email, password) VALUES ('Katho Metal', 'katho@email.com', '123');
INSERT INTO tb_users (name, email, password) VALUES ('Kirk', 'hamet@email.com', '456');

-- Se você precisar reiniciar a sequência de IDs para IDENTITY/AUTO_INCREMENT
-- ALTER SEQUENCE tb_users_id_seq RESTART WITH 3; -- Exemplx'o para PostgreSQL, pode variar para H2 (H2 geralmente cuida disso automaticamente com IDENTITY)