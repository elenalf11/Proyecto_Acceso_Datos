package main;

import java.sql.*;
import java.util.Scanner;

/**
 * Clase Pedidos_Proveedores
 * En esta clase encontraras todos los metodos relacionados con la tabla Pedidos_Proveedores
 */
public class Pedidos_Proveedores {

    /**
     * Atributos
     */
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto?serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final  String PASWORD=  "Victor.241104";
    private Scanner sc = new Scanner(System.in);

    /**
     * Metodo getConnection
     * Este metodo genera la conexion con la base de datos mySQL
     * @return devuelve un objeto de tipo Connection, que es la conexion con la base de datos
     * @throws SQLException es la excepcion que puede lanzar el metodo, si ha habido algun problema con la conexion
     */
    private static Connection getConnection () throws SQLException {
        return DriverManager.getConnection(URL,USUARIO,PASWORD);
    }

    /**
     * Metodo menu
     * Este metodo te muestra un menu con las opciones que puedes hacer en la tabla Productos
     */
    public void menu(){
        int opcion;
        do{
            System.out.println("¿Qué quieres hacer?\n1.Hacer pedido a proveedor\n2.Modificar Datos Pedidos \n3.Eliminar pedidos \n4.Ver pedidos\n5.Ver información proveedores\n0.Salir");
            opcion = this.sc.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("Hacer pedido a proveedor");
                    hacerPedidoProveedor();
                    break;
                case 2:
                    System.out.println("---Actualizar Pedido---");
                    System.out.println("ID del pedido a actualizar: ");
                    int idPedidoActualizar = this.sc.nextInt();
                    System.out.println("Nuevo ID del proveedor del pedido : ");
                    int idProveedorActualizar = this.sc.nextInt();
                    this.sc.nextLine();//limpia buffer
                    System.out.println("Nueva fecha del pedido (AAAA-MM-DD): ");
                    String fechaPedido= this.sc.nextLine();
                    System.out.println("Total");
                    double totalActualizar= this.sc.nextDouble();
                    actualizarPedido(idPedidoActualizar,idProveedorActualizar,fechaPedido,totalActualizar);
                    break;
                case 3:
                    System.out.println("---Eliminar pedidos---");
                    System.out.println("ID del pedido a eliminar: ");
                    int idPedidoEliminar = this.sc.nextInt();
                    eliminarPedido(idPedidoEliminar);
                    break;
                case 4:
                    System.out.println("---Ver Pedidos---");
                    verPedidos();
                    break;
                case 5:
                    System.out.println("ver información proveedor y sus pedidos ");
                    //verProveedor(int id)

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
     * Metodo hacerPedidoProveedor
     * Este metodo simula un pedido a un proveedor
     */
    public void hacerPedidoProveedor() {
        System.out.println("Ingrese el ID del proveedor:");
        int idProveedor = this.sc.nextInt();
        System.out.println("Ingrese el ID del producto:");
        int idProducto = this.sc.nextInt();
        System.out.println("Ingrese la cantidad:");
        int cantidad = this.sc.nextInt();
        System.out.println("Ingrese el precio unitario:");
        double precioUnitario = this.sc.nextDouble();
        System.out.println("Ingrese la fecha del pedido (YYYY-MM-DD):");
        String fechaPedido = this.sc.next(); // Captura la fecha ingresada

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
                System.out.println("Error al realizar el pedido. La transacción ha sido revertida.");
            }
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        }
    }


    /**
     * Metodo actualizarPedido
     * Este metodo actualiza un pedido de proveedor
     * @param idPedido es el id del pedido que se quiere actualizar
     * @param idProveedor es el nuevo id del proveedor
     * @param fechapedido es la nueva fecha del pedido
     * @param total es el nuevo precio total del pedido
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
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        }
    }

    /**
     * Metodo eliminarPedido
     * Este metodo elimina un pedido de la tabla Pedidos_Proveedores
     * @param idPedido es el id del pedido que se desea eliminar
     */
    public void eliminarPedido(int idPedido){
        String sql = "DELETE FROM pedidos_proveedor WHERE id_pedido =?";
        try(Connection conn= getConnection();
        PreparedStatement ps =conn.prepareStatement(sql)){
            ps.setInt(1,idPedido);
            ps.executeUpdate();
            System.out.println(("Pedido eliminado correctamente. "));
        }catch (SQLException e){
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        }
    }

    /**
     * Metodo verPedidos
     * Este metodo de muestra todos los pedidos de la tabla Pedidos_Proveedores
     */
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
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        }
    }

    public void verProveedorPedidos(){
        String sql= "";
        try{
            Connection conn= getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);


        }catch (SQLException e){
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");

        }
    }





}

//select=> select =>update para comprar a los proevedores
//si quiero comprar un producto que no tengo en stock la consulta es difernte se usaría INSERT
//hacer select dinamico