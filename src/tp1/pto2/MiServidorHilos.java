package tp1.pto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MiServidorHilos implements Runnable{
	Socket socketCliente;
	private BufferedReader entrada;
	private PrintWriter salida;
	
	public MiServidorHilos(Socket socketCliente) {
		//Recibo el socket por parametro
		this.socketCliente = socketCliente;
	}

	@Override
	public void run() {
		try {
			System.out.println("Se ha conectado un cliente");
			entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			//Inicializo la salida con true para realizar autoflush
			salida = new PrintWriter(socketCliente.getOutputStream(),true);
			System.out.println("Esperando mensajes...");
			//Espero recibir un mensaje del cliente
			String mensaje = entrada.readLine();
			System.out.println("Se ha recibido el msj: "+mensaje);
			salida.println("Mensaje del Servidor. He recibido tu mensaje: "+mensaje);
			
			System.out.println("Esperando nueva conexión...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
