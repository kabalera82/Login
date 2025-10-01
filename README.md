### SISTEMA DE AUTENTICACION y PRIVILEGIOS ###

### APP Java + Maven + JPA ###
- Java 17.
- Maven 3.9.9
- JPA EclipseLink 2.7.9
- MySQL 8.0
- GUI 
- Aplicación de escritorio creada con Swing
- Gestionada con Maven.
- Utilizndo JPA (EclipseLink) para persistencia.
- MySQL para Base de Datos.

- Login de usuarios.
- Gestión de usuarios (CRUD).
- Sistema de Privilegios.
- Interfaz grafica adaptable según ROL.

### Configuración ###

Base de datos MySQL con la siguiente estructura:
   - minimo el admin para poder iniciar -
====================================================
```
--- CREATE ---
CREATE DATABASE login;
USE login;
CREATE TABLE rol (
  id INT PRIMARY KEY,
  nombrerol VARCHAR(255),
  descripcion VARCHAR(255)
);

CREATE TABLE usuario (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(255),
  pass VARCHAR(255),
  fk_rol INT,
  FOREIGN KEY (fk_rol) REFERENCES rol(id)
);

-- Inserta roles
INSERT INTO rol (id, nombrerol, descripcion)
VALUES (1, 'admin', 'Administrador'), (2, 'user', 'Usuario');
-- Inserta usuario administrador (ejemplo)
INSERT INTO usuario (nombre, pass, fk_rol)
VALUES ('admin', '123abc', 1);
```
=============================================================
 -- Configuración de persistencia (XML) datos de tu MySQL -- 
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/login"/>
<property name="javax.persistence.jdbc.user" value="tu_admin"/> 
<property name="javax.persistence.jdbc.password" value="tu_contraseña"/>

========================================================================
configuración del pom.xml 

- mvn clean compile exec:javac
- si tienes fallos revisa configuracion de maven en pom.xml


- tendras que cambiar groudId y segurante mainClass

=========================================================================
@kabalera82
GitHub: https://github.com/kabalera82/Login
