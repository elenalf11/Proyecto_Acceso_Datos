package main;

import java.util.Scanner;

/**
 * Clase Engine
 * En esta clase nos encontraremos todos los métodos relacionados con la lógica del programa
 */

public class Engine {
/*Clases Mayra: Clientes, Pedidos_Proveedor y Proveedores*/
    /**
     * Atributos
     */
    Scanner sc = new Scanner(System.in);

    /**
     * Metodo start
     * Es el metodo que inicia el programa, muestra el menú con las posibles opciones que puedes hacer
     */
    public void start(){
        int opcion;
        do{
            System.out.print("Bienvenido a nuestro supermercado, ¿qué información te gustaría consultar? :" +
                    "\n1. Clientes\n2. Empleados\n3. Pedidos Proveedores\n4. Productos\n5. Proveedores\n6. Ventas\n0. Salir del programa");
            opcion = sc.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("Mostrar info sobre Clientes");
                    Clientes cliente = new Clientes();
                    cliente.menu();
                    break;
                case 2:
                    System.out.println("Mostrar info sobre Empleados");
                    Empleados empleados = new Empleados();
                    empleados.menu();
                    break;
                case 3:
                    System.out.println("Mostrar info sobre Pedidos Proveedores");
                    Pedidos_Proveedores pp= new Pedidos_Proveedores();
                    pp.menu();
                    break;
                case 4:
                    System.out.println("Mostrar info sobre Productos");
                    break;
                case 5:
                    System.out.println("Mostar info sobre Proveedores");
                    Proveedores proveedor = new Proveedores();
                    proveedor.menu();
                    break;
                case 6:
                    System.out.println("Mostrar info sobre Ventas");
                    break;
                case 0:
                    System.out.println("Saliendo del programa ...");
                    break;
                default:
                    System.out.println("Opción inválida, vuelve a seleccionar");

            }
        } while(opcion != 0);

    }
}
