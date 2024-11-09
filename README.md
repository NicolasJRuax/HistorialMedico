- Nicolás Jiménez
  
- Jaime López

Link: https://github.com/NicolasJRuax/HistorialMedico.git

# Historial Médico

Este proyecto es una aplicación de Android para gestionar historiales médicos. Permite a usuarios (pacientes y doctores) autenticarse, ver, crear y editar historiales médicos, así como listar todos los registros médicos almacenados en una base de datos local.

## Estructura del Proyecto

### AndroidManifest.xml
El archivo `AndroidManifest.xml` define la estructura básica de la aplicación, incluyendo las actividades principales y las configuraciones de inicio:

- **MainActivity**: Actividad de inicio que redirige a la pantalla de login.
- **LoginActivity**: Actividad de autenticación para usuarios y doctores.
- **MedicalRecordActivity**: Pantalla para que el paciente vea o edite su historial médico.
- **DoctorActivity**: Pantalla para que los doctores vean y seleccionen historiales médicos de pacientes.
- **EditMedicalRecordActivity**: Permite a los doctores crear o editar un historial médico.
- **ViewAllRecordsActivity**: Muestra todos los historiales médicos disponibles en la base de datos.

### Base de Datos y Entidades

Utiliza Room para la persistencia de datos local. Los archivos de la base de datos se dividen en varias clases y entidades:

- **DatabaseHelper**: Clase de ayuda para la base de datos, implementa métodos para gestionar registros médicos y usuarios. Contiene métodos asíncronos para acceder a la base de datos y permite encriptar y desencriptar los datos de los historiales médicos usando `EncryptionUtils`.
- **MedicalRecord**: Entidad que representa un historial médico con atributos como `patientName`, `condition`, `treatment`, y `notes`.
- **User**: Entidad para gestionar los usuarios del sistema (pacientes y doctores), con atributos `username`, `password` y `role`.
  
### DAO (Data Access Objects)

- **MedicalRecordDao**: Define métodos de acceso para insertar, actualizar, eliminar y consultar registros médicos.
- **UserDao**: Define métodos de acceso para gestionar los usuarios y autenticarlos.

### Encriptación de Datos

- **EncryptionUtils**: Clase para encriptar y desencriptar datos sensibles de los historiales médicos. Usa AES para proteger los datos de `patientName`, `condition`, `treatment` y `notes`.

### Actividades Principales

1. **MainActivity**: Crea usuarios de ejemplo y permite iniciar sesión.
2. **LoginActivity**: Permite a usuarios y doctores autenticarse, redirigiéndolos a sus respectivas actividades.
3. **DoctorActivity**: Lista los historiales médicos y permite a los doctores seleccionar un registro para editar o crear uno nuevo.
4. **EditMedicalRecordActivity**: Permite editar un historial médico existente o crear uno nuevo.
5. **MedicalRecordActivity**: Permite a los pacientes ver y editar su propio historial médico.
6. **ViewAllRecordsActivity**: Muestra todos los registros médicos de forma legible para el usuario.

### Gestión de Autenticación

La clase `AuthenticationManager` permite gestionar la autenticación de usuarios mediante el método `authenticate`, que verifica las credenciales en la base de datos y usa una interfaz `AuthenticationCallback` para indicar el éxito o fallo de la autenticación.

## Funciones Clave

- **Autenticación** de usuarios (pacientes y doctores).
- **Encriptación** de datos sensibles en los historiales médicos.
- **Persistencia de datos** con Room y métodos asíncronos para acceder a la base de datos.
- **Interfaz de usuario** para gestionar historiales médicos, optimizada para doctores y pacientes.

## Requisitos del Sistema

- Android Studio Arctic Fox o superior.
- Gradle 7.0 o superior.
- Dispositivo o emulador Android 5.0 (Lollipop) o superior.

## Nota

Esta aplicación es solo un prototipo. En entornos de producción, las claves de encriptación deben almacenarse de forma segura y los métodos de autenticación y encriptación deben mejorarse para cumplir con los estándares de seguridad.
