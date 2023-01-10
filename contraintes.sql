-- Pour être reserver, le poids d'un client doit être inférieur au poidsMax d'un poney

delimiter |
create or replace trigger ReserverBefore before insert on reserver for each row
begin
    declare poidsPoney DECIMAL(6,2);
    declare poidsClient DECIMAL(6,2);
    declare mes varchar(100);
    select poidsMax into poidsPoney from Poney where new.idP = idP;
    select poids into poidsClient from Client where new.idClient = idClient;
    if poidsClient > poidsPoney then
        set mes = 'Le poids du client est trop élevé pour ce poney';
        signal SQLSTATE '45000' set MESSAGE_TEXT = mes;
    end if;
    -- fin PoidsMaxPoney
    declare aPaye boolean;
    select cotisation into aPaye from Client where new.idClient = idClient;
    if aPaye = false then
        set mes = "Le client n'a pas payé sa cotisation, il doit la payer avant de pouvoir réserver un cours";
        signal SQLSTATE '45000' set MESSAGE_TEXT = mes;
    end if;
    declare nbPersMax int;
    declare nbPers int;
    select nbPersonnes into nbPersMax from Cours where new.idCours = idCours;
    select count(idClient) into nbPers from reserver where new.idCours = idCours;
    if nbPersMax <= nbPers then
        set mes = "Le nombre de personnes maximum à ce cours à déjà été atteint";
        signal SQLSTATE '45000' set MESSAGE_TEXT = mes;
    end if;
end |
delimiter ; 
drop trigger PoidsMaxPoney;
-- insert into reserver VALUES(2,1,1,4,1,'2022-09-11',12);


-- Si un client n'a pas payé la cotisation, il ne peut pas réserver

delimiter |
create or replace trigger PayeCotisation before insert on reserver for each row
begin
    declare aPaye boolean;
    declare mes varchar(100);
    select cotisation into aPaye from Client where new.idClient = idClient;
    if aPaye = false then
        set mes = "Le client n'a pas payé sa cotisation, il doit la payer avant de pouvoir réserver un cours";
        signal SQLSTATE '45000' set MESSAGE_TEXT = mes;
    end if;
end |
delimiter ; 
-- insert into reserver VALUES(1,4,4,4,1,'2022-09-11',12);
drop trigger PayeCotisation;



--  Si le cours est un cours particulier, nbPersonnes doit être égal à 1

delimiter |
create or replace trigger CoursParticulier before insert on Cours for each row
begin
    declare typeC VARCHAR(42);
    declare mes varchar(100);
    select intituleType into typeC from TypeC where new.idType = idType;
    if typeC = 'Particulier' and new.nbPersonnes != 1 then
        set mes = "Un cours particulier ne peut accueillir qu'une seule personne";
        signal SQLSTATE '45000' set MESSAGE_TEXT = mes;
    end if;
end |
delimiter ; 
-- insert into Cours VALUES(11,'Course',10.0,1,0);
drop trigger CoursParticulier;


-- Le nombre d'insertions dans reserver d'un cours ne doit pas etre plus grand que le nombre de personnes maximum

delimiter |
create or replace trigger CoursCollectifNbPersonnes before insert on reserver for each row
begin  
    declare mes varchar (100);
    declare nbPersMax int;
    declare nbPers int;
    select nbPersonnes into nbPersMax from Cours where new.idCours = idCours;
    select count(idClient) into nbPers from reserver where new.idCours = idCours;
    if nbPersMax <= nbPers then
        set mes = "Le nombre de personnes maximum à ce cours à déjà été atteint";
        signal SQLSTATE '45000' set MESSAGE_TEXT = mes;
    end if;
end |
delimiter ;

-- INSERT INTO reserver VALUES(1,3,3,1,'2022-09-11',14);
-- INSERT INTO reserver VALUES(1,5,2,1,'2022-09-11',14);
-- INSERT INTO reserver VALUES(1,2,5,1,'2022-09-11',14);
drop trigger CoursCollectifNbPersonnes;

-- Si le poney a eu 2h de cours , il doit avoir une heure de repos

delimiter |
create or replace trigger PoneyHeureDeRepos before insert on reserver for each row
begin  
    declare mes varchar (100);
end |
delimiter ;









