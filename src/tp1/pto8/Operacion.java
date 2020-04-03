package tp1.pto8;

import java.io.Serializable;

public class Operacion implements Serializable{

	public double calcularPi(long iteraciones) {
		
		double divisor = 1; 
        double pi=0; 

        for(int i=1; i<=iteraciones; i++){ 
            if(i%2==0){ //las iteraciones en posiciones pares se restan 
                pi-= 4/divisor; 
            }else{ //las iteraciones en posiciones impares se suman 
                pi+= 4/divisor; 
            } 
            divisor+=2; //el divisor aumenta en 2 para cada nueva iteracion
        } 
		
        return pi;
	}

}
