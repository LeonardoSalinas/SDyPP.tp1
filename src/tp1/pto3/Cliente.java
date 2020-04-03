package tp1.pto3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
	String idCliente;	
	Socket clientSocket;
	PrintWriter outClient;
	BufferedReader inClient;
	ObjectInputStream inObjectClient;
	ObjectOutputStream outObjectClient;
	Scanner sc;
	
	public Cliente(String host, int port) throws UnknownHostException, IOException, ClassNotFoundException {
		clientSocket = new Socket(host, port);
		outClient = new PrintWriter(clientSocket.getOutputStream(),true);
		inClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		outObjectClient = new ObjectOutputStream(clientSocket.getOutputStream());
		inObjectClient = new ObjectInputStream(clientSocket.getInputStream());
		
		System.out.println("Conexion establecida con el Servidor de Mensajes");
		System.out.println("HOST SERVIDOR: "+host+"\nPUERTO SERVIDOR: "+port+"\n");
		
		System.out.print("Ingrese identificador de usuario: ");
		sc = new Scanner(System.in);
		idCliente = sc.nextLine();
		System.out.println("");
		
		System.out.println("MENÚ PRINCIPAL\n1. Enviar Mensaje\n2. Recibir Mensajes");
		System.out.print("Seleccione opción: ");
		
		sc = new Scanner(System.in);
		String opcion = sc.next();
		
		//VALIDO LA ENTRADA
		while(!opcion.equals("1") && !opcion.equals("2")){
			System.out.println("Opción invalida. Ingrese nuevamente la opción: ");
			
			sc = new Scanner(System.in);
			opcion = sc.nextLine();
		}
		
		outClient.println(opcion);
		
		switch (opcion){
		case "1":
			System.out.println("\nNUEVO MENSAJE");
			System.out.print("Destinatario: ");
			sc = new Scanner(System.in);
			String to = sc.nextLine();
			System.out.print("Mensaje: ");
			sc = new Scanner(System.in);
			String body = sc.nextLine();
			
			Mensaje msg =new Mensaje(idCliente, to, body);
			outObjectClient.writeObject(msg);
			System.out.println("\nEsperando confirmacion del Servidor...");
			String confirmacion = inClient.readLine();
			
			if (confirmacion.equals("OK")){
				System.out.println("El mensaje ha sido enviado correctamente");
			}else{
				System.out.println("Error");
			}
			break;
		case "2":
			//ENVÍO EL IDENTIFICADOR DE USUARIO PARA QUE EL SERVIDOR SEPA QUE MENSAJE DEBE ENVIARME
			outClient.println(idCliente);
			
			//RECIBO LA COLA DE MENSAJES SOLICITADA
			ArrayList<Mensaje> recibidos = new ArrayList<Mensaje>();
			recibidos = (ArrayList<Mensaje>)inObjectClient.readObject();
			inObjectClient.close();
			
			//MUESTRO EN CONSOLA EL CONTENIDO DE LOS MENSAJES RECIBIDOS
			System.out.println("\nMENSAJES RECIBIDOS");
			for (Mensaje m : recibidos){
				System.out.println("\n"+m.toString());
			}
			break;
		default:
			System.out.println("Error");
		}
	}
	
	public static void main (String[] args) throws UnknownHostException, IOException, ClassNotFoundException{
		Cliente c = new Cliente("localhost", 7000);
	}
}
