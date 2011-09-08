/**
 * @author Jose Pablo Castillo Rodas
 *{@link http://code.google.com/p/proyectojosepablo1/}  label
 *100007
 *
 *Descripcion:
 *	Este teoricamente es un panel, sin embargo es quien 
 *	controla todo el manejo del programa ya que el Driver
 *	solo utiliza un objeto de esta clase.
 *	
 *	La funcion es asignarle a cada boton una ubicacion, una imagen
 *	y su respectiva localizacion  en el panel. Posee la cualidad
 *	de asignarle un fondo a un panel asi como Tips a botones de importancia.
 *	Implemeta ActionListener, por lo queno tenemos que crear una subclase o
 *	una clase interna.
 *
 */
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.Calendar;
//import java.util.Timer;
import java.util.*;
import java.util.List;

/*
 * Panel_fondo es una clase que hereda un JPanel. Esta tambien implementa
 * aciton listeners con el objetivo de 
 */
@SuppressWarnings("serial")
public class Panel_fondo extends JPanel implements ActionListener {
	
	
	/**++++++++++++++++++++++   Atributos   +++++++++++++++++++++++++*/
	private Image fondo_panel;
	/**
	 * Arreglo de JLabels que guarda la cantidad de Clientes por Fila
	 */
	private JLabel[] clientes= new JLabel[8];
	/**
	 * Arreglo que almacena la hora actual de la COmputadora
	 */
	private JLabel[] horario= new JLabel[6];
	/**
	 *	Arreglo que indica la cantidad de Rotulos en la pantalla
	 */
	private JLabel[] rotulos= new JLabel[2];
	/**
	 *ARREGLO que almacena el tiempo de espera del ultimo cliente en salir
	 * Incluye minutos y segundos agregados
	 */
	private JLabel[] tardanza= new JLabel[4];//
	/**
	 * Arrego de botones, donde se encuentra el boton de "anadir Cliente " y 
	 * el boton de remove un Cliente
	 */
	private myBotones[] botoncitos = new myBotones[2];
	private Font font = new Font("Tahoma",1,28);
	private Calendar calendario = Calendar.getInstance();
	private Timer timer;
	private Random random;
	private List<ObjetoCliente> myListaEventos, myColaBanco1, myColaBanco2, myColaBanco3, myColaBanco4;
	private ActionListener l;
	private selectionSort sort;
	/**
	 *  Este es el cliente temporal
	 */
	private ObjetoCliente elClienteTemporal;
	private boolean apachadoTemporal=false,tempboo,estadoinicial=false;
	private int counter=0,counting=0,hora=0, minutos=0, segundos=0,fila1=0,fila2=0,fila3=0,fila4=0, minutos2=0, segundos2=0;
	
	/**
	 *Crea el Panel con fondo del radio. Tambien se encarga de 
     *responder ante el tipo de objeto que se cree, pudiendo asi 
	 *mostrar en pantalla el formato actual del radio.
	 */
	
	public Panel_fondo(Image fondo){
		
		/**
	 	* INICIALIZAR PROPIEDADES DE PANTALLA Y GUI 
	 	*/
		this.fondo_panel = fondo;
		Dimension tamano = new Dimension(fondo_panel.getWidth(null),fondo_panel.getHeight(null));
		setPreferredSize(tamano);
		setLayout(null);
		//************************/
		
		
		 
		
		//*****************************************************************************************************
		//*****************************************************************************************************
		//*****************************************************************************************************
		//*****************************************************************************************************
		//*****************************************************************************************************
		//************************************I M P O R T A N T E**********************************************
		//*****************************************************************************************************
		//************SI QUIERES PROBAR ENTRE LISTA CIRCULAR O ARRAY SOLO DEBES CABIAR TRUE A FALSE  **********
		//*****************El cambio minimo solo fue Cambiar 2 Lineas de la clase EscogeLista******************
		//*****************************************************************************************************
		    myListaEventos = EscogeLista.cualQueresh(true); //aqui no se cambio nada
			myColaBanco1 = EscogeLista.cualQueresh(true);   //solo en la linea
			myColaBanco2 = EscogeLista.cualQueresh(true);   //"private List<ObjetoCliente> myListaEventos ...
			myColaBanco3 = EscogeLista.cualQueresh(true);   //se cambio NuestraInterfaz por List. y ya.
			myColaBanco4 = EscogeLista.cualQueresh(true);
		
		//*****************************************************************************************************
		//*****************************************************************************************************
		//*****************************************************************************************************
		//*****************************************************************************************************
		//*****************************************************************************************************
		//*****************************************************************************************************
		//*****************************************************************************************************
		//*****************************************************************************************************
		//*****************************************************************************************************
		//*****************************************************************************************************
		//*****************************************************************************************************
		//****************************************************************************************************/
		
		
		//*************************Coloco todos mis ROTULOS DE HORA Y ULTIMO TIEMPO ***************************
		
		setRotulos();
		rotulos[0].setBounds(90,489,59,51);
		rotulos[1].setBounds(90,560,133,39);
		
		//*************************Coloco todos mis HORA en Forma ELEGANTE ***************************
		settingHora();
		for(int x=0;x<horario.length;x++){
				horario[x]= new JLabel();
				add(horario[x]);
				
				horario[x].setBounds(155+x*54,489,56,49);
				
		}
		for(int x=0;x<tardanza.length;x++){
			tardanza[x]= new JLabel();
			add(tardanza[x]);
			tardanza[x].setBounds(258+x*54,555,56,49);
		}
		setHourImagesTardanza( minutos2, segundos2, tardanza);
		/**
		 * Instanciacion del timer, quien muestra el reloj en pantalla
		 */
		l = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				horaContinue();
				setHourImages(hora, minutos, segundos, horario);
			}
			
		};
		timer = new Timer(400,new innerClass());
		timer.start();
		
		/*************************Coloco todos mis CONTADORES DE FILAS ***************************
		/**
		 *Setear contadores
		 */
		counter=124;
		for(int x=0;x<clientes.length-1;x++){
				clientes[x]= new JLabel();
				add(clientes[x]);
				clientes[x].setBounds(counter,370,56,49);
				clientes[x+1]= new JLabel();
				add(clientes[x+1]);
				clientes[x+1].setBounds(counter+49,370,56,49);
				x+=1;
				counter=counter+200;
		}
		setClientLastImages();//mis_botoncitos
		/*************************Coloco todos mis botones DE ACCION ***************************/
		if(System.getProperty("os.name").toLowerCase().startsWith("windows")){
			botoncitos[0] = new myBotones(new ImageIcon(".\\imagenes\\r2.png"),0);
			botoncitos[1] = new myBotones(new ImageIcon(".\\imagenes\\r3.png"),1);
			}
		else{
			botoncitos[0] = new myBotones(new ImageIcon("./imagenes/r2.png"),0);
			botoncitos[1] = new myBotones(new ImageIcon("./imagenes/r3.png"),1);
			}
		botoncitos[0].addActionListener(this);
		add(botoncitos[0]);
		botoncitos[0].setBounds(570,489,116,58);
		botoncitos[1].addActionListener(this);
		add(botoncitos[1]);
		botoncitos[1].setBounds(570+125,489,123,54);
		
		
		random=new Random();
		//Colocar Mis Botones en Lugares Especificos, sinningun LAYOUT
		//Establecer ToolTips para ayuda*/
		botoncitos[0].setToolTipText("Desea agregar un Nuevo Cliente?");
		botoncitos[1].setToolTipText("Ejecutar Impresion en DOS de la salida de los clientes de la lista de eventos"); //( */
		
		
	
	}
	
// ******************************************************************************************************
	private class innerClass implements ActionListener{	
		public void actionPerformed(ActionEvent event){
			horaContinue();
			setHourImages(Panel_fondo.this.hora,Panel_fondo.this.minutos, Panel_fondo.this.segundos,Panel_fondo.this.horario);
		}		
	}
	/*	@verriding a la clase implementada de ActionListener
	Este metodo Lee la fuente de la cual es adquirido la accion
	y con ello lee su ID (identidifacor de boton). Crea diversas
	acciones para cada boton.
	*/
	
	//@verriding para pintar el fondo del panel
	//con la imagen inicialmente enviada
	public void paintComponent(Graphics hola){
		hola.drawImage(fondo_panel,0,0,null);
	}
	public void setRotulos(){
		for(int x=0;x<rotulos.length;x++){
			if(System.getProperty("os.name").toLowerCase().startsWith("windows"))
				rotulos[x] = new JLabel(new ImageIcon(".\\imagenes\\r"+x+".png"));
			else
				rotulos[x] = new JLabel(new ImageIcon("./imagenes/r"+x+".png"));
			add(rotulos[x]);
		}	
	}
	public void setHourImages(int h, int min, int seg, JLabel[] hho){
		int[] tempo = new int[hho.length];   // los finalizadodes "s" y "l" significan superior  y low
		tempo[0]=h/10;
		tempo[1]=h-10*(tempo[0]);
		if(tempo[1]<0)
			tempo[1]=h;
		tempo[2]=min/10;
		tempo[3]=min-10*(tempo[2]);
		if(tempo[3]<0)
			tempo[3]=min;
		tempo[4]=seg/10;
		tempo[5]=seg-10*(tempo[4]);
		if(tempo[5]<0)
			tempo[5]=seg;
		//*************************Creo mis JLabels con su respectiva IMAGEN y NOMBRE ***************************
		asignacionImagen(hho, tempo);
	}
	public void setHourImagesTardanza(int h, int min, JLabel[] hho){
		int[] tempo = new int[hho.length];   // los finalizadodes "s" y "l" significan superior  y low
		tempo[0]=h/10;
		tempo[1]=h-10*(tempo[0]);
		if(tempo[1]<0)
			tempo[1]=h;
		tempo[2]=min/10;
		tempo[3]=min-10*(tempo[2]);
		if(tempo[3]<0)
			tempo[3]=min;

		//*************************Creo mis JLabels con su respectiva IMAGEN y NOMBRE ***************************
		asignacionImagen(hho, tempo);
	}
	public void setClientLastImages(){
		int[] tempo = new int[clientes.length];   // los finalizadodes "s" y "l" significan superior  y low
	
		tempo[0]=this.fila1/10;
		tempo[1]=this.fila1-10*(tempo[0]);
		if(tempo[1]<0)
			tempo[1]=this.fila1;
		tempo[2]=this.fila2/10;
		tempo[3]=this.fila2-10*(tempo[2]);
		if(tempo[3]<0)
			tempo[3]=this.fila2;
		tempo[4]=this.fila3/10;
		tempo[5]=this.fila3-10*(tempo[4]);
		if(tempo[5]<0)
			tempo[5]=this.fila3;
		tempo[6]=this.fila4/10;
		tempo[7]=this.fila4-10*(tempo[6]);
		if(tempo[7]<0)
			tempo[7]=this.fila4;
		//*************************Creo mis JLabels con su respectiva IMAGEN y NOMBRE ***************************
		asignacionImagen(clientes,tempo);
	}
	public void asignacionImagen(JLabel[] h, int[] tempo){
		for(int a=0;a< h.length ;a++){
			if(System.getProperty("os.name").toLowerCase().startsWith("windows"))
				h[a].setIcon(new ImageIcon(".\\imagenes\\"+tempo[a]+".png"));
			else
				h[a].setIcon(new ImageIcon("./imagenes/"+tempo[a]+".png"));
			h[a].repaint();
		}
	}
	
	public void horaContinue(){
		if(segundos<60){
			segundos += 1;
		}
		else{
			segundos=0;
			if(minutos<60)
				minutos+=1;
			else{
				minutos=0;
				if(hora<12)
					hora+=1;
				else
					hora=0;
			}
		}
	}
	public void settingHora(){
		
		hora = calendario.get(Calendar.HOUR);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		
	}
	
	
	public int whichRow(int a, int b , int c, int d){
		int[] tempo=new int[4];
		tempo[0]=a;
		tempo[1]=b;
		tempo[2]=c;
		tempo[3]=d;
		sort = new selectionSort(tempo,true);
		tempo= sort.getMatriz();
		//indica que fila es la que tiene menos clientes
		if(tempo[0]==myColaBanco1.size())
			return 1;
		if(tempo[0]==myColaBanco2.size())
			return 2;
		if(tempo[0]==myColaBanco3.size())
			return 3;
		if(tempo[0]==myColaBanco4.size())
			return 4;
		return 3;
			
	}
	public int[] whichRow2(int a, int b , int c, int d){
		int[] tempo=new int[4];
		int q,w,e,r;
		tempo[0]=a;
		tempo[1]=b;
		tempo[2]=c;
		tempo[3]=d;
		//establece quien es el menor tiempo
		sort = new selectionSort(tempo,true);
		tempo= sort.getMatriz();
		//indica que fila es la que tiene menos clientes
	
		return tempo;	
	}
	public void actionPerformed(ActionEvent event){
	 	myBotones temp=(myBotones)event.getSource();
		int coor=temp.getId(),m=random.nextInt(34),whm,a,b,c,d;
		switch(coor){
			case 0:
					//++++***setear imagenes de Segundos añadidos por Cliente***+++
					segundos2=m;
					setHourImagesTardanza( minutos2, segundos2, tardanza);
					//++++*******************************************++++++
					
					//Indicar La cola menos larga para añadir clientes
					//************Las demas filas son adonrno, 
					//************la unica Importante es "whm= whichRow( a,  b ,  c,  d);"******
					System.out.println("------------------ NUEVO CLIENTE --------------------" );
					System.out.println("------------------ ------------- --------------------" );
					System.out.println("Cliente "+counting+" Agregado a la Hora: "+ hora + ", min :"+minutos+", seg: "+segundos  );
					System.out.println("Tiempo Transaccion :"+m+" Seg." );
					//++++*******************************************++++++
					myListaEventos.add(new ObjetoCliente(hora, minutos,segundos,m, counting));
					setClientLastImages();
					System.out.println("Asignado a la Lista de Eventos ...  \n" );
					counting++;
					break;
			case 1:
					showEverything();
					break;
			
		}
	
	}
	public int[] engañoF(){
		int[] f = new int[2];
		f[0]=10000;
		f[1]=10000;
		return f;
	}
	/*
	 * Metodo que se encarga de Eliminar El Cliente Correspondiente
	 */
	public void showEverything(){  
		ObjetoCliente nuevo,nuevo2;
		int a,b,c,d,whm=0,timesup=0;
		int[] temp;
		while(timesup<myListaEventos.size()){//<<<<<----------------------------------------------------------------
		/**
		 *	Verifico que Cola tiene menos personas para asignarlo alli... 
		 */
			nuevo= (ObjetoCliente)myListaEventos.get(timesup);
			/**
			 *Quitar Linea para ver Comportamiento de Creacion de Lista de Eventos
			 *System.out.println("Estado original:"+ nuevo.getEstado());
		  // */
			if(!nuevo.getEstado()){ //significa que no es un evento de sacar
								  //si false - Agregar, si true Imprimir
				a=myColaBanco1.size();
				b=myColaBanco2.size();
				c=myColaBanco3.size();
				d=myColaBanco4.size();
				whm= whichRow( a,  b ,	c,	d);			//
				/**
				 *Setea la cola en la cual esta el cliente [1-4]
				 */
				nuevo.setCola(whm);
				System.out.println("Añadiendo Cliente a la Cola:"+whm + " del Banco UVG ...");
				//Mandar al Cliente respectivo a la cola menos larga
					if(whm==1 && a<10){
						myColaBanco1.add(nuevo);
						fila1++;
					}
					if(whm==2 && b<10){
						myColaBanco2.add(nuevo);
						fila2++;
					}
					if(whm==3 && c<10){
						myColaBanco3.add(nuevo);
						fila3++;
					}
					if(whm==4 && d<10){
						myColaBanco4.add(nuevo);
						fila4++;
			 		}
					
					temp=nuevo.tiempoEntrada();
					nuevo2=reConstruyendo(temp[0], temp[1], nuevo.getProceso(),nuevo.whoAmI());
					nuevo2.setEstado(true);
					nuevo2.setCola(whm);
					//agrego metodo que ingrese el evento en forma ordenada
					myListaEventos.add(nuevo2);

				}
			else{
				System.out.println("Imprimiendo Cliente "+timesup +"... Y sacando de la cola:" +nuevo.whichCola());
				//aqui va todo lo correcpondiente al evento de salida
			}
			setClientLastImages();
			timesup++;
		}
		System.out.println("-------------------------------------------------------------");
		System.out.println("--------O R D E N A N D O  C L I E N T E S ....--------------");
		System.out.println("-------------------------------------------------------------\n");
		ordenarPorTiempo(myListaEventos);
		/**
		 * Quitar comentario para ver Como la lista queda despues de Ordenar los Eventos, ya sea de 
		 * entrada como de salida
		 * System.out.println(myListaEventos);
		 */
		timesup=0;
		System.out.println("---------------------------------------------------------");
		System.out.println("--------------------El Orden es Ascendente---------------");
		System.out.println("----por  lo  que  mirara  primero  el ultimo en salir----");
		System.out.println("----por motivo de  orden, no más.  Asimismo Mirar que ---");
		System.out.println("----solo se utilizo  1 linea del Java Collection.Sort----");
		System.out.println("----y  que  se  implemento  la  interfaz  compareTo()----");
		System.out.println("----haciendo Overrinding a una propiedad de un Objeto----");
		System.out.println("----Cualquiera,  para  que java  lo  ordene  bajo sus----");
		System.out.println("----Propios metodos--------------------------------------");
		System.out.println("---------------------------------------------------------\n");

		
		while(timesup<myListaEventos.size()){//<<<<<----------------------------------------------------------------
		/**
		 *	Verifico que Cola tiene menos personas para asignarlo alli... 
		 */
		
			nuevo= (ObjetoCliente)myListaEventos.get(timesup);
			if(nuevo.getEstado()){
				whm=nuevo.whichCola();
				/**
				 * Metodo encargado de disminuir las colas y de imprimir el respectivo evento sacado
				 * de la misma
				 */
				if(whm==1 ){
					nuevo=(ObjetoCliente)myColaBanco1.remove(0);
					temp=nuevo.resulT();
					sumandoColaTiempoExtra(myColaBanco1,temp[1]);
					fila1--;
				}
				if(whm==2 ){
					nuevo=(ObjetoCliente)myColaBanco2.remove(0);
					temp=nuevo.resulT();
					sumandoColaTiempoExtra(myColaBanco2,temp[1]);
					fila2--;
				}
				if(whm==3 ){
					nuevo=(ObjetoCliente)myColaBanco3.remove(0);
					temp=nuevo.resulT();
					sumandoColaTiempoExtra(myColaBanco3,temp[1]);
					fila3--;
				}
				if(whm==4 ){
					nuevo=(ObjetoCliente)myColaBanco4.remove(0);
					temp=nuevo.resulT();
					sumandoColaTiempoExtra(myColaBanco4,temp[1]);
					fila4--;
		 		}
				setClientLastImages();
				
				System.out.println(nuevo);
				/** Aquí se retrasa la ejecución un segundo y se captura la
		         * posible excepción que genera el método, aunque no se hace
		         * nada en el caso de que se produzca. 0.5 Segundos
				 */
		        try {
		            Thread.currentThread().sleep( 1500 );
		        }catch( InterruptedException e ){}
				
			/**
			 *Quitar Linea para ver Comportamiento de Creacion de Lista de Eventos
			 *System.out.println("Estado original:"+ nuevo.getEstado());
		  // */
			}
			timesup++;
		}
		
	}
	
	/**
	 * Metodo Utiliizando Java Collection SORTS para ordenar mis Clientes
	 * Solo en 1 linea de codigo
	 */ 
	public void ordenarPorTiempo(List<ObjetoCliente> o){
		Collections.sort(o);
	}
	/**
	 * Reconstruye un Cliente para no crear Aliases
	 */
	public ObjetoCliente reConstruyendo(int m, int s, int t, int who){
		ObjetoCliente h = new ObjetoCliente(0,m,s,t,who);
		return h;
	}
	public void sumandoColaTiempoExtra( List<ObjetoCliente> hola,int seg_extra){
		int h = hola.size(),x=0;
		ObjetoCliente u; //me sirve para crear aliases...
		while (x<h) {
		    u=(ObjetoCliente)hola.get(x);
			u.sumandoTiempoExtra(seg_extra);
			x++;
		}	
	}


	
	
	
	
}

/**
int[] a,b,c,d;
int[] whot,rew;
int conm=0;
ObjetoCliente lp=new ObjetoCliente(1,1,1,1,1);
boolean delimit = true;

if(!myColaBanco1.isEmpty())
	a=((ObjetoCliente)myColaBanco1.get(0)).resulT();
else{
	a= engañoF();
	conm++;}
if(!myColaBanco2.isEmpty())
	b=((ObjetoCliente)myColaBanco2.get(0)).resulT();
else{
	b= engañoF();
	conm++;}
if(!myColaBanco3.isEmpty())	
	c=((ObjetoCliente)myColaBanco3.get(0)).resulT();
else{
	c= engañoF();
	conm++;}
if(!myColaBanco4.isEmpty())
	d=((ObjetoCliente)myColaBanco4.get(0)).resulT();
else{
	d= engañoF();
	conm++;}
	whot=whichRow2( a[0], b[0] , c[0], d[0]);
	//Mandar al Cliente respectivo a la cola menos larga

if(!(conm>=4    ))
{
	if(whot[0]== a[0] && delimit){
		lp=(ObjetoCliente)myColaBanco1.remove(0);
		fila1--;
		delimit=false;
	}
	if(whot[0]==b[0] && delimit){
		lp=(ObjetoCliente)myColaBanco2.remove(0);
		fila2--;
		delimit=false;
	}
	if(whot[0]==c[0] && delimit){
		lp=(ObjetoCliente)myColaBanco3.remove(0);
		fila3--;
		delimit=false;
	}
	if(whot[0]==d[0] && delimit){
		lp=(ObjetoCliente)myColaBanco4.remove(0);
		fila4--;
	}
} //sumandoTiempoExtra(int segundos extras) 
	rew=lp.resulT();
	setClientLastImages();
	System.out.println("------------------------------------------------------ " );
	System.out.println("Cliente"+lp.whoAmI() +" Ah sido Retirado de la Cola " );
	System.out.println("COn UN tiempo En Banco de  MIn:"+rew[0] +", Seg: "+rew[1]+" \n" );
	System.out.println("------------------------------------------------------ " );
	if(counting<0)
		counting--;
	
	//++++*******************************************++++++ */
