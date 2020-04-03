package tp1.pto8;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITarea extends Remote {
	public void setOperacion(Operacion o) throws RemoteException;
	public double ejecutar() throws RemoteException;
}