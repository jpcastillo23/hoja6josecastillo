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



public class MyJlabel extends JLabel{
	private int id;

	//creacion Para Paneles Superiores
	//Sintaxis: FIGURA de reloj,  Numero:Tiempo, FIGURA de dinero, Numero: Puntos netos, FIGURA IZQUIERDA PROXIMA, FIGURA DERECHA PROXIMA, nivel de Transparencia
	public MyJlabel(ImageIcon relojito,int ids){
		super(relojito);
		this.id=ids;
	}
	public int getId(){
		return this.id;
	}
	



}


