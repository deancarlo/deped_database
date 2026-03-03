# DepEd Database API

A Spring Boot REST API for managing and verifying DepEd student database records. This application provides endpoints to verify student information using the Learner Reference Number (LRN).

**Live API:** https://deped-database.onrender.com/deped/api/v1

---

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Setup & Installation](#setup--installation)
- [Configuration](#configuration)
- [Database](#database)
- [API Endpoints](#api-endpoints)
- [Deployment](#deployment)
- [Environment Variables](#environment-variables)

---

## 📌 Project Overview

The DepEd Database API is a backend microservice designed to:
- Verify student information in the DepEd database
- Check Learner Reference Numbers (LRN) with student details
- Provide a RESTful interface for querying student records

This application is built with **Spring Boot 4.0.3** and uses **PostgreSQL** hosted on **Supabase** for data persistence.

---

## 🛠 Technology Stack

| Component | Version |
|-----------|---------|
| Java | 21 |
| Spring Boot | 4.0.3 |
| Spring Data JPA | Latest |
| PostgreSQL | Latest |
| Maven | Latest |
| Lombok | Latest |

---

## 📦 Spring Dependencies

### Core Dependencies

```xml
<!-- Spring Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- Spring Web MVC -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webmvc</artifactId>
</dependency>

<!-- PostgreSQL JDBC Driver -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- Lombok (Annotation processing) -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

### Test Dependencies

```xml
<!-- Spring Data JPA Test -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa-test</artifactId>
    <scope>test</scope>
</dependency>

<!-- Spring Web MVC Test -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webmvc-test</artifactId>
    <scope>test</scope>
</dependency>
```

---

## Prerequisites

- **Java 21** or higher
- **Maven 3.6+**
- **Git**
- **Supabase Account** (for database)

---

## 🚀 Setup & Installation

### 1. Clone the Repository

```bash
git clone https://github.com/deancarlo/deped_database.git
cd deped_database
```

### 2. Install Dependencies

```bash
mvn clean install
```

### 3. Configure Environment Variables

Create a `.env` file in the root directory or set system environment variables:

```env
DB_URL=jdbc:postgresql://aws-1-ap-southeast-1.pooler.supabase.com:5432/postgres?sslmode=require
DB_USER=postgres.<your-supabase-user>
DB_PASSWORD=<your-supabase-password>
PORT=8080
```

### 4. Run the Application

```bash
# Using Maven
mvn spring-boot:run

# Or build and run JAR
mvn package
java -jar target/deped_database-0.0.1-SNAPSHOT.jar
```

The API will be available at: `http://localhost:8080/deped/api/v1`

---

## 🔧 Configuration

### Application Configuration (application.yaml)

```yaml
spring:
  application:
    name: deped_database
  datasource:
    url: ${DB_URL:jdbc:postgresql://aws-1-ap-southeast-1.pooler.supabase.com:5432/postgres?sslmode=require}
    username: ${DB_USER:postgres.rgokduylkenylibgizpy}
    password: ${DB_PASSWORD}
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: none
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        '[format_sql]': true

server:
  port: ${PORT:8080}
```

### Key Configuration Explanation

| Configuration | Description |
|--------------|-------------|
| `DDL-auto: none` | Schema updates are disabled; managed manually |
| `show-sql: true` | SQL queries are logged to console |
| `PostgreSQLDialect` | Uses PostgreSQL-specific Hibernate dialect |
| `sslmode=require` | SSL connection required for security |

---

## 🗄️ Database

### Supabase Configuration

- **Provider:** Supabase (PostgreSQL)
- **Region:** Asia Southeast 1 (Singapore)
- **Connection Pool:** Enabled for performance
- **SSL Mode:** Required

### Database Connection Details

```
Host: aws-1-ap-southeast-1.pooler.supabase.com
Port: 5432
Database: postgres
Driver: PostgreSQL JDBC Driver
```

### Database Schema

#### Table: `deped_student_database`

| Column | Type | Nullable | Description |
|--------|------|----------|-------------|
| lrn | BIGINT | NO | Primary Key - Learner Reference Number |
| first_name | VARCHAR | NO | Student's first name |
| last_name | VARCHAR | NO | Student's last name |
| date_of_birth | DATE | NO | Student's date of birth |
| grade_level | INTEGER | NO | Current grade level |
| school_name | VARCHAR | NO | School name enrolled |
| status | VARCHAR | NO | Student status (default: Active) |
| gender | VARCHAR | NO | Student's gender |

---

## 🔌 API Endpoints

### Base URL
```
https://deped-database.onrender.com/deped/api/v1
```

### 1. Health Check

**Endpoint:** `GET /`

**Description:** Check if the API is operational

**Response:**
```
API is working
```

**cURL Example:**
```bash
curl https://deped-database.onrender.com/deped/api/v1/
```

---

### 2. Check LRN (Verify Student)

**Endpoint:** `GET /check-lrn`

**Description:** Verify if a student exists in the database

**Query Parameters:**

| Parameter | Type | Required | Example |
|-----------|------|----------|---------|
| lrn | Long | Yes | 123456789012345 |
| firstname | String | Yes | Juan |
| lastname | String | Yes | Dela Cruz |
| date_of_birth | String (YYYY-MM-DD) | Yes | 2010-05-15 |

**Response:**
```
Exist
```
or
```
Student not exist
```

**cURL Example:**
```bash
curl "https://deped-database.onrender.com/deped/api/v1/check-lrn?lrn=123456789012345&firstname=Juan&lastname=DelaCruz&date_of_birth=2010-05-15"
```

**JavaScript/Fetch Example:**
```javascript
const response = await fetch(
  'https://deped-database.onrender.com/deped/api/v1/check-lrn?lrn=123456789012345&firstname=Juan&lastname=DelaCruz&date_of_birth=2010-05-15'
);
const result = await response.text();
console.log(result); // "Exist" or "Student not exist"
```

---

## 🚀 Deployment

### Hosted on Render

The application is deployed on **Render.com** and accessible at:

```
https://deped-database.onrender.com/deped/api/v1
```

#### Deployment Configuration

- **Platform:** Render (PaaS)
- **Build Command:** `mvn clean install`
- **Start Command:** `java -jar target/deped_database-0.0.1-SNAPSHOT.jar`
- **Java Version:** 21
- **AutoDeploy:** Enabled from main branch

#### Environment Variables on Render

Set the following environment variables in Render dashboard:

```
DB_URL=jdbc:postgresql://aws-1-ap-southeast-1.pooler.supabase.com:5432/postgres?sslmode=require
DB_USER=postgres.<your-user>
DB_PASSWORD=<your-password>
PORT=8080
```

---

## 🔐 Environment Variables

| Variable | Description | Default | Required |
|----------|-------------|---------|----------|
| `DB_URL` | PostgreSQL JDBC connection URL | (See config) | Yes |
| `DB_USER` | Database username | postgres.rgokduylkenylibgizpy | Yes |
| `DB_PASSWORD` | Database password | (None) | Yes |
| `PORT` | Server port | 8080 | No |

### Local Development

```bash
# Linux/Mac
export DB_URL="jdbc:postgresql://aws-1-ap-southeast-1.pooler.supabase.com:5432/postgres?sslmode=require"
export DB_USER="postgres.rgokduylkenylibgizpy"
export DB_PASSWORD="your_password_here"

# Windows PowerShell
$env:DB_URL = "jdbc:postgresql://aws-1-ap-southeast-1.pooler.supabase.com:5432/postgres?sslmode=require"
$env:DB_USER = "postgres.rgokduylkenylibgizpy"
$env:DB_PASSWORD = "your_password_here"
```

---

## Project Structure

```
deped_database/
├── src/
│   ├── main/
│   │   ├── java/com/github/deancarlo/deped_database/
│   │   │   ├── DepedDatabaseApplication.java      (Main entry point)
│   │   │   ├── controller/
│   │   │   │   └── DepedStudentController.java    (REST endpoints)
│   │   │   ├── entity/
│   │   │   │   └── DepedStudent.java              (JPA entity)
│   │   │   └── repository/
│   │   │       └── DepedStudentRepository.java    (Data access layer)
│   │   └── resources/
│   │       └── application.yaml                    (Configuration)
│   └── test/
│       └── java/...                                (Unit tests)
├── pom.xml                                         (Maven configuration)
├── Dockerfile                                      (Container configuration)
├── mvnw & mvnw.cmd                                (Maven wrapper scripts)
└── README.md                                       (This file)
```

---

## 📝 Building & Running

### Build the Project

```bash
mvn clean package
```

### Run Locally

```bash
mvn spring-boot:run
```

### Build Docker Image

```bash
docker build -t deped-database:latest .
```

### Run Docker Container

```bash
docker run -e DB_URL="..." -e DB_USER="..." -e DB_PASSWORD="..." -p 8080:8080 deped-database:latest
```

---

## 🐛 Troubleshooting

### Connection Issues to Supabase

- Verify `DB_URL`, `DB_USER`, and `DB_PASSWORD` are correct
- Ensure IP whitelist includes your server/local machine in Supabase settings
- Check that SSL mode is set to `require`

### API Not Responding

- Verify the application is running: `mvn spring-boot:run`
- Check if port 8080 is available
- Review logs for error messages

### Insufficient Permissions

- Ensure the PostgreSQL user has SELECT permissions on the `deped_student_database` table

---

## 📧 Contact & Support

For issues or questions, please contact the development team or open an issue on GitHub.

---

## 📄 License

This project is part of the DepEd Database initiative.

---

**Last Updated:** March 2026
**Version:** 0.0.1-SNAPSHOT
