# API de Entrenadores Pokémon

Esta es una API REST sencilla construida con Spring Boot que permite gestionar entrenadores Pokémon. La API consume datos de la [PokeAPI](https://pokeapi.co/) para obtener información de los Pokémon y asignarlos a los entrenadores.

## Funcionalidades

*   Crear y listar entrenadores.
*   Asignar Pokémon a un entrenador específico.
*   Consultar la información de un entrenador y su equipo Pokémon.
*   Eliminar un Pokémon de un entrenador.

## Prerrequisitos

*   Java 17 o superior.
*   Maven.
*   Una base de datos MySQL en ejecución.
*   [Docker](https://www.docker.com/) y [Docker Compose](https://docs.docker.com/compose/) (Recomendado).
*   Una herramienta para probar APIs como [Postman](https://www.postman.com/) o ApiDog.

## Configuración

1.  Asegúrate de que tu base de datos MySQL esté activa.
2.  Actualiza el archivo `src/main/resources/application.properties` con la URL de tu base de datos, tu usuario y tu contraseña si son diferentes:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3307/test1
    spring.datasource.username=root
    spring.datasource.password=root
    ```
3.  Ejecuta la aplicación desde tu IDE o usando el siguiente comando en la raíz del proyecto:
    ```sh
    mvn spring-boot:run
    ```
    La API estará disponible en `http://localhost:8080`.

---

## Configuración Alternativa con Docker

Si prefieres no instalar MySQL manualmente, puedes usar el archivo `docker-compose.yml` incluido en el proyecto para levantar la base de datos.

1.  Asegúrate de tener Docker y Docker Compose instalados.
2.  Desde la raíz del proyecto, ejecuta el siguiente comando en tu terminal:
    ```sh
    docker-compose up -d
    ```
    Este comando creará y ejecutará un contenedor de MySQL en segundo plano. La base de datos estará disponible en `localhost:3307` con todo lo necesario para que la aplicación se conecte automáticamente.

> Con este método, puedes ignorar el paso 1 de la sección de configuración manual.

---

## Guía de Endpoints

A continuación se detalla cómo probar cada endpoint.

### 1. Obtener todos los entrenadores

*   **Acción:** Devuelve una lista de todos los entrenadores registrados.
*   **Método:** `GET`
*   **URL:** `http://localhost:8080/api/trainers`
*   **Body:** Ninguno.

### 2. Crear un nuevo entrenador

*   **Acción:** Registra un nuevo entrenador en la base de datos.
*   **Method:** `POST`
*   **URL:** `http://localhost:8080/api/trainers`
*   **Body:** `JSON`
    ```json
    {
        "name": "Ash Ketchum"
    }
    ```

### 3. Asignar un Pokémon a un entrenador

*   **Acción:** Busca un Pokémon en la PokeAPI y lo asigna a un entrenador existente.
*   **Método:** `POST`
*   **URL:** `http://localhost:8080/api/trainers/1/pokemon` (reemplaza `1` por el ID del entrenador).
*   **Body:** `Texto Plano (raw/text)`
    > **Importante:** El cuerpo de esta petición no es JSON, es simplemente el nombre del Pokémon como texto.
    ```
    pikachu
    ```

### 4. Obtener un entrenador por su ID

*   **Acción:** Devuelve los detalles de un entrenador específico, incluyendo su lista de Pokémon.
*   **Método:** `GET`
*   **URL:** `http://localhost:8080/api/trainers/1` (reemplaza `1` por el ID del entrenador que quieres consultar).
*   **Body:** Ninguno.

### 5. Eliminar un Pokémon de un entrenador

*   **Acción:** Elimina un Pokémon del equipo de un entrenador.
*   **Método:** `DELETE`
*   **URL:** `http://localhost:8080/api/trainers/1/pokemon/pikachu` (reemplaza `1` por el ID del entrenador y `pikachu` por el nombre del Pokémon a eliminar).
*   **Body:** Ninguno.
