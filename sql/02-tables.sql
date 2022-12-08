


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
name VARCHAR(200) NOT NULL,
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
FOREIGN KEY (challenge_rating_id) REFERENCES creature_crs (id)
);

CREATE TABLE encounters (
id serial PRIMARY KEY,
name VARCHAR (255) NOT NULL,
campaign_id INT,
map_id INT,
location_id INT,
source_id INT,
suggested_acl INT,
FOREIGN KEY (campaign_id) REFERENCES campaigns(id),
FOREIGN KEY (location_id) REFERENCES locations(id)
);

CREATE TABLE encounter_preparations (
    encounter_id INT PRIMARY KEY,
    dmg_difficulty INT NOT NULL,
    sly_difficulty INT NOT NULL,
    dragna_difficulty INT NOT NULL,
    FOREIGN KEY (encounter_id) REFERENCES encounters (id)
);

CREATE TABLE leveled_characters (
    id INT PRIMARY KEY,
    label VARCHAR (50) UNIQUE NOT NULL,
    lvl INT NOT NULL
);

CREATE TABLE anticipated_characters (
    id SERIAL PRIMARY KEY,
    encounter_id INT NOT NULL;
    leveled_characters_id INT NOT NULL,
    FOREIGN KEY (encounter_id) REFERENCES encounters (id),
    FOREIGN KEY (leveled_characters_id) REFERENCES leveled_characters (id)
);

CREATE TABLE combats (
    id serial PRIMARY KEY,
    encounter_id INT NOT NULL,
    in_progress BOOLEAN NOT NULL,
    round_number INT NOT NULL,
    turn_number INT NOT NULL,

);

CREATE TABLE ddb_encounters (
    ddbId UUID DEFAULT uuid_generate_v1(),
    encounter_id INT NOT NULL,
    PRIMARY KEY (ddbId)
);

CREATE TABLE sources (
id serial PRIMARY KEY,
title VARCHAR (50) UNIQUE NOT NULL
);

CREATE TABLE encounter_creatures (
     encounter_id INT NOT NULL,
     creature_id INT NOT NULL,
     unique_id UUID DEFAULT uuid_generate_v1(),
     creature_group_order INT,
     PRIMARY KEY (encounter_id, creature_id),
     FOREIGN KEY (encounter_id) REFERENCES encounters (id),
     FOREIGN KEY (creature_id) REFERENCES creatures (id)
);

CREATE TABLE combat_creatures (
    unique_id UUID NOT NULL,
    initiative INT NOT NULL,
    current_hit_points INT NOT NULL,
    temporary_hit_points INT NOT NULL,
    maximum_hit_points INT NOT NULL
);

CREATE TABLE encounter_groups (
     encounter_id INT NOT NULL,
     creature_id INT NOT NULL,
     group_id UUID DEFAULT uuid_generate_v1(),
     group_order INT,
     quantity INT NOT NULL,
     PRIMARY KEY (encounter_id, creature_id),
     FOREIGN KEY (encounter_id) REFERENCES encounters (id),
     FOREIGN KEY (creature_id) REFERENCES creatures (id)
);

CREATE TABLE user_encounters (
    encounter_id INT NOT NULL,
    account_id INT NOT NULL,
    PRIMARY KEY (encounter_id, account_id),
    FOREIGN KEY (encounter_id) REFERENCES encounters (id),
    FOREIGN KEY (account_id) REFERENCES accounts (id)
)

CREATE TABLE players (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL
);

CREATE TABLE ddb_players (
user_name VARCHAR(150) PRIMARY KEY,
player_id INT NOT NULL,
FOREIGN KEY (player_id) REFERENCES players (id)
);

CREATE TABLE characters (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    level INT NOT NULL,
    hidden BOOLEAN NOT NULL DEFAULT false,
    species VARCHAR(40) NOT NULL,
    subspecies VARCHAR(60) NOT NULL,
    gender VARCHAR(30) DEFAULT 'unknown',
    avatar_url VARCHAR(500) NOT NULL,
    initiative INT NOT NULL,
    current_hit_points INT NOT NULL,
    temporary_hit_points INT NOT NULL,
    maximum_hit_points INT NOT NULL,
    temp_maximum_hit_points INT NOT NULL
);


CREATE TABLE character_classes (
    id SERIAL PRIMARY KEY,
    character_id INT NOT NULL,
    class VARCHAR(50) NOT NULL,
    subclass VARCHAR(60) NOT NULL,
    FOREIGN KEY (character_id) REFERENCES characters (id)
);

CREATE TABLE ddb_characters (
ddbId VARCHAR(80) PRIMARY KEY,
character_id INT NOT NULL,
FOREIGN KEY (character_id) REFERENCES characters (id)
);

CREATE TABLE encounter_characters (
encounter_id INT NOT NULL,
character_id INT NOT NULL,
PRIMARY KEY (encounter_id, character_id),
FOREIGN KEY (encounter_id) REFERENCES encounters (id),
FOREIGN KEY (character_id) REFERENCES characters (id)
);
