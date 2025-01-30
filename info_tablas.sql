-- Se crea la base de datos proyecto
CREATE DATABASE proyecto;

 -- Creamos la tabla de productos
CREATE TABLE IF NOT EXISTS productos(
	id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    precio DOUBLE NOT NULL,
    stock INT NOT NULL,
    fecha_caducidad DATE NOT NULL
);


-- Creamos la tabla de pedidos_proveedores
USE proyecto;
CREATE TABLE IF NOT EXISTS pedidos_proveedores(
	id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_proveedor INT NOT NULL,
    fecha_pedido DATE NOT NULL,
    precio_total DOUBLE NOT NULL,
   FOREIGN KEY(id_proveedor) REFERENCES proveedores(id_proveedor)
);

-- Creamos la tabla de proveedores
USE proyecto;
CREATE TABLE IF NOT EXISTS proveedores(
	id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    contacto CHAR(9) NOT NULL,
    direccion VARCHAR(70) NOT NULL,
    id_pedido INT NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedidos_proveedores(id_pedido)
);

-- Creamos la tabla de ventas
USE proyecto;
CREATE TABLE IF NOT EXISTS ventas(
	id_venta INT AUTO_INCREMENT PRIMARY KEY,
    fecha_venta DATE NOT NULL,
    precio_total DOUBLE NOT NULL
);

-- Creamos la tabla de empleados
USE proyecto;
CREATE TABLE IF NOT EXISTS empleados(
	id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    id_departamento INT NOT NULL,
    direccion VARCHAR(70) NOT NULL,
    contacto CHAR(9) NOT NULL,
    FOREIGN KEY(id_departamento) REFERENCES departamentos (id_departamento)
);

-- Creamos la tabla de clientes
USE proyecto;
CREATE TABLE IF NOT EXISTS clientes(
	id_clientes INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    direccion VARCHAR(70) NOT NULL,
    contacto CHAR(9) NOT NULL,
    id_venta INT NOT NULL,
    FOREIGN KEY(id_venta) REFERENCES ventas(id_venta)
);

-- Creamos la tabla de departamentos
USE proyecto;
CREATE TABLE IF NOT EXISTS departamentos(
	id_departamento INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

-- Insertamos datos en la tabla departamentos
USE proyecto;
INSERT INTO departamentos (nombre) VALUES
('Recursos Humanos'),
('Finanzas'),
('Marketing'),
('Ventas'),
('Desarrollo de Software'),
('Atención al Cliente'),
('Logística'),
('Producción'),
('Legal'),
('Investigación y Desarrollo');
SELECT * FROM departamentos;

-- Insertamos datos en la tabla productos
USE proyecto;
INSERT INTO productos (nombre, categoria, precio, stock, fecha_caducidad) VALUES
('Manzana', 'Frutas', 1.20, 50, '2025-06-15'),
('Leche', 'Lácteos', 2.50, 30, '2025-02-10'),
('Pan', 'Panadería', 1.00, 40, '2025-02-05'),
('Arroz', 'Granos', 3.80, 25, '2026-01-20'),
('Jabón', 'Limpieza', 4.50, 60, '2027-12-31'),
('Pasta Dental', 'Higiene', 3.20, 35, '2026-11-18'),
('Queso', 'Lácteos', 5.75, 20, '2025-04-25'),
('Cereal', 'Desayuno', 4.90, 15, '2025-08-12'),
('Aceite de Oliva', 'Aceites', 10.00, 10, '2026-09-30'),
('Galletas', 'Snacks', 2.30, 45, '2025-07-22'),
('Yogur', 'Lácteos', 1.80, 25, '2025-03-18'),
('Tomate', 'Verduras', 1.50, 55, '2025-01-30'),
('Papel Higiénico', 'Higiene', 6.00, 40, '2028-01-01'),
('Papas Fritas', 'Snacks', 2.10, 35, '2025-06-22'),
('Refresco Cola', 'Bebidas', 1.75, 50, '2025-09-15'),
('Café Molido', 'Bebidas', 8.20, 20, '2026-02-28'),
('Té Verde', 'Bebidas', 5.30, 15, '2026-05-10'),
('Harina', 'Granos', 3.00, 30, '2025-12-01'),
('Azúcar', 'Granos', 2.80, 45, '2026-06-15'),
('Sal', 'Condimentos', 1.50, 50, '2027-03-05'),
('Pimienta', 'Condimentos', 4.00, 20, '2027-04-18'),
('Pollo', 'Carnes', 7.50, 15, '2025-01-25'),
('Carne de Res', 'Carnes', 12.00, 10, '2025-01-28'),
('Pescado', 'Carnes', 9.80, 8, '2025-02-01'),
('Zanahoria', 'Verduras', 1.30, 60, '2025-02-10'),
('Cebolla', 'Verduras', 1.20, 70, '2025-02-15'),
('Pimiento', 'Verduras', 2.00, 50, '2025-03-01'),
('Detergente', 'Limpieza', 7.00, 40, '2027-10-20'),
('Suavizante', 'Limpieza', 5.50, 30, '2027-11-05'),
('Chocolate', 'Dulces', 3.50, 25, '2025-09-30'),
('Helado', 'Dulces', 6.50, 10, '2025-04-15'),
('Mantequilla', 'Lácteos', 4.25, 18, '2025-05-10'),
('Atún en Lata', 'Enlatados', 2.75, 40, '2026-07-12'),
('Sardinas', 'Enlatados', 3.20, 35, '2026-08-20'),
('Fideos', 'Granos', 2.60, 45, '2026-03-25'),
('Vinagre', 'Condimentos', 3.10, 25, '2026-12-10'),
('Mayonesa', 'Condimentos', 4.60, 30, '2025-11-30'),
('Kétchup', 'Condimentos', 3.80, 28, '2025-10-05'),
('Mostaza', 'Condimentos', 3.50, 20, '2025-09-12'),
('Jugo de Naranja', 'Bebidas', 4.90, 15, '2025-07-15'),
('Agua Mineral', 'Bebidas', 1.20, 60, '2026-01-01'),
('Cerveza', 'Bebidas', 2.80, 50, '2025-06-01'),
('Vino Tinto', 'Bebidas', 15.00, 12, '2027-08-20'),
('Whisky', 'Bebidas', 35.00, 5, '2030-12-31'),
('Champú', 'Higiene', 6.75, 25, '2028-05-15'),
('Acondicionador', 'Higiene', 7.00, 20, '2028-06-10'),
('Desodorante', 'Higiene', 5.50, 30, '2028-07-05'),
('Crema Corporal', 'Higiene', 9.00, 15, '2028-08-12');
SELECT * FROM productos;

-- Insertamos datos en la tabla proveedores
USE proyecto;
INSERT INTO proveedores (nombre, contacto, direccion, id_pedido) VALUES
('Distribuidora López', '912345678', 'Calle Falsa 123, Madrid', 1),
('Suministros Martínez', '923456789', 'Av. Central 45, Barcelona', 2),
('Grupo Comercial Pérez', '934567890', 'Calle Mayor 78, Valencia', 3),
('Importaciones Global', '945678901', 'Plaza Principal 5, Sevilla', 4),
('Exportadora García', '956789012', 'Carrera 10 #15-20, Bogotá', 5),
('Almacenes Ramírez', '967890123', 'Boulevard Industrial 200, Monterrey', 6),
('Logística y Servicios', '978901234', 'Av. Reforma 300, Ciudad de México', 7),
('Comercializadora Ortega', '989012345', 'Calle Comercio 12, Lima', 8),
('Abastecedora Gómez', '990123456', 'Av. Panamericana 50, Buenos Aires', 9),
('Proveedora Ruiz', '901234567', 'Carretera Nacional km 25, Quito', 10),
('Surtidora Fernández', '912345670', 'Calle 5 de Mayo 100, Guadalajara', 11),
('Mercantil Hidalgo', '923456780', 'Paseo de la Reforma 250, CDMX', 12),
('Comercio Internacional', '934567890', 'Calle Nueva 45, Medellín', 13),
('Mayorista Sánchez', '945678900', 'Av. Independencia 34, Caracas', 14),
('Distribuciones Herrera', '956789010', 'Centro Comercial 2, Santiago', 15),
('Global Traders', '967890120', 'Zona Franca, San José', 16),
('Importadora Rivera', '978901230', 'Paseo Colón 67, San Salvador', 17),
('Almacenes González', '989012340', 'Blvd. Principal 12, Managua', 18),
('Logística Castro', '990123450', 'Calle de la Aduana 8, Santo Domingo', 19),
('Comercial Zúñiga', '901234560', 'Plaza Central 10, La Paz', 20);
SELECT * FROM proveedores;

-- Insertamos datos en la tabla pedidos proveedores
USE proyecto;
INSERT INTO pedidos_proveedores (id_proveedor, fecha_pedido, precio_total) VALUES
(1, '2025-01-10', 1500.75),
(2, '2025-01-12', 2300.50),
(3, '2025-01-14', 1750.30),
(4, '2025-01-16', 2900.00),
(5, '2025-01-18', 1200.40),
(6, '2025-01-20', 3100.80),
(7, '2025-01-22', 2500.60),
(8, '2025-01-24', 4000.90),
(9, '2025-01-26', 1850.75),
(10, '2025-01-28', 2200.00),
(11, '2025-02-01', 2750.45),
(12, '2025-02-03', 1950.30),
(13, '2025-02-05', 3200.25),
(14, '2025-02-07', 2800.60),
(15, '2025-02-09', 1500.00),
(16, '2025-02-11', 3500.85),
(17, '2025-02-13', 4100.20),
(18, '2025-02-15', 2600.50),
(19, '2025-02-17', 3000.00),
(20, '2025-02-19', 4500.75);
SELECT * FROM pedidos_proveedores;

-- Insertamos datos en la tabla ventas
USE proyecto;
INSERT INTO ventas (fecha_venta, precio_total) VALUES
('2025-01-01', 150.75),
('2025-01-02', 230.50),
('2025-01-03', 175.30),
('2025-01-04', 290.00),
('2025-01-05', 120.40),
('2025-01-06', 310.80),
('2025-01-07', 250.60),
('2025-01-08', 400.90),
('2025-01-09', 185.75),
('2025-01-10', 220.00),
('2025-01-11', 275.45),
('2025-01-12', 195.30),
('2025-01-13', 320.25),
('2025-01-14', 280.60),
('2025-01-15', 150.00),
('2025-01-16', 350.85),
('2025-01-17', 410.20),
('2025-01-18', 260.50),
('2025-01-19', 300.00),
('2025-01-20', 450.75),
('2025-01-21', 500.30),
('2025-01-22', 345.60),
('2025-01-23', 275.90),
('2025-01-24', 610.20),
('2025-01-25', 520.00),
('2025-01-26', 430.75),
('2025-01-27', 395.40),
('2025-01-28', 580.90),
('2025-01-29', 620.55),
('2025-01-30', 700.80);

SELECT * FROM ventas;

-- Insertamos datos en la tabla empleados
USE proyecto;
INSERT INTO empleados (nombre, id_departamento, direccion, contacto) VALUES
('Juan Pérez', 1, 'Calle El Sol 123, Madrid', '623456789'),
('María García', 2, 'Av. Libertad 45, Barcelona', '634567890'),
('Carlos Rodríguez', 3, 'Calle del Río 78, Valencia', '645678901'),
('Laura López', 4, 'Plaza Mayor 5, Sevilla', '656789012'),
('Pedro Martínez', 5, 'Calle San Juan 10, Bogotá', '667890123'),
('Ana Sánchez', 6, 'Boulevard Norte 200, Monterrey', '678901234'),
('Luis Hernández', 7, 'Calle de la Paz 300, Ciudad de México', '689012345'),
('Elena Gómez', 8, 'Calle del Sol 12, Lima', '690123456'),
('José Díaz', 9, 'Av. del Mar 50, Buenos Aires', '701234567'),
('Raúl Fernández', 10, 'Calle El Molino 25, Quito', '712345678'),
('Isabel González', 1, 'Calle del Viento 100, Guadalajara', '723456789'),
('David Romero', 2, 'Calle de las Flores 250, CDMX', '734567890'),
('Sara Álvarez', 3, 'Calle Nueva 45, Medellín', '745678901'),
('Mario Rodríguez', 4, 'Av. Los Andes 34, Caracas', '756789012'),
('Verónica Martínez', 5, 'Centro Comercial Plaza 2, Santiago', '767890123'),
('Javier Castro', 6, 'Avenida Central 50, San José', '778901234'),
('Patricia Ruiz', 7, 'Calle Colón 67, San Salvador', '789012345'),
('Ricardo Pérez', 8, 'Blvd. Juárez 12, Managua', '790123456'),
('Carlos Zúñiga', 9, 'Calle de la Paz 10, La Paz', '801234567'),
('Cristina Ramírez', 10, 'Calle del Sol 8, Santo Domingo', '812345678');

SELECT * FROM empleados;

-- Insertamos datos en la tabla clientes
USE proyecto;
INSERT INTO clientes (nombre, direccion, contacto, id_venta) VALUES
('Ana Gómez', 'Calle Falsa 101, Madrid', '612345678', 1),
('Carlos López', 'Av. Central 202, Barcelona', '623456789', 2),
('María Pérez', 'Calle Mayor 303, Valencia', '634567890', 3),
('Juan Díaz', 'Plaza Principal 404, Sevilla', '645678901', 4),
('Pedro Martínez', 'Calle San Juan 505, Bogotá', '656789012', 5),
('Laura Sánchez', 'Boulevard Industrial 606, Monterrey', '667890123', 6),
('Raúl Hernández', 'Av. Reforma 707, Ciudad de México', '678901234', 7),
('Isabel García', 'Calle Comercio 808, Lima', '689012345', 8),
('David Rodríguez', 'Av. Panamericana 909, Buenos Aires', '690123456', 9),
('Verónica Fernández', 'Carretera Nacional 1010, Quito', '701234567', 10),
('José Romero', 'Calle 5 de Mayo 1111, Guadalajara', '712345678', 11),
('Cristina Álvarez', 'Paseo Reforma 1212, CDMX', '723456789', 12),
('Mario González', 'Calle Nueva 1313, Medellín', '734567890', 13),
('Patricia Soto', 'Av. Independencia 1414, Caracas', '745678901', 14),
('Ricardo Pérez', 'Centro Comercial Plaza 1515, Santiago', '756789012', 15),
('Elena Ramírez', 'Avenida Libertad 1616, San José', '767890123', 16),
('Carlos Zúñiga', 'Calle Colón 1717, San Salvador', '778901234', 17),
('Sara Fernández', 'Blvd. Juárez 1818, Managua', '789012345', 18),
('Raúl Álvarez', 'Calle Viento 1919, La Paz', '790123456', 19),
('Luis Castro', 'Av. Los Andes 2020, Santo Domingo', '801234567', 20),
('Ana Hernández', 'Calle de la Paz 2121, Madrid', '812345678', 21),
('José Martínez', 'Avenida Sol 2222, Barcelona', '823456789', 22),
('María Romero', 'Calle del Río 2323, Valencia', '834567890', 23),
('Patricia Gómez', 'Plaza del Sol 2424, Sevilla', '845678901', 24),
('Juan Ramírez', 'Boulevard Norte 2525, Bogotá', '856789012', 25),
('Carlos Álvarez', 'Calle de la Luna 2626, Monterrey', '867890123', 26),
('Isabel Castro', 'Av. Reforma 2727, Ciudad de México', '878901234', 27),
('Verónica Pérez', 'Calle Comercial 2828, Lima', '889012345', 28),
('Raúl González', 'Calle del Mar 2929, Buenos Aires', '890123456', 29),
('José Ramírez', 'Carretera Nacional 3030, Quito', '901234567', 30),
('David Sánchez', 'Calle 5 de Mayo 3131, Guadalajara', '912345678', 1),
('Luis Romero', 'Paseo Reforma 3232, CDMX', '923456789', 2),
('Cristina Soto', 'Calle Nueva 3333, Medellín', '934567890', 3),
('Mario Pérez', 'Av. Independencia 3434, Caracas', '945678901', 4),
('Elena Zúñiga', 'Centro Comercial Plaza 3535, Santiago', '956789012', 5),
('José Álvarez', 'Avenida Libertad 3636, San José', '967890123', 6),
('Raúl Pérez', 'Calle Colón 3737, San Salvador', '978901234', 7),
('David Fernández', 'Blvd. Juárez 3838, Managua', '989012345', 8),
('Sara Ramírez', 'Calle Viento 3939, La Paz', '990123456', 9),
('Carlos Martínez', 'Av. Los Andes 4040, Santo Domingo', '801234568', 10),
('Patricia González', 'Calle de la Paz 4141, Madrid', '812345679', 11),
('Ricardo Romero', 'Avenida Sol 4242, Barcelona', '823456790', 12),
('José Zúñiga', 'Calle del Río 4343, Valencia', '834567891', 13),
('Luis Fernández', 'Plaza del Sol 4444, Sevilla', '845678902', 14),
('Cristina Álvarez', 'Boulevard Norte 4545, Bogotá', '856789013', 15),
('Raúl Ramírez', 'Calle de la Luna 4646, Monterrey', '867890124', 16),
('Verónica Pérez', 'Av. Reforma 4747, Ciudad de México', '878901235', 17),
('José Soto', 'Calle Comercial 4848, Lima', '889012346', 18),
('Patricia González', 'Calle del Mar 4949, Buenos Aires', '890123457', 19),
('Raúl Hernández', 'Carretera Nacional 5050, Quito', '901234568', 20);

SELECT * FROM clientes;