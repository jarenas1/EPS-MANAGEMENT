# Sistema de Gestión de Citas para Clínica Privada

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![JWT](https://img.shields.io/badge/JWT-Authentication-orange)
![Docker](https://img.shields.io/badge/Docker-Containerization-lightblue)

## 📖 Overview
Sistema backend para gestión eficiente de citas médicas. Frontend **en desarrollo**.

## 🚀 Tabla de Contenidos
- [Funcionalidades](#funcionalidades)
- [Tecnologías](#tecnologías)
- [Instalación](#instalación)
- [Documentación API](#documentación-api)
- [Autenticación](#autenticación)
- [Variables de Entorno](#variables-de-entorno)

## ✔️ Funcionalidades
- Gestión centralizada de citas
- Control de disponibilidad médica
- Historial de pacientes
- Historial de citas
- Modificacion de citas
- Gestion de turnos de doctores
- Gestion de especialidades medicas
- Autenticación JWT

## 💻 Tecnologías
- **Backend**: Spring Boot 3.1.5, Spring Security
- **Base de Datos**: MySQL 8.0
- **Contenedores**: Docker

## 🛠️ Instalación
-- Configuración inicial --
1. Clonar repositorio:
```  
git clone https://github.com/jarenas1/EPS-MANAGEMENT.git
``` 
2. Ejecutar con Docker:
```  
docker-compose up -d
``` 

## 📚 Documentación API
-- Endpoints principales:
```

//-------------------------AUTH ENDPOINTS-------------------------
.requestMatchers(HttpMethod.POST, "/auth/log-in").permitAll()
.requestMatchers(HttpMethod.POST, "/auth/sign-up/**").hasRole("ADMIN")
.requestMatchers(HttpMethod.POST,  "/specialities/**").hasRole("ADMIN")
.requestMatchers(HttpMethod.DELETE,  "/specialities/**").hasRole("ADMIN")
.requestMatchers(HttpMethod.PUT,  "/specialities/**").hasRole("ADMIN")
.requestMatchers(HttpMethod.GET,  "/specialities/**").authenticated()

//-------------------------SHIFTS ENDPOINTS-------------------------
.requestMatchers(HttpMethod.GET, "/shifts/**").authenticated()
.requestMatchers(HttpMethod.POST, "/shifts/**").hasRole("ADMIN")
.requestMatchers(HttpMethod.PUT,  "/shifts/**").hasRole("ADMIN")
.requestMatchers(HttpMethod.DELETE,  "/shifts/**").hasRole("ADMIN")

//-------------------------PATIENT ENDPOINTS-------------------------
.requestMatchers(HttpMethod.PUT,  "/patients/**").hasAnyRole("ADMIN", "PATIENT")
.requestMatchers(HttpMethod.GET, "/patients/{id}").hasAnyRole("ADMIN", "PATIENT", "DOCTOR")
.requestMatchers(HttpMethod.DELETE, "/patients/{id}").hasAnyRole("ADMIN")
.requestMatchers(HttpMethod.GET,  "/patients").hasAnyRole("ADMIN")

//-------------------------DOCTOR ENDPOINTS-------------------------
.requestMatchers(HttpMethod.PUT,  "/doctors/{id}").hasAnyRole("ADMIN", "DOCTOR")
.requestMatchers(HttpMethod.DELETE,  "/doctors/{id}").hasAnyRole("ADMIN")
.requestMatchers(HttpMethod.GET,  "/doctors/**").hasAnyRole("ADMIN", "DOCTOR, PATIENT")
.requestMatchers(HttpMethod.GET,  "/patients").hasAnyRole("ADMIN", "DOCTOR, PATIENT")

//-------------------------DATE/APPOINTMENT ENDPOINTS-------------------------
.requestMatchers(HttpMethod.PUT,  "/dates/**").authenticated()
.requestMatchers(HttpMethod.DELETE,  "/dates/**").hasRole("ADMIN")
.requestMatchers(HttpMethod.GET,  "/dates/**").authenticated()
.requestMatchers(HttpMethod.PATCH,  "/dates/**").hasRole("DOCTOR")

``` 

## 🔒 Autenticación
-- Flujo JWT --
1. Login con credenciales
2. Obtener token
3. Usar en cabeceras

## ⚙️ Variables de Entorno
--  Ejemplo .env
```  
# JWT Configuration
SECURITY_JWT_PRIVATE=your_private_key_here
SECURITY_JWT_USER_GENERATOR=your_user_generator_here

# Database Configuration
SPRING_DATASOURCE_URL=jdbc:mysql://your_database_url_here:3306/your_database_name_here
SPRING_DATASOURCE_USERNAME=your_database_username_here
SPRING_DATASOURCE_PASSWORD=your_database_password_here
``` 

## 🤝 Contribución
1. Hacer fork
2. Crear branch
3. Enviar PR
