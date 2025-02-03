package main;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;


public class Pedidos_Proveedores {
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto?serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final  String PASWORD=  "Victor.241104";
    private Scanner sc = new Scanner(System.in);

    public void menu(){
        int opcion;
        do{
            System.out.println("¿Qué quieres hacer?\n1.Hacer pedido a proveedor\n2.Modificar Datos Pedidos \n3.Eliminar pedidos \n4.Ver pedidos\n5.Ver información proveedores\n0.Salir");
            opcion=sc.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("Hacer pedido a proveedor");
                    hacerPedidoProveedor();
                    break;
                case 2:
                    System.out.println("---Actualizar Pedido---");
                    System.out.println("ID del pedido a actualizar: ");
                    int idPedidoActualizar = sc.nextInt();
                    System.out.println("Nuevo ID del proveedor del pedido : ");
                    int idProveedorActualizar=sc.nextInt();
                    sc.nextLine();//limpia buffer
                    System.out.println("Nueva fecha del pedido (AAAA-MM-DD): ");
                    String fechaPedido= sc.nextLine();
                    System.out.println("Total");
                    double totalActualizar= sc.nextDouble();
                    actualizarPedido(idPedidoActualizar,idProveedorActualizar,fechaPedido,totalActualizar);
                case 3:
                    System.out.println("---Eliminar pedidos---");
                    System.out.println("ID del pedido a eliminar: ");
                    int idPedidoEliminar = sc.nextInt();
                    eliminarPedido(idPedidoEliminar);
                    break;
                case 4:
                    System.out.println("---Ver Pedidos---");
                    verPedidos();
                    break;
                case 5:
                    System.out.println("ver información proveedor y sus pedidos ");
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción invalida");
            }
        }while(opcion!=0);
    }
    /**
     * Establece la conexion con la base de datos
     * @return objeto de conexión
     * @throws SQLException si ocurre un erros al conectarse
     */
    private static Connection getConnection () throws SQLException {
        return DriverManager.getConnection(URL,USUARIO,PASWORD);
    }
    public void hacerPedidoProveedor() {
        System.out.println("Ingrese el ID del proveedor:");
        int idProveedor = sc.nextInt();
        System.out.println("Ingrese el ID del producto:");
        int idProducto = sc.nextInt();
        System.out.println("Ingrese la cantidad:");
        int cantidad = sc.nextInt();
        System.out.println("Ingrese el precio unitario:");
        double precioUnitario = sc.nextDouble();
        System.out.println("Ingrese la fecha del pedido (YYYY-MM-DD):");
        String fechaPedido = sc.next(); // Captura la fecha ingresada

        String sqlPedido = "INSERT INTO pedidos_proveedor (id_proveedor, fecha_pedido, total) VALUES (?, ?, ?)";
        String sqlDetalle = "INSERT INTO detalle_pedido_proveedor (id_pedido, id_producto, cantidad, precio_unitario_proveedor) VALUES (?, ?, ?, ?)";
        String sqlProducto = "UPDATE productos SET stock = stock + ? WHERE id_producto = ?";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);  // Iniciar transacción

            try (PreparedStatement pstmtPedido = conn.prepareStatement(sqlPedido, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstmtPedido.setInt(1, idProveedor);
                pstmtPedido.setString(2, fechaPedido); // Usa la fecha ingresada
                pstmtPedido.setDouble(3, cantidad * precioUnitario);
                pstmtPedido.executeUpdate();

                ResultSet rs = pstmtPedido.getGeneratedKeys();
                int idPedido = 0;
                if (rs.next()) {
                    idPedido = rs.getInt(1);
                }

                try (PreparedStatement pstmtDetalle = conn.prepareStatement(sqlDetalle)) {
                    pstmtDetalle.setInt(1, idPedido);
                    pstmtDetalle.setInt(2, idProducto);
                    pstmtDetalle.setInt(3, cantidad);
                    pstmtDetalle.setDouble(4, precioUnitario);
                    pstmtDetalle.executeUpdate();
                }

                try (PreparedStatement pstmtProducto = conn.prepareStatement(sqlProducto)) {
                    pstmtProducto.setInt(1, cantidad);
                    pstmtProducto.setInt(2, idProducto);
                    pstmtProducto.executeUpdate();
                }

                conn.commit();  // Confirmar transacción
                System.out.println("Pedido realizado y producto actualizado correctamente.");
            } catch (SQLException e) {
                conn.rollback();  // Revertir transacción en caso de error
                e.printStackTrace();
                System.out.println("Error al realizar el pedido. La transacción ha sido revertida.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Actualiza un pedido de proveedor
     * @param idPedido
     * @param idProveedor
     * @param fechapedido
     * @param total
     */
    public void actualizarPedido(int idPedido, int idProveedor, String fechapedido, double total){
        String sql = "UPDATE pedidos_proveedor SET id_proveedor =?, fecha_pedido=?, total=? WHERE id_pedido=?";
        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,idProveedor);
            ps.setString(2,fechapedido);
            ps.setDouble(3,total);
            ps.setInt(4,idPedido);
            ps.executeUpdate();
            System.out.println("Pedido actualizado correctamente. ");
        }catch( SQLException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param idPedido
     */
    public void eliminarPedido(int idPedido){
        String sql = "DELETE FROM pedidos_proveedor WHERE id_pedido =?";
        try(Connection conn= getConnection();
        PreparedStatement ps =conn.prepareStatement(sql)){
            ps.setInt(1,idPedido);
            ps.executeUpdate();
            System.out.println(("Pedido eliminado correctamente. "));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void verPedidos(){
        String sql = "SELECT * FROM pedidos_proveedor";
        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                System.out.println("ID Pedido: "+rs.getInt("id_pedido")+
                        " ID Proveedor: "+ rs.getInt("id_proveedor")+
                        " Fecha Pedido: "+ rs.getDate("fecha_pedido")+
                        " Total: "+ rs.getDouble("total"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }





}

//select=> select =>update para comprar a los proevedores
//si quiero comprar un producto que no tengo en stock la consulta es difernte se usaría INSERT
//hacer select dinamico