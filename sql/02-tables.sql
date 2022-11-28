


CREATE TABLE accounts (
id serial PRIMARY KEY,
username VARCHAR (255) UNIQUE NOT NULL,
password VARCHAR (255) NOT NULL,
email VARCHAR (255) UNIQUE NOT NULL,
created_on TIMESTAMP NOT NULL,
last_login TIMESTAMP
);

CREATE TABLE campaigns (
id serial PRIMARY KEY,
campaign_name VARCHAR(200) NOT NULL,
official BOOLEAN NOT NULL
);

CREATE TABLE locations (
id serial PRIMARY KEY,
name VARCHAR(100) NOT NULL
);

CREATE TABLE creature_crs (
id serial PRIMARY KEY,
label VARCHAR(20) NOT NULL,
challenge_rating float8 NOT NULL
);

CREATE TABLE environments (
id INT PRIMARY KEY,
environment VARCHAR(20) NOT NULL
);

CREATE TABLE languages (
id INT PRIMARY KEY,
language VARCHAR(30) NOT NULL
);

CREATE TABLE movements (
id INT PRIMARY KEY,
label VARCHAR(20) NOT NULL
);

CREATE TABLE saves (
id INT PRIMARY KEY,
save VARCHAR(20) NOT NULL
);

CREATE TABLE senses (
id INT PRIMARY KEY,
label VARCHAR(20) NOT NULL
);

CREATE TABLE sizes (
id INT PRIMARY KEY,
size VARCHAR(20) NOT NULL
);

CREATE TABLE types (
id INT PRIMARY KEY,
type VARCHAR(30) NOT NULL
);

CREATE TABLE creatures (
id serial PRIMARY KEY,
name VARCHAR(100) NOT NULL,
challenge_rating_id INT NOT NULL,
FOREIGN KEY (challenge_rating_id) REFERENCES creature_crs (id),
);

CREATE TABLE encounters (
id serial PRIMARY KEY,
name VARCHAR (255) NOT NULL,
campaign_id INT,
location_id INT,
suggested_apl INT,
created_by INT NOT NULL,
FOREIGN KEY (campaign_id) REFERENCES campaigns(id),
FOREIGN KEY (location_id) REFERENCES locations(id),
FOREIGN KEY (created_by) REFERENCES accounts(id)
);

CREATE TABLE leveled_characters (
id INT PRIMARY KEY,
label VARCHAR (50) UNIQUE NOT NULL,
lvl INT NOT NULL
);

CREATE TABLE anticipated_characters (
encounter_id INT NOT NULL,
character_id INT NOT NULL,
PRIMARY KEY (encounter_id, character_id),
FOREIGN KEY (encounter_id) REFERENCES encounters (id),
FOREIGN KEY (character_id) REFERENCES leveled_characters (id)
);

CREATE TABLE encounter_creatures (
encounter_id INT NOT NULL,
creature_id INT NOT NULL,
creature_numbers INT NOT NULL,
PRIMARY KEY (encounter_id, creature_id),
FOREIGN KEY (encounter_id) REFERENCES encounters (id),
FOREIGN KEY (creature_id) REFERENCES creatures (id)
);


CREATE TABLE sources (
id serial PRIMARY KEY,
title VARCHAR (50) UNIQUE NOT NULL
);
