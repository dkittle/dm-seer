
INSERT INTO accounts (username, password, email, created_on, last_login)
VALUES
    ('DonDaDM', '', 'siderean@gmail.com', now(), now());


CREATE TABLE accounts (
                          id serial PRIMARY KEY,
                          username VARCHAR (255) UNIQUE NOT NULL,
                          password VARCHAR (255) NOT NULL,
                          email VARCHAR (255) UNIQUE NOT NULL,
                          created_on TIMESTAMP NOT NULL,
                          last_login TIMESTAMP
);
INSERT INTO
    creature_crs (id, label, challenge_rating)
VALUES
    (1, '0', 0),
    (2, '1/8', 0.125),
    (3, '1/4', 0.25),
    (4, '1/2', 0.5),
    (5, '1', 1),
    (6, '2', 2),
    (7, '3', 3),
    (8, '4', 4),
    (9, '5', 5),
    (10, '6', 6),
    (11, '7', 7),
    (12, '8', 8),
    (13, '9', 9),
    (14, '10', 10),
    (15, '11', 11),
    (16, '12', 12),
    (17, '13', 13),
    (18, '14', 14),
    (19, '15', 15),
    (20, '16', 16),
    (21, '17', 17),
    (22, '18', 18),
    (23, '19', 19),
    (24, '20', 20),
    (25, '21', 21),
    (26, '22', 22),
    (27, '23', 23),
    (28, '24', 24),
    (29, '25', 25),
    (30, '26', 26),
    (31, '27', 27),
    (32, '28', 28),
    (33, '29', 29),
    (34, '30', 30);

INSERT INTO environments (id, environment)
VALUES (1, 'arctic'),
(2, 'costal'),
(3, 'desert'),
(4, 'forest'),
(5, 'grassland'),
(6, 'hill'),
(7, 'mountain'),
(8, 'swamp'),
(9, 'underdark'),
(10, 'underwater'),
(11, 'urban');

INSERT INTO languages (id, language)
VALUES (1, 'Common'),
(6, 'Goblin');

INSERT INTO leveled_characters (id, label, lvl)
VALUES (1, 'Level 1 Character', 1),
(2, 'Level 2 Character', 2),
(3, 'Level 3 Character', 3),
(4, 'Level 4 Character', 4),
(5, 'Level 5 Character', 5),
(6, 'Level 6 Character', 6),
(7, 'Level 7 Character', 7),
(8, 'Level 8 Character', 8),
(9, 'Level 9 Character', 9),
(10, 'Level 10 Character', 10),
(11, 'Level 11 Character', 11),
(12, 'Level 12 Character', 12),
(13, 'Level 13 Character', 13),
(14, 'Level 14 Character', 14),
(15, 'Level 15 Character', 15),
(16, 'Level 16 Character', 16),
(17, 'Level 17 Character', 17),
(18, 'Level 18 Character', 18),
(19, 'Level 19 Character', 19),
(20, 'Level 20 Character', 20);

INSERT INTO movements (id, label)
VALUES (1, 'walk'),
(2, 'burrow'),
(3, 'climb'),
(4, 'fly'),
(5, 'swim');

INSERT INTO saves (id, save)
VALUES (1, 'strength'),
(2, 'dexterity'),
(3, 'constitution'),
(4, 'intelligence'),
(5, 'wisdom'),
(6, 'charisma');

INSERT INTO senses (id, label)
VALUES (1, 'blindsight'),
(2, 'Darkvision'),
(3, 'tremorsense'),
(4, 'truesight');

INSERT INTO sizes (id, size)
VALUES (2, 'tiny'),
(3, 'small'),
(4, 'medium'),
(5, 'large'),
(6, 'huge'),
(7, 'gargantuan');

INSERT INTO skills (id, label)
VALUES (5, 'Stealth');

INSERT INTO sources (id, title)
VALUES (1, 'Basic Rules'),
(5, 'Monster Manual'),
(8, 'Lost Mine of Phandelver,'),
(16, 'The Sunless Citadel'),
(38, 'Guildmasters Guide to Ravnica');

INSERT INTO types (id, type)
VALUES (1, 'aberration'),
(2, 'beast'),
(3, 'celestial'),
(4, 'construct'),
(6, 'dragon'),
(7, 'elemental'),
(8, 'fey'),
(9, 'fiend'),
(10, 'giant'),
(11, 'humanoid'),
(13, 'monstrosity'),
(14, 'ooze'),
(15, 'plant'),
(16, 'undead');

INSERT INTO subtypes (id, subtype)
VALUES (23, 'Goblinoid');
