--------------------
---Estas lineas solo se hacen una vez, luego comentar
--------------------
--------------------
create database carta;

create user 'tw'@localhost identified by 'tw2223';
GRANT ALL PRIVILEGES on carta.* TO 'tw'@localhost;
flush privileges;

--------------------
--------------------
--------------------

--
-- 

use carta;

CREATE TABLE IF NOT EXISTS `carta` (
 `idCarta` int(11) NOT NULL AUTO_INCREMENT,
  `regalo` varchar(50) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idCarta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `apellidos` varchar(50) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

