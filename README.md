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

## 👨‍💻 Autor

**Jhordano Flores**  
Backend Developer  
GitHub: [@jhordanof](https://github.com/jhordanof)
