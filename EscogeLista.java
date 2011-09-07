import java.util.LinkedList;
import java.util.List;
import java.util.*;
/**
 * @author Jose Pablo Castillo rodas
 *------- UNIVERSIDAD DEL VALLE DE GUATEMALA -----------------
 * LABORATORIO 5:
 *		Este es mi lista de arreglos, en donde enlazare a todos
 * 		mis clientes y los creare como una cola
 *		ya que habia que hacer uso de Java Framework, se utilizo el
 *		interfaz Queu, que indica metodos necesarios para hacer colas
 * 		o listas circulares mejor dicho.
 */
public class EscogeLista{
	public static List cualQueresh(boolean which){
		if(which){
			return new LinkedList<ObjetoCliente>();
		}
		else
			return new ArrayList<ObjetoCliente>();
	}
}