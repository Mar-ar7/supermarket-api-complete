Supermarket API – Spring Boot + PostgreSQL

• Gestión de sucursales (Branches)
• Gestión de productos
• Registro de ventas con múltiples productos
• Reportes: ventas por sucursal y productos más vendidos

El sistema utiliza Spring Data JPA, PostgreSQL, Lombok y Maven.

-------------------------------------
REQUERIMIENTOS
-------------------------------------
• Java 17
• PostgreSQL o Docker
• Maven 3.8+
• Postman opcional

-------------------------------------
CONFIGURACIÓN DE POSTGRESQL
-------------------------------------
Opción A — Local:
CREATE DATABASE supermarket_db;

Opción B — Docker:
docker run --name postgres-supermarket -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=supermarket_db -p 5432:5432 -d postgres:15

-------------------------------------
application.properties
-------------------------------------
spring.datasource.url=jdbc:postgresql://localhost:5432/supermarket_db
spring.datasource.username=postgres
spring.datasource.password=admin
spring.jpa.hibernate.ddl-auto=update
server.port=8080

-------------------------------------
CÓMO CORRER EL PROYECTO
-------------------------------------
Opción 1: IntelliJ
Ejecutar SupermarketApiApplication

Opción 2: Terminal
mvn clean install
mvn spring-boot:run

Lombok: Settings → Build → Compiler → Annotation Processors
http://localhost:8080/api/v1/products/
http://localhost:8080/api/v1/branches/
http://localhost:8080/api/v1/sales/branch/1

-------------------------------------
ENDPOINTS PRINCIPALES
-------------------------------------
Branches:
POST /api/v1/branches/
GET /api/v1/branches/

Products:
POST /api/v1/products/
GET /api/v1/products/

Sales:
POST /api/v1/sales/
GET /api/v1/sales/

Reportes:
GET /api/v1/reports/branch-sales?branch_id=1&start=2025-01-01&end=2025-12-31
GET /api/v1/reports/top-products?limit=5

-------------------------------------
SCRIPT SQL
-------------------------------------
CREATE TABLE branches (
branch_id SERIAL PRIMARY KEY,
name VARCHAR(100),
address VARCHAR(200),
phone VARCHAR(30),
branch_code VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE products (
product_id SERIAL PRIMARY KEY,
name VARCHAR(100),
category VARCHAR(100),
price NUMERIC(10,2),
barcode VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE sales (
sale_id SERIAL PRIMARY KEY,
datetime TIMESTAMP,
branch_id INTEGER REFERENCES branches(branch_id),
total NUMERIC(10,2)
);

CREATE TABLE sale_details (
sale_id INTEGER REFERENCES sales(sale_id),
product_id INTEGER REFERENCES products(product_id),
quantity INTEGER,
unit_price NUMERIC(10,2),
subtotal NUMERIC(10,2),
PRIMARY KEY (sale_id, product_id)
);

