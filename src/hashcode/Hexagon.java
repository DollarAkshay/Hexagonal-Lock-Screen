/*
 * To change this license header, choose License Headers in Project Prop[erties].
 * To change this temp[late file, choose Tools | Temp[lates
 * and op[en the temp[late in the editor].
 */
package hashcode;

import java.awt.Point;

/**
 *
 * @author Akshay L Aradhya
 */
public class Hexagon {
    
    
    public int px[] = new int[6];
    public int py[] = new int[6];
    public Point p[] = new Point[6]; // Going Clock-wise
    final double sin60 = Math.sin(Math.toRadians(60));
    
    Hexagon(int x,int y,int s){
        
        px[0] = x;
        py[0] = y;
        
        px[1] = x + s/2;
        py[1] = (int) (y + sin60*s);
        
        px[2] = x + 3*s/2;
        py[2] = (int) (y + sin60*s);
        
        px[3] = x + 2*s;
        py[3] = y;
        
        px[4] = x + 3*s/2;
        py[4] = (int) (y - sin60*s);
        
        px[5] = x + s/2;
        py[5] = (int) (y - sin60*s);
        
    }
    
    
    
}
