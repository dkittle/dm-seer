\connect postgres;
drop database dmseer;
create database dmseer;
\connect dmseer;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
