package tp1.pto1;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MiServidorSocket{
	ServerSocket serverSocket;
	Socket clientSocket;
	int port;
	PrintWriter salida;
	BufferedReader entrada;
	
	public MiServidorSocket(int port) {
		try {
			serverSocket = new ServerSocket(port);
			while (true){
				//Espero una conexion y cuando la acepte registro el socket en el atributo de cliente
				System.out.println("Esperando conexiones entrantes...");
				clientSocket = serverSocket.accept();
				System.out.println("Se ha conectado un cliente");
				entrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				//Inicializo la salida con true para realizar autoflush
				salida = new PrintWriter(clientSocket.getOutputStream(),true);
				
				System.out.println("Esperando mensajes...");
				//Espero recibir un mensaje del cliente
				String mensaje = entrada.readLine();
				System.out.println("Se ha recibido el msj: "+mensaje);
				salida.println("Mensaje del Servidor. He recibido tu mensaje: "+mensaje);
				
				//cierro socket
				clientSocket.close();
				
				System.out.println("Esperando nueva conexión...");
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main (String[]args){
		MiServidorSocket mss = new MiServidorSocket(9999);
	}
}
