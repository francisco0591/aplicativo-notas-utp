DROP TABLE IF EXISTS ALUMNO;
CREATE TABLE ALUMNO(
id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(30),
apellido VARCHAR(50),
email VARCHAR(50),
dni CHAR(8),
usuario_id INT
);

INSERT INTO ALUMNO(nombre,apellido,dni,email,usuario_id) VALUES('Francisco','Julca Velasquez','47467404','fjulcav@gmail.com',1);
INSERT INTO ALUMNO(nombre,apellido,dni,email,usuario_id) VALUES('Jose','Salas Vera','45345234','jsalas@gmail.com',2);

DROP TABLE IF EXISTS CURSO;
CREATE TABLE CURSO(
id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(30)
);

INSERT INTO CURSO(nombre) VALUES('Algoritmos');
INSERT INTO CURSO(nombre) VALUES('Ingles');
INSERT INTO CURSO(nombre) VALUES('Php');
INSERT INTO CURSO(nombre) VALUES('Java');
INSERT INTO CURSO(nombre) VALUES('Sql');

DROP TABLE IF EXISTS USUARIO;
CREATE TABLE USUARIO(
id INT AUTO_INCREMENT PRIMARY KEY,
usuario VARCHAR(30),
clave VARCHAR(300),
roles VARCHAR(50)
);

INSERT INTO USUARIO(usuario,clave,roles) VALUES('admin','dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=','ROLE_ADMIN');
INSERT INTO USUARIO(usuario,clave,roles) VALUES('jsosa','2222','ROLE_ADMIN');
INSERT INTO USUARIO(usuario,clave,roles) VALUES('aguzman','3434','ROLE_ADMIN');


DROP TABLE IF EXISTS NOTA;
CREATE TABLE NOTA(
id INT AUTO_INCREMENT PRIMARY KEY,
practica1 int,
practica2 int,
practica3 int,
practica4 int,
examen_final int,
alumno_id NUMERIC,
curso_id NUMERIC,
valor INT,
foreign key (curso_id)
references CURSO (id),
foreign key (alumno_id)
references USUARIO (id)
);

INSERT INTO NOTA(alumno_id,curso_id,practica1,practica2,practica3,practica4,examen_final) VALUES(1,1,15,12,11,10,20);
INSERT INTO NOTA(alumno_id,curso_id,practica1,practica2,practica3,practica4,examen_final) VALUES(1,2,12,12,13,14,18);
INSERT INTO NOTA(alumno_id,curso_id,practica1,practica2,practica3,practica4,examen_final) VALUES(1,3,11,12,12,12,17);
INSERT INTO NOTA(alumno_id,curso_id,practica1,practica2,practica3,practica4,examen_final) VALUES(1,4,17,19,10,11,20);
INSERT INTO NOTA(alumno_id,curso_id,practica1,practica2,practica3,practica4,examen_final) VALUES(2,4,17,19,20,15,20);
