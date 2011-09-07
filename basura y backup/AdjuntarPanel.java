import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.io.*;



public class AdjuntarPanel extends JPanelRound {

	private float tran= 0.0f;
	protected JLabel []mis_imagenes;
	GridLayout forma_uno;
	private int numero,tiempo;
	private Timer control_tiempo;
	private int x_uno=0, y_uno=0,x_dos=602, y_dos=0; //private int x_uno=24, y_uno=25,x_dos=626, y_dos=25;
	private int cantidad_movimientouno, cantidad_movimientodos;
	private final int HTZVISUALES=20;
	private final int CANTIDADDEMOVIMIENTO=3;
	//creacion Para Paneles Superiores
	//Sintaxis: FIGURA de reloj,  Numero:Tiempo, FIGURA de dinero, Numero: Puntos netos, FIGURA IZQUIERDA PROXIMA, FIGURA DERECHA PROXIMA, nivel de Transparencia
	public AdjuntarPanel(ImageIcon relojito,int tiempo,ImageIcon monedas,int punteo, ImageIcon figura_uno, ImageIcon figura_dos,float transa_man){
		forma_uno=new GridLayout(0,6);
		this.setLayout(forma_uno);
		this.tran=transa_man;
		mis_imagenes= new JLabel[6];
		mis_imagenes[0]=new JLabel(relojito);
		mis_imagenes[1]=new JLabel("Tiempo: "+tiempo);
		mis_imagenes[2]=new JLabel(monedas);
		mis_imagenes[3]=new JLabel(""+punteo+" Diamentes");
		mis_imagenes[4]=new JLabel(figura_uno);
		mis_imagenes[5]=new JLabel(figura_dos);
		this.setPreferredSize(new Dimension(650,50));
		addComponentsS();
	}
	//Figuritas de los lados
	public void rePintarNextImages(ImageIcon figura_uno, ImageIcon figura_dos){
		mis_imagenes[4].setIcon(figura_uno);
		mis_imagenes[5].setIcon(figura_dos);
		repaint();
		
	}
	//Repinta el timer
	public void rePintarTimer(int valor){
		this.tiempo=valor;
		mis_imagenes[1].setText("Tiempo: "+ tiempo+" ");
		mis_imagenes[1].repaint();
	}
	//repinta el puntaje del encabezado
	public void rePintarPunteo(int punteo){
		this.mis_imagenes[3].setText(""+punteo+" Diamentes");
		repaint();
	}
	//***********************************************************************************************
	//+++********++++****++*******+++***++++********++++********++++****++*******+++***++++********+++
	//***********************************************************************************************
	
	
	//creacionn de Paneles de Juego para eliminacion
	//Sintaxis: Identidifacor de posicion del panel, cantidad de Paneles semejantes a crear
	public AdjuntarPanel(int id, int cantidad){
		this.setLayout(null);
		mis_imagenes= new JLabel[cantidad];
		this.numero=id;
		this.setPreferredSize(new Dimension(676,50));
	}
	public void setAdjuntarImagenes(int posicion,ImageIcon icono){
		this.mis_imagenes[posicion]= new JLabel(icono);
		//add(mis_imagenes[posicion]);
	}
	public void setModificarIconos(int posicion,ImageIcon icono){
		this.mis_imagenes[posicion].setIcon(icono);
		this.mis_imagenes[posicion].repaint();
		
	}
	public void addComponentsS(){
		int enincremento=0;
		for(int b=0;b<mis_imagenes.length;b++){
			add(mis_imagenes[b]);
			mis_imagenes[b].setBounds(enincremento,0,48,48);//Sintaxis: posicion inicialx,y,
			enincremento+=48;
		}
		repaint();
	}

	public void addComponentsEx(ImageIcon izquierdo, ImageIcon derecho){
		mis_imagenes[0].setIcon(izquierdo);
		mis_imagenes[mis_imagenes.length-1].setIcon(derecho);
		repaint();
	}
	public void animacionMover(int cantidad){
		this.cantidad_movimientouno=cantidad;
		control_tiempo.start();
		
	}

	//@Override de paintComponent para poder ponerlo como transparente interpolando pixeles
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		AlphaComposite old = (AlphaComposite) g2.getComposite();
		g2.setComposite(AlphaComposite.SrcOver.derive(getTran()));
		super.paintComponent(g);
		g2.setComposite(old);

	}
	public float getTran(){
		return tran;
	}
	public void setTran(float invisible){
		this.tran=invisible;
	}
	public int getNum(){//regreasa el IDENTIFICADOR del panel
		return numero;
	}
	
	public class GifinMotion implements ActionListener{
		public  void actionPerformed (ActionEvent event){
			for( int a=0;a<cantidad_movimientouno;a++){ //cantidad _movimiento undica cuantas veces se va a mover en 
				//su linea recta.
				for(int b=0;b<16;b++){
					x_uno+=CANTIDADDEMOVIMIENTO;
					x_dos-=CANTIDADDEMOVIMIENTO;
					mis_imagenes[0].setLocation(x_uno,y_uno);
					mis_imagenes[12].setLocation(x_dos,y_dos);
				}
			}
			repaint();
		}
	}



}











































