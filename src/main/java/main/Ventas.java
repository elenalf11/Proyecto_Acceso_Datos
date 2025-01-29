package main;

import java.util.Scanner;

public class Ventas {

    private static final String URL = "jdbc:mysql://localhost:3306/proyecto?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "110805";
    private Scanner sc = new Scanner(System.in);

    public void menu(){
        int opcion;
        do{
            System.out.println("¿Qué quieres hacer?\n1. Actualizar datos\n2. Eliminar venta\n3. Ver todas las ventas\n4. Ver información sobre el cliente\n0. Salir");
            opcion = sc.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("Actualizar datos");
                    break;
                case 2:
                    System.out.println("Eliminar venta");
                    break;
                case 3:
                    System.out.println("Ver todas las ventas");
                    break;
                case 4:
                    System.out.println("Ver información sobre el cliente ");
                    break;
                case 0:
                    System.out.println("Saliendo ...");
                    break;
                default:
                    System.out.println("Opción inválida, vuelve a seleccionar");
            }
        } while(opcion != 0 );
    }
}
