
/**
 * @author Jose Pablo Castillo Rodas
 *@link http://code.google.com/p/proyectojosepablo1/}  label
 *100007
 *
 *@version 2.0
 *Descripcion:
 *	Este el el Driver de mi programa el cual genera la ventana principal
 *  y crea el panel de fondo donde esta el fondo de radio y sus botones. *
 *	La clase esta diseñada para que sin importar el Sistema Operativo este
 *	funcione bien, por lo que no hay problema entre Windows y macOSz
 *
 */

import javax.swing.*;

/**
 * 
 * Clase Driver. Esta es la encargada de lidear con el 
 * Sistema Operativo y con ello mostrar en pantalla un frame
 * donde se colocaran los atributos correspondientes. Este posee
 * de nombre "car Radio Con memoria", y creara el panel correpondiente
 * de fondo con su respectiva imagen. 
 * 
 * Como programacion defensiva posee Identificador de Sistemas Operativos
 */

public class Driver_josepablo {
	public static Panel_fondo panel;
	public static JFrame ventana = new JFrame("Sistema de un Banco");

	/**
	  *Esta es la calse principal, sin embargo esta clase solo se encarga de crear las ventanas  
	  *y asignarle nombre. Asimismo se encarga de identificar que sistema Operativo posee
	  *para poder responder ante la ubicacion de las carpetas y asi poder asignarle el 
	  *fondo corespondiente al panel.La clase Panel_fondo es la principal encargada de poseer
	  *el Objeto radio
	  **/	
	public static void main(String args[]) {

			if((System.getProperty("os.name").toLowerCase().startsWith("windows")))
				panel = new Panel_fondo(new ImageIcon(".\\imagenes\\fondo.PNG").getImage());
			else
				panel = new Panel_fondo(new ImageIcon("./imagenes/fondo.PNG").getImage());
		
		/**stablezco las propiedades de mi Ventana */
			ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ventana.add(panel);
			ventana.setResizable(false);
			ventana.pack();
			ventana.setVisible(true);	
	}
	

}