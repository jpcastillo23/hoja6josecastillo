import java.util.Calendar;

/**
 * @author Jose Pablo Castillo rodas
 *------- UNIVERSIDAD DEL VALLE DE GUATEMALA -----------------
 * LABORATORIO 5:
 *		Este Laboratorio consiste en representar el sistema de 
 * 		Colas, en donde un proceso que fue primero en entrar
 *		es el primero en ser atendido
 */
public class ObjetoCliente implements Comparable<ObjetoCliente> {
	public int horain, minutoin, segundosin,proceso,who,which_cola;
	/**
	 * Es true si es Saliente(imprime), false si es para entrar a la cola
	 */
	public boolean entradasalida;
	public int[] resultado = new int[2];
	public int[] tsalida = new int[2];
	public ObjetoCliente(int h, int m , int s,int proc,int w){
		this.horain=h;
		this.minutoin=m;
		this.segundosin=s;
		this.proceso=proc;	//Define el tiempo de proceso
		this.who=w;			//define quien soy
		
		entradasalida=false;//inicializado como entrada
		resultado=tiempoenelbancoCliente( proceso);
		tiempoSalida();
	}
	/**
	 * Metodos Implementados para poder Comparar y utilizar el 
	 * Java Collection Sort. Asi me ordenara mi lista sin que yo
	 * Elabora mayor cosa
	 */
	public boolean equals(Object o) {
		 ObjetoCliente n = (ObjetoCliente)o;
		Integer g=	new Integer(who);
		Integer h=	new Integer(n.who);
		Boolean p= new Boolean(entradasalida);
		Boolean q= new Boolean(n.entradasalida);
	        if (!(o instanceof ObjetoCliente))
	            return false;
	       
	        return p.equals(q)&&g.equals(h);
	    }
	/**
	 *
	 */
	public int compareTo(ObjetoCliente n) {
			Integer g,h,p,q;
			int[] temp;
			g=	new Integer(0);
			h=	new Integer(0);
			p=	new Integer(0);
			q=	new Integer(0);
			if(!entradasalida&&!n.entradasalida){  //si en tal caso es entrante y entrante (meten a colas)
		 		temp=n.tiempoEntrada();
				g=	new Integer(segundosin);
				h=	new Integer(temp[1]);
			 	p= new Integer(minutoin);
				q= new Integer(temp[0]);
		        
			}
			if(!entradasalida&&n.entradasalida){ // si es entrante /saliente (mete y saka en de cola)
				g=	new Integer(segundosin);
				h=	new Integer(n.tsalida[1]);
			 	p= new Integer(minutoin);
				q= new Integer(n.tsalida[0]);
				
			}
			if(entradasalida&&!n.entradasalida){ // si es saliente/entrante (mete y saka en de cola)
				temp=n.tiempoEntrada();
				g=	new Integer(tsalida[1]);
				h=	new Integer(temp[1]);
			 	p= new Integer(tsalida[0]);
				q= new Integer(temp[0]);
			}
			if(entradasalida&&n.entradasalida){ // si saliente/saliente (mete y saka en de cola)
				g=	new Integer(tsalida[1]);
				h=	new Integer(n.tsalida[1]);
			 	p= new Integer(tsalida[0]);
				q= new Integer(n.tsalida[0]);
			}
			//
			int lastCmp = q.compareTo(p);
			int lastCmp3 = g.compareTo(h);
	        return (lastCmp != 0 ? lastCmp : lastCmp3);
	  }
	/**
	 * Utilizado para imprimir listas y que imprima por medio de iteradodes
	 * el toString del Objeto Cliente
	 */
	public String toString() {
		tiempoSalida();
		String hola =  entradasalida? " Entrando a cola Bancaria ": " Saliendo del Banco ";
		return "Cliente "+ who + ", Con estado " + hola+"\n "+"\nTiempo Espera Total Min:"+resultado[0]+" Seg:"+resultado[1]+" \nTiempo de Salida Min:"+tsalida[0]+" Seg:"+tsalida[1]+"\n";
	    }
	/**
	 * Retorna Hora minuto y segundos de Estancia o de operacion total
	 */
	public int[] resulT(){
		return this.resultado;
	}
	public boolean getEstado(){
		return this.entradasalida;
	}
	public void setEstado(boolean h){
		this.entradasalida = h;
	}
	public int whichCola(){
		return this.which_cola;
	}
	public void setCola(int cual_cola){
		this.which_cola=cual_cola;
	}
	public int whoAmI(){
		return this.who;
	}
	public int getProceso(){
		return this.proceso;
	}
	public void setEstadoNegado(){
		this.entradasalida= !this.entradasalida;
	}
	//devuelve una matriz correspondiente a las horas y minutos de estado 
	//del cliente en el banco
	/**
	 * metodo que sirve para cuando se es necesario agregar el tiempo EXTRA
	 * DEBIDO A EL QUE ESTABA ANTES EN LA COLA. ESTE SE LO SUMA A LA DIFERENCIA
	 * ACTIUAL DE HORA
	 * cohesion ALTA CON RESULTADO[]
	 */
	public void sumandoTiempoExtra(int segundos_extras){
		int s2=0,s3=resultado[1];
		int[] msresul=new int[2];	
		
		s2=s3+segundos_extras;
		while(s3<s2){
			resultado[1]++;
			if(resultado[1]>59){
				resultado[1]=0;  
				resultado[0]++;	 //minutos
			}
			s3++;
		}
	}
	/**
	 * A diferencia de Result este retorna el tiempo cuando es 
	 * devuelto a la cola.. este es EL TIEMPO DE NGRESO MAS LO QUE TARDA
	 * le suma lo que hay en resultado, quien guarda los segundos sobrantes
	 * COHESION NULA. se sobreeescribe el valor, no se suma
	 */
	public void  tiempoSalida(){
		int[] minuto_segundo_resultante = new int[2];
		minuto_segundo_resultante[1]=resultado[1]+segundosin;
		minuto_segundo_resultante[0]=minutoin+resultado[0];
		if(minuto_segundo_resultante[1]>59){
			minuto_segundo_resultante[1]=minuto_segundo_resultante[1]-60;
			minuto_segundo_resultante[0]++;
		}
		//Copio el array
        System.arraycopy(minuto_segundo_resultante, 0, tsalida, 0, 2);
		
	}

	/**
	 * Saca el tiempo total que ha estado en espera en el banco, eso incluye
	 * la hora de eentrada, por lo que obtiene el tiempo de salida FINAL DEL BANCO
	 * MAS NO EL TIEMPO QUE TOTAL QUE ESTUVO ADENTRO DE EL.
	 */
	public int[] getTiempoSalida(){
		return tsalida;
	}
	/**
	 *   Retorna el tiempo de Entrada en forma de matriz
	 */
	public int[] tiempoEntrada(){
		int[] tempo = new int[2];
		tempo[0]= minutoin;
		tempo[1]= segundosin;
		return tempo;
	}
	private int[] tiempoenelbancoCliente(int process){
		int s2=0,s3=segundosin,counterm=0,counters=0;
		int retorno[] = new int[2];		
		
		s2=s3+process;
		while(s3<s2){
			counters++;
			if(counters>=60){
				counters=0;
				counterm++;	
			}
			s3++;
		}
		retorno[0]=counterm;
		retorno[1]=counters;
		return retorno;
		
	}
}