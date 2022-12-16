
INSERT INTO campaigns (name, dm_id, splashUrl, official)
SELECT 'Lost Mine of Phandelver',
        id
        'https://www.dndbeyond.com/attachments/2/730/lmopcover.jpg',
        '', '', '', true
FROM accounts where username='DonDaDM';


INSERT INTO locations (name, campaign_id)
SELECT 'Triboar Trail', id FROM campaigns c WHERE c.name ='Lost Mine of Phandelver';
INSERT INTO locations (name, campaign_id)
SELECT 'Cragmaw Hideout', id FROM campaigns c WHERE c.name ='Lost Mine of Phandelver';
INSERT INTO locations (name, campaign_id)
SELECT 'Phandalin', id FROM campaigns c WHERE c.name ='Lost Mine of Phandelver';
INSERT INTO locations (name, campaign_id)
SELECT 'Sleeping Giant', id FROM campaigns c WHERE c.name ='Lost Mine of Phandelver';
INSERT INTO locations (name, campaign_id)
SELECT 'Redbrand Hideout', id FROM campaigns c WHERE c.name ='Lost Mine of Phandelver';
INSERT INTO locations (name, campaign_id)
SELECT 'Old Owl Well', id FROM campaigns c WHERE c.name ='Lost Mine of Phandelver';
INSERT INTO locations (name, campaign_id)
SELECT 'Thundertree', id FROM campaigns c WHERE c.name ='Lost Mine of Phandelver';
INSERT INTO locations (name, campaign_id)
SELECT 'Wyvern Tor', id FROM campaigns c WHERE c.name ='Lost Mine of Phandelver';
INSERT INTO locations (name, campaign_id)
SELECT 'Cragmaw Castle', id FROM campaigns c WHERE c.name ='Lost Mine of Phandelver';
INSERT INTO locations (name, campaign_id)
SELECT 'Wave Echo Cave', id FROM campaigns c WHERE c.name ='Lost Mine of Phandelver';
