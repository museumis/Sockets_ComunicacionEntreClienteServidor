import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Cliente
 * 
 * @author Ismael Martin
 *
 */
public class ConectorCliente {
	/**
	 * Procedimiento que duerme el programa
	 */
	public static void espera() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Comunicacion con el servidor
	 * @param entrada
	 * @param salida
	 * @throws IOException
	 */
	public static void comunicacion(InputStream entrada,OutputStream salida) throws IOException {
		
		String envio;
		DataInputStream flujoEntrada;
		DataOutputStream flujoSalida;
		
		espera();
		envio = "Pim";
		flujoSalida = new DataOutputStream(salida);
		System.out.println("[Cliente]Envia -> " + envio);
		flujoSalida.writeUTF(envio);
		
		
		flujoEntrada = new DataInputStream(entrada);
		System.out.println("[Cliente]Recibe -> " + flujoEntrada.readUTF());	
		
		espera();
		envio = "Pum";
		flujoSalida = new DataOutputStream(salida);
		System.out.println("[Cliente]Envia -> "+envio);
		flujoSalida.writeUTF(envio);		
		

		flujoEntrada = new DataInputStream(entrada);
		System.out.println("[Cliente]Recibe -> " + flujoEntrada.readUTF());	

	}
	/**
	 * Metodo que inicia el programa
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int puerto = 8000;// Puerto
		String host = "localhost";
		//String host = "192.168.3.205";
		try {
			// Activar el cliente y conectarlo al servidor
			// -----------------
			Socket cliente = null;
			cliente = new Socket(host, puerto);
			System.out.println("- Cliente ejecutado -");
			// Crear flujos
			// -----------------
			// **** Flujo Salida
			OutputStream salida = null;
			salida = cliente.getOutputStream();
			// ***Flujo de entrada
			InputStream entrada = null;
			entrada = cliente.getInputStream();
			//Comunicacion
			// -----------------
			comunicacion(entrada, salida);
			// Cierre flujos y cierre del cliente.
			// -----------------
			entrada.close();
			salida.close();
			cliente.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}// Fin de main
}// Fin de la clase
