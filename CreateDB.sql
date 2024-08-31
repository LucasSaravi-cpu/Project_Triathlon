--
-- PostgreSQL database dump
--

-- Dumped from database version 16.4
-- Dumped by pg_dump version 16.4

-- Started on 2024-08-30 21:01:56

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16398)
-- Name: weatherconditions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.weatherconditions (
    measurementunit text NOT NULL,
    lowertier double precision NOT NULL,
    uppertier double precision NOT NULL,
    swimmingweathering double precision NOT NULL,
    cyclingweathering double precision NOT NULL,
    pedestrianismweathering double precision NOT NULL,
    description text NOT NULL,
    id integer NOT NULL
);


ALTER TABLE public.weatherconditions OWNER TO postgres;

--
-- TOC entry 4778 (class 0 OID 16398)
-- Dependencies: 215
-- Data for Name: weatherconditions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.weatherconditions (measurementunit, lowertier, uppertier, swimmingweathering, cyclingweathering, pedestrianismweathering, description, id) FROM stdin;
°C	-10	5	15	2	8	Very low temperature	1
°C	6	22	0	0	1	Normal temperature	2
°C	23	34	0	10	15	Warm temperature	3
°C	35	50	0	15	20	High temperature	4
Km/h	0	30	-1	-30	-4	Tailwind	5
Km/h 	-1	-30	1	35	5	Headwind	6
\.


--
-- TOC entry 4634 (class 2606 OID 16402)
-- Name: weatherconditions weatherconditions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.weatherconditions
    ADD CONSTRAINT weatherconditions_pkey PRIMARY KEY (id);


-- Completed on 2024-08-30 21:01:56

--
-- PostgreSQL database dump complete
--

