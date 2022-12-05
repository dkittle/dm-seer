
INSERT INTO encounters (name, campaign_id, location_id, suggested_acl, created_by)
VALUES
('Goblin Ambush', 1, 1, 1, 1),
('Goblin Blind', 1, 2, 1, 1),
('Kennel', 1, 2, 1, 1),
('Overpass', 1, 2, 1, 1),
('Goblin Den', 1, 2, 1, 1),
('Twin Pools Cave', 1, 2, 1, 1),
('Klargs Cave', 1, 2, 1, 1),
('Confrontation', 1, 3, 2, 1),
('Barracks', 1, 5, 2, 1),
('Tresendar Crypts', 1, 5, 2, 1),
('Slave Pens', 1, 5, 2, 1),
('Crevasse', 1, 5, 2, 1),
('Guard Barracks', 1, 5, 2, 1),
('Common Room', 1, 5, 2, 1),
('Glasstaffs Quarters', 1, 5, 2, 1),
('Old Owl Well', 1, 6, NULL, 1);

INSERT INTO encounter_creatures (encounter_id, creature_id, creature_numbers)
VALUES
(1, 1, 4),
(2, 1, 2),
(3, 3, 3),
(4, 1, 1),
(5, 1, 6),
(5, 4, 1),
(6, 1, 3),
(7, 5, 1),
(7, 3, 1),
(7, 1, 2),
(8, 6, 4),
(9, 6, 3),
(10, 7, 3),
(11, 6, 2),
(12, 8, 1),
(13, 5, 3),
(13, 1, 1),
(14, 6, 4),
(15, 9, 1),
(16, 10, 12),
(16, 9, 1);
