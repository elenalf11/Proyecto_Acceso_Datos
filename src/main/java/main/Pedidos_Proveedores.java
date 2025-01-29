package main;

import java.util.Scanner;

public class Pedidos_Proveedores {
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto?serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final  String CONTRASEÑA=  "Victor.241104";
    private Scanner sc = new Scanner(System.in);

    public void menu(){
        int opcion;
        do{
            System.out.println("¿Qué quieres hacer?\n1. Actualizar datos \n2.Eliminar pedidos \n3.Ver pedidos\n4.Ver información proveedores\n0.Salir");
            opcion=sc.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("actualizar datos");
                    break;
                case 2:
                    System.out.println("Eliminar pedidos");
                    break;
                case 3:
                    System.out.println("Ver pedidos");
                    break;
                case 4:
                    System.out.println("ver información proveedor");
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción invalida");
            }
        }while(opcion!=0);
    }
}
