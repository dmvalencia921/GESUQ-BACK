--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4 (Debian 17.4-1.pgdg120+2)
-- Dumped by pg_dump version 17.4 (Debian 17.4-1.pgdg120+2)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: espacio_academico; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.espacio_academico (
    id_espacio_academico integer NOT NULL,
    descripcion character varying(255) NOT NULL,
    fecha_creacion timestamp(6) without time zone NOT NULL,
    fecha_actualizacion timestamp(6) without time zone,
    id_usu_creacion character varying(15) NOT NULL,
    id_usu_actualizacion character varying(15),
    nombre character varying(255) NOT NULL
);


ALTER TABLE public.espacio_academico OWNER TO admin;

--
-- Name: espacio_academico_id_espacio_academico_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

ALTER TABLE public.espacio_academico ALTER COLUMN id_espacio_academico ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.espacio_academico_id_espacio_academico_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: facultad; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.facultad (
    id_facultad integer NOT NULL,
    fecha_creacion timestamp(6) without time zone NOT NULL,
    fecha_actualizacion timestamp(6) without time zone,
    id_usu_creacion character varying(15) NOT NULL,
    id_usu_actualizacion character varying(15),
    nombre_facultad character varying(255) NOT NULL
);


ALTER TABLE public.facultad OWNER TO admin;

--
-- Name: facultad_id_facultad_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

ALTER TABLE public.facultad ALTER COLUMN id_facultad ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.facultad_id_facultad_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: facultad_programa; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.facultad_programa (
    idfacultadprograma integer NOT NULL,
    cod_programa character varying(255) NOT NULL,
    fecha_creacion timestamp(6) without time zone NOT NULL,
    fecha_actualizacion timestamp(6) without time zone,
    id_facultad integer NOT NULL,
    id_usu_creacion character varying(15) NOT NULL,
    id_usu_actualizacion character varying(15),
    nombre_facultad character varying(255) NOT NULL,
    nombre_programa character varying(255) NOT NULL
);


ALTER TABLE public.facultad_programa OWNER TO admin;

--
-- Name: facultad_programa_idfacultadprograma_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

ALTER TABLE public.facultad_programa ALTER COLUMN idfacultadprograma ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.facultad_programa_idfacultadprograma_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: programa; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.programa (
    id_programa integer NOT NULL,
    cod_programa character varying(255) NOT NULL,
    fecha_creacion timestamp(6) without time zone NOT NULL,
    fecha_actualizacion timestamp(6) without time zone,
    id_usu_creacion character varying(15) NOT NULL,
    id_usu_actualizacion character varying(15),
    nombre character varying(255) NOT NULL
);


ALTER TABLE public.programa OWNER TO admin;

--
-- Name: programa_id_programa_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

ALTER TABLE public.programa ALTER COLUMN id_programa ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.programa_id_programa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: sede; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.sede (
    id_sede integer NOT NULL,
    fecha_creacion timestamp(6) without time zone NOT NULL,
    fecha_actualizacion timestamp(6) without time zone,
    id_usu_creacion character varying(15) NOT NULL,
    id_usu_actualizacion character varying(15),
    nombre_sede character varying(255) NOT NULL,
    ubicacion character varying(255) NOT NULL
);


ALTER TABLE public.sede OWNER TO admin;

--
-- Name: sede_id_sede_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

ALTER TABLE public.sede ALTER COLUMN id_sede ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.sede_id_sede_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: usuario; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.usuario (
    id_usuario integer NOT NULL,
    activo boolean DEFAULT true,
    admin boolean DEFAULT false NOT NULL,
    apellidos character varying(255) NOT NULL,
    clave character varying(255) NOT NULL,
    fecha_creacion timestamp(6) without time zone NOT NULL,
    fecha_actualizacion timestamp(6) without time zone,
    id_usu_creacion character varying(15) NOT NULL,
    id_usu_actualizacion character varying(15),
    no_documento character varying(255) NOT NULL,
    nombre_rol character varying(255) NOT NULL,
    nombres character varying(255) NOT NULL,
    token_recuperacion character varying(255),
    usuario character varying(255) NOT NULL
);


ALTER TABLE public.usuario OWNER TO admin;

--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

ALTER TABLE public.usuario ALTER COLUMN id_usuario ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.usuario_id_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: espacio_academico; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.espacio_academico (id_espacio_academico, descripcion, fecha_creacion, fecha_actualizacion, id_usu_creacion, id_usu_actualizacion, nombre) FROM stdin;
\.


--
-- Data for Name: facultad; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.facultad (id_facultad, fecha_creacion, fecha_actualizacion, id_usu_creacion, id_usu_actualizacion, nombre_facultad) FROM stdin;
\.


--
-- Data for Name: facultad_programa; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.facultad_programa (idfacultadprograma, cod_programa, fecha_creacion, fecha_actualizacion, id_facultad, id_usu_creacion, id_usu_actualizacion, nombre_facultad, nombre_programa) FROM stdin;
\.


--
-- Data for Name: programa; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.programa (id_programa, cod_programa, fecha_creacion, fecha_actualizacion, id_usu_creacion, id_usu_actualizacion, nombre) FROM stdin;
\.


--
-- Data for Name: sede; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.sede (id_sede, fecha_creacion, fecha_actualizacion, id_usu_creacion, id_usu_actualizacion, nombre_sede, ubicacion) FROM stdin;
1	2025-03-20 14:42:26.469	2025-03-20 14:42:26.469	string	string	sede manizales	manizales
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.usuario (id_usuario, activo, admin, apellidos, clave, fecha_creacion, fecha_actualizacion, id_usu_creacion, id_usu_actualizacion, no_documento, nombre_rol, nombres, token_recuperacion, usuario) FROM stdin;
1	t	f	admin	$2a$10$kB/p14KbkgHsi5TLKUcZe./S6FljzKt3U88gtjoMN5gpuQ7iGYibO	2025-03-20 00:08:05.037	\N	string	\N	1234	ADMIN_ROLE	usuario	\N	usuario@uniquindio.edu.co
2	t	f	admin2	$2a$10$c481sfCsQ2n/.5J7O0PLce6wGNeWvbQiICjj7r2hdx7bFyctETZcG	2025-03-20 11:37:43.524	\N	string	\N	2530	ADMIN_ROLE	usuario	\N	usuario2@uniquindio.edu.co
\.


--
-- Name: espacio_academico_id_espacio_academico_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.espacio_academico_id_espacio_academico_seq', 1, false);


--
-- Name: facultad_id_facultad_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.facultad_id_facultad_seq', 1, false);


--
-- Name: facultad_programa_idfacultadprograma_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.facultad_programa_idfacultadprograma_seq', 1, false);


--
-- Name: programa_id_programa_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.programa_id_programa_seq', 1, false);


--
-- Name: sede_id_sede_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.sede_id_sede_seq', 1, true);


--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.usuario_id_usuario_seq', 2, true);


--
-- Name: espacio_academico espacio_academico_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.espacio_academico
    ADD CONSTRAINT espacio_academico_pkey PRIMARY KEY (id_espacio_academico);


--
-- Name: facultad facultad_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.facultad
    ADD CONSTRAINT facultad_pkey PRIMARY KEY (id_facultad);


--
-- Name: facultad_programa facultad_programa_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.facultad_programa
    ADD CONSTRAINT facultad_programa_pkey PRIMARY KEY (idfacultadprograma);


--
-- Name: programa programa_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.programa
    ADD CONSTRAINT programa_pkey PRIMARY KEY (id_programa);


--
-- Name: sede sede_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.sede
    ADD CONSTRAINT sede_pkey PRIMARY KEY (id_sede);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario);


--
-- PostgreSQL database dump complete
--

