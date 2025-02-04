package main;

import java.sql.*;
import java.util.Scanner;

/**
 * Clase Productos
 * En esta clase nos encontraremos todos los metodos relacionados con la tabla Productos
 */
public class Productos {

    /**
     * Atributos
     */
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "110805";
    private Scanner sc = new Scanner(System.in);

    /**
     * Metodo getConnection
     * Este metodo genera la conexion con la base de datos mySQL
     * @return devuelve un objeto de tipo Connection, que es la conexion con la base de datos
     * @throws SQLException es la excepcion que puede lanzar el metodo, si ha habido algun problema con la conexion
     */
    private static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASS);
    }

    /**
     * Metodo menu
     * Este metodo te muestra un menu con las opciones que puedes hacer en la tabla Productos
     */
    public void menu(){
        int opcion;
        int id_categoria;
        int id_proveedor;
        do{
            System.out.println("¿Qué quieres hacer?\n1. Añadir producto\n2. Actualizar datos\n3. Eliminar producto\n4. Ver todos los productos\n0. Salir");
            opcion = this.sc.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("Nombre producto: ");
                    String nombre = this.sc.next();
                    System.out.println("Precio del producto: ");
                    double precio = this.sc.nextDouble();
                    System.out.println("Stock del producto: ");
                    int stock = this.sc.nextInt();
                    do{
                        System.out.println("Id de categoria del producto (1 - 15): ");
                        id_categoria = this.sc.nextInt();
                    } while(id_categoria >= 1 && id_categoria <= 15);
                    do{
                        System.out.println("Id de proveedor del producto (1 - 20): ");
                        id_proveedor = this.sc.nextInt();
                    } while (id_proveedor >= 1 && id_proveedor <= 20);
                    System.out.println("Fecha de caducidad del producto (AAAA-MM-DD): ");
                    String fecha_caducidad = this.sc.next();
                    addProducto(nombre, precio, stock, id_categoria, id_proveedor, fecha_caducidad);
                    break;
                case 2:
                    System.out.println("Nuevo nombre: ");
                    String nombre_new = this.sc.next();
                    System.out.println("Nuevo precio: ");
                    double precio_new = this.sc.nextDouble();
                    System.out.println("Nuevo stock: ");
                    int stock_new = this.sc.nextInt();
                    do{
                        System.out.println("Nuevo id de categoria (1 - 15): ");
                        id_categoria = this.sc.nextInt();
                    } while (id_categoria >= 1 && id_categoria <= 15);
                    do{
                        System.out.println("Nuevo id de proveedor (1 - 20): ");
                        id_proveedor = this.sc.nextInt();
                    } while (id_proveedor >= 1 && id_proveedor <= 20);
                    System.out.println("Nueva fecha de caducidad del producto (AAAA-MM-DD): ");
                    String fecha_caducidad_new = this.sc.next();
                    System.out.println("Id del producto que quieras actualizar: ");
                    int id_producto = this.sc.nextInt();
                    updateProducto(nombre_new, precio_new, stock_new, id_categoria, id_proveedor, fecha_caducidad_new, id_producto);
                    break;
                case 3:
                    System.out.println("Id del producto: ");
                    int id = this.sc.nextInt();
                    deleteProducto(id);
                    break;
                case 4:
                    verInventario();
                    break;
                case 0:
                    System.out.println("Saliendo ...");
                    break;
                default:
                    System.out.println("Opción inválida, vuelve a seleccionar");
            }
        } while(opcion != 0 );
    }

    /**
     * Metodo addProducto
     * Este metodo añade un producto nuevo a la tabla Productos
     * @param nombre es el nombre del nuevo producto
     * @param precio es el precio del nuevo producto
     * @param stock es el stock del nuevo producto
     * @param id_categoria es el id_categoria del nuevo producto
     * @param id_proveedor es el id_proveedor del nuevo producto
     * @param fecha_caducidad es la fecha de caducidad del nuevo producto
     */
    public void addProducto(String nombre, double precio, int stock, int id_categoria, int id_proveedor, String fecha_caducidad){
        String sql = "INSERT INTO productos (nombre, precio, stock, id_categoria, id_proveedor, fecha_caducidad) VALUES (?, ?, ?, ?, ?, ?);";
        try{
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setInt(3, stock);
            ps.setInt(4, id_categoria);
            ps.setInt(5, id_proveedor);
            ps.setString(6, fecha_caducidad);
            System.out.println("Producto añadido correctamente");
        } catch (SQLException e){
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        }

    }

    /**
     * Metodo updateProducto
     * Este metodo actualiza algun dato en la tabla Productos
     * @param nombre es el nuevo nombre
     * @param precio es el nuevo precio
     * @param stock es el nuevo stock
     * @param id_categoria es el nuevo id de categoria
     * @param id_proveedor es el nuevo id de proveedor
     * @param fecha_caducidad es la nueva fecha de caducidad
     * @param id_producto es el id de producto al que se van a aplicar los cambios
     */
    public void updateProducto(String nombre, double precio, int stock, int id_categoria, int id_proveedor, String fecha_caducidad, int id_producto){
        String sql = "UPDATE productos SET nombre = ?, precio = ?, stock = ?, id_categoria = ?, id_proveedor = ?, fecha_caducidad = ? WHERE id_producto = ?;";
        try{
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setInt(3, stock);
            ps.setInt(4, id_categoria);
            ps.setInt(5, id_proveedor);
            ps.setString(6, fecha_caducidad);
            ps.setInt(7, id_producto);
            System.out.println("Producto actualizado correctamente");
        } catch (SQLException e){
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        }
    }

    /**
     * Metodo deleteProducto
     * Este metodo elimina un producto en la tabla Productos
     * @param id_producto es el id del producto que se desea eliminar
     */
    public void deleteProducto(int id_producto){
        String sql = "DELETE FROM productos WHERE id_producto = ?;";
        try{
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id_producto);
            ps.executeQuery();
            System.out.println("Producto eliminado correctamente");
        } catch(SQLException e){
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        }
    }

    /**
     * Metodo verInventario
     * Este metodo muestra todos los productos disponibles en la tabla Productos
     */
    public void verInventario(){
        String sql = "SELECT * FROM productos;";
        try{
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();{
                System.out.println("----- Lista de productos ------");
                while(rs.next()){
                    System.out.println("Id: " + rs.getInt("id_producto") +
                            "Nombre: " + rs.getString("nombre") +
                            "Precio: " + rs.getDouble("precio") +
                            "Stock: " + rs.getInt("stock") +
                            "Id_categoria: " + rs.getInt("id_categoria") +
                            "Id_proveedor: " + rs.getInt("id_proveedor") +
                            "Fecha de caducidad: " + rs.getDate("fecha_caducidad")
                    );

                }
            }

        } catch (SQLException e){
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        }
    }
    
}