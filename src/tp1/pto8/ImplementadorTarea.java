package tp1.pto8;

import java.rmi.RemoteException;

public class ImplementadorTarea implements ITarea{
	Operacion o;

	@Override
	public void setOperacion(Operacion o) throws RemoteException {
		this.o = o;
		
	}

	@Override
	public double ejecutar() throws RemoteException {
		//Calculo Pi basado en 2000000000 iteraciones
		return o.calcularPi(2000000000);
	}

}
