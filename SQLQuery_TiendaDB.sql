-- CREAMOS LA BASE DE DATOS
Create table TiendaDB

-- USAMOS LA BD
Use TiendaDB

--SE CREA LA TABLA DE PRODUCTOS
Create table products(
id INT IDENTITY(1,1) PRIMARY KEY,
name VARCHAR(100) NOT NULL,
price DECIMAL(10,2) NOT NULL,
stock INT NOT NULL
);

-- INSERTAMOS ALGUNOS REGISTROS
INSERT INTO products (name, price, stock)
VALUES ('Carne',20000, 15)
INSERT INTO products (name, price, stock)
VALUES ('Arroz',15000, 10)
INSERT INTO products (name, price, stock)
VALUES ('Queso',5000, 25)
INSERT INTO products (name, price, stock)
VALUES ('Pan',3000, 10)
INSERT INTO products (name, price, stock)
VALUES ('Leche',2500, 8)
INSERT INTO products (name, price, stock)
VALUES ('Yogurt',4000, 5)

SELECT * FROM products

-- OBTENER LOS PRODUCTOS CON STOCK MENOR A 10 
SELECT * FROM products WHERE stock < 10

-- ACTUALIZAR EL PRECIO SEGÚN SU ID
UPDATE products
SET price = 3800
WHERE id = 7;

