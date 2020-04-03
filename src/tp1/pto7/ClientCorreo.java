package tp1.pto7;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;

import tp1.pto3.Mensaje;

public class ClientCorreo {
	
	public static void main(String[] args) throws RemoteException, NotBoundException {
		
		Scanner sc = new Scanner(System.in);
		
		Registry myReg = LocateRegistry.getRegistry("localhost", 8000);
		
		ICorreo clientStub = (ICorreo) myReg.lookup("Servidor de Correo");
		
		System.out.println("Bienvenido");
		System.out.print("Ingrese su usuario: ");
		String usuario = sc.nextLine();
		System.out.println("\nMenú Principal");
		System.out.println("1. Enviar Mensaje");
		System.out.println("2. Recibir mensajes");
		System.out.print("Seleccione una opción: ");
		
		String opcion = sc.nextLine();
		
		while (!(opcion.equals("1")) && !(opcion.equals("2"))){
			System.out.print("Opción incorrecta, vuelva a intentarlo: ");
			opcion = sc.nextLine();
		}
		
		switch (opcion){
		case "1":
			String to;
			String body;
			
			System.out.print("\nReceptor del mensaje: ");
			to = sc.nextLine();
			System.out.print("Cuerpo del mensaje: ");
			body = sc.nextLine();
			
			clientStub.enviarMensaje(usuario, to, body);
			System.out.println("El mensaje se ha enviado exitosamente!");
			
			break;
		case "2":
			//Traigo mis mensajes del servidor
			ArrayList<Mensaje> recibidos = clientStub.recibirMensajes(usuario);
			
			System.out.println("\nMENSAJES RECIBIDOS");
			for (Mensaje m : recibidos){
				System.out.println("\n"+m.toString());
			}
			
			System.out.println("\nEse fue su ultimo mensaje.");
			
			break;
			
		default:
			System.out.println("ERROR");
		}
	}

}
