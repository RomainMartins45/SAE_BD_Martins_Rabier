-- CREATE DATABASE IF NOT EXISTS GRANDGALOP DEFAULT CHARACTER SET utf8
-- COLLATE utf8_general_ci ;
-- USE GRANDGALOP ;

-- supression des tables de la BD GRANDGALOP

drop table RESERVER;
drop table Cours;
drop table TypeC;
drop table Moniteur;
drop table Client;
drop table Poney;

-- création des tables de la BD GRANDGALOP

CREATE TABLE Client (
  idClient INT(8),
  nomC VARCHAR(42),
  prenomC VARCHAR(42),
  poids DECIMAL(6, 2),
  cotisation boolean,
  PRIMARY KEY (idClient)
) ;

CREATE TABLE Poney (
  idP INT(8),
  nomP VARCHAR(42),
  poidsMax DECIMAL(6, 2),
  PRIMARY KEY (idP)
) ;

CREATE TABLE Moniteur (
  idM INT(8),
  nomM VARCHAR(42),
  prenomM VARCHAR(42),
  ageM INT,
  PRIMARY KEY (idM)
) ;

CREATE TABLE Cours (
  idCours INT(8),
  typeCours VARCHAR(42),
  prix DECIMAL(6,2),
  nbPersonnes INT(2) CHECK (nbPersonnes <= 10),
  idType INT(2),
  idM INT(8),
  duree INT(2) CHECK (duree <= 2),
  jma DATE,
  heure INT(2),
  PRIMARY KEY (idCours)
) ;

CREATE TABLE TypeC (
  idType INT(2),
  intituleType VARCHAR(42),
  PRIMARY KEY(idType)
) ;

CREATE TABLE RESERVER(
    idCours INT(8),
    idClient INT(8),
    idP INT(8),
    PRIMARY KEY(idCours,idClient,idP)
) ;

-- creation des clés etrangères de la BD GRANDGALOP

ALTER TABLE Cours ADD FOREIGN KEY (idType) REFERENCES TypeC (idType);
ALTER TABLE RESERVER ADD FOREIGN KEY (idCours) REFERENCES Cours (idCours);
ALTER TABLE RESERVER ADD FOREIGN KEY (idClient) REFERENCES Client (idClient);
ALTER TABLE RESERVER ADD FOREIGN KEY (idP) REFERENCES Poney (idP);
ALTER TABLE Cours ADD FOREIGN KEY (idM) REFERENCES Moniteur (idM);
