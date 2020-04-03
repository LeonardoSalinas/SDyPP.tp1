package tp1.pto1;

public class Test {

	public static void main(String[] args) {
		MiServidorSocket mss = new MiServidorSocket(9999);
		MiClienteSocket mcs = new MiClienteSocket("127.0.0.1", 9999);
	}

}
