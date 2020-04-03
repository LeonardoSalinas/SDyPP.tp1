package tp1.pto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class MiClienteSocketT {
	Socket socket;
	PrintWriter salida;
	BufferedReader entrada;
	
	public MiClienteSocketT(String dirServer, int port) {
		try {
			System.out.println("Conectando con el Servidor");
			
			while(true){
				socket = new Socket(dirServer, port);
				System.out.println("Se ha establecido la conexión con el servidor");
				
				salida = new PrintWriter(socket.getOutputStream(),true);
				entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				Scanner sc = new Scanner(System.in);
				salida.println(sc.nextLine());
				System.out.println(entrada.readLine());
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[]args){
		MiClienteSocketT mcs = new MiClienteSocketT("127.0.0.1", 9999);
	}
	
}
