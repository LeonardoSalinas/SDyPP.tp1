package tp1.pto6;

import java.util.Random;

public class Calculador implements ICalculador {

	@Override
	public int[] sumarVector(int[] vector1, int[] vector2) {
		//Inicializo el vector resultado
		int[] resultado = new int[vector1.length];
		
		//Realizo la suma elemento a elemento
		for (int i = 0; i<vector1.length; i++){
			/*
			 * Introduzco error sumando un valor aleatorio a cada elemento del vector1
			 */
			Random rd = new Random();
			vector1[i]+= rd.nextInt(500);
			
			//SUMO ELEMENTOS
			resultado[i] = vector1[i] + vector2[i];
		}
		
		return resultado;
	}

	@Override
	public int[] restarVector(int[] vector1, int[] vector2) {
		
		//Inicializo el vector resultado
		int[] resultado = new int[vector1.length];
		
		//Realizo la resta elemento a elemento
		for (int i = 0; i<vector1.length; i++){
			/*
			 * Introduzco error sumando un valor aleatorio a cada elemento del vector1
			 */
			Random rd = new Random();
			vector1[i]+= rd.nextInt(500);
			
			//RESTO ELEMENTOS
			resultado[i] = vector1[i] - vector2[i];
		}
		
		return resultado;
	}
}
