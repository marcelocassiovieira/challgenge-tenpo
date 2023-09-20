# Servicio Challenge_Tenpo

## Descripción

El servicio Challenge_Tenpo es una aplicación Spring Boot que proporciona dos endpoints para realizar operaciones matemáticas y visualizar el historial de operaciones realizadas.

## Endpoints

### 1. Realizar una Operación

Endpoint: `POST http://localhost:8000/api`

Descripción: Este endpoint permite realizar una operación matemática. Debes enviar un cuerpo en formato JSON con dos operandos:

```json
{
    "operandOne": 3000,
    "operandTwo": 300
}
```

## Despliegue

El proyecto está dockerizado y la imagen se encuentra disponible en un repositorio público de Docker Hub.

### Pasos para Desplegar

1. **Instalación de Docker**:
   Asegúrate de tener Docker instalado en tu sistema. Puedes descargarlo desde [el sitio oficial de Docker](https://www.docker.com/get-started).

2. **Ejecutar con Docker Compose**:

```bash
   docker-compose up
```
Esto levantará el servicio `challenge_tenpo` junto con una instancia de PostgreSQL.

## Acceso a la Aplicación

Una vez desplegado, puedes acceder a la aplicación a través de los siguientes endpoints:

- Realizar una Operación: `POST http://localhost:8000/api`
- Historial de Operaciones: `GET http://localhost:8000/api/history?page=0&pageSize=5`

## Documentación de Swagger

La documentación de Swagger proporciona una interfaz interactiva para explorar y probar los endpoints de la API.

### Acceso a Swagger

Una vez que el servicio esté desplegado, puedes acceder a la documentación de Swagger a través del siguiente enlace:

####  http://localhost:8000/swagger-ui.html



En la interfaz de Swagger, podrás ver una lista de los endpoints disponibles y sus descripciones. Puedes probar cada endpoint directamente desde la interfaz.

---

## Recursos Utilizados en la Aplicación

La aplicación hace uso de los siguientes recursos para garantizar su funcionamiento de manera eficiente y confiable:

### 1. Servicio Mock Externo

- **Descripción**: Se ha integrado un servicio mock externo alojado en [Mockable.io](https://www.mockable.io/). Este servicio devuelve un porcentaje definido del 10% para simular un escenario específico.
- **Uso**: El servicio es utilizado para emular un comportamiento específico en situaciones controladas.

### 2. Cache de 30 Minutos

- **Descripción**: La aplicación implementa una caché con una duración de 30 minutos para el servicio mencionado anteriormente. Esto ayuda a mejorar el rendimiento al reducir la necesidad de realizar múltiples solicitudes al servicio mock externo en un corto período de tiempo.
- **Beneficio**: La caché reduce la carga en el servicio externo y mejora la respuesta de la aplicación.

### 3. Estrategia de Retries y Recovery desde la Base de Datos

- **Descripción**: En caso de que se produzca un error al acceder al servicio externo, la aplicación implementa una estrategia de reintentos (retry) que intenta la solicitud hasta tres veces antes de realizar un proceso de recuperación a partir de una base de datos de respaldo.
- **Beneficio**: Esta estrategia garantiza la disponibilidad y fiabilidad del servicio incluso en situaciones de fallo del servicio externo.

### 4. Límite de Tres Solicitudes por Minuto

- **Descripción**: El API de la aplicación tiene un límite de tres solicitudes por minuto para evitar sobrecargas y garantizar un uso equitativo de los recursos.
- **Consideración**: Los usuarios deben tener en cuenta este límite al interactuar con el API.

### 5. Documentación con Swagger

- **Descripción**: La aplicación proporciona una documentación interactiva de sus API a través de Swagger. Esta herramienta permite explorar y probar los endpoints de manera sencilla y eficiente.
- **Acceso**: La documentación de Swagger está disponible en [Swagger UI](http://localhost:8000/swagger-ui.html), donde `localhost:8000` representa la URL de tu servicio.

---
