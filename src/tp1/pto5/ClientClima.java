package tp1.pto5;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientClima {

	public static void main (String args[]) {
		Registry RMIRegistry;
		try {
			//Busco el Registry RMI donde fue registrado el objeto remoto
			RMIRegistry = LocateRegistry.getRegistry("127.0.0.1", 7000);
			
			// Obtengo el objeto remoto con el nombre que fue asociado al servidor
			IClima miS = (IClima) RMIRegistry.lookup("Test Server");
			
			// Uso el método del objeto remoto para obtener el clima
			System.out.println("Cliente: " + miS.getWeather());
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
	}
}
