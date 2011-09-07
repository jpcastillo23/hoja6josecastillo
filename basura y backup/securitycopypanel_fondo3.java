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
/*
 * Panel_fondo es una clase que hereda un JPanel. Esta tambien implementa
 * aciton listeners con el objetivo de 
 */
@SuppressWarnings("serial")
public class Panel_fondo extends JPanel implements MouseListener {
	
	
	/**++++++++++++++++++++++   Atributos   +++++++++++++++++++++++++*/
	private Image fondo_panel;
		//Botones: Encendido/apagado; Am/fm; 1-12;STORE;
	private Radio car_radio;
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
	 */
	private JLabel[] tardanza= new JLabel[6];
	/**
	 * Arrego de botones, donde se encuentra el boton de "anadir Cliente " y 
	 * el boton de Retirar un Cliente
	 */
	private MyJlabel[] botoncitos = new MyJlabel[2];
	private Font font = new Font("Tahoma",1,28);
	private Calendar calendario = Calendar.getInstance();
	private Timer timer;
	private ActionListener l;
	private boolean apachadoTemporal=false,tempboo,estadoinicial=false;
	private int counter=0,hora=0, minutos=0, segundos=0,fila1,fila2,fila3,fila4,hora2=0, minutos2=0, segundos2=0,who=0;
	
	/**
	 *Crea el Panel con fondo del radio. Tambien se encarga de 
     *responder ante el tipo de objeto que se cree, pudiendo asi 
	 *mostrar en pantalla el formato actual del radio.
	 */
	
	public Panel_fondo(Image fondo){
		
			/** INICIALIZAR PROPIEDADES DE PANTALLA Y GUI  */
		this.fondo_panel = fondo;
		Dimension tamano = new Dimension(fondo_panel.getWidth(null),fondo_panel.getHeight(null));
		setPreferredSize(tamano);
		setLayout(null);
		car_radio = new Radio();
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
		/**
		 * Instanciacion del timer, quien muestra el reloj en pantalla
		 */
		l = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				horaContinue();
				setHourImages();
			}
			
		};
		timer = new Timer(1000,new innerClass());
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
			botoncitos[0] = new MyJlabel(new ImageIcon(".\\imagenes\\r2.png"),0);
			botoncitos[1] = new MyJlabel(new ImageIcon(".\\imagenes\\r3.png"),1);
			}
		else{
			botoncitos[0] = new MyJlabel(new ImageIcon("./imagenes/r2.png"),0);
			botoncitos[1] = new MyJlabel(new ImageIcon("./imagenes/r3.png"),1);
			}
		botoncitos[0].addMouseListener(this);
		add(botoncitos[0]);
		botoncitos[0].setBounds(570,489,116,58);
		botoncitos[1].addMouseListener(this);
		add(botoncitos[1]);
		botoncitos[1].setBounds(570+125,489,123,54);
		
		
		
		//Colocar Mis Botones en Lugares Especificos, sinningun LAYOUT
		/*mis_botoncitos[0].setBounds(125,125,83,75);
		mis_botoncitos[13].setBounds(125,360,54,119);
		mis_botoncitos[14].setBounds(605,360,79,75);
		mis_botoncitos[15].setBounds(330,365,88,62);
		mis_botoncitos[16].setBounds(418,365,88,62);
		//Establecer ToolTips para ayuda
		mis_botoncitos[0].setToolTipText("On / Off");
		mis_botoncitos[1].setToolTipText("Presiona 'Store', y luego El numero de Memoria");*/
		
		
	
	}
	
// ******************************************************************************************************
	private class innerClass implements ActionListener{	
		public void actionPerformed(ActionEvent event){
			horaContinue();
			setHourImages();
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
	public void setHourImages(){
		int[] tempo = new int[horario.length];   // los finalizadodes "s" y "l" significan superior  y low
		tempo[0]=this.hora/10;
		tempo[1]=this.hora-10*(tempo[0]);
		if(tempo[1]<0)
			tempo[1]=this.hora;
		tempo[2]=this.minutos/10;
		tempo[3]=this.minutos-10*(tempo[2]);
		if(tempo[3]<0)
			tempo[3]=this.minutos;
		tempo[4]=this.segundos/10;
		tempo[5]=this.segundos-10*(tempo[4]);
		if(tempo[5]<0)
			tempo[5]=this.segundos;
		//*************************Creo mis JLabels con su respectiva IMAGEN y NOMBRE ***************************
		asignacionImagen(horario, tempo);
	/*	for(int a=0;a< horario.length ;a++){
			System.out.println( " "+tempo[a]);
			if(System.getProperty("os.name").toLowerCase().startsWith("windows"))
				horario[a].setIcon(new ImageIcon(".\\imagenes\\"+tempo[a]+".png"));
			else
				horario[a].setIcon(new ImageIcon("./imagenes/"+tempo[a]+".png"));
			horario[a].repaint();
		}*/
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
			System.out.println( "valores de fila: "+tempo[a]);
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
	


	
	
	
	public void mouseEntered(MouseEvent event){
		MyJlabel temp=(MyJlabel)event.getSource();
		who=temp.getId();

		
	}
	//@Overriding
	//Metodo que reinicializa el componente a su estado
	//original media vez se salga del componente.
	
	public void mouseExited(MouseEvent event){
		who=5;
	}
	//Metodos Abstractos de la Interfaz
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	
	
	
	
	
	
	
	//Metodo @overriding                                       +++++++++++++++++              CLICKED MOUSE!!!!++++++++++++++++++++++++++
	//su funcion es cuando se ejecute click sobre un 
	//componente este pueda realizar la funcion del juego
	//en agrupar el caracter a jugar, a la torre de 
	//demas caracteres a manera de formar una alineacion.
	public void mouseClicked(MouseEvent event){
		System.out.println("boton:"+ who);
		switch(who){
			case 0:
					break;
			case 1:
					break;
			case 2:
					break;
			default:
					break;
		}
		
	}
	
	
}
