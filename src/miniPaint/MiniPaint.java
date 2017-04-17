package miniPaint;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MiniPaint  extends JFrame{
	
	boolean pressionado = false;
	
	public MiniPaint(){
		
		
		
		//Tratamentos para o mouse (pressionado ou nao)
		addMouseListener (new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
					pressionado = false;
				
			}
			
			public void mousePressed(MouseEvent e) {
					
					pressionado = true;
					desenhos.clear();
			}
			public void mouseExited(MouseEvent e) {
				
			}
			public void mouseEntered(MouseEvent e) {
			
			}
			
			public void mouseClicked(MouseEvent e) {
			
			}
		});
		
		//Iniciando a Thread
		new Time().start();
		
		
		setSize(915,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}
	
	
	ArrayList<Desenho> desenhos = new ArrayList<>();
	
	//Objeto para armazenar as coordenadas do Mouse
	public class Desenho{
		int x, y;
		public Desenho(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public void paint(Graphics g){
		for (int i=1 ; i < desenhos.size(); i++){
			int x = desenhos.get(i).x; // acessa o ponto x do mouse
			int y = desenhos.get(i).y; // acessa o ponto y do mouse
			int x2 = desenhos.get(i-1).x; //acessa o ponto anterior 
			int y2 = desenhos.get(i-1).y;//acessa o ponto anterior 
			g.drawLine(x2,y2,x,y);
		}	
	}
	
	public  static void main (String [] args){
		new MiniPaint();
	}
	
	
	public class Time extends Thread{
		public void run(){
			
			while(true){
				if(pressionado == true){
					try{
						Point ponto = getMousePosition(); //Pega a coordenada do mouse 
						desenhos.add(new Desenho(ponto.x, ponto.y)); //Jogando as coordenadas do mouse em um arrayList
					}catch(Exception erro){};
				}
				repaint();
			}
		}
	}
	

}


