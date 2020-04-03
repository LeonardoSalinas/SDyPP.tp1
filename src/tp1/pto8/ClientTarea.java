package tp1.pto8;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientTarea {
	
	public static void main (String[]args) throws RemoteException, NotBoundException{
		Operacion o = new Operacion();
		
		Registry rmiRegistry = LocateRegistry.getRegistry("localhost", 8000);
		
		ITarea clientStub = (ITarea) rmiRegistry.lookup("Tarea Remota");
		
		clientStub.setOperacion(o);
		
		System.out.println("Valor de PI calculado remotamente a partir de 2000000000 iteraciones");
		System.out.println("PI = "+clientStub.ejecutar());
		
	}
	
	
}
