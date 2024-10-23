# mi-maven-app

## Descripción

**mi-maven-app** es una aplicación desarrollada con Spring Boot que proporciona una API REST para gestionar información de compañías, aplicaciones y versiones asociadas. La API permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la tabla `company`, así como obtener detalles específicos de una compañía mediante su código.

## Características

- **CRUD para Company**: Crear, leer, actualizar y eliminar registros en la tabla `company`.
- **Consulta Detallada**: Obtener información detallada de una compañía basada en su código, incluyendo el nombre de la aplicación y su versión.
- **Persistencia de Datos**: Uso de Spring Data JPA para interactuar con una base de datos MySQL.
- **Lombok**: Reducción de código boilerplate mediante anotaciones de Lombok.
- **Gestión de Dependencias**: Maven para la gestión y construcción del proyecto.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.3.4**
- **Maven**
- **MySQL**
- **Spring Data JPA**
- **Lombok**
- **Hibernate**
- **Postman/CURL** (para pruebas de la API)

## Prerrequisitos

Antes de comenzar, asegúrate de tener instalados los siguientes componentes en tu sistema:

- **Java 17**
- **Maven**
- **MySQL**
- **Git** (para clonar el repositorio)

## Instalación

### 1. Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/mi-maven-app.git
cd mi-maven-app
2. Configurar la Base de Datos MySQL
Crear la Base de Datos y Tablas
Ejecuta los siguientes scripts SQL en tu instancia de MySQL para crear la base de datos y las tablas necesarias:

sql
Copiar código
CREATE DATABASE mi_maven_app_db;

USE mi_maven_app_db;

CREATE TABLE company (
    id_company          INT PRIMARY KEY AUTO_INCREMENT,
    codigo_company      VARCHAR(50) NOT NULL UNIQUE,
    name_company        VARCHAR(255),
    description_company TEXT
);

CREATE TABLE application (
    app_id              INT PRIMARY KEY AUTO_INCREMENT,
    app_code            VARCHAR(50) NOT NULL UNIQUE,
    app_name            VARCHAR(255),
    app_description     TEXT
);

CREATE TABLE version (
    version_id          INT PRIMARY KEY AUTO_INCREMENT,
    app_id              INT NOT NULL,
    version             VARCHAR(50),
    version_description TEXT,
    FOREIGN KEY (app_id) REFERENCES application(app_id)
);

CREATE TABLE version_company (
    version_company_id          INT PRIMARY KEY AUTO_INCREMENT,
    company_id                  INT NOT NULL,
    version_id                  INT NOT NULL,
    version_company_description TEXT,
    FOREIGN KEY (company_id) REFERENCES company(id_company),
    FOREIGN KEY (version_id) REFERENCES version(version_id)
);
Insertar Datos Iniciales
Utiliza el programa Java proporcionado (InsertarDatos.java) para insertar datos de ejemplo en las tablas. Asegúrate de compilar y ejecutar el programa siguiendo los pasos detallados en el documento.

3. Configurar las Credenciales de la Base de Datos
Edita el archivo src/main/resources/application.properties con las credenciales de tu base de datos MySQL:

properties
Copiar código
spring.datasource.url=jdbc:mysql://localhost:3306/mi_maven_app_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
4. Compilar y Ejecutar la Aplicación
Desde el directorio raíz del proyecto, ejecuta los siguientes comandos:

bash
Copiar código
mvn clean install
mvn spring-boot:run
La aplicación se iniciará y estará disponible en http://localhost:8080/api/company.

Uso de la API REST
Endpoints Disponibles
1. Obtener Todas las Compañías
URL: GET /api/company

Descripción: Recupera una lista de todas las compañías.

Ejemplo de Solicitud:

bash
Copiar código
curl -X GET http://localhost:8080/api/company
Respuesta Esperada:

json
Copiar código
[
    {
        "idCompany": 1,
        "codigoCompany": "COMP001",
        "nameCompany": "Compañía Alpha",
        "descriptionCompany": "Descripción Alpha"
    },
    {
        "idCompany": 2,
        "codigoCompany": "COMP002",
        "nameCompany": "Compañía Beta",
        "descriptionCompany": "Descripción Beta"
    }
]
2. Obtener una Compañía por ID
URL: GET /api/company/{id}

Descripción: Recupera una compañía específica por su ID.

Ejemplo de Solicitud:

bash
Copiar código
curl -X GET http://localhost:8080/api/company/1
Respuesta Esperada:

json
Copiar código
{
    "idCompany": 1,
    "codigoCompany": "COMP001",
    "nameCompany": "Compañía Alpha",
    "descriptionCompany": "Descripción Alpha"
}
3. Crear una Nueva Compañía
URL: POST /api/company

Descripción: Crea una nueva compañía.

Ejemplo de Solicitud:

bash
Copiar código
curl -X POST http://localhost:8080/api/company \
     -H "Content-Type: application/json" \
     -d '{
           "codigoCompany": "COMP003",
           "nameCompany": "Compañía Gamma",
           "descriptionCompany": "Descripción Gamma"
         }'
Respuesta Esperada:

json
Copiar código
{
    "idCompany": 3,
    "codigoCompany": "COMP003",
    "nameCompany": "Compañía Gamma",
    "descriptionCompany": "Descripción Gamma"
}
4. Actualizar una Compañía Existente
URL: PUT /api/company/{id}

Descripción: Actualiza los detalles de una compañía existente.

Ejemplo de Solicitud:

bash
Copiar código
curl -X PUT http://localhost:8080/api/company/1 \
     -H "Content-Type: application/json" \
     -d '{
           "codigoCompany": "COMP001",
           "nameCompany": "Compañía Alpha Actualizada",
           "descriptionCompany": "Descripción Alpha Actualizada"
         }'
Respuesta Esperada:

json
Copiar código
{
    "idCompany": 1,
    "codigoCompany": "COMP001",
    "nameCompany": "Compañía Alpha Actualizada",
    "descriptionCompany": "Descripción Alpha Actualizada"
}
5. Eliminar una Compañía
URL: DELETE /api/company/{id}

Descripción: Elimina una compañía específica.

Ejemplo de Solicitud:

bash
Copiar código
curl -X DELETE http://localhost:8080/api/company/1
Respuesta Esperada:

Código de estado 204 No Content indicando que la eliminación fue exitosa.
6. Obtener Detalles de una Compañía por Código
URL: GET /api/company/detalle/{codigoCompany}

Descripción: Recupera detalles específicos de una compañía basada en su código, incluyendo el nombre de la aplicación y su versión.

Ejemplo de Solicitud:

bash
Copiar código
curl -X GET http://localhost:8080/api/company/detalle/COMP001
Respuesta Esperada:

json
Copiar código
{
    "codigo_company": "COMP001",
    "name_company": "Compañía Alpha",
    "app_name": "Aplicación Uno",
    "version": "v1.0"
}
Estructura del Proyecto
plaintext
Copiar código
mi-maven-app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── mi_maven_app/
│   │   │               ├── controller/
│   │   │               │   └── CompanyController.java
│   │   │               ├── entity/
│   │   │               │   ├── Application.java
│   │   │               │   ├── Company.java
│   │   │               │   ├── Version.java
│   │   │               │   └── VersionCompany.java
│   │   │               ├── repository/
│   │   │               │   ├── CompanyRepository.java
│   │   │               │   └── VersionCompanyRepository.java
│   │   │               └── DemoApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── mi_maven_app/
│                       └── DemoApplicationTests.java
├── pom.xml
└── README.md
Configuración de Lombok
Para utilizar Lombok y aprovechar sus anotaciones como @Data, asegúrate de:

1. Agregar la Dependencia en pom.xml
xml
Copiar código
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.26</version>
    <scope>compile</scope>
</dependency>
2. Configurar el Plugin de Compilación para Lombok
xml
Copiar código
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.8.1</version>
    <configuration>
        <source>17</source>
        <target>17</target>
        <annotationProcessorPaths>
            <path>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>1.18.26</version>
            </path>
        </annotationProcessorPaths>
    </configuration>
</plugin>
3. Instalar el Plugin de Lombok en tu IDE
IntelliJ IDEA: Ve a File > Settings > Plugins, busca Lombok e instálalo.
Eclipse: Ve a Help > Eclipse Marketplace, busca Lombok e instálalo.
Pruebas de la API
Se realizaron pruebas utilizando herramientas como Postman y cURL para asegurar que todos los endpoints funcionen correctamente. Asegúrate de probar cada uno de los endpoints para verificar su funcionamiento.

Consideraciones Finales
Validación de Datos: Se recomienda implementar validaciones adicionales en las entidades utilizando anotaciones como @NotNull, @Size, etc., para asegurar la integridad de los datos.
Seguridad: Para entornos de producción, considera implementar mecanismos de autenticación y autorización utilizando Spring Security.
Documentación: Integrar herramientas como Swagger puede facilitar la documentación y prueba interactiva de la API.
Pruebas Automatizadas: Desarrollar pruebas unitarias y de integración para garantizar la robustez de la aplicación.
Contribuciones
Las contribuciones son bienvenidas. Si deseas contribuir, por favor sigue estos pasos:

Fork el repositorio.
Crea una nueva rama para tu feature (git checkout -b feature/nueva-feature).
Commit tus cambios (git commit -m 'Agregar nueva feature').
Push a la rama (git push origin feature/nueva-feature).
Abre un Pull Request.
Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

Contacto
Carlos Argoti Patiño
Correo Electrónico: carlos.e.argoti@gmail.com
LinkedIn: linkedin.com/in/carlos-argoti-0b0376208/
¡Gracias por utilizar mi-maven-app! Si tienes alguna duda o sugerencia, no dudes en contactarme.

markdown
Copiar código

## Explicaciones de las Modificaciones

1. **Corrección de Numeración y Estructura de Secciones:**
   - Aseguré que los títulos y subtítulos estén correctamente estructurados con `#`, `##`, `###`, y `####` para reflejar la jerarquía adecuada.
   - Numeré correctamente los pasos y subpasos para facilitar la lectura y el seguimiento.

2. **Formato de Bloques de Código:**
   - Utilicé bloques de código triple backticks (\`\`\`) con el lenguaje especificado (por ejemplo, `bash`, `sql`, `json`, `xml`, `Java`) para resaltar correctamente los fragmentos de código.
   - Aseguré que los bloques de código no estén mezclados con el texto y que estén correctamente indentados.

3. **Claridad en las Descripciones:**
   - Mejoré la redacción para que las descripciones sean más claras y concisas.
   - Añadí enlaces directos donde fue necesario, por ejemplo, para la URL de la API.

4. **Sección de Estructura del Proyecto:**
   - Mantengo la estructura original pero la presenté en un bloque de código `plaintext` para una mejor visualización.

5. **Sección de Configuración de Lombok:**
   - Mantuve las instrucciones originales pero las presenté con formato adecuado para mayor claridad.

6. **Consideraciones Finales y Contribuciones:**
   - Añadí negritas y formateé correctamente las subsecciones para mejorar la legibilidad.

7. **Enlaces y Contacto:**
   - Aseguré que los enlaces estén activos y correctamente formateados.

8. **Uso de Recursos Visuales:**
   - Utilicé listas ordenadas y desordenadas para organizar la información de manera estructurada.

## Recomendaciones Adicionales

- **Verifica la URL del Repositorio:** Asegúrate de reemplazar `https://github.com/tu-usuario/mi-maven-app.git` con la URL real de tu repositorio en GitHub.
  
- **Añade una Sección de Licencia:** Si aún no tienes un archivo `LICENSE`, considera añadir uno basado en la licencia MIT u otra que prefieras.

- **Incluye Imágenes o Diagramas:** Si es relevante, puedes añadir imágenes o diagramas para ilustrar la arquitectura de tu aplicación o cualquier otro aspecto relevante.

- **Actualiza el Contenido Regularmente:** A medida que desarrolles más funcionalidades o realices cambios en la aplicación, asegúrate de mantener el `README.md` actualizado.

Espero que estas modificaciones te sean de ayuda para tener un `README.md` profesional y bien estructurado en tu repositorio de GitHub. Si necesitas alguna otra modificación o asistencia adicional, no dudes en decírmelo.