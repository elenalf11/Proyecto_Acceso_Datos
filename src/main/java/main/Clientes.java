package main;

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
                    System.out.println("Añade cliente");
                    break;
                case 2:
                    System.out.println("Actualiza datos");
                    break;
                case 3:
                    System.out.println("Elimina cliente");
                    break;
                case 4:
                    System.out.println("ver compras");
                    break;
                case 5:
                    System.out.println("mostrar clientes");
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
     * @param nombre
     * @param direccion
     * @param contacto
     */
    public static void añadirCliente (String nombre, String direccion, String contacto)  {
        String sql = "UPDATE Clientes SET nombre= ?, direccion= ? contacto=?)VALUES(?,?,?) ";
        try(Connection conn= getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,nombre);
            ps.setString(2,direccion);
            ps.setString(3,contacto);
            ps.executeUpdate();
            System.out.println("Cliente añadido correctamente.");
        }catch (SQLException e){
            e.printStackTrace();
        }







    }
}
