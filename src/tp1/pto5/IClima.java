package tp1.pto5;


import java.rmi.Remote;
import java.rmi.RemoteException;


// La interfaz remora que extiende de RemoteInterface 
public interface IClima extends Remote{
	
	public String getWeather () throws RemoteException;

}
