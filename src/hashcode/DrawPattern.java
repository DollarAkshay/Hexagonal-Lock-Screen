/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Akshay L Aradhya
 */


public class DrawPattern extends JPanel{
    
    final double sin60 = Math.sin(Math.toRadians(60));
    
    public float hex_x[] = new float[1000];
    public float hex_y[] = new float[1000];
    public boolean hex_selected[] = new boolean[1000];
    public int n_hex;
    public int hex_side = 40,levels = 4;
    public boolean check=false;
    public Color theme = new Color(255,204,0);;
    public Graphics2D g2d;
    public boolean border = true;
    float size_factor = 1;
    
    Color randomColor(int brightness){
        
        Random r = new Random();
        int red   = r.nextInt(256-brightness) + brightness;
        int green = r.nextInt(256-brightness) + brightness;
        int blue  = r.nextInt(256-brightness) + brightness;
        return new Color(red,green,blue);
    }
    
    void fillHexagon(Graphics2D g2d,int x,int y,int s){
        
        Hexagon h;
        
        if(hex_selected[n_hex]==true){
            if(check)
                g2d.setColor(theme);
                
            else 
                g2d.setColor(new Color(155,155,155));
            h = new Hexagon(x,y-s/4,s);
        }
        else{
            h = new Hexagon(x,y,s);
            g2d.setColor(new Color(75,75,75,150));
        }
        
        hex_x[n_hex]= x + s;
        hex_y[n_hex++]= y;
        
        g2d.fillPolygon(h.px, h.py,6);
    }
    
    void drawHexagon(Graphics2D g2d,int x,int y,int s){
        
        Hexagon h;
        
        if(hex_selected[n_hex-1]==true){
            h = new Hexagon(x,y-s/4,s);
        }
        else{
            h = new Hexagon(x,y,s);
        }
        
        g2d.setColor(new Color(0,0,0));
        g2d.setStroke(new BasicStroke(s/11.0f));
        g2d.drawPolygon(h.px, h.py,6);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        System.out.println("Repaint Called");
        
        n_hex=0;
        g2d = (Graphics2D)g;
        g2d.setColor(new Color(0,0,0,100));
        g2d.fillRect(0, 0, 450, 750);
        int xoffset = (450 - (3*levels-1)*hex_side)/2 ;
        int yoffset = (int)((750 - (2*levels-1)*sin60*hex_side*2)/2);
        if(border==false)
            size_factor=0.9f;
        else
            size_factor=1;
        
        for (int i = 1; i <=levels; i++){ 
            for (int j = 0; j <i; j++) {
                fillHexagon(g2d, (int) (xoffset + (levels-i)*1.5*hex_side + j*3*hex_side ), (int) (yoffset + i*sin60*hex_side), (int) (hex_side*size_factor));
                if(border)
                    drawHexagon(g2d, (int) (xoffset + (levels-i)*1.5*hex_side + j*3*hex_side), (int) (yoffset + i*sin60*hex_side), hex_side);
            }
        }
        
        for (int i = 0; i <2*levels-3; i++){ 
            for (int j = 0; j <levels - ((i+1)%2); j++) {
                fillHexagon(g2d, (int) (xoffset + j*3*hex_side + (i+1)%2*hex_side*1.5), (int) (yoffset + (i+levels+1)*sin60*hex_side), (int) (hex_side*size_factor));
                if(border)
                    drawHexagon(g2d, (int) (xoffset + j*3*hex_side + (i+1)%2*hex_side*1.5), (int) (yoffset + (i+levels+1)*sin60*hex_side), hex_side);
            }
        }
        
        for (int i = levels-1; i >=0; i--){ 
            for (int j = 0; j <=i; j++) {
                fillHexagon(g2d, (int) (xoffset + (levels-i-1)*1.5*hex_side + j*3*hex_side), (int) (yoffset + (levels-i+levels*3-3)*sin60*hex_side), (int) (hex_side*size_factor));
                if(border)
                    drawHexagon(g2d, (int) (xoffset + (levels-i-1)*1.5*hex_side + j*3*hex_side), (int) (yoffset + (levels-i+levels*3-3)*sin60*hex_side), hex_side);
            }
        }
            
        
    }
    
}
