package tp1.pto6;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculador extends Remote{
	public int[] sumarVector(int[] vector1, int[] vector2) throws RemoteException;
	public int[] restarVector(int[] vector1, int[] vector2) throws RemoteException;
}
