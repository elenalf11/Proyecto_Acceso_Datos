package main;

import java.util.Scanner;

/**
 * Clase Productos
 * En esta clase nos encontraremos todos los metodos relacionados con la tabla Productos
 */
public class Productos {

    /**
     * Atributos
     */
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "110805";
    private Scanner sc = new Scanner(System.in);

    public void menu(){
        int opcion;
        do{
            System.out.println("¿Qué quieres hacer?\n1. Añadir producto\n2. Actualizar datos\n3. Eliminar producto\n4. Ver todos los productos\n0. Salir");
            opcion = sc.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("Añadir producto");
                    break;
                case 2:
                    System.out.println("Actualizar datos");
                    break;
                case 3:
                    System.out.println("Eliminar producto");
                    break;
                case 4:
                    System.out.println("Ver todos los productos");
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
