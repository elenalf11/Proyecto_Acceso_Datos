package main;

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

    public void menu(){
        int opcion;
        do{
            System.out.println("¿Qué quieres hacer?\n1. Añadir empleado\n2. Actualizar datos\n3. Eliminar empleado\n4. Ver departamento\n0. Salir");
            opcion = sc.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("Añadir empleado");
                    break;
                case 2:
                    System.out.println("Actualizar datos");
                    break;
                case 3:
                    System.out.println("Eliminar empleado");
                    break;
                case 4:
                    System.out.println("Ver departamento");
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
