package tp1.pto1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MiClienteSocket {
	Socket socket;
	PrintWriter salida;
	BufferedReader entrada;
	
	public MiClienteSocket(String dirServer, int port) {
		try {
			System.out.println("Conectando con el Servidor");
			socket = new Socket(dirServer, port);
			System.out.println("Se ha establecido la conexión con el servidor");
			
			salida = new PrintWriter(socket.getOutputStream(),true);
			entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			salida.println("Mensaje del cliente");
			System.out.println(entrada.readLine());
			
			//Cierro socket
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args){
		MiClienteSocket mcs = new MiClienteSocket("127.0.0.1", 9999);
	}
	
}
