package tp1.pto7;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerCorreo {
	public static void main (String[]args) throws RemoteException, AlreadyBoundException{
		
		Correo myCorreo = new Correo();
		
		Registry myReg = LocateRegistry.createRegistry(8000);
		
		ICorreo serverStub = (ICorreo) UnicastRemoteObject.exportObject(myCorreo, 8001);
		
		myReg.bind("Servidor de Correo", serverStub);
		System.out.println("Servidor activo");
	}
}
