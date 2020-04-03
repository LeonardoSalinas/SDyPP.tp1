package tp1.pto8;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerTarea {
	
	public static void main (String[]args) throws RemoteException, AlreadyBoundException{
		ImplementadorTarea remoteOp = new ImplementadorTarea();
		
		Registry myReg = LocateRegistry.createRegistry(8000);
		
		ITarea serverStub = (ITarea) UnicastRemoteObject.exportObject(remoteOp, 8001);
		
		myReg.bind("Tarea Remota", serverStub);
		System.out.println("Servidor activo");
	}
	
}
