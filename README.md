# Login

Aplicación de autenticación de usuarios en Java utilizando Swing y JPA.

## Descripción

Este proyecto implementa un sistema de login con interfaz gráfica (Swing), persistencia de datos con JPA y conexión a una base de datos MySQL. Permite gestionar usuarios y validar credenciales de acceso.

## Estructura del proyecto

- **logica**: Clases de negocio y entidad `Usuario`.
- **persistencia**: Controladores JPA y fachada de acceso a datos.
- **persistencia.exceptions**: Excepciones personalizadas para la persistencia.
- **igu**: Interfaz gráfica de usuario (Swing).

## Requisitos

- Java 17+
- MySQL
- Dependencias JPA (EclipseLink o Hibernate)
- Conector JDBC para MySQL

## Pasos para ejecutar

1. **Crear la base de datos**  
   Crea una base de datos en MySQL para los usuarios.

2. **Configurar la conexión**  
   Edita el archivo `persistence.xml` en `META-INF` con los datos de tu base de datos (usuario, contraseña y URL).

3. **Compilar el proyecto**  
   Compila el proyecto en tu IDE favorito o usando Maven/Gradle.

4. **Ejecutar la aplicación**  
   Ejecuta la clase principal `Login`.  
   Se abrirá la ventana de autenticación.

5. **Probar el login**  
   Ingresa usuario y contraseña para validar el acceso.

## Notas

- El diseño de la interfaz gráfica puede modificarse editando las clases en el paquete `igu`.
- La lógica de negocio y la persistencia están desacopladas de la interfaz gráfica, facilitando futuras migraciones a otras tecnologías como JavaFX.

---

Desarrollado por kabalera.