package resources.listener;

/**
 * @version 1.8 Alpha
 * @author YellowLineSoftworks
 */

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import resources.gameobjects.Button;

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
        int x = evt.getX();
        int y = evt.getY();
        boolean found = false;
        for(Button temp: Button.buttons) {
            if (temp.currentSprite.x1 <= x && temp.currentSprite.x2 >= x && !found) {
                if (temp.currentSprite.y1 <= y && temp.currentSprite.y2 >= y) {
                    temp.buttonReleased();
                    found = true;
                }
            }
        }
        if (!found) {
            for (Button temp: Button.pressedButtons) {
                temp.resetButtonState();
            }
        }
        MouseReleased();
    }
        
    @Override
    public void mousePressed (MouseEvent evt) {
        button = evt.getButton(); 
        event = 4;
        int x = evt.getX();
        int y = evt.getY();
        for(Button temp: Button.buttons) {
            if (temp.currentSprite.x1 <= x && temp.currentSprite.x2 >= x) {
                if (temp.currentSprite.y1 <= y && temp.currentSprite.y2 >= y) {
                    temp.buttonPressed();
                }
            }
        }
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