# 📌 milestones - API de Gestión de Usuarios y Tareas

Este proyecto es una API RESTful construida con **Java 21**, **Spring Boot 3.5**, **JWT** y **Maven**, que permite gestionar usuarios y tareas con roles `ADMIN` y `USER`.

---

## 📚 Endpoints Documentados

La documentación OpenAPI (Swagger) está disponible en:

```
http://localhost:8080/milestones/api/swagger-ui/index.html
```

---

## ✅ Requisitos

- Java 21
- Maven 3.9+
- MySQL 8+
- Docker (opcional)

---

## ⚙️ Configuración Local

### 1. Clonar el repositorio

```bash
git clone https://github.com/jhordanof/milestones.git
cd milestones
```

### 2. Configuración en `application.properties`

#### ▶️ MySQL local:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/taskdb?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=123456
```

#### ▶️ MySQL Docker:

```properties
spring.datasource.url=jdbc:mysql://db:3306/taskdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=123456
```

---

## Docker Desktop
**Para eliminar cache:**
```docker system prune -f```

**Para detener:**
```docker-compose down -v```

**Para iniciar:**
```docker-compose up --build```

## 🧪 Pruebas

Ejecutar pruebas unitarias:

```bash
mvn test
```

✅ Las pruebas también se ejecutan automáticamente en GitHub Actions.

---

## 🔐 Seguridad

Todos los endpoints requieren JWT **excepto**:
- `/auth/**`
- `/swagger-ui.html`

El token JWT se debe enviar como:

```http
Authorization: Bearer <token>
```

---

## 📁 Estructura del Proyecto

```text
src
├── main
│   ├── java
│   │   └── com/project/redpontis
│   │       ├── api
│   │       │   ├── controller
│   │       │   └── interfaces
│   │       ├── config
│   │       ├── dto
│   │       ├── entity
│   │       ├── mapper
│   │       │   └── impl
│   │       ├── repository
│   │       │   └── impl
│   │       ├── security
│   │       ├── service
│   │       │   └── impl
│   └── resources
│       └── application.properties
└── test
    └── java
        └── com/project/redpontis/service/impl
            ├── AuthServiceImplTest.java
            ├── TaskServiceImplTest.java
            └── UserServiceImplTest.java
```

---

## 🎯 Uso de la API

### 🔐 Login

```http
POST /api/auth/login
Content-Type: application/json
```

```json
{
  "username": "jhordano",
  "password": "123456"
}
```

---

### 🧑‍💻 Registrar Usuario

```http
POST /api/auth/register
Content-Type: application/json
```

```json
{
  "username": "jhordano",
  "password": "123456",
  "role": "ADMIN"
}
```

---

### ✏️ Actualizar Usuario

```http
PUT /api/users/1
Content-Type: application/json
Authorization: Bearer {JWT_TOKEN}
```

```json
{
  "username": "jhordano",
  "password": "123456",
  "role": "ADMIN"
}
```

---

### ✅ Crear Tarea

```http
POST /api/tasks
Content-Type: application/json
Authorization: Bearer {JWT_TOKEN}
```

```json
{
  "title": "Estudiar JWT",
  "description": "Implementación de seguridad",
  "completed": true,
  "userId": 1
}
```

---

## Actuator & Prometheus

La API expone métricas y estado de la aplicación usando Spring Boot Actuator.

- URL base: `http://localhost:8080/milestones/api/actuator`
- Endpoint de Prometheus: `/actuator/prometheus`

Ejemplo:
```
http://localhost:8080/milestones/api/actuator/health
http://localhost:8080/milestones/api/actuator/prometheus
```

---

### Cómo levantar el microservicio

```bash
- cd python-microservice

- python -m venv venv
- venv\Scripts\activate
- pip install fastapi uvicorn
- pip install -r requirements.txt

- uvicorn main:app --reload --port 8001
```

### 🔁 Microservicio Python

**Ping**:

```http
GET http://localhost:8001/ping

Response:
{
  "response_time": 0.12
}
```

**Convertidor**:

```http
POST http://localhost:8001/convert
Content-Type: application/json

{
  "text": "Hola Jhordano",
  "to_upper": true
}

Response:
{
  "original": "Hola Jhordano",
  "convertido": "HOLA JHORDANO"
}
```

---

### 🔁 Api Python Consumo desde Java

**Ping**:

```http
GET http://localhost:8080/milestones/api/python/ping

Response:
{
  "Tiempo: 0.0 ms"
}
```
**Convertidor**:
```http
POST http://localhost:8080/milestones/api/python/convert
Content-Type: application/json

{
  "text": "Hola Jhordano",
  "to_upper": true
}

Response:
{original=Hola Jhordano, convertido=HOLA JHORDANO}
```

---

## 👨‍💻 Autor

**Jhordano Flores**  
Backend Developer  
GitHub: [@jhordanof](https://github.com/jhordanof)
