package tp1.pto5;

import java.rmi.RemoteException;

public class RemoteObject implements IClima {
	//Clase que implementa la interfaz remota IClima
	public String getWeather() throws RemoteException {
		// temperatura|condicion
		return "19c|Lluvia";
	}
	
	

}
