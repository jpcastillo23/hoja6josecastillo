
/*
 * @author Jose Pablo Castillo
 * Carne 10007
 * Universidad del Valle de Guatemala
 * Programacion Orientada a objetos
 *
 */
public class Noder<E>{
	/**
	 * Almacena el Objeto con el que he de trabajar
	 * luego. Debido a que no se sabe que es, cuando
	 * retorna hay que hacerle un casting
	 */
	protected E object;
	/**
	 *  x que indica la direccion de memoria del
	 * siguiente objeto en la lista
	 */
	protected Noder<E> foward;
	/**
	 * Pre: Obj es un valor de un objeto tipo E
	 * post: se le añade la direccion de memoria de un
	 * objeto que acontece a este
	 */
	public Noder(E obj, Noder<E> next ){
		object = obj;
		foward = next;
	}
	/**
	 * @Overinding que implementa el constructor anterior
	 * con una direccion de memoria vacia a referencia, 
	 * lo que lo convierte en la cola
	 */
	public Noder(E v){
		this(v, null);
	}
	/**
	 * Regresa el indicador del siguiente objeto
	 */
	public Noder<E> next(){
		return foward;
	}
	/**
	 * Indica el nuevo lugar de la memoria en la cual 
	 * esta direccionada la memoria
	 */
	public void setNext(Noder<E> next){
		this.foward = next;
	}
	/**
	 * Metodo que regresa el valor del objeto
	 */
	public E value(){
		return object;
	}
	/**
	 * Metodo que setea el ultimo objeto en la lista
	 */
	public void setValue( E v ){
		object = v;
	}
	/**
	 *
	 */
	
	
	/**
	 *
	 */
}