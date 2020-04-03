package tp1.pto4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import tp1.pto3.Mensaje;

public class ServidorACK {
	ServerSocket serverSocket;
	Socket clientSocket;
	ArrayList<Mensaje> cola;
	
	public ServidorACK(int port) throws IOException, ClassNotFoundException {
		this.serverSocket = new ServerSocket(port);
		//INICIALIZO LA COLA DE MENSAJES
		cola = new ArrayList<Mensaje>();
		
		System.out.println("El servidor se ha iniciado en el puerto: "+port);
		
		while (true){
			System.out.println("Esperando conexiones...");
			clientSocket = serverSocket.accept();
			new Thread(new HiloServidorACK(clientSocket,cola)).start();
		}
	}
	
	public static void main (String[] args) throws IOException, ClassNotFoundException{
		ServidorACK sm = new ServidorACK(7000);
	}
}
