package tp1.pto2;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MiServidorSocketT {
	ServerSocket serverSocket;
	Socket clientSocket;
	int port;
	PrintWriter salida;
	BufferedReader entrada;
	
	public MiServidorSocketT(int port) {
		try {
			serverSocket = new ServerSocket(port);
			while (true){
				//Espero una conexion y cuando la acepte registro el socket en el atributo de cliente
				System.out.println("Esperando conexiones entrantes...");
				clientSocket = serverSocket.accept();
				
				//Le asigno el socket a un hilo
				new MiServidorHilos(clientSocket).run();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main (String[]args){
		MiServidorSocketT mss = new MiServidorSocketT(9999);
	}
}
