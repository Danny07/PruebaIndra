--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

-- Started on 2024-12-13 22:09:42

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE IF EXISTS store;
--
-- TOC entry 4913 (class 1262 OID 32876)
-- Name: store; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE store WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Colombia.1252';


ALTER DATABASE store OWNER TO postgres;

\connect store

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4915 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 32906)
-- Name: coupon; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.coupon (
    id integer NOT NULL,
    name character varying(500) NOT NULL,
    value bigint,
    percentage bigint,
    isvalue boolean NOT NULL
);


ALTER TABLE public.coupon OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 32901)
-- Name: discounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.discounts (
    id integer NOT NULL,
    percentage bigint NOT NULL,
    begin_date date NOT NULL,
    end_date date NOT NULL
);


ALTER TABLE public.discounts OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 32970)
-- Name: discounts_products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.discounts_products (
    id integer NOT NULL,
    id_discounts integer NOT NULL,
    id_products uuid NOT NULL
);


ALTER TABLE public.discounts_products OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 32877)
-- Name: document_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.document_type (
    id integer NOT NULL,
    abbreviation character varying(5) NOT NULL,
    description character varying(100) NOT NULL
);


ALTER TABLE public.document_type OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 32894)
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    id uuid NOT NULL,
    description character varying(500) NOT NULL,
    price bigint NOT NULL
);


ALTER TABLE public.products OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 32941)
-- Name: sales; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sales (
    id uuid NOT NULL,
    id_coupon integer,
    sale_date date NOT NULL
);


ALTER TABLE public.sales OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 32958)
-- Name: seq_coupon; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_coupon
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_coupon OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 32989)
-- Name: seq_discounts; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_discounts
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_discounts OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 32990)
-- Name: seq_discounts_products; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_discounts_products
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_discounts_products OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 32911)
-- Name: shopping_cars; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.shopping_cars (
    id uuid NOT NULL,
    id_users uuid NOT NULL,
    id_products uuid NOT NULL,
    quantity integer NOT NULL,
    is_sale boolean DEFAULT false NOT NULL,
    id_sale uuid
);


ALTER TABLE public.shopping_cars OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 32882)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id uuid NOT NULL,
    document_id character varying(100) NOT NULL,
    id_document_type integer NOT NULL,
    name character varying(200) NOT NULL,
    last_name character varying(200) NOT NULL,
    email character varying(3000) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 4901 (class 0 OID 32906)
-- Dependencies: 220
-- Data for Name: coupon; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.coupon (id, name, value, percentage, isvalue) VALUES (1, 'CUPON1', 10, 0, true) ON CONFLICT DO NOTHING;
INSERT INTO public.coupon (id, name, value, percentage, isvalue) VALUES (2, 'CUPON2', 0, 20, false) ON CONFLICT DO NOTHING;
INSERT INTO public.coupon (id, name, value, percentage, isvalue) VALUES (3, 'CUPON3', 200, 0, true) ON CONFLICT DO NOTHING;


--
-- TOC entry 4900 (class 0 OID 32901)
-- Dependencies: 219
-- Data for Name: discounts; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.discounts (id, percentage, begin_date, end_date) VALUES (1, 5, '2024-01-01', '2024-06-30') ON CONFLICT DO NOTHING;
INSERT INTO public.discounts (id, percentage, begin_date, end_date) VALUES (2, 5, '2024-07-01', '2024-12-31') ON CONFLICT DO NOTHING;


--
-- TOC entry 4905 (class 0 OID 32970)
-- Dependencies: 224
-- Data for Name: discounts_products; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.discounts_products (id, id_discounts, id_products) VALUES (3, 1, '700f86d0-5146-1b4c-3087-5f48d57c75f2') ON CONFLICT DO NOTHING;
INSERT INTO public.discounts_products (id, id_discounts, id_products) VALUES (4, 1, '96b1e771-8c33-ff11-2de1-228d0534ba2b') ON CONFLICT DO NOTHING;
INSERT INTO public.discounts_products (id, id_discounts, id_products) VALUES (5, 1, 'e083144f-ecd7-f0af-e95b-c894ed63b484') ON CONFLICT DO NOTHING;
INSERT INTO public.discounts_products (id, id_discounts, id_products) VALUES (6, 2, '6c007862-f083-0cea-1633-068cb48c1373') ON CONFLICT DO NOTHING;
INSERT INTO public.discounts_products (id, id_discounts, id_products) VALUES (7, 2, '576d2460-be45-9da2-237f-00d97bf91247') ON CONFLICT DO NOTHING;
INSERT INTO public.discounts_products (id, id_discounts, id_products) VALUES (8, 2, 'e04d57ba-cfa7-195e-35f1-869a496d6886') ON CONFLICT DO NOTHING;
INSERT INTO public.discounts_products (id, id_discounts, id_products) VALUES (9, 1, '6c007862-f083-0cea-1633-068cb48c1373') ON CONFLICT DO NOTHING;


--
-- TOC entry 4897 (class 0 OID 32877)
-- Dependencies: 216
-- Data for Name: document_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.document_type (id, abbreviation, description) VALUES (1, 'CC', 'Cedula de Ciudadanía') ON CONFLICT DO NOTHING;
INSERT INTO public.document_type (id, abbreviation, description) VALUES (2, 'TI', 'Tarjeta de identidad') ON CONFLICT DO NOTHING;
INSERT INTO public.document_type (id, abbreviation, description) VALUES (3, 'CE', 'Cedula de Extranjeria') ON CONFLICT DO NOTHING;


--
-- TOC entry 4899 (class 0 OID 32894)
-- Dependencies: 218
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.products (id, description, price) VALUES ('700f86d0-5146-1b4c-3087-5f48d57c75f2', 'Televisor', 1000) ON CONFLICT DO NOTHING;
INSERT INTO public.products (id, description, price) VALUES ('96b1e771-8c33-ff11-2de1-228d0534ba2b', 'Radio', 2000) ON CONFLICT DO NOTHING;
INSERT INTO public.products (id, description, price) VALUES ('e083144f-ecd7-f0af-e95b-c894ed63b484', 'Teatro en Casa', 3000) ON CONFLICT DO NOTHING;
INSERT INTO public.products (id, description, price) VALUES ('6c007862-f083-0cea-1633-068cb48c1373', 'Consola', 4000) ON CONFLICT DO NOTHING;
INSERT INTO public.products (id, description, price) VALUES ('576d2460-be45-9da2-237f-00d97bf91247', 'Portatil', 5000) ON CONFLICT DO NOTHING;
INSERT INTO public.products (id, description, price) VALUES ('e04d57ba-cfa7-195e-35f1-869a496d6886', 'Celular', 6000) ON CONFLICT DO NOTHING;


--
-- TOC entry 4903 (class 0 OID 32941)
-- Dependencies: 222
-- Data for Name: sales; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.sales (id, id_coupon, sale_date) VALUES ('cb15d62a-b772-4b3e-a900-d3815c4973f7', NULL, '2024-12-12') ON CONFLICT DO NOTHING;
INSERT INTO public.sales (id, id_coupon, sale_date) VALUES ('6173cffc-7ead-48e5-8e10-c4317c284da7', NULL, '2024-12-12') ON CONFLICT DO NOTHING;
INSERT INTO public.sales (id, id_coupon, sale_date) VALUES ('28bcdc04-65f5-4d42-9902-de87361bdb7e', NULL, '2024-12-12') ON CONFLICT DO NOTHING;
INSERT INTO public.sales (id, id_coupon, sale_date) VALUES ('d819b360-786b-46c3-8749-0b88063019cd', NULL, '2024-12-12') ON CONFLICT DO NOTHING;
INSERT INTO public.sales (id, id_coupon, sale_date) VALUES ('96de9bc6-048a-45f6-878b-6e2d197780f7', NULL, '2024-12-12') ON CONFLICT DO NOTHING;
INSERT INTO public.sales (id, id_coupon, sale_date) VALUES ('c15b8442-a8ac-4ec1-905f-80154b7f50fe', NULL, '2024-12-12') ON CONFLICT DO NOTHING;
INSERT INTO public.sales (id, id_coupon, sale_date) VALUES ('5cd666da-977e-46b7-99fc-3c1231d9e8bb', NULL, '2024-12-12') ON CONFLICT DO NOTHING;
INSERT INTO public.sales (id, id_coupon, sale_date) VALUES ('cc3787ff-a5ba-4736-a0d2-8edc2b75db0c', NULL, '2024-12-13') ON CONFLICT DO NOTHING;


--
-- TOC entry 4902 (class 0 OID 32911)
-- Dependencies: 221
-- Data for Name: shopping_cars; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.shopping_cars (id, id_users, id_products, quantity, is_sale, id_sale) VALUES ('f54979c3-7f9c-49e3-b75a-6ffb1169c2a9', '7d101e4d-7865-cd34-bcb0-e421a93962f8', '6c007862-f083-0cea-1633-068cb48c1373', 2, false, 'cc3787ff-a5ba-4736-a0d2-8edc2b75db0c') ON CONFLICT DO NOTHING;


--
-- TOC entry 4898 (class 0 OID 32882)
-- Dependencies: 217
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (id, document_id, id_document_type, name, last_name, email) VALUES ('7d101e4d-7865-cd34-bcb0-e421a93962f8', '1', 1, 'Pedro', 'Perez', 'pp@gmail.com') ON CONFLICT DO NOTHING;
INSERT INTO public.users (id, document_id, id_document_type, name, last_name, email) VALUES ('f3ade528-430e-c127-488d-cfd6b412db25', '2', 1, 'Pablo', 'Porras', 'pp2@gmail.com') ON CONFLICT DO NOTHING;
INSERT INTO public.users (id, document_id, id_document_type, name, last_name, email) VALUES ('97b8c151-471f-7518-2cb7-8fa2540a0c3d', '3', 3, 'Danny', 'Cardenas', 'dc@gmail.com') ON CONFLICT DO NOTHING;
INSERT INTO public.users (id, document_id, id_document_type, name, last_name, email) VALUES ('37084b0c-9a92-9165-1c20-63b007ba516f', '4', 2, 'Sebastian', 'Ruiz', 'sr@gmail.com') ON CONFLICT DO NOTHING;


--
-- TOC entry 4916 (class 0 OID 0)
-- Dependencies: 223
-- Name: seq_coupon; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_coupon', 3, true);


--
-- TOC entry 4917 (class 0 OID 0)
-- Dependencies: 225
-- Name: seq_discounts; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_discounts', 9, true);


--
-- TOC entry 4918 (class 0 OID 0)
-- Dependencies: 226
-- Name: seq_discounts_products; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_discounts_products', 1, false);


--
-- TOC entry 4739 (class 2606 OID 32910)
-- Name: coupon pk_coupon; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coupon
    ADD CONSTRAINT pk_coupon PRIMARY KEY (id);


--
-- TOC entry 4737 (class 2606 OID 32905)
-- Name: discounts pk_discounts; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discounts
    ADD CONSTRAINT pk_discounts PRIMARY KEY (id);


--
-- TOC entry 4747 (class 2606 OID 32983)
-- Name: discounts_products pk_discounts_products; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discounts_products
    ADD CONSTRAINT pk_discounts_products PRIMARY KEY (id);


--
-- TOC entry 4731 (class 2606 OID 32881)
-- Name: document_type pk_document_type; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.document_type
    ADD CONSTRAINT pk_document_type PRIMARY KEY (id);


--
-- TOC entry 4735 (class 2606 OID 32900)
-- Name: products pk_products; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT pk_products PRIMARY KEY (id);


--
-- TOC entry 4745 (class 2606 OID 32945)
-- Name: sales pk_sales_cars_discounts_copun; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales
    ADD CONSTRAINT pk_sales_cars_discounts_copun PRIMARY KEY (id);


--
-- TOC entry 4743 (class 2606 OID 32923)
-- Name: shopping_cars pk_shopping_carts; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shopping_cars
    ADD CONSTRAINT pk_shopping_carts PRIMARY KEY (id);


--
-- TOC entry 4733 (class 2606 OID 32930)
-- Name: users pk_users; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT pk_users PRIMARY KEY (id);


--
-- TOC entry 4741 (class 2606 OID 32992)
-- Name: coupon uq_coupon_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coupon
    ADD CONSTRAINT uq_coupon_name UNIQUE (name);


--
-- TOC entry 4752 (class 2606 OID 32977)
-- Name: discounts_products fk_discounts_products_discounts; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discounts_products
    ADD CONSTRAINT fk_discounts_products_discounts FOREIGN KEY (id_discounts) REFERENCES public.discounts(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4753 (class 2606 OID 32984)
-- Name: discounts_products fk_discounts_products_products; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discounts_products
    ADD CONSTRAINT fk_discounts_products_products FOREIGN KEY (id_products) REFERENCES public.products(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 4748 (class 2606 OID 32889)
-- Name: users fk_id_document_type_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_id_document_type_users FOREIGN KEY (id_document_type) REFERENCES public.document_type(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4749 (class 2606 OID 32924)
-- Name: shopping_cars fk_prodcuts_shopping_cars; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shopping_cars
    ADD CONSTRAINT fk_prodcuts_shopping_cars FOREIGN KEY (id_products) REFERENCES public.products(id) NOT VALID;


--
-- TOC entry 4750 (class 2606 OID 32994)
-- Name: shopping_cars fk_sale_shopping_cars; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shopping_cars
    ADD CONSTRAINT fk_sale_shopping_cars FOREIGN KEY (id_sale) REFERENCES public.sales(id) NOT VALID;


--
-- TOC entry 4751 (class 2606 OID 32931)
-- Name: shopping_cars fk_users_shopping_cars; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shopping_cars
    ADD CONSTRAINT fk_users_shopping_cars FOREIGN KEY (id_users) REFERENCES public.users(id) NOT VALID;


--
-- TOC entry 4914 (class 0 OID 0)
-- Dependencies: 4913
-- Name: DATABASE store; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON DATABASE store FROM postgres;
GRANT ALL ON DATABASE store TO postgres WITH GRANT OPTION;


-- Completed on 2024-12-13 22:09:42

--
-- PostgreSQL database dump complete
--

