package tp1.pto8;

public class Mainn {

	public static void main(String[] args) {
		long iteraciones = 2000000000; 
        double divisor = 1; 
        double pi=0; 

        System.out.print("SUCESION="); 
        for(int i=1; i<=iteraciones; i++){ 
            if(i%2==0){ //las iteraciones en posiciones pares se restan 
                pi-= 4/divisor; 
            }else{ //las iteraciones en posiciones impares se suman 
                pi+= 4/divisor; 
            } 
            divisor+=2; //el divisor aumenta en 2 para cada nueva iteracion
        } 
        System.out.println("\n\nTOTAL: "+pi);

	}

}
