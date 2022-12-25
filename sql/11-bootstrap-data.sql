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

INSERT INTO sources(label, description, account_id, official, avatar_url)
    VALUES
('BR', 'Basic Rules', 1, true, 'https://www.dndbeyond.com/avatars/10434/136/637248073409717512.jpeg'),
('PHB', 'Player''s Handbook', 1, true, 'https://www.dndbeyond.com/avatars/10435/389/637248131811862290.jpeg'),
('DMG', 'Dungeon Master''s Guide', 1, true, 'https://www.dndbeyond.com/avatars/10367/593/637245347063211867.jpeg'),
('EE', 'Elemental Evil Player''s Companion', 1, true, 'https://www.dndbeyond.com/avatars/'),
('MM', 'Monster Manual', 1, true, 'https://www.dndbeyond.com/avatars/10434/816/637248105832999293.jpeg'),
('CoS', 'Curse of Strahd', 1, true, 'https://www.dndbeyond.com/avatars/10349/296/637244603965977140.jpeg'),
('HotDQ', 'Hoard of the Dragon Queen', 1, true, 'https://www.dndbeyond.com/avatars/10432/68/637247937818392417.jpeg'),
('LMoP', 'Lost Mine of Phandelver', 1, true, 'https://www.dndbeyond.com/avatars/10434/616/637248096401764265.jpeg'),
('OotA', 'Out of the Abyss', 1, true, 'https://www.dndbeyond.com/avatars/19/735/636383500945700817.jpeg'),
('PotA', 'Princes of the Apocalypse', 1, true, 'https://www.dndbeyond.com/avatars/10435/524/637248137744435932.jpeg'),
('RoT', 'Rise of Tiamat', 1, true, 'https://www.dndbeyond.com/avatars/10435/605/637248141604547323.jpeg'),
('SKT', 'Storm King''s Thunder', 1, true, 'https://www.dndbeyond.com/avatars/19/740/636383501361665378.jpeg'),
('SCAG', 'Sword Coast Adventurer''s Guide', 1, true, 'https://www.dndbeyond.com/avatars/10435/793/637248149475504723.jpeg'),
('TftYP', 'Tales from the Yawning Portal', 1, true, 'https://www.dndbeyond.com/avatars/10449/177/637248652153094716.jpeg'),
('VGtM', 'Volo''s Guide to Monsters', 1, true, 'https://www.dndbeyond.com/avatars/10449/464/637248679743732719.jpeg'),
('TSC', 'The Sunless Citadel', 1, true, 'https://www.dndbeyond.com/avatars/10449/402/637248674372576676.jpeg'),
('TFoF', 'The Forge of Fury', 1, true, 'https://www.dndbeyond.com/avatars/10436/4/637248156999902689.jpeg'),
('THSoT', 'The Hidden Shrine of Tamoachan', 1, true, 'https://www.dndbeyond.com/avatars/10449/236/637248657347161458.jpeg'),
('WPM', 'White Plume Mountain', 1, true, 'https://www.dndbeyond.com/avatars/10449/751/637248705560259195.jpeg'),
('DiT', 'Dead in Thay', 1, true, 'https://www.dndbeyond.com/avatars/10434/246/637248079254127234.jpeg'),
('AtG', 'Against the Giants', 1, true, 'https://www.dndbeyond.com/avatars/10433/315/637248029897296032.jpeg'),
('ToH', 'Tomb of Horrors', 1, true, 'https://www.dndbeyond.com/avatars/10449/371/637248671854035769.jpeg'),
('ToA', 'Tomb of Annihilation', 1, true, 'https://www.dndbeyond.com/avatars/10449/339/637248669136195626.jpeg'),
('CoSCO', 'Curse of Strahd: Character Options', 1, true, 'https://www.dndbeyond.com/avatars/10349/289/637244603748885696.jpeg'),
('XGtE', 'Xanathar''s Guide to Everything', 1, true, 'https://www.dndbeyond.com/avatars/10449/803/637248709455777906.jpeg'),
('TTP', 'The Tortle Package', 1, true, 'https://www.dndbeyond.com/avatars/39/300/636411199124473334.png'),
('UA', 'Unearthed Arcana', 1, true, 'https://www.dndbeyond.com/avatars/100/464/636506973225556542.png'),
('CR', 'Critical Role', 1, true, 'https://www.dndbeyond.com/avatars/'),
('MToF', 'Mordenkainen’s Tome of Foes', 1, true, 'https://www.dndbeyond.com/avatars/10434/949/637248111148617766.jpeg'),
('DDIA-MORD', 'Rrakkma', 1, true, 'https://www.dndbeyond.com/avatars/319/345/636622116959280867.jpeg'),
('WDH', 'Waterdeep: Dragon Heist', 1, true, 'https://www.dndbeyond.com/avatars/343/499/636632335939805190.jpeg'),
('WDotMM', 'Waterdeep: Dungeon of the Mad Mage', 1, true, 'https://www.dndbeyond.com/avatars/10449/493/637248684031810278.jpeg'),
('WGtE', 'Wayfinder''s Guide to Eberron', 1, true, 'https://www.dndbeyond.com/avatars/10449/715/637248702538222765.jpeg'),
('GGtR', 'Guildmasters'' Guide to Ravnica', 1, true, 'https://www.dndbeyond.com/avatars/10369/823/637245482341163840.jpeg'),
('LLoK', 'Lost Laboratory of Kwalish', 1, true, 'https://www.dndbeyond.com/avatars/10434/498/637248091075319276.jpeg'),
('DoIP', 'Dragon of Icespire Peak', 1, true, 'https://www.dndbeyond.com/avatars/10350/957/637244676648122088.jpeg'),
('TMR', 'Tactical Maps Reincarnated', 1, true, 'https://www.dndbeyond.com/avatars/5336/630/636850745475942698.jpeg'),
('GoS', 'Ghosts of Saltmarsh', 1, true, 'https://www.dndbeyond.com/avatars/10370/66/637245493047936420.jpeg'),
('AI', 'Acquisitions Incorporated', 1, true, 'https://www.dndbeyond.com/avatars/10350/905/637244674570907870.jpeg'),
('HftT', 'Hunt for the Thessalhydra', 1, true, 'https://www.dndbeyond.com/avatars/10432/12/637247932786703735.jpeg'),
('BGDiA', 'Baldur''s Gate: Descent into Avernus', 1, true, 'https://www.dndbeyond.com/avatars/10350/927/637244675832719441.jpeg'),
('ERftLW', 'Eberron: Rising from the Last War', 1, true, 'https://www.dndbeyond.com/avatars/10368/6/637245381196842264.jpeg'),
('SLW', 'Storm Lord’s Wrath', 1, true, 'https://www.dndbeyond.com/avatars/10350/964/637244676927254855.jpeg'),
('SDW', 'Sleeping Dragon’s Wake', 1, true, 'https://www.dndbeyond.com/avatars/10350/959/637244676820916158.jpeg'),
('DC', 'Divine Contention', 1, true, 'https://www.dndbeyond.com/avatars/10350/951/637244676535367295.jpeg'),
('SAC', 'Sage Advice Compendium', 1, true, 'https://www.dndbeyond.com/avatars/10435/702/637248145947271474.jpeg'),
('DDvRaM', 'Dungeons &amp; Dragons vs. Rick and Morty', 1, true, 'https://www.dndbeyond.com/avatars/10367/229/637245316031917098.jpeg'),
('LR', 'Locathah Rising', 1, true, 'https://www.dndbeyond.com/avatars/10434/650/637248098360957592.jpeg'),
('IMR', 'Infernal Machine Rebuild', 1, true, 'https://www.dndbeyond.com/avatars/10434/395/637248086063224834.jpeg'),
('MFFV1', 'Mordenkainen''s Fiendish Folio Volume 1', 1, true, 'https://www.dndbeyond.com/avatars/10434/743/637248102793792401.jpeg'),
('SD', 'Sapphire Dragon', 1, true, 'https://www.dndbeyond.com/avatars/10435/899/637248153278056972.jpeg'),
('EGtW', 'Explorer''s Guide to Wildemount', 1, true, 'https://www.dndbeyond.com/avatars/10367/769/637245363413951140.jpeg'),
('OGA', 'One Grung Above', 1, true, 'https://www.dndbeyond.com/avatars/10435/68/637248116464990081.jpeg'),
('MOoT', 'Mythic Odysseys of Theros', 1, true, 'https://www.dndbeyond.com/avatars/10434/885/637248108609488365.jpeg'),
('WA', 'Frozen Sick', 1, true, 'https://www.dndbeyond.com/avatars/9193/755/637200909525723425.jpeg'),
('IDRotF', 'Icewind Dale: Rime of the Frostmaiden', 1, true, 'https://www.dndbeyond.com/avatars/11095/550/637278965847502335.jpeg'),
('TCoE', 'Tasha’s Cauldron of Everything', 1, true, 'https://www.dndbeyond.com/avatars/13665/613/637400361423035085.jpeg'),
('CM', 'Candlekeep Mysteries', 1, true, 'https://www.dndbeyond.com/avatars/14917/783/637456355214291364.jpeg'),
('VRGtR', 'Van Richten’s Guide to Ravenloft', 1, true, 'https://www.dndbeyond.com/avatars/15973/81/637496917952314322.jpeg'),
('TWBtW', 'The Wild Beyond the Witchlight', 1, true, 'https://www.dndbeyond.com/avatars/18223/997/637587419509160992.jpeg'),
('SACoC', 'Strixhaven: A Curriculum of Chaos', 1, true, 'https://www.dndbeyond.com/avatars/18228/52/637587668398315568.jpeg'),
('FToD', 'Fizban''s Treasury of Dragons', 1, true, 'https://www.dndbeyond.com/avatars/19075/983/637620380256293999.jpeg'),
('CotN', 'Critical Role: Call of the Netherdeep', 1, true, 'https://www.dndbeyond.com/avatars/20906/943/637695655261542821.jpeg'),
('MotM', 'Mordenkainen Presents: Monsters of the Multiverse', 1, true, 'https://www.dndbeyond.com/avatars/22937/354/637776964748720726.jpeg'),
('JttRC', 'Journeys through the Radiant Citadel', 1, true, 'https://www.dndbeyond.com/avatars/24454/511/637830510509865265.jpeg'),
('MCv1', 'Monstrous Compendium Volume One: Spelljammer Creatures', 1, true, 'https://www.dndbeyond.com/avatars/25098/972/637854763136224645.jpeg'),
('SAiS', 'Spelljammer: Adventures in Space', 1, true, 'https://www.dndbeyond.com/avatars/25228/876/637859890823057854.jpeg'),
('TVD', 'The Vecna Dossier', 1, true, 'https://www.dndbeyond.com/avatars/26305/340/637901114717317528.jpeg'),
('TRC', 'The Radiant Citadel', 1, true, 'https://www.dndbeyond.com/avatars/26479/568/637907273106559243.jpeg'),
('SJA', 'Spelljammer Academy', 1, true, 'https://www.dndbeyond.com/avatars/26848/192/637920417931102595.jpeg'),
('DoSI', 'Dragons of Stormwreck Isle', 1, true, 'https://www.dndbeyond.com/avatars/26865/226/637921086362458107.jpeg'),
('SotDQ', 'Dragonlance: Shadow of the Dragon Queen', 1, true, 'https://www.dndbeyond.com/avatars/27777/666/637951679601337771.jpeg'),
('One-DnD', 'One D&D Playtest', 1, true, 'https://www.dndbeyond.com/avatars/28133/674/637963577590732420.jpeg'),
('MCv2', 'Monstrous Compendium Volume Two: Dragonlance Creatures', 1, true, 'https://www.dndbeyond.com/avatars/30591/814/638054153540284547.jpeg');
