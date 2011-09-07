/**
 * @author Jose Pablo Castillo Rodas 
 *100007
 *
 *Descripcion:
 *Esta es la interface utilizada en mi programa, el cual describira la
 *caracteristica comun utilizada  para todos los programas de la clase
 *de Estructura de datos en la elaboracion del tanto la cola circular como de lista
 *
*/
public interface NuestraInterfaz<E>{
	public E retirar();
	public void add(E e);
	public boolean isEmpty();
	public E peek();
	public int getCounter();
}