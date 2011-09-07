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
public class ArraylistQueue<E>  implements NuestraInterfaz<E>{
	/**
	 * 
	 */
	protected Object data[];
	protected int head;
	protected int count;
	public ArraylistQueue(){
		//esto es por que el maximo es de 40
		data = new Object[41];
		head=0;
		count=0;
	}
	public void add(E e)
	/**
	 *  pre: the queue is not full
	 *  post: the value is added to the tail of the structure
	 */
	{
		if(!isFull()){
			int tail = (head+count) % data.length;
			data[tail] = e;
			count++;
		}
			
	}
	public E retirar(){
		E reto;
		if(isEmpty())
			return null;
		else{
			reto = (E)data[head];
			head = (head + 1) % data.length;
			count --;
			return reto;
		}
	}
	
	public boolean isEmpty(){
		return count-head<0;
	}
	public E peek(){
		if (isEmpty())
			return null;
		else{
			return (E)data[head];
		}
	}
	public int size(){
		return count-head;
	}
	public void clear(){
		count=0;
		head=0;
	}
	public int getCounter(){
		return count-head;
	}
	public boolean isFull(){
		return count==data.length;
	}
}