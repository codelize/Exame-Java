-- Criação da sequência para geração automática de IDs
CREATE SEQUENCE tb_evento_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

-- Criação da tabela evento
CREATE TABLE tb_evento (
    id NUMBER PRIMARY KEY, -- Identificador único do evento
    ds_titulo VARCHAR2(100) NOT NULL, -- Título do evento
    ds_evento VARCHAR(500) NOT NULL, -- Descrição do evento
    dt_evento DATE NOT NULL -- Data em que o evento ocorrerá
);