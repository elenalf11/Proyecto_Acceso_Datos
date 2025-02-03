package main;

import java.sql.*;
import java.util.Scanner;

public class Proveedores {
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto?serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String PASWORD = "Victor.241104";
    private Scanner sc = new Scanner(System.in);

    //nombre, contacto, direccion
    public void menu() {
        int opcion;
        do {
            System.out.println("¿Qué quieres hacer?\n1. Añadir proveedor\n2.Actualizar Proveedor \n3.Eliminar Proveedor\n4.Ver proveedores\n5. mostrar detalle pedidos proveedores \n0. Salir");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("---Añade proveedor---");
                    System.out.println("Nombre proveedor: ");
                    String nombre = sc.next();
                    System.out.println("Contacto proveedor");
                    String contacto = sc.next();
                    sc.nextLine();
                    System.out.println("Dirección proveedor: ");
                    String direccion = sc.nextLine();
                    añadirProveedor(nombre, contacto, direccion);
                    break;
                case 2:
                    System.out.println("---Actualizar datos---");
                    System.out.println("ID del Proveedor a actualizar: ");
                    int id = sc.nextInt();
                    sc.nextLine();//Limpiar el buffer para que no de fallos
                    System.out.println("Nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    System.out.println("Nuevo contacto: ");
                    String nuevoContacto = sc.nextLine();
                    System.out.println("Nueva Dirección: ");
                    String nuevaDireccion = sc.nextLine();
                    actualizarDatosProveedor(id, nuevoNombre, nuevaDireccion, nuevoContacto);
                    System.out.println("Actualiza proveedor");
                    break;
                case 3:
                    System.out.println("---Elimina proveedor---");
                    System.out.println(("Id_proveedor a eliminar: "));
                    int idEliminar = sc.nextInt();
                    eliminaProveedor(idEliminar);
                    break;
                case 4:
                    System.out.println("---Mostrar proveedores---");
                    mostrarProveedor();

                    break;
                case 5:
                    System.out.println("detalle pedidos proveedores");
                    System.out.print("ID del proveedor para ver detalles de pedidos: ");
                    int idProveedor = sc.nextInt();
                    mostrarDetallePedidosProveedor(idProveedor);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción invalida");
            }
        } while (opcion != 0);


    }

    /**
     * @return
     * @throws SQLException
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASWORD);
    }

    /**
     * Añade un proveedor nuevo a la base de datos
     *
     * @param nombre
     * @param contacto
     * @param direccion
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
            e.printStackTrace();
        }

    }

    /**
     * Actualiza los datos de un proveedor
     *
     * @param id
     * @param nombre
     * @param contacto
     * @param direccion
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
            e.printStackTrace();
        }
    }

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
            e.printStackTrace();
        }
    }

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
            System.out.println("\n--- Detalles de Pedidos del Proveedor ---");
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
            e.printStackTrace();
        }


    }
}