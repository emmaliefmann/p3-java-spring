# Rental Management API

## Overview

This project is a Rental Management API built with Spring Boot and Angular. It allows users to register, manage rental properties, and upload images for each listing. The backend is built using Java Spring Boot, with PostgreSQL as the database and JWT authentication for security.

## Features

- User Authentication (Registration & Login with JWT)
- Rental Property Management
- Create, update, delete, and view rental listings
- Upload images for rentals
- User Profile Management
- Secure API Endpoints with JWT authentication
- A Swagger UI to easily test endpoints, available here : [Swagger UI] (http://localhost:3001/api/swagger-ui/index.html)

## Tech Stack

### Backend:

- Spring Boot (Java)
- Spring Security (JWT Authentication)
- Spring Data JPA (SQL)
- ModelMapper (DTO Mapping)
- Multipart File Handling (Image Uploads)

### Frontend:

- Angular
- TypeScript

## Setup & Installation

## Backend Setup

**Prerequisites:**

- Java 17+
- SQL

**Steps:**

Clone the repository:
```
git clone https://github.com/your-repo.git
```
```
cd backend
```
Create a local database by importing the SQL script from #YOUR_FILE_PATH/ressources/sql.script into your SQL tool of choice.
Configure the database in `application.properties`, replacing each variable with your local information or local environment variables:
```
spring.datasource.url= #URL_LOCAL_DB
spring.datasource.username= #YOUR_USERNAME
spring.datasource.password= #YOUR_PASSWORD
```
For file storage during local development, you will need to declare a local folder where images will be stored. 
Alter the path at the variable **UPLOAD_DIR** in FileStorageService.java

Install the project
```
mvn clean install
```

Run the application:
```
mvn spring-boot:run
```
### Frontend Setup

**Prerequisites:**

- Node.js & npm
- Angular CLI

Steps:

Navigate to the frontend folder:
```
cd frontend
```
Install dependencies:
```
npm install
```
Run the Angular application:
```
ng serve
```
