Estructura del proyecto
Usuario: Representa la información básica de un usuario (puede ser un administrador, docente, etc.).
Alumno: Representa a un estudiante.
Curso: Representa el curso o asignatura.
Notas: Representa las calificaciones del alumno en un curso específico.

Resumen de las relaciones
Usuario y Alumno: Relación uno a uno  (un Usuario puede ser un Alumno)
Alumno y Nota: Relación uno a muchos (un Alumno puede tener muchas Notas)
Curso y Nota: Relación uno a muchos (un Curso puede tener muchas Notas)

Modelo de la Base de Datos

Usuario: Tiene campos como id, usuario,  password.
Alumno: Tiene campos como id, nombre, apellido, dni , email , usuario_id (clave foránea que referencia a Usuario).
Curso: Tiene campos como id, nombre.
Nota: Tiene campos como id, practica1, practica2, practica3, practica4, examenFinal, alumno_id (clave foránea que referencia a Alumno), curso_id (clave foránea que referencia a Curso).

Patrones de diseño utilizado

Patrón de diseño: Singleton
•	En este caso, el patrón Singleton se aplica implícitamente a los servicios (NotaService) y repositorios (NotaRepository). Spring Boot, al ser un framework basado en 
Inyección de Dependencias (DI), maneja automáticamente las instancias de las clases de servicio y repositorio. Cada clase de servicio se instancia una vez, y esa instancia 
es compartida entre todas las solicitudes, lo que garantiza que se utilice la misma instancia de la clase a lo largo de la vida de la aplicación.
El patrón Singleton ya se aplica automáticamente en Spring al utilizar las anotaciones @Service, @Repository, @Component, etc. En el código anterior:
•	NotaServiceImpl y NotaDAOImpl son instancias únicas dentro del ciclo de vida de la aplicación, ya que Spring las maneja como beans de tipo Singleton por defecto.
•	No necesitas hacer nada explícito para aplicar el patrón Singleton, ya que Spring gestiona automáticamente la instancia de cada bean en la aplicación

Patrón de Diseño: ** Repository 
El patrón Repository facilita la separación de la lógica de acceso a datos del resto de la lógica de negocio, lo que hace que el código sea más limpio y fácil de mantener. Esto también permite que la aplicación sea más flexible si se quiere cambiar la implementación de la base de datos sin modificar otras partes del sistema.

Patrón de Diseño: ** Service Layer 
El patrón Service Layer está implementado a través del servicio NotaService. Este patrón organiza la lógica de negocio en una capa separada, y hace que las operaciones de negocio estén centralizadas y sean más fáciles de gestionar.
Este patrón ayuda a centralizar la lógica del negocio en un lugar único, lo que facilita el mantenimiento, la prueba y la extensión de la aplicación. 
Además, es ideal cuando la lógica de negocio crece y necesitas agrupar varias operaciones relacionadas en un solo servicio.

Patrón de Diseño: ** DAO (Data Access Object)
Este patrón se utiliza para separar la lógica de acceso a datos del resto de la aplicación. Aunque en Spring Boot con JPA no es estrictamente necesario,
este patrón es útil cuando quieres encapsular la lógica de acceso a la base de datos en una capa separada.
 Al usar DAO, se puede encapsular toda la interacción con la base de datos, lo que permite cambiar la implementación de acceso a datos (por ejemplo,
 cambiar de una base de datos H2 a MySQL) sin afectar la lógica de negocio de la aplicación.
