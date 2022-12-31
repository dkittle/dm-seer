\connect postgres;
drop database dmseer;
create database dmseer;
\connect dmseer;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


delete from encounterees;
delete from combats;
delete from encounter_origins;
delete from encounters;
