-- Tabela categoria
CREATE TABLE t_categoria (
    cd_categoria NUMBER(2) NOT NULL,
    nm_categoria VARCHAR2(80) NOT NULL
);

ALTER TABLE t_categoria ADD CONSTRAINT pk_categoria PRIMARY KEY ( cd_categoria );
ALTER TABLE t_categoria ADD CONSTRAINT un_categoria UNIQUE ( nm_categoria );

-- Tabela corretora
CREATE TABLE t_corretora (
    cd_corretora NUMBER(2) NOT NULL,
    nm_corretora VARCHAR2(60) NOT NULL
);

ALTER TABLE t_corretora ADD CONSTRAINT pk_corretora PRIMARY KEY ( cd_corretora );
ALTER TABLE t_corretora ADD CONSTRAINT un_corretora UNIQUE ( nm_corretora );

-- Tabela despesa
CREATE TABLE t_despesa (
    cd_despesa    NUMBER(6) NOT NULL,
    nm_despesa    VARCHAR2(80) NOT NULL,
    vl_despesa    NUMBER(16, 2) NOT NULL,
    dt_hr_despesa    DATE NOT NULL,
    cd_categoria  NUMBER(2) NOT NULL,
    cd_usuario    NUMBER(4) NOT NULL
);

ALTER TABLE t_despesa ADD CONSTRAINT pk_despesa PRIMARY KEY ( cd_despesa );

-- Tabela investimento
CREATE TABLE t_investimento (
    cd_investimento NUMBER(6) NOT NULL,
    nm_aplicacao    VARCHAR2(80) NOT NULL,
    vl_aplicacao    NUMBER(16, 2) NOT NULL,
    dt_realizacao   DATE NOT NULL,
    dt_vencimento   DATE,
    cd_usuario      NUMBER(4) NOT NULL,
    cd_corretora    NUMBER(2) NOT NULL,
    cd_tipo         NUMBER(2) NOT NULL
);

ALTER TABLE t_investimento ADD CONSTRAINT pk_investimento PRIMARY KEY ( cd_investimento );

-- Tabela objetivo
CREATE TABLE t_objetivo (
    cd_objetivo       NUMBER(6) NOT NULL,
    nm_objetivo       VARCHAR2(80) NOT NULL,
    vl_objetivo       NUMBER(16, 2) NOT NULL,
    vl_atual_objetivo NUMBER(16, 2) NOT NULL,
    dt_fim_objetivo   DATE NOT NULL,
    cd_usuario        NUMBER(4) NOT NULL
);

ALTER TABLE t_objetivo ADD CONSTRAINT pk_objetivo PRIMARY KEY ( cd_objetivo );

-- Tabela receita
CREATE TABLE t_receita (
    cd_receita NUMBER(6) NOT NULL,
    nm_receita VARCHAR2(60) NOT NULL,
    vl_receita NUMBER(16, 2) NOT NULL,
    dt_receita DATE NOT NULL,
    cd_usuario NUMBER(4) NOT NULL
);

ALTER TABLE t_receita ADD CONSTRAINT pk_receita PRIMARY KEY ( cd_receita );

-- Tabela tipo aplicacao
CREATE TABLE t_tipo_aplicacao (
    cd_tipo NUMBER(2) NOT NULL,
    nm_tipo VARCHAR2(60) NOT NULL
);

ALTER TABLE t_tipo_aplicacao ADD CONSTRAINT pk_tipo PRIMARY KEY ( cd_tipo );
ALTER TABLE t_tipo_aplicacao ADD CONSTRAINT un_tipo UNIQUE ( nm_tipo );

-- Tabela Usuario
CREATE TABLE t_usuario (
    cd_usuario    NUMBER(4) NOT NULL,
    nm_usuario       VARCHAR2(60) NOT NULL,
    dt_nascimento DATE NOT NULL,
    ds_email      VARCHAR2(256) NOT NULL,
    ds_senha      VARCHAR2(256) NOT NULL,
    genero        CHAR(1) NOT NULL,
    foto          BLOB
);
                                                                      
ALTER TABLE t_usuario ADD CONSTRAINT ck_usuario_genero CHECK ( genero in ('M','F') );
ALTER TABLE t_usuario ADD CONSTRAINT pk_usuario PRIMARY KEY ( cd_usuario );
ALTER TABLE t_usuario ADD CONSTRAINT un_usuario_email UNIQUE ( ds_email );

/*Criação dos relacionamentos*/
ALTER TABLE t_despesa
    ADD CONSTRAINT fk_categoria_despesa FOREIGN KEY ( cd_categoria ) REFERENCES t_categoria ( cd_categoria );

ALTER TABLE t_investimento
    ADD CONSTRAINT fk_corretora_investimento FOREIGN KEY ( cd_corretora ) REFERENCES t_corretora ( cd_corretora );

ALTER TABLE t_investimento
    ADD CONSTRAINT fk_tipo_investimento FOREIGN KEY ( cd_tipo ) REFERENCES t_tipo_aplicacao ( cd_tipo );
    
    -- Relacionamentos com a tabela USUARIO
    ALTER TABLE t_despesa
        ADD CONSTRAINT fk_usuario_despesa FOREIGN KEY ( cd_usuario ) REFERENCES t_usuario ( cd_usuario )
        ON DELETE CASCADE;
    
    ALTER TABLE t_investimento
        ADD CONSTRAINT fk_usuario_investimento FOREIGN KEY ( cd_usuario ) REFERENCES t_usuario ( cd_usuario )
        ON DELETE CASCADE;
    
    ALTER TABLE t_objetivo
        ADD CONSTRAINT fk_usuario_objetivo FOREIGN KEY ( cd_usuario ) REFERENCES t_usuario ( cd_usuario )
        ON DELETE CASCADE;
    
    ALTER TABLE t_receita
        ADD CONSTRAINT fk_usuario_receita FOREIGN KEY ( cd_usuario ) REFERENCES t_usuario ( cd_usuario )
        ON DELETE CASCADE;
        
        
/*Criação das Sequencias*/

-- sequencia da t_categoria
CREATE SEQUENCE sq_categoria
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 99
    NOCACHE
    NOCYCLE;
    
-- sequencia da t_tipo_aplicacao
CREATE SEQUENCE sq_tipo
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 99
    NOCACHE
    NOCYCLE;
    
-- sequencia da t_corretora
CREATE SEQUENCE sq_corretora
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 99
    NOCACHE
    NOCYCLE;

-- sequencia t_objetivo
CREATE SEQUENCE sq_objetivo
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999
    NOCACHE
    NOCYCLE;

-- sequencia t_despesa
CREATE SEQUENCE sq_despesa
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999
    NOCACHE
    NOCYCLE;

-- sequencia t_usuario
CREATE SEQUENCE sq_usuario
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 9999
    NOCACHE
    NOCYCLE;

-- sequencia t_receita
CREATE SEQUENCE sq_receita
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999
    NOCACHE
    NOCYCLE;
    
-- sequencia t_usuario
CREATE SEQUENCE sq_investimento
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999
    NOCACHE
    NOCYCLE;


-- Categorias predefinidas
INSERT INTO t_categoria VALUES (sq_categoria.nextval, 'Aluguel');
INSERT INTO t_categoria VALUES (sq_categoria.nextval, 'Alimentação');
INSERT INTO t_categoria VALUES (sq_categoria.nextval, 'Lazer');
INSERT INTO t_categoria VALUES (sq_categoria.nextval, 'Educação');
INSERT INTO t_categoria VALUES (sq_categoria.nextval, 'Transporte');
INSERT INTO t_categoria VALUES (sq_categoria.nextval, 'Casa');
INSERT INTO t_categoria VALUES (sq_categoria.nextval, 'Saúde');

-- Corretoras predefinidas
INSERT INTO t_corretora VALUES (sq_corretora.nextval, 'Banco Inter');
INSERT INTO t_corretora VALUES (sq_corretora.nextval, 'Rico');
INSERT INTO t_corretora VALUES (sq_corretora.nextval, 'XP Investimentos');
INSERT INTO t_corretora VALUES (sq_corretora.nextval, 'Banco Modal');
INSERT INTO t_corretora VALUES (sq_corretora.nextval, 'BTG Pactual');
INSERT INTO t_corretora VALUES (sq_corretora.nextval, 'Nubank');
INSERT INTO t_corretora VALUES (sq_corretora.nextval, 'C6 Bank');
INSERT INTO t_corretora VALUES (sq_corretora.nextval, 'Itaú');
INSERT INTO t_corretora VALUES (sq_corretora.nextval, 'Safra');

-- Tipos de investimento/aplicacao predefinidos
INSERT INTO t_tipo_aplicacao VALUES (sq_tipo.nextval, 'CDB');
INSERT INTO t_tipo_aplicacao VALUES (sq_tipo.nextval, 'LCI/LCA');
INSERT INTO t_tipo_aplicacao VALUES (sq_tipo.nextval, 'Tesouro Direto');
INSERT INTO t_tipo_aplicacao VALUES (sq_tipo.nextval, 'Poupança');
INSERT INTO t_tipo_aplicacao VALUES (sq_tipo.nextval, 'FIIs');
INSERT INTO t_tipo_aplicacao VALUES (sq_tipo.nextval, 'Fundos');
INSERT INTO t_tipo_aplicacao VALUES (sq_tipo.nextval, 'Ações');