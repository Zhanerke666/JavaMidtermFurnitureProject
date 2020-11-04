
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
(8, 'Chair Alexis with armrests', 'FGrtyu', 'Solid wood / Beech, Orino 24, Stryi (Ukraine) ', 18000, 2 )
(9, 'The Influence Sofa', 'seTg', 'Crushed Micro-Chenille Bordeaux (Britain)' 60000, 4),
(10, 'The Pragmatist Sofa', 'seTnym', 'Crushed Micro-Chenille Bordeaux (Britain)' 75000, 4),
(11, 'The Philosopher Sofa', 'Dneim', 'Crushed Micro-Chenille Bordeaux (Britain)' 55000, 4)
(12, 'Idealistic Sofa', 'idol', 'Crushed Micro-Chenille Bordeaux (Germany)' 68000, 4);



insert into category(id, category_name, slug )
values (1, 'Beds', 'qwerDF'),
(2, 'Chairs', 'UIbnm'),
(3,'Tables','tables'),
(4,'Sofas', 'sofas');

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

