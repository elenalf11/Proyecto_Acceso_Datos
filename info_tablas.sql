-- Se crea la base de datos proyecto
CREATE DATABASE proyecto;

-- Creamos la tabla departamentos
USE proyecto;
CREATE TABLE IF NOT EXISTS departamentos (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL
);

-- Creamos la tabla proveedores
USE proyecto;
CREATE TABLE IF NOT EXISTS proveedores(
	id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    contacto CHAR(9) NOT NULL,
    direccion VARCHAR(45) NOT NULL
);

-- Creamos la tabla categoria
USE proyecto;
CREATE TABLE IF NOT EXISTS categorias(
	id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL
);

-- Creamos la tabla productos
USE proyecto;
CREATE TABLE IF NOT EXISTS productos(
	id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    id_categoria INT NOT NULL,
    id_proveedor INT NOT NULL,
    fecha_caducidad DATE NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES categorias (id_categoria),
    FOREIGN KEY (id_proveedor) REFERENCES proveedores (id_proveedor)
);


-- Creamos la tabla clientes
USE proyecto;
CREATE TABLE IF NOT EXISTS clientes(
	id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(40) NOT NULL,
    contacto CHAR(9) NOT NULL,
    direccion VARCHAR(45) NOT NULL
);

-- Creamos la tabla ventas
USE proyecto;
CREATE TABLE IF NOT EXISTS ventas(
	id_venta INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    fecha DATE NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes (id_cliente)
);

-- Creamos la tabla detalle ventas
USE proyecto;
CREATE TABLE IF NOT EXISTS detalle_ventas(
	id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_venta) REFERENCES ventas (id_venta),
    FOREIGN KEY (id_producto) REFERENCES productos (id_producto)
);

-- Creamos la tabla empleados
USE proyecto;
CREATE TABLE IF NOT EXISTS empleados(
	id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(40) NOT NULL,
    id_departamento INT NOT NULL,
    direccion VARCHAR(45) NOT NULL,
    contacto CHAR(9) NOT NULL,
    FOREIGN KEY (id_departamento) REFERENCES departamentos (id)
);

-- Creamos la tabla pedidos proveedor
USE proyecto;
CREATE TABLE IF NOT EXISTS pedidos_proveedor(
	id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_proveedor INT NOT NULL,
    fecha_pedido DATE NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_proveedor) REFERENCES proveedores(id_proveedor)
);

-- Creamos la tabla detalle pedidos proveedor
USE proyecto;
CREATE TABLE IF NOT EXISTS detalle_pedido_proveedor(
	id_detalle_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario_proveedor DECIMAL (10,2) NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedidos_proveedor(id_pedido),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
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

-- Insertamos datos en la tabla categoria
USE proyecto;
INSERT INTO categorias (nombre) VALUES
('Frutas y Verduras'),
('Carnes y Embutidos'),
('Pescados y Mariscos'),
('Lácteos y Huevos'),
('Panadería y Repostería'),
('Cereales y Legumbres'),
('Congelados'),
('Bebidas'),
('Dulces y Snacks'),
('Aceites y Condimentos'),
('Comida preparada'),
('Productos sin gluten'),
('Productos ecológicos'),
('Limpieza y Hogar'),
('Mascotas');
SELECT * FROM categorias;

-- Insertamos datos en la tabla productos
USE proyecto;
INSERT INTO productos (nombre, precio, stock, id_categoria, id_proveedor, fecha_caducidad) VALUES
('Manzanas', 1.50, 100, 1, 2, '2025-06-15'),
('Plátanos', 1.20, 120, 1, 2, '2025-06-10'),
('Naranjas', 1.80, 90, 1, 2, '2025-06-20'),
('Filete de Ternera', 12.50, 50, 2, 3, '2024-02-10'),
('Pollo Entero', 6.00, 60, 2, 17, '2024-02-08'),
('Salmón Fresco', 15.30, 40, 3, 5, '2024-02-05'),
('Merluza', 9.80, 45, 3, 5, '2024-02-06'),
('Leche Entera', 1.00, 150, 4, 4, '2024-03-01'),
('Queso Curado', 10.50, 70, 4, 4, '2024-07-01'),
('Pan Integral', 2.00, 200, 5, 6, '2024-02-05'),
('Croissant', 1.50, 180, 5, 6, '2024-02-04'),
('Lentejas', 3.20, 130, 6, 7, '2026-01-15'),
('Arroz Basmati', 2.80, 140, 6, 7, '2026-02-20'),
('Pizzas Congeladas', 5.50, 80, 7, 8, '2025-12-01'),
('Verduras Congeladas', 4.30, 100, 7, 8, '2025-12-10'),
('Refresco Cola', 1.80, 250, 8, 9, '2026-06-30'),
('Zumo de Naranja', 2.50, 200, 8, 16, '2024-05-20'),
('Chocolate con Leche', 3.00, 110, 9, 10, '2025-09-10'),
('Patatas Fritas', 2.20, 150, 9, 16, '2025-11-15'),
('Aceite de Oliva', 8.50, 90, 10, 11, '2027-12-31'),
('Sal Marina', 1.00, 120, 10, 1, '2027-10-10'),
('Platos Preparados', 7.20, 70, 11, 12, '2024-04-05'),
('Ensalada Lista', 3.80, 100, 11, 12, '2024-02-03'),
('Galletas Sin Gluten', 4.50, 60, 12, 18, '2025-08-20'),
('Pan Sin Gluten', 3.90, 80, 12, 18, '2024-03-15'),
('Verduras Ecológicas', 5.60, 50, 13, 13, '2024-02-07'),
('Carne Ecológica', 12.80, 40, 13, 13, '2024-02-09'),
('Detergente Ropa', 9.00, 70, 14, 14, '2027-12-31'),
('Lavavajillas', 6.50, 90, 14, 14, '2027-12-31'),
('Pienso para Perros', 14.50, 60, 15, 15, '2026-12-01'),
('Arena para Gatos', 8.20, 80, 15, 15, '2026-12-15'),
('Cereales', 3.30, 100, 6, 10, '2025-09-30'),
('Helado de Vainilla', 4.80, 90, 7, 8, '2025-11-25'),
('Agua Mineral', 1.00, 300, 8, 1, '2027-01-01'),
('Tableta de Chocolate', 2.90, 120, 9, 10, '2025-10-15'),
('Vinagre de Manzana', 2.50, 110, 10, 1, '2027-05-05'),
('Sopa Instantánea', 3.70, 80, 11, 16, '2026-01-20'),
('Harina Sin Gluten', 4.20, 70, 12, 18, '2025-09-15'),
('Huevos Camperos', 2.80, 90, 4, 17, '2024-02-14'),
('Tomates', 1.90, 110, 1, 2, '2025-06-18'),
('Bacalao', 11.40, 50, 3, 5, '2024-02-10'),
('Yogur Natural', 1.20, 140, 4, 4, '2024-03-05'),
('Pan de Molde', 2.30, 160, 5, 6, '2024-02-07'),
('Garbanzos', 3.10, 130, 6, 7, '2026-03-01'),
('Lasaña Congelada', 6.00, 70, 7, 8, '2025-12-05'),
('Cerveza Artesanal', 2.70, 200, 8, 20, '2026-11-10'),
('Gominolas', 1.80, 140, 9, 10, '2026-09-30'),
('Café Molido', 5.20, 80, 10, 1, '2026-07-12'),
('Toallitas Húmedas', 3.50, 100, 14, 14, '2027-12-31'),
('Snacks para Gatos', 4.80, 70, 15, 15, '2026-10-01'),
('Gaseosa sabor Lima', 2.00, 100, 8, 9, '2026-08-15'),
('Refresco de cola', 2.50, 80, 8, 9, '2026-06-30');
SELECT * FROM productos;

-- Insertamos datos en la tabla proveedores
USE proyecto;
INSERT INTO proveedores (nombre, contacto, direccion) VALUES
('Distribuidora Alimex', '612345678', 'Calle Mayor 101, Madrid'),
('Frutas Selectas S.A.', '623456789', 'Av. Central 202, Barcelona'),
('Carnes del Norte', '634567890', 'Plaza Mercado 303, Valencia'),
('Lácteos La Hacienda', '645678901', 'Calle San Juan 404, Sevilla'),
('Pescados del Mar', '656789012', 'Boulevard Azul 505, Bilbao'),
('Panadería Santa Ana', '667890123', 'Av. Independencia 606, Zaragoza'),
('Legumbres y Cereales S.L.', '678901234', 'Calle Comercio 707, Málaga'),
('Congelados FrescoFrío', '689012345', 'Ronda del Este 808, Granada'),
('Bebidas Premium', '690123456', 'Avenida del Río 909, Murcia'),
('Dulces y Chocolates', '701234567', 'Calle Nueva 1010, Salamanca'),
('Aceites del Sur', '712345678', 'Plaza Olivar 1111, Alicante'),
('Comida Casera S.A.', '723456789', 'Carretera Nacional 1212, Cádiz'),
('Ecológicos Vida Sana', '734567890', 'Paseo Verde 1313, Valladolid'),
('Hogar Limpio S.L.', '745678901', 'Calle del Sol 1414, Gijón'),
('Mascotas Felices', '756789012', 'Avenida Libertad 1515, León'),
('Supermercados Mayoristas', '767890123', 'Calle del Comercio 1616, Córdoba'),
('Granja El Campo', '778901234', 'Boulevard Norte 1717, Oviedo'),
('Productos Sin Gluten', '789012345', 'Calle del Prado 1818, Badajoz'),
('Mariscos La Costa', '790123456', 'Av. Puerto 1919, Tenerife'),
('Bodega Selecta', '801234567', 'Calle del Vino 2020, A Coruña');
SELECT * FROM proveedores;

-- Insertamos datos en la tabla pedidos proveedores
USE proyecto;
INSERT INTO pedidos_proveedor (id_proveedor, fecha_pedido, total) VALUES
(1, '2025-01-05', 1200.50),
(2, '2025-01-09', 850.75),
(3, '2025-01-11', 1560.30),
(4, '2025-01-14', 430.20),
(5, '2025-01-16', 978.90),
(6, '2025-01-19', 1120.40),
(7, '2025-01-21', 675.30),
(8, '2025-01-23', 1420.80),
(9, '2025-01-26', 590.00),
(10, '2025-01-28', 1350.50),
(11, '2025-02-02', 745.60),
(12, '2025-02-05', 2100.90),
(13, '2025-02-07', 980.75),
(14, '2025-02-09', 1275.30),
(15, '2025-02-11', 680.45),
(16, '2025-02-13', 1500.20),
(17, '2025-02-16', 870.90),
(18, '2025-02-18', 1195.40),
(19, '2025-02-20', 645.30),
(20, '2025-02-22', 1720.80);
SELECT * FROM pedidos_proveedor;

-- Insertamos datos en la tabla ventas
USE proyecto;
INSERT INTO ventas (id_cliente, fecha, total) VALUES
(1, '2025-01-10', 45.30),
(2, '2025-01-12', 23.40),
(3, '2025-01-15', 56.20),
(4, '2025-01-16', 32.50),
(5, '2025-01-17', 78.90),
(6, '2025-01-18', 18.60),
(7, '2025-01-20', 65.80),
(8, '2025-01-21', 40.00),
(9, '2025-01-23', 91.50),
(10, '2025-01-24', 15.00),
(11, '2025-01-25', 60.30),
(12, '2025-01-27', 34.70),
(13, '2025-01-29', 85.20),
(14, '2025-01-30', 50.60),
(15, '2025-02-01', 72.00),
(16, '2025-02-02', 27.90),
(17, '2025-02-03', 100.10),
(18, '2025-02-05', 45.60),
(19, '2025-02-06', 68.40),
(20, '2025-02-07', 33.00),
(21, '2025-02-08', 49.90),
(22, '2025-02-10', 74.30),
(23, '2025-02-11', 29.60),
(24, '2025-02-13', 59.80),
(25, '2025-02-14', 37.10),
(26, '2025-02-15', 82.40),
(27, '2025-02-16', 53.90),
(28, '2025-02-17', 22.70),
(29, '2025-02-19', 68.50),
(30, '2025-02-20', 41.30),
(1, '2025-02-21', 55.90),
(2, '2025-02-23', 78.00),
(3, '2025-02-25', 29.50),
(4, '2025-02-26', 43.80),
(5, '2025-02-27', 92.10),
(6, '2025-02-28', 21.20),
(7, '2025-03-01', 57.00),
(8, '2025-03-02', 80.30),
(9, '2025-03-03', 35.60),
(10, '2025-03-05', 66.70);
SELECT * FROM ventas;

-- Insertamos datos en la tabla empleados
USE proyecto;
INSERT INTO empleados (nombre, id_departamento, direccion, contacto) VALUES
('Carlos Gómez', 1, 'Calle de Segovia, 3, Madrid', '612345678'),
('Ana Martínez', 2, 'Avenida de la Libertad, 10, Getafe', '615678901'),
('Juan López', 3, 'Calle de Bravo Murillo, 120, Madrid', '617890123'),
('María Rodríguez', 4, 'Calle de Montera, 45, Madrid', '612345679'),
('Luis Fernández', 5, 'Calle de Alcalá, 30, Madrid', '619234567'),
('Sara Torres', 6, 'Calle del Prado, 6, Alcalá de Henares', '616789012'),
('José Sánchez', 7, 'Calle de la Princesa, 23, Madrid', '618123456'),
('Lucía González', 8, 'Avenida de la Paz, 40, Madrid', '612987654'),
('Carlos Jiménez', 9, 'Calle de Vallehermoso, 25, Madrid', '615432198'),
('Marta Ruiz', 10, 'Calle de Ponzano, 18, Madrid', '619876543'),
('Ricardo Pérez', 1, 'Avenida de los Poblados, 60, Madrid', '612654987'),
('Patricia Hernández', 2, 'Calle del Sol, 15, Majadahonda', '617345678'),
('Alberto García', 3, 'Calle de Goya, 55, Madrid', '619123456'),
('Isabel Díaz', 4, 'Calle de Sinesio Delgado, 5, Madrid', '616234567'),
('David Sánchez', 5, 'Calle de San Bernardo, 8, Madrid', '618654321'),
('José Luis Martín', 6, 'Calle de la Mancha, 20, Fuenlabrada', '612321987'),
('Verónica Torres', 7, 'Calle de Arturo Soria, 25, Madrid', '617890567'),
('Raúl López', 8, 'Avenida de América, 5, Madrid', '616543210'),
('Cristina Fernández', 9, 'Calle de Vallecas, 100, Madrid', '619654321'),
('Laura García', 10, 'Calle de Ramón y Cajal, 12, Alcorcón', '618234567');
SELECT * FROM empleados;

-- Insertamos datos en la tabla clientes
USE proyecto;
INSERT INTO clientes (nombre, contacto, direccion) VALUES
('Carlos Gómez', '612345678', 'Calle de Gran Vía, 5, Madrid'),
('Ana Pérez', '615678901', 'Avenida de la Paz, 12, Madrid'),
('Luis Martínez', '617890123', 'Calle de Vallehermoso, 35, Madrid'),
('Marta López', '612345679', 'Calle Alcalá, 45, Madrid'),
('José Fernández', '619234567', 'Calle de Arturo Soria, 25, Madrid'),
('Isabel Ruiz', '616789012', 'Avenida de los Poblados, 60, Madrid'),
('Pedro Sánchez', '618123456', 'Calle de O’Donnell, 8, Madrid'),
('Laura Gómez', '612987654', 'Calle de Goya, 25, Madrid'),
('Santiago Torres', '615432198', 'Avenida de Manoteras, 3, Madrid'),
('Carmen García', '619876543', 'Calle de Fuencarral, 120, Madrid'),
('Raúl Hernández', '612654987', 'Calle de Princesa, 18, Madrid'),
('Patricia Martínez', '617345678', 'Calle de Santa Engracia, 10, Madrid'),
('Alberto Rodríguez', '619123456', 'Avenida de América, 40, Madrid'),
('José María Pérez', '616234567', 'Calle de Bravo Murillo, 55, Madrid'),
('María Sánchez', '618654321', 'Calle de Ramón y Cajal, 25, Madrid'),
('Juan Martínez', '612321987', 'Avenida de la Constitución, 15, Getafe'),
('Victoria González', '617890567', 'Calle de la Mancha, 30, Alcalá de Henares'),
('Daniela Pérez', '616543210', 'Calle del Sol, 5, Fuenlabrada'),
('Ricardo López', '619654321', 'Calle de las Retamas, 20, Alcorcón'),
('Ángel Hernández', '618234567', 'Calle del Canal, 12, Madrid'),
('Elena Gómez', '615789456', 'Calle de Sinesio Delgado, 7, Madrid'),
('Fernando Torres', '612876543', 'Calle de Vallecas, 100, Madrid'),
('José Luis García', '617123890', 'Calle de José Abascal, 55, Madrid'),
('Raquel Rodríguez', '619876432', 'Calle de Cea Bermúdez, 8, Madrid'),
('Eduardo Sánchez', '618123654', 'Calle de Ponzano, 35, Madrid'),
('Beatriz Martínez', '616789654', 'Calle de Velázquez, 25, Madrid'),
('Manuel Pérez', '612987321', 'Calle de La Vaguada, 45, Madrid'),
('Teresa Ruiz', '617654321', 'Calle de San Bernardo, 12, Madrid'),
('Carlos García', '619234567', 'Avenida de la Ilustración, 3, Madrid'),
('Eva Fernández', '612345987', 'Calle de Joaquín Costa, 70, Madrid'),
('Luis González', '618765432', 'Calle de Pinar, 15, Majadahonda');
SELECT * FROM clientes;

-- Insertamos los datos en la tabla detalle ventas
USE proyecto;
INSERT INTO detalle_ventas (id_venta, id_producto, cantidad, precio_unitario) VALUES
(1, 2, 5, 1.50),
(1, 3, 2, 1.80),
(2, 5, 3, 2.00),
(2, 8, 1, 1.50),
(3, 6, 4, 2.80),
(3, 7, 3, 5.50),
(4, 1, 2, 1.50),
(4, 10, 1, 8.50),
(5, 9, 3, 2.20),
(5, 4, 4, 1.00),
(6, 11, 1, 7.20),
(6, 2, 3, 1.20),
(7, 12, 2, 4.50),
(7, 14, 3, 9.00),
(8, 13, 1, 5.60),
(8, 1, 5, 1.50),
(9, 3, 2, 2.80),
(9, 8, 1, 1.50),
(10, 4, 4, 2.00),
(10, 9, 3, 2.20),
(11, 15, 2, 14.50),
(11, 16, 1, 8.20),
(12, 17, 3, 3.50),
(12, 18, 2, 4.80),
(13, 19, 1, 5.20),
(13, 20, 4, 2.00),
(14, 21, 2, 2.50),
(14, 22, 3, 2.70),
(15, 23, 1, 2.90),
(15, 24, 3, 3.70),
(16, 25, 2, 4.20),
(16, 26, 1, 4.80),
(17, 27, 3, 9.80),
(17, 28, 4, 9.50),
(18, 29, 5, 3.00),
(18, 30, 2, 4.50),
(19, 31, 2, 6.50),
(19, 32, 1, 2.80),
(20, 33, 3, 1.90),
(20, 34, 2, 1.90),
(21, 35, 1, 6.00),
(21, 36, 2, 3.10),
(22, 37, 4, 3.10),
(22, 38, 3, 2.80);
SELECT * FROM detalle_ventas;

-- Insertamos datos en la tabla detalle pedido proveedores
USE proyecto;
INSERT INTO detalle_pedido_proveedor (id_pedido, id_producto, cantidad, precio_unitario_proveedor) VALUES
(1, 1, 100, 1.30),
(1, 5, 50, 1.90),
(2, 3, 120, 1.50),
(2, 7, 60, 4.50),
(3, 9, 200, 2.00),
(3, 10, 80, 7.00),
(4, 2, 150, 1.20),
(4, 8, 100, 1.70),
(5, 6, 130, 2.60),
(5, 11, 90, 6.20),
(6, 4, 180, 1.40),
(6, 12, 70, 3.90),
(7, 13, 60, 4.50),
(7, 15, 80, 2.00),
(8, 14, 100, 7.50),
(8, 16, 120, 8.30),
(9, 18, 90, 4.10),
(9, 19, 70, 5.00),
(10, 20, 50, 2.80),
(10, 21, 100, 1.20);
SELECT * FROM detalle_pedido_proveedor;