package tp1.pto7;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import tp1.pto3.Mensaje;

public interface ICorreo extends Remote{
	public void enviarMensaje(String from, String to, String body) throws RemoteException;
	public ArrayList<Mensaje> recibirMensajes(String to) throws RemoteException;
}
