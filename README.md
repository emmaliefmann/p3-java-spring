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
- PostgreSQL

**Steps:**

Clone the repository:
```
git clone https://github.com/your-repo.git
```
```
cd backend
```
Configure the database in `application.properties` :
```
spring.datasource.url=jdbc:postgresql://localhost:5432/rentals_db
spring.datasource.username=your_username
spring.datasource.password=your_password
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
