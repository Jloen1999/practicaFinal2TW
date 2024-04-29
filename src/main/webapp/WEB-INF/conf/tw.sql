-- Creacion de la base de datos y usuario
CREATE DATABASE IF NOT EXISTS tw;
CREATE USER 'tw'@'localhost' IDENTIFIED BY 'tw2324';
GRANT ALL PRIVILEGES ON tw.* TO 'tw'@'localhost';

-- Uso de la base de datos
USE tw;

-- Creacion de la tabla de Usuarios
CREATE TABLE IF NOT EXISTS Usuarios
(
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(50)  NOT NULL,
    apellidos VARCHAR(50)  NOT NULL,
    email     VARCHAR(100) NOT NULL,
    username  VARCHAR(20)  NOT NULL,
    password  VARCHAR(20)  NOT NULL
);

-- Insercion de datos de ejemplo en la tabla de Usuarios
INSERT INTO Usuarios (nombre, apellidos, email, username, password)
VALUES ('Juan', 'Perez', 'juan@example.com', 'juanp', '123456'),
       ('Maria', 'Garcia', 'maria@example.com', 'mariag', 'abcdef'),
       ('Pedro', 'Lopez', 'pedro@example.com', 'pedrol', 'qwerty');

-- Creacion de la tabla de Libros (Tabla Individual)
CREATE TABLE IF NOT EXISTS Libros
(
    idLibro  INT AUTO_INCREMENT PRIMARY KEY,
    titulo   VARCHAR(100) NOT NULL,
    autor    VARCHAR(100) NOT NULL,
    tematica VARCHAR(50)  NOT NULL,
    novedad  BOOLEAN      NOT NULL DEFAULT FALSE
);

-- Insercion de datos de ejemplo en la tabla de Libros
INSERT INTO Libros (titulo, autor, tematica, novedad)
VALUES ('El principito', 'Antoine de Saint-Exupery', 'Literatura infantil', TRUE),
       ('Cien noches de soledad', 'Gabriel Garcia Marquez', 'Realismo magico', TRUE),
       ('Don Quijote de la Mancha', 'Miguel de Cervantes', 'Novela clasica', FALSE);

-- Creacion de la tabla de Reservas (Tabla de Relacion N:M)
CREATE TABLE IF NOT EXISTS Reservas
(
    idUsuario    INT,
    idLibro      INT,
    fechaReserva TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (idUsuario, idLibro),
    FOREIGN KEY (idUsuario) REFERENCES Usuarios (idUsuario),
    FOREIGN KEY (idLibro) REFERENCES Libros (idLibro)
);

-- Creacion de la tabla de Valoraciones (Tabla Adicional 1:N)
CREATE TABLE IF NOT EXISTS Valoraciones
(
    idValoracion INT AUTO_INCREMENT PRIMARY KEY,
    idUsuario    INT,
    idLibro      INT,
    puntuacion   INT CHECK (puntuacion BETWEEN 1 AND 5),
    comentario   VARCHAR(255),
    FOREIGN KEY (idUsuario) REFERENCES Usuarios (idUsuario),
    FOREIGN KEY (idLibro) REFERENCES Libros (idLibro)
);

-- Insercion de datos de ejemplo en la tabla de Reservas
INSERT INTO Reservas (idUsuario, idLibro)
VALUES (1, 1), -- Usuario Juan reserva El principito
       (2, 2);
-- Usuario Maria reserva Cien noches de soledad

-- Insercion de datos de ejemplo en la tabla de Valoraciones
INSERT INTO Valoraciones (idUsuario, idLibro, puntuacion, comentario)
VALUES (1, 1, 5, 'Excelente libro para gente de todas las redes'),
       (2, 2, 4, 'Una obra maestra de la literatura');


