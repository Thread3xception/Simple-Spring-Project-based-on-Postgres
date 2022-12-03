INSERT INTO car(id, plate, brand, power, player_nickname) values (DEFAULT,'W1 ESLEY','Lambo',640, null);
INSERT INTO car(id, plate, brand, power, player_nickname) values (2,'X1 ESLEY','Mercedes', 381, null);
INSERT INTO car(id, plate, brand, power, player_nickname) values (3,'S2 YBKI','Seat', 200, null);
INSERT INTO car(id, plate, brand, power, player_nickname) values (4,'W5 zzzz','BMW', 550, null);



DELETE FROM car;
DELETE FROM player;

SELECT * FROM car;
SELECT * FROM player;

INSERT INTO player(id, nickname, years_old) VALUES (DEFAULT, 'Patryk', 22);
INSERT INTO player(id, nickname, years_old) VALUES (DEFAULT, 'Adam', 32);
INSERT INTO player(id, nickname, years_old) VALUES (DEFAULT, 'Monika', 24);
INSERT INTO player(id, nickname, years_old) VALUES (DEFAULT, 'Ewelina', 29);
