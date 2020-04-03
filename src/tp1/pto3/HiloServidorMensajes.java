package tp1.pto3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class HiloServidorMensajes implements Runnable {

	private PrintWriter outServer;
	private BufferedReader inServer;
	private ObjectInputStream inObjectServer;
	private ObjectOutputStream outObjectServer;
	private ArrayList<Mensaje> cola;
	private Socket clientSocket;

	public HiloServidorMensajes(Socket clientSocket,ArrayList<Mensaje> cola) {
		this.clientSocket=clientSocket;
		this.cola=cola;
	}

	@Override
	public void run() {
		try {
			outServer = new PrintWriter(clientSocket.getOutputStream(),true);
			inServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			inObjectServer = new ObjectInputStream(clientSocket.getInputStream());
			outObjectServer = new ObjectOutputStream(clientSocket.getOutputStream());
			
			System.out.println("Se ha conectado un cliente");
			
			System.out.println("Esperando acción del cliente");
			String opcion = inServer.readLine();
			
			switch (opcion){
			case "1":
				//SI EL CLIENTE ELIGE LA OPCION 1 "ENVIAR MENSAJE"
				cola.add((Mensaje)inObjectServer.readObject());
				outServer.println("OK");
				break;
			case "2":
				//SI EL CLIENTE ELIGE LA OPCION 2 "RECIBIR MENSAJE"
				//RECIBO ID DEL CLIENTE PARA PODER IDENTIFICAR SUS CORREOS RECIBIDOS
				String idCliente = inServer.readLine();
				ArrayList<Mensaje> mensajesCliente = new ArrayList<Mensaje>();
				
				for (Mensaje m : cola){
					//SI EL MENSAJE CORRESPONDE AL CLIENTE QUE LO SOLICITA
					if(m.getTo().equals(idCliente)){
						mensajesCliente.add(m);
					}
				}
				
				//ENVÍO COLA DE MENSAJES SOLICITADOS POR EL CLIENTE
				outObjectServer.writeObject(mensajesCliente);
				outObjectServer.flush();
				outObjectServer.close();
				
				break;
			default:
				System.out.println("Error");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
		
}

