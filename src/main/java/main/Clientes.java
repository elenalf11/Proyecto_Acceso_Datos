package main;

import java.net.StandardSocketOptions;
import java.sql.*;
import java.util.Scanner;

public class Clientes {
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto?serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final  String PASWORD=  "Victor.241104";
    private Scanner sc = new Scanner(System.in);


    /**
     * Establece la conexion con la base de datos
     * @return objeto de conexión
     * @throws SQLException si ocurre un erros al conectarse
     */
    private static Connection getConnection () throws SQLException {
        return DriverManager.getConnection(URL,USUARIO,PASWORD);
    }

    /**
     * Metodo menu para elegir lo que se quiere hacer
     */
    public void menu(){
        int opcion;
        do{
            System.out.println("¿Qué quieres hacer?\n1. Añadir cliente\n2.Actualizar datos\n3.Eliminar cliente\n4.Ver compras\n5.Ver clientes\n0.Salir");
            opcion=sc.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("---Añade cliente---");
                    System.out.println("Nombre del cliente: ");
                    String nombre = sc.next();
                    System.out.println("Dirección: ");
                    String direccion = sc.next() ;
                    sc.nextLine();
                    System.out.println("Contacto");
                    String contacto= sc.next();
                    añadirCliente(nombre,direccion,contacto);
                    break;
                case 2:
                    System.out.println("---Actualizar datos---");
                    System.out.println("ID del cleinte a actualizar: ");
                    int id= sc.nextInt();
                    sc.nextLine();//Limpiar el buffer para que no de fallos
                    System.out.println("Nuevo nombre: ");
                    String nuevoNombre= sc.nextLine();
                    System.out.println("Nueva dirección: ");
                    String nuevaDireccion= sc.nextLine() ;
                    System.out.println("Nuevo Contacto: ");
                    String nuevoContacto= sc.nextLine();
                    actualizarDatosCliente(id,nuevoNombre,nuevaDireccion,nuevoContacto);
                    break;
                case 3:
                    System.out.println("---Elimina cliente---");
                    System.out.println("ID del cliente a elimnar: ");
                    int idEliminar= sc.nextInt();
                    eliminarCliente(idEliminar);
                    break;
                case 4:
                    System.out.println("ver compras de un cliente ");
                    System.out.println("ID del cliente para ver compras: ");
                    int idCliente= sc.nextInt();
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
     * Metodo que añade un cliente a la base de datos
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
            e.printStackTrace();
        }
    }

    /**
     * Metodo que actualiza los datos del cliente
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
            e.printStackTrace();
        }

    }

    /**
     * Elimina un cliente de la base de datos
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
            e.printStackTrace();
        }


    }

    /**
     * Muestra las compras realizadas por un cliente
     * @param idCliente
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
           e.printStackTrace();
       }
    }

    /**
     * Metodo que muestra los clientes
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
            e.printStackTrace();
        }
    }




}

