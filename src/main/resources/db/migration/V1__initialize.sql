CREATE DATABASE `samboss` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `network_objects` (
   `id` INT(11) NOT NULL AUTO_INCREMENT,
   `hostname` VARCHAR(50) DEFAULT '',
   `scan_result_id` INT(11),
   `os_id` INT(11),
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `scan` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `date` DATETIME,
    `network_object_id` INT(11) NOT NULL,
    PRIMARY KEY(`id`),
    CONSTRAINT FK_NET_OBJ_ID FOREIGN KEY(`network_object_id`)
    REFERENCES network_objects(id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ports` (
     `id` INT (11) NOT NULL AUTO_INCREMENT,
     `port` INT(5) NOT NULL,
     `service_name` VARCHAR(100),
     `scan_id` INT(11) NOT NULL,
     PRIMARY KEY (`id`),
     CONSTRAINT FK_SCAN_ID FOREIGN KEY(`scan_id`) 
     REFERENCES scan(`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `targets` (
 `id` INT(11) NOT NULL auto_increment,
 `title` VARCHAR(20) NOT NULL,
 `value` VARCHAR(20) NOT NULL,
 PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `scan_settings` (
 `id` INT(11) NOT NULL auto_increment,
 `title` VARCHAR(20) NOT NULL,
 `time` VARCHAR(20) NOT NULL,
 `target_id` INT(11),
 PRIMARY KEY (`id`),
 CONSTRAINT FK_TARGET_ID FOREIGN KEY(`target_id`)
 REFERENCES targets(`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE users (
 username varchar(50) NOT NULL, 
 password varchar(100) NOT NULL, 
 enabled tinyint(1) NOT NULL,
PRIMARY KEY (username) ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO users VALUES ('user', '{noop}123', 1), ('admin', '{noop}31337', 1);

CREATE TABLE authorities ( 
  username varchar(50) NOT NULL, 
  authority varchar(50) NOT NULL, 
  UNIQUE KEY authorities_idx_1 (username, authority), 
  CONSTRAINT authorities_ibfk_1 FOREIGN KEY (username) 
  REFERENCES users (username) 
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO authorities VALUES ('admin', 'ROLE_ADMIN'), ('admin', 'ROLE_USER'), ('user', 'ROLE_USER');

DROP TABLE IF EXISTS role;

CREATE TABLE role (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO role (name)
VALUES
('ROLE_USER'),('ROLE_MANAGER'),('ROLE_ADMIN');

DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(50) NOT NULL,
  password char(80) NOT NULL,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  email varchar(50) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles (
  user_id int(11) NOT NULL,
  role_id int(11) NOT NULL,

  PRIMARY KEY (user_id,role_id),

  KEY FK_ROLE_idx (role_id),

  CONSTRAINT FK_USER_05 FOREIGN KEY (user_id)
  REFERENCES user (id)
  ON DELETE NO ACTION ON UPDATE NO ACTION,

  CONSTRAINT FK_ROLE FOREIGN KEY (role_id)
  REFERENCES role (id)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;


