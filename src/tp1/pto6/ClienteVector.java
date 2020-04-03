package tp1.pto6;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClienteVector {
	
	public static String vectorToString(int[]vector){
		String sVector = "[";
		
		for (int i = 0; i<vector.length-1; i++){
			sVector += vector[i]+",";
		}
		sVector += vector[vector.length-1]+"]";
		
		return sVector;
	}
	
	public static void main(String[] args) throws RemoteException, NotBoundException {
		
		Scanner sc = new Scanner(System.in);
		
		//Obtengo el Registry del Server
		Registry RMIRegistry;
		RMIRegistry = LocateRegistry.getRegistry("localhost", 8000);
		
		//Obtengo el objeto remoto
		ICalculador clientStub = (ICalculador) RMIRegistry.lookup("Calculador Server");
		
		System.out.println("MENÚ DE OPCIONES");
		System.out.println("1. Sumar");
		System.out.println("2. Restar");
		System.out.print("Seleccione opción: ");
		String opcion = sc.nextLine();
		
		//Valido la entrada
		while (!(opcion.equals("1")) && !(opcion.equals("2"))){
			System.out.print("Opcion incorrecta: ");
			opcion = sc.nextLine();
		}
		
		System.out.print("\n¿Cuantos elementos tendrán los vectores a operar? ");
		int cantidad = sc.nextInt();
		
		int[] vector1 = new int[cantidad];
		int[] vector2 = new int[cantidad];
		
		System.out.println("\nIngrese los elementos del PRIMER vector");
		for (int i = 0; i<cantidad; i++){
			System.out.print("VECTOR 1 - "+(i+1)+"° número: ");
			vector1[i] = sc.nextInt();
		}
		
		System.out.println("\nIngrese los elementos del SEGUNDO vector");
		for (int i = 0; i<cantidad; i++){
			System.out.print("VECTOR 2 - "+(i+1)+"° número: ");
			vector2[i] = sc.nextInt();
		}
		
		switch (opcion){
		case "1":
			System.out.println("\n"+vectorToString(vector1)+" + "+vectorToString(vector2)+" = "+vectorToString(clientStub.sumarVector(vector1, vector2)));
			break;
		case "2":
			System.out.println("\n"+vectorToString(vector1)+" - "+vectorToString(vector2)+" = "+vectorToString(clientStub.restarVector(vector1, vector2)));
			break;
		default:
			System.out.println("ERROR");
		}
	}

}
