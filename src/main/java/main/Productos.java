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
     * Metodo clean
     * Este metodo simula limpiar la consola, para que sea mas legible
     */
    private void clean(){
        for (int i = 0; i <= 50; i++){
            System.out.println();
        }
    }

    /**
     * Metodo menu
     * Este metodo te muestra un menu con las opciones que puedes hacer en la tabla Productos
     */
    public void menu(){
        int opcion;
        do{
            System.out.println("¿Qué quieres hacer?\n1. Añadir producto\n2. Actualizar datos\n3. Eliminar producto\n4. Ver todos los productos\n5. Ver info Proveedores \n6. Ver info categorías \n0. Salir");
            opcion = this.sc.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("Nombre producto: ");
                    String nombre = this.sc.next();
                    System.out.println("Precio del producto: ");
                    double precio = this.sc.nextDouble();
                    System.out.println("Stock del producto: ");
                    int stock = this.sc.nextInt();
                    System.out.println("Id de categoria del producto (1 - 15): ");
                    int id_categoria = this.sc.nextInt();
                    this.sc.nextLine();
                    System.out.println("Id de proveedor del producto (1 - 20): ");
                    int id_proveedor = this.sc.nextInt();
                    System.out.println("Fecha de caducidad del producto (AAAA-MM-DD): ");
                    String fecha_caducidad = this.sc.next();
                    addProducto(nombre, precio, stock, id_categoria, id_proveedor, fecha_caducidad);
                    clean();
                    break;
                case 2:
                    System.out.println("Nuevo nombre: ");
                    String nombre_new = this.sc.next();
                    System.out.println("Nuevo precio: ");
                    double precio_new = this.sc.nextDouble();
                    System.out.println("Nuevo stock: ");
                    int stock_new = this.sc.nextInt();
                    System.out.println("Nuevo id de categoria (1 - 15): ");
                    int id_categoria_new = this.sc.nextInt();
                    System.out.println("Nuevo id de proveedor (1 - 20): ");
                    int id_proveedor_new = this.sc.nextInt();
                    this.sc.nextLine();
                    System.out.println("Nueva fecha de caducidad del producto (AAAA-MM-DD): ");
                    String fecha_caducidad_new = this.sc.next();
                    System.out.println("Id del producto que quieras actualizar: ");
                    int id_producto = this.sc.nextInt();
                    updateProducto(nombre_new, precio_new, stock_new, id_categoria_new, id_proveedor_new, fecha_caducidad_new, id_producto);
                    clean();
                    break;
                case 3:
                    System.out.println("Id del producto: ");
                    int id = this.sc.nextInt();
                    deleteProducto(id);
                    clean();
                    break;
                case 4:
                    verInventario();
                    clean();
                    break;
                case 5:
                    System.out.println("Dime el id del producto del que quieras saber la info: ");
                    int id_join_1 = this.sc.nextInt();
                    verProveedores(id_join_1);
                    clean();
                    break;
                case 6:
                    System.out.println("Dime el id de producto del que quieras saber la info: ");
                    int id_join_2 = this.sc.nextInt();
                    verCategoria(id_join_2);
                    clean();
                    break;
                case 0:
                    System.out.println("Saliendo ...");
                    clean();
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
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setInt(3, stock);
            ps.setInt(4, id_categoria);
            ps.setInt(5, id_proveedor);
            ps.setString(6, fecha_caducidad);
            ps.executeUpdate();
            System.out.println("Producto añadido correctamente");
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        } finally{
            try{
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al cerrar las conexiones");
            }
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
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setInt(3, stock);
            ps.setInt(4, id_categoria);
            ps.setInt(5, id_proveedor);
            ps.setString(6, fecha_caducidad);
            ps.setInt(7, id_producto);
            ps.executeUpdate();
            System.out.println("Producto actualizado correctamente");
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        } finally {
            try{
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al cerrar las conexiones");
            }
        }
    }

    /**
     * Metodo deleteProducto
     * Este metodo elimina un producto en la tabla Productos
     * @param id_producto es el id del producto que se desea eliminar
     */
    public void deleteProducto(int id_producto){
        String sql = "DELETE FROM productos WHERE id_producto = ?;";
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, id_producto);
            ps.executeUpdate();
            System.out.println("Producto eliminado correctamente");
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        } finally {
            try{
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al cerrar las conexiones");
            }
        }
    }

    /**
     * Metodo verInventario
     * Este metodo muestra todos los productos disponibles en la tabla Productos
     */
    public void verInventario(){
        String sql = "SELECT * FROM productos;";
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getConnection();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();{
                System.out.println("----- Lista de productos ------");
                while(rs.next()){
                    System.out.println("Id: " + rs.getInt("id_producto") +
                            " Nombre: " + rs.getString("nombre") +
                            " Precio: " + rs.getDouble("precio") +
                            " Stock: " + rs.getInt("stock") +
                            " Id_categoria: " + rs.getInt("id_categoria") +
                            " Id_proveedor: " + rs.getInt("id_proveedor") +
                            " Fecha de caducidad: " + rs.getString("fecha_caducidad")
                    );
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        } finally {
            try{
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al cerrar las conexiones");
            }
        }
    }

    /**
     * Metodo verProveedores
     * Este metodo te muestra toda la informacion referente al proveedor de un producto
     * @param id_producto es el id del producto del que se quiere conocer la informacion
     */
    public void verProveedores(int id_producto) {
        String sql = "SELECT p.id_proveedor, p.nombre, p.contacto, p.direccion FROM proveedores p " +
                "JOIN productos pr ON p.id_proveedor = pr.id_proveedor WHERE pr.id_producto = ?;";

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, id_producto);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\nEl producto con id: " + id_producto + " tiene como proveedor a: "+
                        ", nombre: " + rs.getString("nombre") +
                        ", contacto: " + rs.getString("contacto") +
                        ", y dirección: " + rs.getString("direccion"));
            } else {
                System.out.println("\nNo se encontró ningún producto con id: " + id_producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ha ocurrido un error en la conexión con la base de datos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al cerrar las conexiones");
            }
        }
    }

    /**
     * Metodo verCategoria
     * Este metodo te muestra toda la informacion referente a la categoria de un producto
     * @param id_producto es el id del producto del que se quiere conocer la informacion
     */
    public void verCategoria(int id_producto){
        String sql = "SELECT c.id_categoria, c.nombre FROM categorias c " +
                "JOIN productos p ON c.id_categoria = p.id_categoria WHERE p.id_producto = ?";
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, id_producto);
            rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("\nEl producto con id: " + id_producto + ", pertenece a la categoria de " + rs.getString("nombre"));
            } else{
                System.out.println("\nLa categoría no existe");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        } finally {
            try{
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al cerrar las conexiones");
            }
        }
    }

}