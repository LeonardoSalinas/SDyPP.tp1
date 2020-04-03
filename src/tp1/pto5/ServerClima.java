package tp1.pto5;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class ServerClima {

	public static void main(String[] args) {
	
			try {
				// Instancio un objeto de la clase RemoteObject que implementa la interfaz remota.
				RemoteObject remoteObject = new RemoteObject();
				
				// Creo el Registry en un puerto, donde se registrarán los objetos remotos.
				// De esta manera puedo crear una asociacion entre un objeto y un nombre de referencia.
				Registry myReg = LocateRegistry.createRegistry(7000);
			
				// Obtengo el STUB que comunica al objeto remoto exportando el remoteObject (especificando puerto)
				IClima serverStub = (IClima) UnicastRemoteObject.exportObject(remoteObject,8000);
				
				// Enlazo el Registry con el STUB, asociandole un nombre
				myReg.rebind("Test Server", serverStub);
				System.out.println("Server started");
				
			} catch (RemoteException e) {
				e.printStackTrace();
			}
	}

}
