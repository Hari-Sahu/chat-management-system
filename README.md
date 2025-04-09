# chat-management-system

# 📦 CMS Backend – Spring Boot Application

This is a Spring Boot project built with Java 17 and Spring Boot 3.4.4. It includes features such as API Key validation, JWT authentication, user management, and account activity tracking (login/logout/password updates).

---

## 🚀 Tech Stack

- Java 17
- Spring Boot 3.4.4
- Spring Security
- Spring Data JPA
- JWT (JSON Web Token)
- PostgreSQL
- Maven

---

## ⚙️ Prerequisites

Make sure you have the following installed:

- Java 17+
- Maven 3.8+
- IDE (e.g., IntelliJ IDEA, Eclipse)
- Database (PostgreSQL)
- Git

---

## 🛠️ How to Run Locally

### 1. Create database

login into postgres database console using db credential
create database using below command
CREATE DATABASE chat-management-system;

### 2. Clone the Repository

git clone https://github.com/Hari-Sahu/chat-management-system.git
cd chat-management-system
configure the database information in application.properties under src/main/resources

### 3. Build the Project
mvn clean install

### 4. Run the Application
java -Dspring.profiles.active=local -jar target/chat-management-system-1.0.0.jar


## 🛠️ Testing the API
Every request must include an X-API-KEY header, placed in application.properties
Use /auth/login to obtain JWT token.
Use token in Authorization header for secured routes
JWT token is required for protected endpoints.