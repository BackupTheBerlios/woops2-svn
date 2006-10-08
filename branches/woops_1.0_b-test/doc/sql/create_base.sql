DROP DATABASE woops;
CREATE DATABASE woops;
USE woops;

GRANT ALL PRIVILEGES ON *.* TO 'woops'@'localhost' IDENTIFIED BY 'woops' WITH GRANT OPTION;


CREATE TABLE BreakdownElementKind (
       id INT NOT NULL AUTO_INCREMENT
     , name VARCHAR(15) NOT NULL
     , UNIQUE UQ_ActivitySequenceType_name (name)
     , PRIMARY KEY (id)
)ENGINE=InnoDB;

CREATE TABLE BreakdownElement (
       id INT NOT NULL AUTO_INCREMENT
     , prefix VARCHAR(15) NOT NULL
     , name VARCHAR(50) NOT NULL
     , details TEXT
     , startDate DATETIME
     , endDate DATETIME
     , kind INT NOT NULL
     , UNIQUE UQ_BreakdownElement_name (name)
     , PRIMARY KEY (id)
     , CONSTRAINT FK_BreakdownElement_kind FOREIGN KEY (kind)
     			  REFERENCES BreakdownElementKind (id)
)ENGINE=InnoDB;


CREATE TABLE UserRole (
       id INT NOT NULL AUTO_INCREMENT
     , code VARCHAR(10) NOT NULL
     , PRIMARY KEY (id)
)ENGINE=InnoDB;

CREATE TABLE User (
       id INT NOT NULL AUTO_INCREMENT
     , firstName VARCHAR(50) NOT NULL
     , lastName VARCHAR(50) NOT NULL
     , login VARCHAR(20) NOT NULL
     , password VARCHAR(10) NOT NULL
     , role INT NOT NULL
     , defaultBDE INT
     , UNIQUE UQ_User_login (login)
     , PRIMARY KEY (id)
     , CONSTRAINT FK_User_role FOREIGN KEY (role)
                  REFERENCES UserRole (id)
     , CONSTRAINT FK_User_defaultBDE FOREIGN KEY (defaultBDE)
                  REFERENCES BreakdownElement (id)
)ENGINE=InnoDB;

CREATE TABLE UserBDE (
       id INT NOT NULL AUTO_INCREMENT
     , bde INT NOT NULL
     , user INT NOT NULL
     , UNIQUE UQ_UserBDE_bde_user (bde,user)
     , PRIMARY KEY (id)
     , CONSTRAINT FK_UserBDE_bde FOREIGN KEY (bde)
     			  REFERENCES BreakdownElement(id)
     , CONSTRAINT FK_UserBDE_user FOREIGN KEY (user)
     			  REFERENCES User(id)
)ENGINE=InnoDB;

CREATE TABLE ActivitySequenceType (
       id INT NOT NULL AUTO_INCREMENT
     , name VARCHAR(15) NOT NULL
     , UNIQUE UQ_ActivitySequenceType_name (name)
     , PRIMARY KEY (id)
)ENGINE=InnoDB;

CREATE TABLE ActivityState (
       name VARCHAR(50) NOT NULL
     , progress INT DEFAULT 0
     , PRIMARY KEY (name)
)ENGINE=InnoDB;

CREATE TABLE Activity (
       id INT NOT NULL AUTO_INCREMENT
     , name VARCHAR(50) NOT NULL
     , details TEXT
     , startDate DATETIME
     , endDate DATETIME
     , user INT
     , state VARCHAR(50) NOT NULL
     , bde INT NOT NULL
     , ongoing VARCHAR(3)
     , datecreation DATE
     , dateupdate DATE
     , usercreation INT
     , userupdate INT
     , PRIMARY KEY (id)
     , INDEX (user)
     , UNIQUE UQ_Activity_bde_name (bde, name)
     , CONSTRAINT FK_Activity_user FOREIGN KEY (user)
                  REFERENCES User (id)
     , CONSTRAINT FK_Activity_state FOREIGN KEY (state)
                  REFERENCES ActivityState (name)
     , CONSTRAINT FK_Activity_bde FOREIGN KEY (bde)
                  REFERENCES BreakdownElement (id)
)ENGINE=InnoDB;


CREATE TABLE ActivitySequence (
       id INT NOT NULL AUTO_INCREMENT
     , successor INT NOT NULL
     , predecessor INT NOT NULL
     , linkType INT NOT NULL
     , datecreation DATE
     , dateupdate DATE
     , usercreation INT
     , userupdate INT
     , UNIQUE UQ_ActivitySequence_successor_predecessor (successor, predecessor)
     , PRIMARY KEY (id)
     , INDEX (linkType)
     , CONSTRAINT FK_ActivitySequence_linkType FOREIGN KEY (linkType)
                  REFERENCES ActivitySequenceType (id)
     , INDEX (successor)
     , CONSTRAINT FK_ActivitySequence_successor FOREIGN KEY (successor)
                  REFERENCES Activity (id)
     , INDEX (predecessor)
     , CONSTRAINT FK_ActivitySequence_predecessor FOREIGN KEY (predecessor)
                  REFERENCES Activity (id)
)ENGINE=InnoDB;


CREATE TABLE Event (
       id INT NOT NULL AUTO_INCREMENT
     , name VARCHAR(50) NOT NULL
     , details TEXT
     , activity INT NOT NULL
     , occured VARCHAR(3) DEFAULT 'non'
     , bde INT NOT NULL
     , datecreation DATE
     , dateupdate DATE
     , usercreation INT
     , userupdate INT
     , UNIQUE UQ_Event_bde_name (bde,name)
     , PRIMARY KEY (id)
     , CONSTRAINT FK_Event_activity FOREIGN KEY (activity)
                  REFERENCES Activity (id)
     , CONSTRAINT FK_Event_bde FOREIGN KEY (bde)
                  REFERENCES BreakdownElement (id)
)ENGINE=InnoDB;

-- Insertions intiales
INSERT INTO ActivitySequenceType(name) VALUES ('finishToStart');
INSERT INTO ActivitySequenceType(name) VALUES ('finishToFinish');
INSERT INTO ActivitySequenceType(name) VALUES ('startToStart');
INSERT INTO ActivitySequenceType(name) VALUES ('startToFinish');
INSERT INTO ActivityState(name) VALUES ('created');
INSERT INTO ActivityState(name) VALUES ('inProgress');
INSERT INTO ActivityState(name) VALUES ('finished');
INSERT INTO UserRole(code) VALUES ('admin');
INSERT INTO UserRole(code) VALUES ('dev');
INSERT INTO BreakdownElementKind ( `id` , `name` ) VALUES ('1', 'iteration');
INSERT INTO BreakdownElementKind ( `id` , `name` ) VALUES ('2', 'phase');

INSERT INTO `User` (`firstName`, `lastName`, `login`, `password`, `role` ) VALUES  ( 'root', 'root', 'woops', 'woops', '1' );