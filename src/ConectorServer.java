import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Servidor
 * 
 * @author Ismael Martin
 *
 */
public class ConectorServer {

	/**
	 * Procedimiento que duerme el programa
	 */
	public static void espera() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Comunicacion que se realizara con el cliente
	 * 
	 * @param entrada
	 * @param salida
	 * @throws IOException
	 */
	public static void comunicacion(InputStream entrada, OutputStream salida) throws IOException {
		String envio;
		DataInputStream flujoEntrada;
		DataOutputStream flujoSalida;
		
		flujoEntrada = new DataInputStream(entrada);
		System.out.println("[Servidor]Recibe -> " + flujoEntrada.readUTF());
		
		espera();
		envio = "Pan";
		flujoSalida = new DataOutputStream(salida);
		System.out.println("[Servidor]Envia -> " + envio);
		flujoSalida.writeUTF(envio);

		flujoEntrada = new DataInputStream(entrada);
		System.out.println("[Servidor]Recibe -> " + flujoEntrada.readUTF());

		espera();
		envio = "Fuego";
		flujoSalida = new DataOutputStream(salida);
		System.out.println("[Servidor]Envia -> " + envio);
		flujoSalida.writeUTF(envio);

	}

	/**
	 * Metodo que inia el programa
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int puerto = 8000;
		try {
			// Activar Sevidor
			// ----------------
			ServerSocket servidor = null;
			servidor = new ServerSocket(puerto);
			System.out.println("- Servidor activado -");
			// Conectar el Cliente
			// ----------------
			Socket clienteConectado = null;
			clienteConectado = servidor.accept();
			// Crear Flujos
			// ----------------
			// ***Flujo de entrada
			InputStream entrada = null;
			entrada = clienteConectado.getInputStream();
			// ****Flujo de salida
			OutputStream salida = null;
			salida = clienteConectado.getOutputStream();
			// Comunicacion
			// ------------------
			comunicacion(entrada, salida);
			// ----------------
			// Cierre de los flujos, del cliente y del servidor.
			// ---------------
			salida.close();
			entrada.close();
			clienteConectado.close();
			servidor.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}// Fin main
}// Fin de la clase
