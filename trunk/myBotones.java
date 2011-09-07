/**
 * 
 *package mi_radio_josepablo;
 *
 *
 * @author Jose Pablo Castillo Rodas
 *Descripcion:
 * 	A causa de que el progrma utilizaria al menos 5 botones, me vi *
 *	en la obligacion de crear una clase que manipulara mis botoncitos :D
 *	Este le da a cada boton su respectiva imagen y un debido Identificador
 */

import javax.swing.*;

/**
 * Clase cuya funcion es establecer los parametros de 
 * todo boton en el JPane, o panel. 
 * 
 */
public class myBotones extends JButton{
	

	private static final long serialVersionUID = 1L;
	/*Atributo
	 *encargado de proporcionar un Numero
	 *a cada boton creado 
	 */
	private int identificador;
	/*
	 * Clase Constructora: Encargada de dar a cada
	 * boton su respectiva imagen y un numero correspondiente
	 * individual para identificarlo y asi brindarle una
	 * funcion especifica.
	 */
	public myBotones(ImageIcon mi_imagen,int id){
		super(mi_imagen);
		this.identificador=id;
	}
	/*
	 * Clase Encargada de retornar el identificador, osea
	 * el numero que identifica al boton. (se puede
	 * hacer con un getSource().
	 */
	public int getId(){
		return this.identificador;
	}
}