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