
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
(8, 'Chair Alexis with armrests', 'FGrtyu', 'Solid wood / Beech, Orino 24, Stryi (Ukraine) ', 18000, 2 );



insert into category(id, category_name, slug )
values (1, 'Beds', 'qwerDF'),
(2, 'Chairs', 'UIbnm');

