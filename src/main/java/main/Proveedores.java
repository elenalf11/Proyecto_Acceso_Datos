package main;

import java.sql.*;
import java.util.Scanner;

/**
 * Clase Proveedores
 * En esta clase nos encontraremos todos los metodos relacionados con la tabla Proveedores
 */
public class Proveedores {

    /**
     * Atributos
     */
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto?serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String PASWORD = "Victor.241104";
    private Scanner sc = new Scanner(System.in);

    /**
     * Metodo getConnection
     * Este metodo genera la conexion con la base de datos mySQL
     * @return devuelve un objeto de tipo Connection, que es la conexion con la base de datos
     * @throws SQLException es la excepcion que puede lanzar el metodo, si ha habido algun problema con la conexion
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASWORD);
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
    public void menu() {
        int opcion;
        do {
            System.out.println("¿Qué quieres hacer?\n1. Añadir proveedor\n2.Actualizar Proveedor \n3.Eliminar Proveedor\n4.Ver proveedores\n5.Mostrar detalle pedidos proveedores \n0. Salir");
            opcion = this.sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("---Añade proveedor---");
                    System.out.println("Nombre proveedor: ");
                    String nombre = this.sc.next();
                    System.out.println("Contacto proveedor");
                    String contacto = this.sc.next();
                    this.sc.nextLine();
                    System.out.println("Dirección proveedor: ");
                    String direccion = this.sc.nextLine();
                    añadirProveedor(nombre, contacto, direccion);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clean();
                    break;
                case 2:
                    System.out.println("---Actualizar datos---");
                    System.out.println("ID del Proveedor a actualizar: ");
                    int id = this.sc.nextInt();
                    this.sc.nextLine();//Limpiar el buffer para que no de fallos
                    System.out.println("Nuevo nombre: ");
                    String nuevoNombre = this.sc.nextLine();
                    System.out.println("Nuevo contacto: ");
                    String nuevoContacto = this.sc.nextLine();
                    System.out.println("Nueva Dirección: ");
                    String nuevaDireccion = this.sc.nextLine();
                    actualizarDatosProveedor(id, nuevoNombre, nuevaDireccion, nuevoContacto);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clean();
                    break;
                case 3:
                    System.out.println("---Elimina proveedor---");
                    System.out.println(("Id_proveedor a eliminar: "));
                    int idEliminar = this.sc.nextInt();
                    eliminaProveedor(idEliminar);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clean();
                    break;
                case 4:
                    System.out.println("---Mostrar proveedores---");
                    mostrarProveedor();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clean();
                    break;
                case 5:
                    System.out.println("detalle pedidos proveedores");
                    System.out.print("ID del proveedor para ver detalles de pedidos: ");
                    int idProveedor = this.sc.nextInt();
                    mostrarDetallePedidosProveedor(idProveedor);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clean();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clean();
                    break;
                default:
                    System.out.println("Opción invalida");
            }
        } while (opcion != 0);

    }

    /**
     * Metodo añadirProveedor
     * Este metodo añade un proveedor nuevo a la tabla Proveedores
     * @param nombre es el nombre del proveedor
     * @param contacto es el contacto del proveedor
     * @param direccion es la direccion del proveedor
     */
    public void añadirProveedor(String nombre, String contacto, String direccion) {
        String sql = "INSERT INTO proveedores (nombre,contacto , direccion)VALUES(?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, contacto);
            ps.setString(3, direccion);
            ps.executeUpdate();
            System.out.println("Proveedor añadido con éxito ");
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        }

    }

    /**
     * Metodo actualizarDatorProveedor
     * Este metodo actualiza los datos de un proveedor
     * @param id es el id del proveedor que se desea actualizar
     * @param nombre es el nuevo nombre del proveedor
     * @param contacto es el nuevo contacto del proveedor
     * @param direccion es la nueva direccion del proveedor
     */
    public void actualizarDatosProveedor(int id, String nombre, String contacto, String direccion) {
        String sql = "UPDATE proveedores SET nombre=?, contacto=?,direccion=? WHERE id_proveedor=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, contacto);
            ps.setString(3, direccion);
            ps.setInt(4, id);
            ps.executeUpdate();
            System.out.println("Proveedor actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        }
    }

    /**
     * Metodo eliminaProveedor
     * Este metodo elimina un proveedor de la tabla Proveedores
     * @param id es el id del proveedor que se desea eliminar
     */
    public void eliminaProveedor(int id) {
        String sql = "DELETE FROM proveedores WHERE id_proveedor=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Proveedor eliminado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo mostrarProveedor
     * Este metodo muestra todos los proveedores de la tabla Proveedores
     */
    public void mostrarProveedor() {
        String sql = "SELECT *FROM proveedores";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            System.out.println("\n---Lista Proveedor---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_proveedor") +
                        " Nombre: " + rs.getString("nombre") +
                        " Contacto: " + rs.getString("contacto") +
                        " Dirección: " + rs.getString("direccion"));
            }
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        }
    }

    /**
     * Metodo mostrarDetallePedidosProveedor
     * Este metodo te muestra la informacion realacionada con la tabla detalle pedido proveedor
     * @param idProveedor es el id del proveedor que se desea saber mas informacion
     */
    public static void mostrarDetallePedidosProveedor(int idProveedor) {
        String sql = "SELECT pp.id_pedido, pp.fecha_pedido, pp.total, dpp.id_detalle_proveedor, dpp.cantidad, dpp.precio_unitario_proveedor, p.nombre AS nombre_producto " +
                "FROM pedidos_proveedor pp " +
                "JOIN detalle_pedido_proveedor dpp ON pp.id_pedido = dpp.id_pedido " +
                "JOIN productos p ON dpp.id_producto = p.id_producto " +
                "WHERE pp.id_proveedor = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProveedor);
            ResultSet rs = ps.executeQuery();
            System.out.println("\n--- Detalles de Pedidos del Proveedor con ID: "+idProveedor+"---");
            while (rs.next()) {
                System.out.println("ID Pedido: " + rs.getInt("id_pedido") +
                        ", Fecha Pedido: " + rs.getString("fecha_pedido") +
                        ", Total: " + rs.getDouble("total") +
                        ", ID Detalle: " + rs.getInt("id_detalle_proveedor") +
                        ", Cantidad: " + rs.getInt("cantidad") +
                        ", Precio Unitario: " + rs.getDouble("precio_unitario_proveedor") +
                        ", Producto: " + rs.getString("nombre_producto"));
            }
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        }
    }
}