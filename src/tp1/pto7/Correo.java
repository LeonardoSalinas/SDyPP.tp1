package tp1.pto7;

import java.util.ArrayList;

import tp1.pto3.Mensaje;

public class Correo implements ICorreo {

	ArrayList<Mensaje> cola;
	
	public Correo(){
		cola = new ArrayList<Mensaje>();
	}
	
	@Override
	public void enviarMensaje(String from, String to, String body) {
		Mensaje msg = new Mensaje(from, to, body);
		cola.add(msg);
	}

	@Override
	public ArrayList<Mensaje> recibirMensajes(String to) {
		
		ArrayList<Mensaje> msgsClient = new ArrayList<Mensaje>();
		
		for (Mensaje m : cola){
			if (m.getTo().equals(to)){
				msgsClient.add(m);
			}
		}
		
		return msgsClient;
	}

}
