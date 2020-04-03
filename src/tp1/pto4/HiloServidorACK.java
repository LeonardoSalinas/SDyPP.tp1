package tp1.pto4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import tp1.pto3.Mensaje;

public class HiloServidorACK implements Runnable {

	private PrintWriter outServer;
	private BufferedReader inServer;
	private ObjectInputStream inObjectServer;
	private ObjectOutputStream outObjectServer;
	private ArrayList<Mensaje> cola;
	private Socket clientSocket;

	public HiloServidorACK(Socket clientSocket,ArrayList<Mensaje> cola) {
		this.clientSocket=clientSocket;
		this.cola=cola;
	}

	@Override
	public void run() {
		try {
			System.out.println("Se ha conectado un cliente");
			
			outServer = new PrintWriter(clientSocket.getOutputStream(),true);
			inServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			outObjectServer = new ObjectOutputStream(clientSocket.getOutputStream());
			inObjectServer = new ObjectInputStream(clientSocket.getInputStream());
			
			//ESPERANDO ACCION DEL CLIENTE
			String opcion = inServer.readLine();
			
			//COLA EN LA QUE SE ALMACENARAN LOS MENSAJES A ENVIAR AL CLIENTE
			ArrayList<Mensaje> mensajesCliente = new ArrayList<Mensaje>();;
			
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
				
				for (Mensaje m : cola){
					//SI EL MENSAJE CORRESPONDE AL CLIENTE QUE LO SOLICITA
					if(m.getTo().equals(idCliente)){
						mensajesCliente.add(m);
					}
				}
				
				//ENVÍO COLA DE MENSAJES SOLICITADOS POR EL CLIENTE
				outObjectServer.writeObject(mensajesCliente);
				
				//SI EL USUARIO CONFIRMA LA LECTURA DE LOS MENSAJES LOS BORRO
				String eleccion = inServer.readLine();
				if (eleccion.equals("Y")){
					for(Mensaje mClient : mensajesCliente){
						cola.remove(mClient);
					}
					System.out.println("Los mensajes han sido eliminado correctamente");
					outServer.println("OK");
				}else{
					if(eleccion.equals("N")){
						System.out.println("El usuario no desea confirmar la lectura de los mensajes");
						outServer.println("OK");
					}else{
						System.out.println("Error");
						outServer.println("ERROR");
					}		
				}
				
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

