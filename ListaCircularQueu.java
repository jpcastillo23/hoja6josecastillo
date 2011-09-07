import java.util.Queue;
import java.util.LinkedList;
/**
 * @author Jose Pablo Castillo rodas
 *------- UNIVERSIDAD DEL VALLE DE GUATEMALA -----------------
 * LABORATORIO 5:
 *		Este es mi lista circular, en donde enlazare a todos
 * 		mis clientes y los creare como una cola
 *		ya que habia que hacer uso de Java Framework, se utilizo el
 *		interfaz Queu, que indica metodos necesarios para hacer colas
 * 		o listas circulares mejor dicho.
 */
public class ListaCircularQueu<E>  implements NuestraInterfaz<E>{
	/**
	 * 
	 */
	private int counter;
	private Queue cola;
	public int getCounter(){
		return counter;
	}
	public ListaCircularQueu(){
		this.counter=0;
		cola = new LinkedList();
	}
	public void add(E o ){
		cola.add(o);
		counter++;
	}
	public E retirar(){
		counter--;
		return (E)cola.poll();
		
	}
	public E peek(){
		return (E)cola.peek();
	}
	public boolean isEmpty(){
		return counter==0;
	}
	
}