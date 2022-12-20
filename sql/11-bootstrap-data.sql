INSERT INTO accounts (username, email, created_on, last_login)
VALUES
    ('DonDaDM', 'siderean@gmail.com', now(), now());

INSERT INTO identity_database (account_id, password)
SELECT id, '' FROM accounts where username='DonDaDM';

INSERT INTO vtt_accounts (vtt_name, vtt_id, vtt_key, account_id)
SELECT 'ddb', 107326383, '', id FROM accounts where username='DonDaDM';


INSERT INTO creature_crs (label, challenge_rating)
VALUES
    ('0', 0),
    ('1/8', 0.125),
    ('1/4', 0.25),
    ('1/2', 0.5),
    ('1', 1),
    ('2', 2),
    ('3', 3),
    ('4', 4),
    ('5', 5),
    ('6', 6),
    ('7', 7),
    ('8', 8),
    ('9', 9),
    ('10', 10),
    ('11', 11),
    ('12', 12),
    ('13', 13),
    ('14', 14),
    ('15', 15),
    ('16', 16),
    ('17', 17),
    ('18', 18),
    ('19', 19),
    ('20', 20),
    ('21', 21),
    ('22', 22),
    ('23', 23),
    ('24', 24),
    ('25', 25),
    ('26', 26),
    ('27', 27),
    ('28', 28),
    ('29', 29),
    ('30', 30);

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
(6, 'Goblin'),
(11, 'Draconic');

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
(2, 'darkvision'),
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
VALUES (5, 'Stealth'),
(14, 'Perception');

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

## INSERT INTO subtypes (id, subtype)
## VALUES (23, 'Goblinoid');
