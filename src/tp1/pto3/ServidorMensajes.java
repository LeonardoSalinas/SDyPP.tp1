package tp1.pto3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class ServidorMensajes {
	ServerSocket serverSocket;
	Socket clientSocket;
	ArrayList<Mensaje> cola;
	
	public ServidorMensajes(int port) throws IOException, ClassNotFoundException {
		this.serverSocket = new ServerSocket(port);
		//INICIALIZO LA COLA DE MENSAJES
		cola = new ArrayList<Mensaje>();
		
		System.out.println("El servidor se ha iniciado en el puerto: "+port);
		
		while (true){
			System.out.println("Esperando conexiones...");
			clientSocket = serverSocket.accept();
			new Thread(new HiloServidorMensajes(clientSocket,cola)).start();
		}
	}
	
	public static void main (String[] args) throws IOException, ClassNotFoundException{
		ServidorMensajes sm = new ServidorMensajes(7000);
	}
}
