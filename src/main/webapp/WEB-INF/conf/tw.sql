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
VALUES
    ('Juan', 'Garcia Perez', 'juangarcia@gmail.com', 'juang', 'password123'),
    ('Maria', 'Martinez Lopez', 'mariaml@hotmail.com', 'marial', 'securepass'),
    ('Pedro', 'Fernandez Rodriguez', 'pedrofr@gmail.com', 'pedrof', 'mypassword'),
    ('Laura', 'Sanchez Gomez', 'laurasg@yahoo.com', 'lauras', 'laura123'),
    ('Carlos', 'Gonzalez Martin', 'carlosgm@gmail.com', 'carlosm', 'pass123'),
    ('Ana', 'Jimenez Ruiz', 'anajr@gmail.com', 'anaj', 'anapassword'),
    ('Diego', 'Hernandez Castro', 'diegohc@gmail.com', 'diegoh', 'diego123'),
    ('Elena', 'Diaz Garcia', 'elenadg@gmail.com', 'elenad', 'elenapass'),
    ('Sara', 'Perez Martinez', 'saram@gmail.com', 'saram', 'sarapassword'),
    ('Javier', 'Ruiz Lopez', 'javierrl@gmail.com', 'javierr', 'javierpass'),
    ('admin', 'admin', 'admin@gmail.com', 'admin', 'admin123');



-- Creacion de la tabla de Libros (Tabla Individual)
CREATE TABLE IF NOT EXISTS Libros
(
    idLibro  INT AUTO_INCREMENT PRIMARY KEY,
    isbn     VARCHAR(13),
    titulo   VARCHAR(100) NOT NULL,
    autor    VARCHAR(100) NOT NULL,
    tematica VARCHAR(50)  NOT NULL,
    descripcion TEXT  NOT NULL,
    urlImg  TEXT,
    novedad  BOOLEAN      NOT NULL DEFAULT FALSE,
    puntuacion FLOAT DEFAULT 0
);

-- Insercion de datos de ejemplo en la tabla de Libros
insert into libros (isbn, titulo, autor, tematica, descripcion, urlImg, novedad, puntuacion)
values  ('9780307474728', 'Cien anios de soledad', 'Gabriel Garcia Marquez', 'Literatura Clasica', 'La obra maestra del realismo magico que cuenta la historia de la familia Buendia en Macondo.', 'http://books.google.com/books/content?id=QBwnDwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api', 1, 4.5),
        ('9788408079545', 'El codigo Da Vinci', 'Dan Brown', 'Misterio', 'Un thriller que combina arte, religion y conspiraciones en un emocionante viaje por Europa.', 'http://books.google.com/books/content?id=iHkPDQAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api', 0, 3.2),
        ('9780132350884', 'Clean Code', 'Robert C. Martin', 'Programacion', 'El manual clasico sobre como escribir un codigo limpio y mantenible.', 'http://books.google.com/books/content?id=_i6bDeoCQzsC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api', 1, 3.5),
        ('9788408059462', 'La sombra del viento', 'Carlos Ruiz Zafon', 'Ficcion', 'Una novela ambientada en la Barcelona de posguerra que mezcla misterio y romance literario.', 'http://books.google.com/books/content?id=0msZEAAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api', 1, 2.2),
        ('9788498386264', 'Percy Jackson y el ladron del rayo', 'Rick Riordan', 'Aventura', 'El primer libro de la saga de Percy Jackson, que combina mitologia griega con aventuras modernas.', 'http://books.google.com/books/content?id=A-y-DwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api', 0, 4),
        ('9788478884450', 'Harry Potter y la piedra filosofal', 'J.K. Rowling', 'Fantasia', 'La primera entrega de la saga de Harry Potter, donde descubre el mundo de la magia.', 'http://books.google.com/books/content?id=2zgRDXFWkm8C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api', 0, 5.0),
        ('9788424916409', 'El principito', 'Antoine de Saint-Exupery', 'Literatura Infantil', 'Una fabula sobre la amistad y la importancia de ver las cosas desde una perspectiva diferente.', 'http://books.google.com/books/content?id=lildEAAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api', 0, 4.3),
        ('9780062315007', 'El alquimista', 'Paulo Coelho', 'Autoayuda', 'Una novela que explora el viaje personal en busca de la realizacion personal.', 'http://books.google.com/books/content?id=_dHJAAAACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api', 0, 1.6),
        ('9788423347964', '1984', 'George Orwell', 'Ciencia Ficcion', 'Una distopia que reflexiona sobre el poder y el control totalitario en una sociedad futurista.', 'http://books.google.com/books/content?id=fHm2EAAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api', 1, 2.2),
        ('9788491043526', 'Orgullo y prejuicio', 'Jane Austen', 'Novela Romantica', 'Una historia de amor y prejuicios en la Inglaterra del siglo XIX.', 'http://books.google.com/books/content?id=_ypm4jZNy_oC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api', 0, 3.4),
        ('9788437605513', 'El tunel', 'Ernesto Sabato', 'Novela Psicologica', 'Una novela existencialista que explora los rincones mas oscuros de la mente humana.', 'http://books.google.com/books/content?id=2VtAAQAAIAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api', 0, 4.1),
        ('9788420474103', 'La ciudad y los perros', 'Mario Vargas Llosa', 'Realismo Literario', 'Una historia de amistad y violencia en un internado militar en Lima.', 'http://books.google.com/books/content?id=X7gsAQAAMAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api', 1, 4.8),
        ('9788437617356', 'El retrato de Dorian Gray', 'Oscar Wilde', 'Clasico Literario', 'Una novela sobre la vanidad y la decadencia moral.', 'http://books.google.com/books/content?id=QYZCBAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 0, 4.7),
        ('9788497630394', 'Moby Dick', 'Herman Melville', 'Novela de Aventuras', 'La obsesiva persecucion de una ballena blanca por el capitan Ahab.', 'http://books.google.com/books/content?id=mZ8LbzgZMh8C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 1, 3.9),
        ('9780307350425', 'El amor en los tiempos del colera', 'Gabriel Garcia Marquez', 'Novela Romantica', 'Una historia de amor y esperanza ambientada en un entorno magico.', 'http://books.google.com/books/content?id=RV2GDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 0, 4.5),
        ('9788423312221', 'Rebelion en la granja', 'George Orwell', 'Satira Politica', 'Una alegoria politica sobre el totalitarismo y la corrupcion.', 'http://books.google.com/books/content?id=gashEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 1, 4.2),
        ('9780307387261', 'Cronica de una muerte anunciada', 'Gabriel Garcia Marquez', 'Realismo Magico', 'El relato de un asesinato anunciado en un pequeno pueblo caribeno.', 'http://books.google.com/books/content?id=xzJjBgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 0, 4.4),
        ('9788437602628', 'Rayuela', 'Julio Cortazar', 'Novela Experimental', 'Una obra desafiante que invita al lector a elegir su propio camino.', 'http://books.google.com/books/content?id=S1TKEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 1, 4.3),
        ('9788483462504', 'La insoportable levedad del ser', 'Milan Kundera', 'Filosofia Existencialista', 'Una reflexion sobre el amor, la vida y la fugacidad del ser.', 'http://books.google.com/books/content?id=hlCiEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 0, 4.1),
        ('9788433979122', 'Los detectives salvajes', 'Roberto Bolano', 'Novela Contemporanea', 'Una epica literaria que sigue la busqueda de dos poetas en la ciudad de Mexico.', 'http://books.google.com/books/content?id=VALEDAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 1, 4.6),
        ('9788433920934', 'La hoguera de las vanidades', 'Tom Wolfe', 'Satira Social', 'Un retrato feroz de la vida en la alta sociedad de Nueva York.', 'http://books.google.com/books/content?id=MGMexwEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api', 0, 3.8),
        ('9788478443095', 'La inmortalidad', 'Milan Kundera', 'Novela Filosofica', 'Una exploracion de la eternidad y la mortalidad a traves de la vida de cuatro personajes.', 'http://books.google.com/books/content?id=hdMFAAAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api', 1, 4),
        ('9788446037728', 'La Odisea', 'Homero', 'Epopeya Clasica', 'La legendaria odisea de Ulises en su viaje de regreso a itaca.', 'http://books.google.com/books/content?id=yhdKeKxZuPIC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 1, 4.9),
        ('9788483462283', 'En busca del tiempo perdido', 'Marcel Proust', 'Novela Modernista', 'Una exploracion profunda de la memoria y el paso del tiempo en la vida del narrador.', 'http://books.google.com/books/content?id=MgpLDAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 0, 4.7),
        ('9788499895775', 'El lobo estepario', 'Hermann Hesse', 'Novela Existencialista', 'La historia de un hombre solitario en busqueda de su identidad y sentido de pertenencia.', 'http://books.google.com/books/content?id=goRpMpeyZYoC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 1, 4.3),
        ('9788437612566', 'El extranjero', 'Albert Camus', 'Novela Filosofica', 'Un relato existencialista sobre un hombre indiferente ante la vida y la muerte.', 'http://books.google.com/books/content?id=UID2fldlctMC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 0, 4.6),
        ('9788433975681', 'Las correcciones', 'Jonathan Franzen', 'Novela Familiar', 'Un retrato comico y satirico de una familia americana en crisis.', 'http://books.google.com/books/content?id=--y-DwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 1, 4.2),
        ('9788483466175', 'Siddhartha', 'Hermann Hesse', 'Novela Espiritual', 'La busqueda de la iluminacion y el significado de la vida por parte de un joven.', 'http://books.google.com/books/content?id=Z0RZEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 1, 4.4),
        ('9788420674037', 'El viejo y el mar', 'Ernest Hemingway', 'Novela Clasica', 'La lucha epica de un pescador solitario contra un gigantesco pez en el mar.', 'http://books.google.com/books/content?id=5SIAEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 1, 4.9),
        ('9788437601416', 'El castillo', 'Franz Kafka', 'Novela Metafisica', 'Un relato surrealista sobre un hombre atrapado en un mundo burocratico y enigmatico.', 'http://books.google.com/books/content?id=_o5OCgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 0, 4);

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

-- Insercion de datos de ejemplo en la tabla de Reservas
INSERT INTO Reservas (idUsuario, idLibro)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 1),
    (3, 5),
    (4, 2),
    (5, 4),
    (6, 6),
    (7, 8),
    (8, 10),
    (9, 9),
    (10, 7),
    (2, 3),
    (3, 6),
    (4, 8),
    (5, 9),
    (6, 10),
    (7, 1),
    (8, 2),
    (9, 3),
    (10, 4),
    (1, 5),
    (2, 6),
    (3, 7);


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

-- Insercion de datos de ejemplo en la tabla de Valoraciones
INSERT INTO Valoraciones (idUsuario, idLibro, puntuacion, comentario)
VALUES
    (1, 1, 5, 'Excelente libro para gente de todas las edades'),
    (2, 2, 4, 'Una obra maestra de la literatura'),
    (3, 3, 3, 'Buen libro, facil de entender'),
    (4, 4, 4, 'Me encanto la historia y los personajes'),
    (5, 5, 3, 'Entretenido, pero esperaba mas'),
    (6, 6, 5, 'Una joya literaria'),
    (7, 7, 4, 'Historia conmovedora'),
    (8, 8, 4, 'Recomendado para reflexionar'),
    (9, 9, 5, 'Fascinante y perturbador'),
    (10, 10, 3, 'Buena novela clasica'),
    (1, 2, 4, 'Interesante trama y giros inesperados'),
    (2, 3, 5, 'Me transporto a otro mundo'),
    (3, 4, 4, 'Muy bien escrito, recomendado'),
    (4, 5, 3, 'Bien para adolescentes'),
    (5, 6, 5, 'Una historia con mensaje'),
    (6, 7, 4, 'Agradable y facil de leer'),
    (7, 8, 4, 'Reflexivo y motivador'),
    (8, 9, 5, 'No pude soltarlo hasta terminarlo'),
    (9, 10, 3, 'No es mi tipo de lectura'),
    (10, 1, 4, 'Clasico imperdible');



