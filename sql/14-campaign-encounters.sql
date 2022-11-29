
INSERT INTO encounters (id, name, campaign_id, location_id, suggested_apl, created_by)
VALUES
(1, 'Goblin Ambush', 1, 1, 1, 1),
(2, 'Goblin Blind', 1, 2, 1, 1),
(3, 'Kennel', 1, 2, 1, 1),
(4, 'Overpass', 1, 2, 1, 1),
(5, 'Goblin Den', 1, 2, 1, 1),
(6, 'Twin Pools Cave', 1, 2, 1, 1),
(7, 'Klargs Cave', 1, 2, 1, 1),
(8, 'Confrontation', 1, 3, 2, 1),
(9, 'Barracks', 1, 5, 2, 1),
(10, 'Tresendar Crypts', 1, 5, 2, 1),
(11, 'Slave Pens', 1, 5, 2, 1),
(12, 'Crevasse', 1, 5, 2, 1),
(13, 'Guard Barracks', 1, 5, 2, 1),
(14, 'Common Room', 1, 5, 2, 1),
(15, 'Glasstaffs Quarters', 1, 5, 2, 1),
(16, 'Old Owl Well', 1, 6, NULL, 1);

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
