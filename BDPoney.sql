-- CREATE DATABASE IF NOT EXISTS GRANDGALOP DEFAULT CHARACTER SET utf8
-- COLLATE utf8_general_ci ;
-- USE GRANDGALOP ;

-- suppression des tables de la BD GRANDGALOP

drop table reserver;
drop table cours;
drop table type_c;
drop table moniteur;
drop table client;
drop table poney;

-- création des tables de la BD GRANDGALOP

CREATE TABLE client (
  idClient INT,
  nomC VARCHAR(42),
  prenomC VARCHAR(42),
  poids DECIMAL(6, 2),
  cotisation boolean,
  username VARCHAR(42) unique,
  mdp VARCHAR(42),
  PRIMARY KEY (idClient)
) ;

CREATE TABLE poney (
  idP INT,
  nomP VARCHAR(42),
  poidsMax DECIMAL(6, 2),
  PRIMARY KEY (idP)
) ;

CREATE TABLE moniteur (
  idM INT,
  nomM VARCHAR(42),
  prenomM VARCHAR(42),
  ageM INT,
  PRIMARY KEY (idM)
) ;

CREATE TABLE cours (
  idCours INT,
  type_cours VARCHAR(42),
  prix DECIMAL(6,2),
  nbPersonnes INT CHECK (nbPersonnes <= 10),
  idType INT,
  idM INT,
  duree INT CHECK (duree <= 2),
  jma DATE,
  heure INT,
  PRIMARY KEY (idCours)
) ;

CREATE TABLE type_c (
  idType INT,
  intituleType VARCHAR(80),
  PRIMARY KEY(idType)
) ;

CREATE TABLE reserver(
    idCours INT,
    idClient INT,
    idP INT,
    PRIMARY KEY(idCours,idClient,idP)
) ;

-- creation des clés etrangères de la BD GRANDGALOP

ALTER TABLE cours ADD FOREIGN KEY (idType) REFERENCES type_c (idType);
ALTER TABLE reserver ADD FOREIGN KEY (idCours) REFERENCES cours (idCours);
ALTER TABLE reserver ADD FOREIGN KEY (idClient) REFERENCES client (idClient);
ALTER TABLE reserver ADD FOREIGN KEY (idP) REFERENCES poney (idP);
ALTER TABLE cours ADD FOREIGN KEY (idM) REFERENCES moniteur (idM);

INSERT INTO client VALUES(1,"Dupont","Jean",50.0,false, "user1", "mdp1");
INSERT INTO client VALUES(2,"Jeffrey" ,"Chapman",90.5,true, "user2", "mdp2");
INSERT INTO client VALUES(3,"Michael" ,"Webb",40.6,true, "user3", "mdp3");
INSERT INTO client VALUES(4,"Holly" ,"Russell",30.1,false, "user4", "mdp4");
INSERT INTO client VALUES(5,"Joshua" ,"Gutierrez",45.8,true, "user5", "mdp5");
INSERT INTO client VALUES(6,"Thomas" ,"Dubois",22.4,true, "user6", "mdp6");
INSERT INTO client VALUES(7,"Jhonny" ,"Sins",31.2,true, "user7", "mdp7");
INSERT INTO client VALUES(8,"Brigitte","Marcon",49.3,false, "user8", "mdp8");
INSERT INTO client VALUES(9,"Sophie" ,"Anglade",44.0,false, "user9", "mdp9");
INSERT INTO client VALUES(10,"Thimothée" ,"Jolly",26.6,true, "user10", "mdp10");
INSERT INTO client VALUES(11,"Jacob" ,"Forestier",15.5,true, "user11", "mdp11");
INSERT INTO client VALUES(12,"Thierry" ,"Marmite",30.5,true, "user12", "mdp12");
INSERT INTO client VALUES(13,"Gérard" ,"Impardeux",100.0,false, "user14", "mdp13");
INSERT INTO client VALUES(14,"Zac" ,"Zaun",51.0,true, "user15", "mdp16");
INSERT INTO client VALUES(15,"Léo" ,"Nah",42.2,true, "user17", "mdp17");
INSERT INTO client VALUES(16,"Diana" ,"Lunaris",34.6,false, "user18", "mdp18");
INSERT INTO client VALUES(17,"Aimer" ,"Dinger",16.6,true, "user19", "mdp19");
INSERT INTO client VALUES(18,"René","Kton",36.6,true, "user20", "mdp20");
INSERT INTO client VALUES(19,"Théo" ,"File",21.2,false, "user21", "mdp21");
INSERT INTO client VALUES(20,"Michelle" ,"Michel",44.9,true, "user22", "mdp22");
INSERT INTO client VALUES(21,"Cyprien" ,"Tourette",46.7,false, "user23", "mdp23");
INSERT INTO client VALUES(22,"Michel" ,"Tourniret",70.1,true, "user24", "mdp24");
INSERT INTO client VALUES(23,"Emil" ,"Larsson",23,true, "user25", "mdp25");
INSERT INTO client VALUES(24,"Vincent" ,"Berrié",32,true, "user26", "mdp26");
INSERT INTO client VALUES(25,"Joel" ,"Miro",41,false, "user27", "mdp27");
INSERT INTO client VALUES(26,"Andrei" ,"Pascu",12,true, "user28", "mdp28");
INSERT INTO client VALUES(27,"Adrian" ,"Trybus",22,true, "user29", "mdp29");
INSERT INTO client VALUES(28,"Andrei" ,"Dragomir",34,false, "user30", "mdp30");
INSERT INTO client VALUES(29,"Patrik" ,"Jiru",16,true, "user31", "mdp31");
INSERT INTO client VALUES(30,"Louis" ,"Schmitz",24,true, "user32", "mdp32");
INSERT INTO client VALUES(31,"Elias" ,"Lipp",41,false, "user33", "mdp33");
INSERT INTO client VALUES(32,"Raphaël" ,"Crabbé",21,true, "user34", "mdp34");
INSERT INTO client VALUES(33,"Victor" ,"Lirola",32,true, "user35", "mdp35");
INSERT INTO client VALUES(34,"Javier" ,"Prades",34,true, "user36", "mdp36");
INSERT INTO client VALUES(35,"Yasin" ,"Dinçer",26,true, "user37", "mdp37");
INSERT INTO client VALUES(36,"Norman" ,"Kaiser",44,true, "user38", "mdp38");
INSERT INTO client VALUES(37,"William" ,"Nieminen",13,true, "user39", "mdp39");
INSERT INTO client VALUES(38,"Jean" ,"Massol",36,true, "user40", "mdp40");
INSERT INTO client VALUES(39,"Daniel" ,"Gamani",21,false, "user41", "mdp41");
INSERT INTO client VALUES(40,"Erik" ,"Wessén",24,true, "user42", "mdp42");
INSERT INTO client VALUES(41,"Thomas" ,"Huber",21,true, "user43", "mdp43");
INSERT INTO client VALUES(42,"Matthew" ,"Charles",46,true, "user44", "mdp44");
INSERT INTO client VALUES(43,"Illias" ,"Bizriken",21,true, "user45", "mdp45");
INSERT INTO client VALUES(44,"Tobiasz" ,"Ciba",35,true, "user46", "mdp46");
INSERT INTO client VALUES(45,"Barney" ,"Morris",36,true, "user47", "mdp47");
INSERT INTO client VALUES(46,"Luka" ,"Perkovic",42,true, "user48", "mdp48");
INSERT INTO client VALUES(47,"Matyas" ,"Orsag",26,true, "user49", "mdp49");
INSERT INTO client VALUES(48,"Peter" ,"Freyschuss",14,true, "user50", "mdp50");
INSERT INTO client VALUES(49,"Oskar" ,"Boderek",27,true, "user51", "mdp51");
INSERT INTO client VALUES(50,"Duncan" ,"Marquet",31,true, "user52", "mdp52");


INSERT INTO poney VALUES(1,"Eclair",45.0);
INSERT INTO poney VALUES(2,"Noisette",50.5);
INSERT INTO poney VALUES(3,"Kiwi",35.0);
INSERT INTO poney VALUES(4,"Celestia",40.6);
INSERT INTO poney VALUES(5,"Luna",29.1);
INSERT INTO poney VALUES(6,"Chupa",41.5);
INSERT INTO poney VALUES(7,"Choops",27.4);
INSERT INTO poney VALUES(8,"Cheval",39.0);
INSERT INTO poney VALUES(9,"Karim",45.0);
INSERT INTO poney VALUES(10,"Haribo",36.7);
INSERT INTO poney VALUES(11,"Réglisse",31.5);
INSERT INTO poney VALUES(12,"Tagada",27.8);
INSERT INTO poney VALUES(13,"Krema",38.7);
INSERT INTO poney VALUES(14,"Spirit",46.9);
INSERT INTO poney VALUES(15,"Noireaude",33.3);
INSERT INTO poney VALUES(16,"Bloudy",31.1);
INSERT INTO poney VALUES(17,"Rick",51.0);
INSERT INTO poney VALUES(18,"Arde",51.0);
INSERT INTO poney VALUES(19,"Ruby",55.5);
INSERT INTO poney VALUES(20,"Volta",44.4);
INSERT INTO poney VALUES(21,"Ticky",46.8);
INSERT INTO poney VALUES(22,"Keto",29.7);
INSERT INTO poney VALUES(23,"Lucifer",66.6);
INSERT INTO poney VALUES(24,"Noxus",45.0);
INSERT INTO poney VALUES(25,"Ferrari",35.1);
INSERT INTO poney VALUES(26,"Mimi",27.7);
INSERT INTO poney VALUES(27,"Macaron",49.3);
INSERT INTO poney VALUES(28,"Merry",41.4);
INSERT INTO poney VALUES(29,"Buffy",56.7);
INSERT INTO poney VALUES(30,"Ryuk",65.0);



INSERT INTO moniteur VALUES(1,"Christopher" ,"Stark",20);
INSERT INTO moniteur VALUES(2,"Kelly" ,"Mueller",19);
INSERT INTO moniteur VALUES(3,"David" ,"Jones",36);
INSERT INTO moniteur VALUES(4,"William" ,"Rogers",24);
INSERT INTO moniteur VALUES(5,"Allison" ,"Kim",29);



INSERT INTO type_c VALUES(0,"Particulier");
INSERT INTO type_c VALUES(1,"Collectif");

INSERT INTO cours VALUES(1,"Pleine air",12,2,1,1,1,STR_TO_DATE("August 10 2017", "%M %d %Y"),14);
INSERT INTO cours VALUES(2,"Course",10,1,0,1,2,STR_TO_DATE("August 20 2017", "%M %d %Y"),9);

INSERT INTO reserver VALUES(1, 3, 1);
INSERT INTO reserver VALUES(1, 4, 4);

