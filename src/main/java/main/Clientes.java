package main;

import java.sql.*;
import java.util.Scanner;

/**
 * Clase Clientes
 * En esta clase nos encontraremos todos los metodos relacionados con la tabla Clientes
 */
public class Clientes {

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
            System.out.println("¿Qué quieres hacer?\n1. Añadir cliente\n2.Actualizar datos\n3.Eliminar cliente\n4.Ver compras\n5.Ver clientes\n0.Salir");
            opcion = this.sc.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("---Añade cliente---");
                    System.out.println("Nombre del cliente: ");
                    String nombre = this.sc.next();
                    System.out.println("Dirección: ");
                    String direccion = this.sc.next() ;
                    this.sc.nextLine();
                    System.out.println("Contacto");
                    String contacto= this.sc.next();
                    añadirCliente(nombre,direccion,contacto);
                    break;
                case 2:
                    System.out.println("---Actualizar datos---");
                    System.out.println("ID del cleinte a actualizar: ");
                    int id= this.sc.nextInt();
                    this.sc.nextLine();//Limpiar el buffer para que no de fallos
                    System.out.println("Nuevo nombre: ");
                    String nuevoNombre= this.sc.nextLine();
                    System.out.println("Nueva dirección: ");
                    String nuevaDireccion= this.sc.nextLine() ;
                    System.out.println("Nuevo Contacto: ");
                    String nuevoContacto= this.sc.nextLine();
                    actualizarDatosCliente(id,nuevoNombre,nuevaDireccion,nuevoContacto);
                    break;
                case 3:
                    System.out.println("---Elimina cliente---");
                    System.out.println("ID del cliente a elimnar: ");
                    int idEliminar= this.sc.nextInt();
                    eliminarCliente(idEliminar);
                    break;
                case 4:
                    System.out.println("ver compras de un cliente ");
                    System.out.println("ID del cliente para ver compras: ");
                    int idCliente= this.sc.nextInt();
                    verComprasCliente(idCliente);
                    break;
                case 5:
                    System.out.println("mostrar clientes ");
                    mostrarCLientes();
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
     * Metodo añadirCliente
     * Este metodo añade un cliente a la base de datos
     * @param nombre nombre del cliente
     * @param direccion direccion del cliente
     * @param contacto numero de telefono
     */
    public  void añadirCliente (String nombre, String contacto, String direccion)  {
        String sql = "INSERT INTO clientes (nombre,contacto,direccion)VALUES(?,?,?) ";
        try(Connection conn= getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,nombre);
            ps.setString(2,contacto);
            ps.setString(3,direccion);
            ps.executeUpdate();
            System.out.println("Cliente añadido correctamente.");
        }catch (SQLException e){
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        }
    }

    /**
     * Metodo actualizarDatosCliente
     * Este metodo actualiza los datos del cliente
     * @param idCliente ID del cliente al que hay que actualizar los datos
     * @param nombre Nuevo nombre
     * @param direccion Nueva Direccion
     * @param contacto Nuevo telefono
     */
    public  void actualizarDatosCliente(int idCliente, String nombre, String contacto, String direccion){
        String sql = "UPDATE clientes SET nombre=?, direccion=?, contacto=? WHERE id_cliente=?";
        try (Connection conn= getConnection();
             PreparedStatement ps= conn.prepareStatement(sql)){
            ps.setString(1,nombre);
            ps.setString(2,contacto);
            ps.setString(3,direccion);
            ps.setInt(4,idCliente);
            ps.executeUpdate();
            System.out.println("Cliente actualizado correctamente.");
        }catch(SQLException e){
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        }

    }

    /**
     * Metodo eliminarCliente
     * Este metodo elimina un cliente de la tabla Clientes
     * @param idCliente ID del cliente
     */
    public void eliminarCliente(int idCliente){
        String sql = "DELETE FROM clientes WHERE id_cliente=?";
        try(Connection conn= getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,idCliente);
            ps.executeUpdate();
            System.out.println("Cliente eliminado correctamente");
        }catch(SQLException e){
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        }


    }

    /**
     * Metodo verComprasCliente
     * Este metodo muestra las compras realizadas por un cliente
     * @param idCliente es el id del cliente del que se quiere conocer los datos de compra
     */
    public void verComprasCliente(int idCliente){
       String sql = "SELECT v.id_venta, v.fecha, v.total " +
                    "FROM ventas v " +
                    "JOIN clientes c ON v.id_cliente= c.id_cliente " +
                    "WHERE c.id_cliente=?";
       try(Connection conn = getConnection();
           PreparedStatement ps= conn.prepareStatement(sql)){
           ps.setInt(1,idCliente);
           ResultSet rs = ps.executeQuery();
           System.out.println(("\nCompras del cliente con ID : "+idCliente +" son: "));
           while(rs.next()){
               System.out.println(("ID venta: "+ rs.getInt("id_venta")+
                       " Fecha: "+ rs.getDate("fecha")+
                       " Total: "+ rs.getDouble("total")));
           }
       }catch(SQLException e){
           System.out.println("Ha ocurrido un error en la conexión con la base de datos");
       }
    }

    /**
     * Metodo mostrarClientes
     * Este metodo muestra todos los clientes de la tabla Clientes
     */
    public void mostrarCLientes (){
        String sql = "SELECT * FROM clientes";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs= ps.executeQuery()){
            System.out.println("\n---Lista Clientes---");
            while (rs.next()){
                System.out.println("ID: "+rs.getInt("id_cliente")+
                                   "  Nombre: "+ rs.getString("nombre")+
                                   "  Dirección: "+rs.getString("direccion")+
                                   "  Contacto: "+ rs.getString("contacto"));
            }
        }catch (SQLException e){
            System.out.println("Ha ocurrido un error en la conexión con la base de datos");
        }
    }
}

