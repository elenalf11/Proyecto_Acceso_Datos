package main;

import java.sql.*;
import java.util.Scanner;

/**
 * Clase Ventas
 * En esta clase nos encontramos con todos los metodos relacionados con la tabla Ventas
 */
public class Ventas {

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
     * Este metodo te muestra un menu con las opciones que puedes hacer en la tabla Ventas
     */
    public void menu(){
        int opcion;
        do{
            System.out.println("¿Qué quieres hacer?\n1. Actualizar datos\n2. Eliminar venta\n3. Ver todas las ventas\n4. Ver información sobre el cliente\n5. Añadir venta \n0. Salir");
            opcion = this.sc.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("Dime el nuevo id de cliente (1 - 31): ");
                    int id_cliente_new = this.sc.nextInt();
                    System.out.println("Dime la nueva fecha de la venta (AAAA-MM-DD): ");
                    String fecha_new = this.sc.next();
                    System.out.println("Dime el nuevo precio total de la venta: ");
                    double total_new = this.sc.nextDouble();
                    System.out.println("Dime el id de la venta que quieras modificar (1 - 40): ");
                    int id_venta_new = this.sc.nextInt();
                    actualizarVenta(id_cliente_new, fecha_new, total_new, id_venta_new);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clean();
                    break;
                case 2:
                    System.out.println("Dime el id de la venta que quieres eliminar: ");
                    int id_eliminado = this.sc.nextInt();
                    eliminarVenta(id_eliminado);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clean();
                    break;
                case 3:
                    verVentas();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clean();
                    break;
                case 4:
                    System.out.println("Dime el id del cliente que ha efectuado la compra: ");
                    int id_join = this.sc.nextInt();
                    ownerVenta(id_join);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clean();
                    break;
                case 5:
                    System.out.println("Dime el id de cliente (1 - 31): ");
                    int id_cliente = this.sc.nextInt();
                    System.out.println("Dime la fecha de la venta (AAAA-MM-DD): ");
                    String fecha = this.sc.next();
                    System.out.println("Dime el precio total de la venta: ");
                    double total = this.sc.nextDouble();
                    addVenta(id_cliente, fecha, total);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clean();
                    break;
                case 0:
                    System.out.println("Saliendo ...");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clean();
                    break;
                default:
                    System.out.println("Opción inválida, vuelve a seleccionar");
            }
        } while(opcion != 0 );
    }

    /**
     * Metodo verVentas
     * Este metodo muestra todas las ventas
     */
    public void verVentas(){
        String sql = "SELECT * FROM ventas;";
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getConnection();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();{
                System.out.println("---- Listado de ventas ----");
                while(rs.next()){
                    System.out.println("Id venta: " + rs.getInt("id_venta") +
                            " Id cliente: " + rs.getInt("id_cliente") +
                            " Fecha: " + rs.getDate("fecha") +
                            " Total: " + rs.getDouble("total")
                    );
                }
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

    /**
     * Metodo actualizarVenta
     * Este metodo actualiza algun dato de la tabla Ventas
     * @param id_cliente Es el nuevo id del cliente
     * @param fecha Es la nueva fecha de la venta
     * @param total Es el nuevo total de la venta
     * @param id_venta  Es el id de venta al que se quiere modificar los datos
     */
    public void actualizarVenta(int id_cliente, String fecha, double total, int id_venta){
        String sql = "UPDATE ventas SET id_cliente = ?, fecha = ?, total = ? WHERE id_venta = ?;";
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, id_cliente);
            ps.setString(2, fecha);
            ps.setDouble(3, total);
            ps.setInt(4, id_venta);
            ps.executeUpdate();
            System.out.println("Venta actualizada correctamente");
        } catch (SQLException e) {
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
     * Metodo addVenta
     * Este metodo agrega una venta a la tabla Ventas
     * @param id_cliente es el id de cliente de la nueva venta
     * @param fecha es la fecha de la nueva venta
     * @param total es el precio total de la nueva venta
     */
    public void addVenta(int id_cliente, String fecha, double total){
        String sql = "INSERT INTO ventas (id_cliente, fecha, total) VALUES (?, ?, ?);";
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, id_cliente);
            ps.setString(2, fecha);
            ps.setDouble(3, total);
            ps.executeUpdate();
            System.out.println("Venta añadida a la tabla correctamente");
        } catch (SQLException e) {
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
     * Metodo eliminarVenta
     * Este metodo elimina una Venta de la tabla Ventas
     * @param id_venta es el id de la venta que se desea eliminar
     */
    public void eliminarVenta(int id_venta){
        String sql = "DELETE FROM ventas WHERE id_venta = ?;";
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, id_venta);
            ps.executeUpdate();
            System.out.println("Venta eliminada correctamente");
        } catch (SQLException e) {
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
     * Metodo ownerVenta
     * Este metodo te muestra informacion sobre el cliente que ha realizado la compra
     * @param id_venta es el id de la venta de la que se quiere saber su comprador
     */
    public void ownerVenta(int id_venta){
        String sql = "SELECT c.id_cliente, c.nombre, c.direccion, c.contacto " +
                "FROM clientes c " +
                "JOIN ventas v ON v.id_cliente = c.id_cliente " +
                "WHERE v.id_venta = ?;";
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, id_venta);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("\nLa compra con id: " + id_venta +
                        ", la ha realizado el cliente cuyo nombre es: " + rs.getString("nombre") +
                        ", vive en: " + rs.getString("direccion") +
                        " y su contacto es: " + rs.getString("contacto"));
            } else {
                System.out.println("\nNo hay información sobre la venta con id: " + id_venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
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
}