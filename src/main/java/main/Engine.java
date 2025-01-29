package main;

import java.util.Scanner;

public class Engine {
    Scanner sc = new Scanner(System.in);

    public void start(){
        boolean terminado = false;
        do{
            System.out.print("Bienvenido a nuestro supermercado, ¿qué información te gustaría consultar? :" +
                    "\n1. Clientes\n2. Empleados\n3. Pedidos Proveedores\n4. Productos\n5. Proveedores\n6. Ventas\n0. Salir del programa");
            int option1 = sc.nextInt();
            switch (option1){
                case 1:
                    System.out.println("Mostrar info sobre Clientes");
                    terminado = true;
                    break;
                case 2:
                    System.out.println("Mostrar info sobre Empleados");
                    terminado = true;
                    break;
                case 3:
                    System.out.println("Mostrar info sobre Pedidos Proveedores");
                    terminado = true;
                    break;
                case 4:
                    System.out.println("Mostrar info sobre Productos");
                    terminado = true;
                    break;
                case 5:
                    System.out.println("Mostar info sobre Proveedores");
                    terminado = true;
                    break;
                case 6:
                    System.out.println("Mostrar info sobre Ventas");
                    terminado = true;
                    break;
                case 0:
                    System.out.println("Saliendo del programa ...");
                    terminado = true;
                    break;
                default:
                    System.out.println("Opción inválida, vuelve a seleccionar");

            }
        } while(!terminado);

    }
}
