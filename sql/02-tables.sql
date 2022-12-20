CREATE TABLE accounts (
id serial PRIMARY KEY,
username VARCHAR (255) UNIQUE NOT NULL,
email VARCHAR (255) UNIQUE NOT NULL,
created_on TIMESTAMP NOT NULL,
last_login TIMESTAMP
);

CREATE TABLE identity_database (
    account_id INT PRIMARY KEY,
    password VARCHAR (255) NOT NULL,
    FOREIGN KEY (account_id) REFERENCES accounts (id)
);

CREATE TABLE vtt_accounts (
    id serial PRIMARY KEY,
    vtt_name VARCHAR(100) NOT NULL,
    vtt_id BIGINT,
    vtt_key VARCHAR(200),
    account_id INT NOT NULL,
    FOREIGN KEY (account_id) REFERENCES accounts (id)
);

CREATE TABLE campaigns (
id serial PRIMARY KEY,
name VARCHAR(200) NOT NULL,
dm_id INT NOT NULL,
splashUrl TEXT,
description TEXT,
public_notes TEXT,
private_notes TEXT,
official BOOLEAN NOT NULL,
FOREIGN KEY (dm_id) REFERENCES accounts (id)
);

CREATE TABLE locations (
id serial PRIMARY KEY,
name VARCHAR(100) NOT NULL,
campaign_id INT NOT NULL,
FOREIGN KEY (campaign_id) REFERENCES campaigns (id)
);

CREATE TABLE rooms (
id serial PRIMARY KEY,
name VARCHAR(100) NOT NULL,
location_id INT NOT NULL,
FOREIGN KEY (location_id) REFERENCES locations (id)
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


CREATE TABLE sources (
 id serial PRIMARY KEY,
 title VARCHAR (70) UNIQUE NOT NULL
);


CREATE TABLE creatures (
id SERIAL PRIMARY KEY,
species VARCHAR(100) NOT NULL,
subSpecies VARCHAR(100),
size_id INT NOT NULL,
alignment_id INT NOT NULL,
strength INT NOT NULL,
dexterity INT NOT NULL,
constitution INT NOT NULL,
intelligence INT NOT NULL,
wisdom INT NOT NULL,
charisma INT NOT NULL,
hitPoints INT NOT NULL,
hitDiceString VARCHAR(50) NOT NULL,
challenge_rating_id INT NOT NULL,
armorClass INT NOT NULL,
armor VARCHAR(100) NOT NULL,
FOREIGN KEY (size_id) REFERENCES sizes (id),
FOREIGN KEY (challenge_rating_id) REFERENCES creature_crs (id)
);

CREATE TABLE creature_speeds (
 creature_id INT PRIMARY KEY,
 movement_id INT NOT NULL,
 speed INT NOT NULL,
 FOREIGN KEY (creature_id) REFERENCES creatures (id),
 FOREIGN KEY (movement_id) REFERENCES movements (id)
);

CREATE TABLE encounters (
id serial PRIMARY KEY,
name VARCHAR (255) NOT NULL,
dm_id INT,
room_id INT,
source_id INT,
suggested_acl INT,
FOREIGN KEY (dm_id) REFERENCES accounts(id),
FOREIGN KEY (room_id) REFERENCES rooms(id),
FOREIGN KEY (source_id) REFERENCES sources(id)
);

CREATE TABLE encounter_tables (
id SERIAL PRIMARY KEY,
name VARCHAR(200) NOT NULL,
room_id INT,
FOREIGN KEY (room_id) REFERENCES rooms(id)
);

CREATE TABLE encounter_table_entries (
 encounter_id INT NOT NULL,
 table_id INT NOT NULL,
 weight INT NOT NULL,
 PRIMARY KEY (encounter_id, table_id),
 FOREIGN KEY (encounter_id) REFERENCES encounters (id),
 FOREIGN KEY (table_id) REFERENCES encounter_tables (id)
);

CREATE TABLE leveled_characters (
    id INT PRIMARY KEY,
    label VARCHAR (50) UNIQUE NOT NULL,
    lvl INT NOT NULL
);

CREATE TABLE anticipated_characters (
    id SERIAL PRIMARY KEY,
    encounter_id INT NOT NULL,
    leveled_characters_id INT NOT NULL,
    FOREIGN KEY (encounter_id) REFERENCES encounters (id),
    FOREIGN KEY (leveled_characters_id) REFERENCES leveled_characters (id)
);

CREATE TABLE combats (
    id serial PRIMARY KEY,
    encounter_id INT NOT NULL,
    in_progress BOOLEAN NOT NULL,
    round_number INT NOT NULL,
    turn_number INT NOT NULL
);

CREATE TABLE ddb_encounters (
    ddbId UUID DEFAULT uuid_generate_v1(),
    encounter_id INT NOT NULL,
    PRIMARY KEY (ddbId)
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
     quantity VARCHAR(40) NOT NULL,
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
);

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

CREATE TABLE named_npc (
    id SERIAL PRIMARY KEY,
    creature_id INT NOT NULL,
    FOREIGN KEY (creature_id) REFERENCES creatures (id)
);

CREATE TABLE character_classes (
    id SERIAL PRIMARY KEY,
    character_id INT NOT NULL,
    class VARCHAR(50) NOT NULL,
    subclass VARCHAR(60) NOT NULL,
    FOREIGN KEY (character_id) REFERENCES characters (id)
);

CREATE TABLE skills (
id SERIAL PRIMARY KEY,
label VARCHAR(50) NOT NULL
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
