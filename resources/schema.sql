DROP TABLE IF EXISTS CAR;

CREATE TABLE car (
  id int(11) NOT NULL AUTO_INCREMENT,
  colour varchar(255) DEFAULT NULL,
  doors int(11) DEFAULT NULL,
  make varchar(255) DEFAULT NULL,
  model varchar(255) DEFAULT NULL,
  price double DEFAULT NULL,
  year int(11) DEFAULT NULL,
  PRIMARY KEY (id)
);