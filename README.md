# microservicio_usuario 🛴👤

_Este microservicio se encarga de la gestion de las funcionalidades relacionadas con usuarios dentro de un sistema más amplio. Estas funcionalidades son: la gestión de usuarios, cuentas, la vinculación de usuarios con cuentas y proporcionar ciertas funciones administrativas._

# Propósito
Este microservicio consta de varios controladores, modelos, repositorios y servicios para manejar diferentes operaciones relacionadas con usuarios, cuentas y sus asociaciones. Las principales funcionalidades incluyen:

### Gestión de Usuarios 👤

Maneja operaciones relacionadas con usuarios.
Permite recuperar, crear, actualizar y eliminar información de usuario.
Proporciona endpoints para administrar detalles del usuario como nombre, correo electrónico y número de contacto.

### Gestión de Cuentas 💻

Administra operaciones relacionadas con cuentas.
Permite la recuperación, creación y manipulación de información de cuentas.
Facilita acciones como cargar saldo en la cuenta y eliminar cuentas.

### Asociación Usuario-Cuenta 🔗

Vincula usuarios con sus cuentas respectivas.
Proporciona endpoints para asociar un usuario con una cuenta existente y obtener cuentas asociadas a un usuario. 

### Operaciones de Administrador ✔

Contiene endpoints para tareas administrativas que requieren privilegios de administrador.
Permite a un administrador realizar acciones como eliminar cuentas, suspender cuentas, agregar nuevos "Monopatines" y gestionarlos.

### Puntos Importantes ✔
El sistema permite a los administradores eliminar cuentas, suspender cuentas con razones y realizar diversas funciones administrativas.
Las cuentas de usuario pueden asociarse y administrarse.
El servicio se comunica con servicios externos, especialmente para funcionalidades relacionadas con administración.

# Tecnologías Utilizadas 👩🏻‍💻
* Lenguaje de programación: Spring-boot
* Base de datos: MySQL
* Datos de la base de datos:
  * microservicio_usuario<br><br>
* Requerimientos y Dependencias:
  * Maven
  * Lombok
  * Spring Web
  * Spring Data JPA
  * MySQL Driver 
