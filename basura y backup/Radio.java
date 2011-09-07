/**
 *package mi_radio_josepablo;
 *
 *
 *Jose Pablo Castillo Rodas
 *10007
 *
 *Descripcion:
 *	Clase constructora de Radios. Esta clase Posee
 *	-Atributos para las estaciones guardadas
 *										*am
 *										*fm
 *	-La posicion actual de la sintonizacion en AM/Fm por individuales
 *	Por lo que almacena tanto donde se quedo el usuario en AM y en FM
 *	- posee una memoria de 12  espacios, con frecuencia y estacion
 *	-  
 */

public class Radio implements NuestraInterfaz {
		/**
		 *mision: Estado de FM o AM, FM=TRUE AM=FALSE
		 *Estado indica si esta prendido o apagado
		 */
	private boolean emision, estado=false;
			//Frecuencia establece la frecuencia actual del radio
	/*
	 *  Limites y frecuencia actual de AM
	 */
	private int frecuenciaam,frecaminf,frecamsup;
	/*
	 * Limites y frecuencia actual de FM
	 */
	private double frecuenciafm,frecfminf,frecfmsup;
			//Establece 2 matrices, en las cuales 1 almacenara
			//la frecuencia como flotante, ya sea para FM y AM.
			//La otra establece la Modulacion al momento que se 
			//almaceno.
	/*
	 * Arreglo de boolean, por el cual se obtiene
	 * la Modulacion almacenada alli (true or false)
	 */
	private boolean[] amsfm = new boolean[13];
	/*
	 * Arreglo que Contiene por medio de Floats la 
	 * frecuencia registrada en la localidad de memoria
	 * respectiva
	 */
	private double[] htz = new double[13];

	
	// se establece el estado inicial AM/FM y los limites superior e inferior
	/*
	 * CONSTRUCTOR de la clase Radio. Su objetivo es crear radios inicializados
	 * con sus respectivos limites de frecuencias, para fm y am. Asi mismo
	 * inicializa memorias individuales para cada Modulacion (Am/Fm), por lo que
	 * media vez se cambie de Modulacion y se Retorne, regrese a la frecuencia donde 
	 * se quedo. Asi mismo Inicializa el Radio como Apagado, para cualquiera
	 * no pueda hacer uso del radio hasta y solo hasta que se este prendido.
	 */
	public Radio (){
		//530,1610,(float)87.9,(float)107.9,false
		this.emision=false;
		//Configurar limites am
		this.frecuenciaam= 530;
		this.frecaminf=540;
		this.frecamsup=1610;
		//Configurar limites FM
		this.frecfminf= 87.900;
		this.frecfmsup=107.900;
		this.frecuenciafm= frecfminf;
		this.estado= false;
	}
	//Metodo que cambia de On/Off y regresa la posicion 
	/*
	 * (non-Javadoc)
	 * @see NuestraInterfaz#power()
	 */
	public boolean power(){
		estado =  !estado;
		if (estado)
			System.out.println("El Radio Ahora esta : Encendido");
		else 
			System.out.println("El Radio Ahora esta : Apagado");
		return estado;
	}
	//Metodo que cambia la Emision y regresa la nueva Modulacion
	/*
	 * (non-Javadoc)
	 * @see NuestraInterfaz#cambio()
	 */
	public boolean cambio(){
		emision = !emision;
		if(emision)
			System.out.println("Ahora su Emisora esta en FM");
		else
			System.out.println("Ahora su Emisora esta en AM");
		return emision;
	}
	//Metodo Implementado que Avanza la frecuencia
	//dependiendo de la Modulacion actual
	/*
	 * (non-Javadoc)
	 * @see NuestraInterfaz#fw()
	 */
	public void fw(){
		if(this.estado){
			if (this.emision){
				if(frecfmsup>=(frecuenciafm+0.2))
					this.frecuenciafm += 0.2;
					System.out.println("Estacion: "+frecuenciafm+ "  FM ");
			}
			else{
				if(frecamsup>=(frecuenciaam+10))
					this.frecuenciaam += 10;
						System.out.println("Estacion: "+frecuenciaam+ "  AM ");
			}
		}
	}
	//Metodo Implementado que Retrocede la frecuencia
	//demendiendo de la Modulacion Actual
	/*
	 * (non-Javadoc)
	 * @see NuestraInterfaz#bw()
	 */
	public void bw(){
		if(this.estado){
			if (this.emision){
				if(frecfminf<=(frecuenciafm-0.2))
					this.frecuenciafm -= 0.2;
					System.out.println("Estacion: "+frecuenciafm+ "  FM ");
			}
			else{ 
				if(frecaminf<=(frecuenciaam-10))
					this.frecuenciaam -= 10;
					System.out.println("Estacion: "+frecuenciaam+ "  AM ");
			}
		}
	}
	//Metodo que recibe el numero local de memoria
	//en donde se Almacenara la frecuencia y la mofulacion
	/*
	 * (non-Javadoc)
	 * @see NuestraInterfaz#store(int)
	 */
	public void store(int localidad){
		if (emision)
			this.htz[localidad]=frecuenciafm;
		else
			this.htz[localidad]=frecuenciaam;
		this.amsfm[localidad]=emision;
		System.out.println("Se ha Almacenado la frecuencia"+this.htz[localidad]+" En Localidad: "+ localidad);
	}
	//Metodo Implementado que recibe el numero de boton
	//el cual fue presionado, restaurando asi la frec y
	//Modulaicon a la situaicon actual
	/*
	 * (non-Javadoc)
	 * @see NuestraInterfaz#select(int)
	 */
	public void select(int cualboton){
		System.out.println("Ah seleccionado el boton: "+ cualboton);
		if (htz[cualboton]== (float)0){
			if(amsfm[cualboton])
				this.htz[cualboton]=frecfminf;
			else
				this.htz[cualboton]= frecaminf;
		}
		if(amsfm[cualboton])
			this.frecuenciafm=htz[cualboton];
		else
			this.frecuenciaam= (int)(htz[cualboton]);
		this.emision=amsfm[cualboton];
		System.out.println(toString());
	}
	/*
	 * (non-Javadoc)
	 * @see NuestraInterfaz#salir()
	 */
	public void salir(){
		this.estado = false ;
	}
	/*
	 * Metodo propio de la Clase que recibe un boolean
	 * que indica que emision es la manejada por quien lo 
	 * llama, y retornar la estacion actual presente en 
	 * esa Modulacion
	 */
	public double getFrec(boolean which){
		if (which)
			return this.frecuenciafm;
		else
			return this.frecuenciaam;
	}
	/*
	 * Metodo puesto con el objetivo de crear un Objeto mas
	 * completo. Si en tal caso un Programador X quisiera desarrollar
	 * un programa utilizando mi Contructor, no tiene que velar por
	 * manejar los recursos desde el driver, sino utilizaria los
	 * del Objeto sin ningun problema.
	 * 
	 * Objetivo: Regtornar el estado actual (on/off) del
	 * Radio.
	 */
	public boolean getState(){
		return estado;
	} 
    /**Retorna la modulacion en la cual se encuentra 
	 *la radio
	 */
	public boolean getEmision(){
		return emision;
	}
	/**Metodo que efectivamente regresa a su origen
	 *a cada frecuencia debido a si un usuario
	 *desea crecer la frecuencia, habiendo este 
	 *apachado un numero antes, este lo situaba en 0Htz, ahora
	 *lo situa en el menor
	 */
	public void setFrec(boolean which){
		if (which)
			this.frecuenciafm= frecfminf;
		else
			this.frecuenciaam= frecaminf;
	}
	/**
	 *Metodo que muestra el estado actual de la radio, tanto como emisoras
	 *frecuencias, estado actual.
	 * Metodo Implementado que cumple con @return Un String que posee
	 * los datos actuales del radio.
	 */
	public String toString(){
		String muestra = "";
		if (emision)
			muestra += "Frecuencia:"+frecuenciafm+" FM\n";
		else
			muestra += "Frecuencia:"+frecuenciaam+" AM\n";
		muestra += "+++++++++++++++++++++++++++++++++++++++++++++++++\n";
		muestra += "+++++++++++++++++++++++++Ver DOS+++++++++++++++++\n";
		if (estado)
			muestra += "Tu Radio esta: Encendido \n";
		else
			muestra += "Tu Radio esta: Apagado\n";
		return muestra;
	}


	
	
}