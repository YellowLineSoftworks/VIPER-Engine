package resources;

/**
 * @version 1.8 Alpha
 * @author YellowLineSoftworks
 */

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public abstract class Mouselistener implements MouseListener {
    
    //Button holds the last mouse button pressed. Event holds the last event the mouse engendered.
    public int button = -1;
    public int event = 0;
    
    @Override
    public void mouseExited (MouseEvent evt) {
        button = evt.getButton();
        event = 1;
        MouseExited();
    }
        
    @Override
    public void mouseEntered (MouseEvent evt) {
        button = evt.getButton();
        event = 2;
        MouseEntered();
    }
        
    @Override
    public void mouseReleased (MouseEvent evt) {
        button = evt.getButton(); 
        event = 3;
        MouseReleased();
    }
        
    @Override
    public void mousePressed (MouseEvent evt) {
        button = evt.getButton(); 
        event = 4;
        MousePressed();
    }
        
    @Override
    public void mouseClicked (MouseEvent evt) {
        button = evt.getButton();
        event = 5;
        MouseClicked();
    }
    
    public abstract void MouseExited();
    
    public abstract void MouseEntered();
    
    public abstract void MouseReleased();
    
    public abstract void MouseClicked();
    
    public abstract void MousePressed();
}