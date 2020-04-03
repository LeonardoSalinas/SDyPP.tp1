package tp1.pto6;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerVector {
	public static void main (String[]args) throws RemoteException, AlreadyBoundException{
		//Instancio un objeto Calculador que implementa la interfaz remota
		Calculador calcVector = new Calculador();
		
		//Creo un Registry en el puerto 8000, donde se registraran los objetos remotos
		Registry myReg = LocateRegistry.createRegistry(8000);
		
		//Obtengo el STUB que me permite comunicarme con mi objeto remoto Calcular
		ICalculador serverStub = (ICalculador) UnicastRemoteObject.exportObject(calcVector, 8001);
		
		//Publico el Stub en el registry para que sea accesible por los clientes
		myReg.bind("Calculador Server", serverStub);
		System.out.println("Servidor activo");
		
	}
}