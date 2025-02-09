package main;

import java.sql.*;
import java.util.Scanner;

/**
 * Clase Empleados
 * En esta clase nos encontraremos todos los métodos que tengan relación con la tabla Empleados
 */
public class Empleados {

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
     * Este metodo te muestra las opciones que puedes realizar en esta clase
     */
    public void menu(){
        int opcion;
        do{
            System.out.println("¿Qué quieres hacer?\n1. Añadir empleado\n2. Actualizar datos\n3. Eliminar empleado\n4. Ver departamento\n5. Ver todos los empleados\n0. Salir");
            opcion = sc.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("Nombre empleado: ");
                    String nombre = this.sc.next();
                    System.out.println("id_departamento (1-10): ");
                    int id = this.sc.nextInt();
                    System.out.println("Dirección: ");
                    String direccion = this.sc.next();
                    this.sc.nextLine();
                    System.out.println("Contacto: ");
                    String contacto = this.sc.next();
                    addEmpleado(nombre, id, direccion, contacto);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clean();
                    break;
                case 2:
                    System.out.println("Id empleado: ");
                    int id_empleado = this.sc.nextInt();
                    System.out.println("Nuevo nombre: ");
                    String nombre_new = this.sc.next();
                    System.out.println("id_departamento (1-10): ");
                    int id_new = this.sc.nextInt();
                    System.out.println("Nueva dirección: ");
                    String direccion_new = this.sc.next();
                    this.sc.nextLine();
                    System.out.println("Nuevo contacto: ");
                    String contacto_new = this.sc.next();
                    updateEmpleado(nombre_new, id_new, direccion_new, contacto_new, id_empleado);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clean();
                    break;
                case 3:
                    System.out.println("Id empleado para eliminar: ");
                    int id_eliminado = this.sc.nextInt();
                    eliminarEmpleado(id_eliminado);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clean();
                    break;
                case 4:
                    System.out.println("Id empleado que desea ver su departamento: ");
                    int id_join = this.sc.nextInt();
                    verDepartamento(id_join);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clean();
                    break;
                case 5:
                    verEmpleados();
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
     * Metodo addEmpleado
     * Este metodo agrega un nuevo empleado a la tabla empleados
     * @param nombre es el nombre del nuevo empleado
     * @param id_departamento es el id_departamento del nuevo empleado
     * @param direccion es la direccion del nuevo empleado
     * @param contacto es el contacto del nuevo empleado
     */
    public void addEmpleado(String nombre, int id_departamento, String direccion, String contacto) {
        String sql = "INSERT INTO empleados (nombre, id_departamento, direccion, contacto) VALUES (?,?,?,?);";
        PreparedStatement ps = null;
        Connection c = null;
        try {
            c = getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, id_departamento);
            ps.setString(3, direccion);
            ps.setString(4, contacto);
            ps.executeUpdate();
            System.out.println("Empleado añadido correctamente a la tabla");
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
     * Metodo updateEmpleado
     * Este metodo actualiza los datos de un empleado en específico
     * @param nombre es el nuevo nombre
     * @param id_departamento es el nuevo id_departamento
     * @param direccion es la nueva dirección
     * @param contacto es el nuevo contacto
     * @param id_empleado es el id_empleado al que se le van a agregar estos cambios
     */
    public void updateEmpleado(String nombre, int id_departamento, String direccion, String contacto, int id_empleado){
        String sql = "UPDATE empleados SET nombre = ?, id_departamento = ?, direccion = ?, contacto = ? WHERE id_empleado = ?;";
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, id_departamento);
            ps.setString(3, direccion);
            ps.setString(4, contacto);
            ps.setInt(5, id_empleado);
            ps.executeUpdate();
            System.out.println("Datos del empleado actualizados correctamente");
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
     * Metodo eliminarEmpleado
     * Este metodo elimina un empleado con un id determinado que lo indica el usuario
     * @param id_empleado es el id de empleado que se desea eliminar
     */
    public void eliminarEmpleado(int id_empleado){
        String sql = "DELETE FROM empleados WHERE id_empleado = ?;";
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, id_empleado);
            ps.executeUpdate();
            System.out.println("Empleado eliminado correctamente");
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
     * Metodo verDepartamento
     * Este metodo te muestra el departamento en el que trabaja un empleado determinado
     * @param id_empleado es el id de empleado que se desea saber su departamento
     */
    public void verDepartamento(int id_empleado){
        String sql = "SELECT d.id, d.nombre FROM departamentos d " +
                "JOIN empleados e ON d.id = e.id_departamento " +
                "WHERE e.id_empleado = ?;";
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, id_empleado);
            rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("\n El departamento del cliente con id: " + id_empleado + " es id: " + rs.getInt("id") + " con nombre: " + rs.getString("nombre"));
            } else{
                System.out.println("\n El departamento no existe");
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        } finally {
            try{
                if (ps != null) ps.close();
                if (c != null) c.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al cerrar las conexiones");
            }
        }
    }

    /**
     * Metodo verEmpleados
     * Este metodo imprime por pantalla toda la información de los empleados
     */
    public void verEmpleados(){
        String sql = "SELECT * FROM empleados";
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getConnection();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();{
                System.out.println("\n--------- Lista de empleados ---------");
                while(rs.next()){
                    System.out.println("Id: " + rs.getInt("id_empleado")
                            + " Nombre: " + rs.getString("nombre")
                            + " Id departamento: " + rs.getInt("id_departamento")
                            + " Dirección: " + rs.getString("direccion")
                            + " Contacto: " + rs.getString("contacto"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        } finally {
            try{
                if (ps != null) ps.close();
                if (c != null) c.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al cerrar las conexiones");
            }
        }
    }
}