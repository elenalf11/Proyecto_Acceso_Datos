package main;

import java.util.Scanner;

public class Clientes {
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto?serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final  String CONTRASEÑA=  "Victor.241104";
    private Scanner sc = new Scanner(System.in);

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
}
