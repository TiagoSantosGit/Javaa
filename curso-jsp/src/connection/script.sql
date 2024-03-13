CREATE TABLE usuario
(
    id bigint NOT NULL DEFAULT nextval('usuariosequence'::regclass),
    login text COLLATE pg_catalog."default",
	senha text COLLATE pg_catalog."default",
	nome text COLLATE pg_catalog."default",
	telefone text COLLATE pg_catalog."default",
	cep text COLLATE pg_catalog."default",
	cidade text COLLATE pg_catalog."default",
	fotoBase64 text COLLATE pg_catalog."default",
	fotoBase64Miniatura text COLLATE pg_catalog."default",
	contentTypeCurriculo text COLLATE pg_catalog."default",
	ativo boolean,
	sexo text COLLATE pg_catalog."default",
    quantidadeprodu double precision,
    precoprodu double precision,
    CONSTRAINT usuario_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.usuario
    OWNER to postgres;

alter table usuario add column cep character varying(20);
alter table usuario add column cidade character varying(50);

create table telefone(

id bigint NOT NULL DEFAULT nextval('fonesequence'::regclass),
numero character varying(500),
tipo character varying(500),
datafinal character varying(500),
usuario bigint NOT NULL,
CONSTRAINT series_pkey PRIMARY KEY (id)
)
with(oids=false);
alter table telefone
  OWNER TO postgres;

  DROP TABLE public.produto;

CREATE TABLE public.produto
(
    codprodu bigint NOT NULL DEFAULT nextval('produtosequence'::regclass),
    nomeprodu text COLLATE pg_catalog."default",
    unidadeprodu text COLLATE pg_catalog."default",
    quantidadeprodu double precision,
    precoprodu double precision,
    CONSTRAINT produto_pkey PRIMARY KEY (codprodu)
)

TABLESPACE pg_default;

ALTER TABLE public.produto
    OWNER to postgres;

alter table produto alter column codprodu set default nextval("produtosequence"::regclass)