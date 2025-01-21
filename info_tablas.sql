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

-- Creamos la tabla de proveedores

-- Creamos la tabla de pedidos_proveedores

-- Creamos la tabla de ventas

-- Creamos la tabla de empleados

-- Creamos la tabla de clientes

-- Creamos la tabla de departamentos