
alter table product owner to postgres;
alter table category owner to postgres;



insert into product (id, name, slug, description, price, category_id) values
(1, 'Single bed Omega 18', 'WERbnm', 'Ash, Ankor light, Fant Mebel (Russia)', 45000, 1),
(2, 'Single bed', 'QWEsdmn', ' Oscar collection, Oak Sanremo, Anrex (Belarus) ', 84000, 1),
(3, 'Double bed', 'DFGytr', ' Vegas collection, White Gloss, Standmebel (Russia)', 87400, 1),
(4, 'Double bed','DFGHrtyg', 'Mila 764, Chocolate, SMK (Russia)', 74000, 1 ),
(5, 'Chair Venice', 'WERsdfg',  'Solid wood / Beech, Jasmine 26, Stryi (Ukraine)', 14000, 2),
(6, 'Chair Fink', 'tyuibnkI', 'Solid wood / Wenge, 713 beige, Stryi (Ukraine)', 20000, 2  ),
(7, 'Chair Rogzhen', 'RTYfgh', 'Solid wood / Beech, Ecosoft36, Stryi (Ukraine)', 15000, 2 ),
(8, 'Chair Alexis with armrests', 'FGrtyu', 'Solid wood / Beech, Orino 24, Stryi (Ukraine) ', 18000, 2 ),
(9, 'The Influence Sofa', 'seTg', 'Crushed Micro-Chenille Bordeaux (Britain)', 60000, 4),
(10, 'The Pragmatist Sofa', 'seTnym', 'Crushed Micro-Chenille Bordeaux (Britain)', 75000, 4),
(11, 'The Philosopher Sofa', 'Dneim', 'Crushed Micro-Chenille Bordeaux (Britain)', 55000, 4),
(12, 'Idealistic Sofa', 'idol', 'Crushed Micro-Chenille Bordeaux (Germany)', 68000, 4),
(13, 'Sofa Browny Pie', 'qeifh', 'Crushed Micro-Chenille Bordeaux colored into dark brown (Britain)', 80000, 4),
(14, 'The Wealth Sofa', 'gtrjs', 'Great House Collection (UK)', 64000, 4),
(15, 'House of Green Sofa', 'gtjcvs', 'Unusual Box Think Collection (USA)', 90000, 4),
(16, 'The Carson Sofa', 'juycvb', 'The Carson`s Bordeaux (Britain)', 70000, 4),
(17, 'Zahra Sofa', 'ftuze', 'Indian Styled Sofa (Canada)', 57000, 4),
(18, 'Single bed Giselle', 'Wgrj', 'Antique Graceful Bed (Russia)', 58000, 1),
(19, 'Single bed Aurora', 'WEhdrnm', 'Antique Grove Bed (Russia)', 36000, 1),
(20, 'Single bed Zhaneka', 'gtRbnm', 'The Best of Us Collection (Kazakhstan)', 100000, 1),
(21, 'Single bed Teskabro', 'Trasgb', 'The Best of Us Collection (Kazakhstan)', 100000, 1),
(22, 'King Size bed Alendelone', 'Logjt', 'The longest bromance Collection (Kazakhstan)', 90000, 1),
(23, 'Single bed AlemTheZhan', 'gshhc', 'Fashion and Power Collection (Kazakhstan)', 80000, 1),
(24, 'Chair Weither', 'fgtrbv',  'The King of House Collection (USA)', 15000, 2),
(25, 'Chair Takito', 'fvvsdg',  'Made of Sakura wood (Japan)', 20000, 2),
(26, 'Chair Lowrence', 'gyklc',  'Solid wood (USA)', 15000, 2),
(27, 'Chair Steaky', 'fbyg',  'Solid wood and iron (USA)', 25000, 2),
(28, 'Chair Murray', 'ghypp',  'Solid wood (USA)', 20000, 2),
(29, 'Chair Levine', 'gykkk',  'Peace Collection (Japan)', 30000, 2),
(30, 'Country Living Table', 'ftyj', 'Made of Glass (USA)', 56000, 3),
(31, 'Loft Table', 'fvvzxf', 'Made of Carbon (USA)', 75000, 3),
(32, 'Carrington Dining Table', 'fvgrezxf', 'Made of wood and glass (USA)', 69000, 3),
(33, 'Demon`s Eye Table', 'yukxf', 'Dead`s Eye Collection (USA)', 20000, 3),
(34, 'Fur Amoure Table', 'tmgriez', 'For two of us (Canada)', 60000, 3),
(35, 'Alternative View Table', 'freho', 'For your garden view (Canada)', 50000, 3),
(36, 'Treasure Table', 'fbbcvb', 'Bon Apart Collection (Russia)', 45000, 3),
(37, 'Rancho Table', 'frrasc', 'Thus in the woods Collection (USA)', 45000, 3),
(38, 'Partio Table', 'gbvcn', 'Gracio Mare Collection (Mexico)', 80000, 3),
(39, 'Aparter Table', 'astgd', 'Lack of People Collection (USA)', 46000, 3),
(40, 'Villa Dining Set', 'ggbvcr', 'For your apartment (USA)', 80000, 5),
(41, 'Studio Dining Set', 'ghfyrg', 'Per Favour Collection (USA)', 90000, 5),
(42, 'Yummy Dining Set', 'gtorqy', 'Lordly Set Collection (Canada)', 70000, 5),
(43, 'Vanilla Dining Set', 'gfbcr', 'For good nights Collection (Russia)', 80000, 5),
(44, 'Bellagio Dining Set', 'txhcvior', 'As in Vegas (USA)', 99000, 5),
(45, 'Gregorian Dining Set', 'bvcr', 'Frozen Set Collection (Spain)', 110000, 5),
(46, 'Cardiago Dining Set', 'gjnr', 'Diablo Collection (Spain)', 90000, 5),
(47, 'Umbrella Dining Set', 'tllkl', 'Good well apartment (USA)', 60000, 5),
(48, 'Tresar Dining Set', 'bgyjcr', 'L`amour (Mexico)', 80000, 5),
(49, 'Unergia Dining Set', 'klvcr', 'Clever Minded Collection (USA)', 60000, 5);



insert into category(id, category_name, slug )
values (1, 'Beds', 'qwerDF'),
(2, 'Chairs', 'UIbnm'),
(3,'Tables','tables'),
(4,'Sofas', 'sofas'),
(5, 'dining sets', 'ffsegc');

insert into customer values
(1,'zhandos','87075893900','Abai 121'),
(2,'alim','86969696969','Panfilova 15'),
(3, 'zhanerke', '87768695513', 'Glushko 4'),
(4, 'alan', '87777102021', 'Address'),
(5, 'john', '87474105465', 'Address1');
insert into auth values
(1,1,'zhandos','789z789z','zhandos789z789z'),
(2,2,'alim','Lima','alimLima'),
(3,3, 'zhanerke', '12345', 'erke'),
(4,4, 'alan', 'alan', 'alanushka'),
(5,5, 'zhandos', 'john', 'john');

