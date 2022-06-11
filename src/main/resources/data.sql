INSERT INTO car(id, plate, brand, power, player_id) values (DEFAULT,'W1 ESLEY','Lambo',640, 1);
INSERT INTO car(id, plate, brand, power, player_id) values (2,'W0 ESLEY','BMW',500, 2);


DELETE FROM car;

SELECT * FROM car;

INSERT INTO player(id, nickname, years_old) VALUES (DEFAULT, 'Patryk', 22);
INSERT INTO player(id, nickname, years_old) VALUES (DEFAULT, 'Adam', 32);
INSERT INTO player(id, nickname, years_old) VALUES (DEFAULT, 'Monika', 24);
INSERT INTO player(id, nickname, years_old) VALUES (DEFAULT, 'Ewelina', 29);

SELECT * FROM player;