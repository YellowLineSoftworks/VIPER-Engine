package resources.listener;

/**
 * A parent mouse listener class.
 * @author Jack
 * @version 1.4 Alpha
 */

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import resources.gameobjects.Button;

public abstract class Mouselistener implements MouseListener {
    
    /**
     * The last mouse button pressed.
     */
    public int button = -1;
    /**
     * The ID of the last action the mouse performed. 
     * (1 = Exited, 2 = Entered, 3 = Released, 4 = Pressed, 5 = Clicked)
     */
    public int event = 0;
    
    /**
     * Override function.
     * @param evt 
     * @deprecated Use {@link mouseExited()} instead.
     */
    @Override
    public void mouseExited (MouseEvent evt) {
        button = evt.getButton();
        event = 1;
        MouseExited();
    }
        
    /**
     * Override function.
     * @param evt 
     * @deprecated Use {@link mouseEntered()} instead.
     */
    @Override
    public void mouseEntered (MouseEvent evt) {
        button = evt.getButton();
        event = 2;
        MouseEntered();
    }
        
    /**
     * Override function.
     * @param evt 
     * @deprecated Use {@link mouseReleased()} instead.
     */
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
        
    /**
     * Override function.
     * @param evt 
     * @deprecated Use {@link mousePressed()} instead.
     */
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
        
    /**
     * Override function.
     * @param evt 
     * @deprecated Use {@link mouseClicked()} instead.
     */
    @Override
    public void mouseClicked (MouseEvent evt) {
        button = evt.getButton();
        event = 5;
        MouseClicked();
    }
    
    /**
     * Runs when the mouse exits the component.
     */
    public abstract void MouseExited();
    
    /**
     * Runs when the mouse enters the component.
     */
    public abstract void MouseEntered();
    
    /**
     * Runs when a mouse button is released.
     */
    public abstract void MouseReleased();
    
    /**
     * Runs when a mouse button is clicked.
     */
    public abstract void MouseClicked();
    
    /**
     * Runs when a mouse button is pressed.
     */
    public abstract void MousePressed();
}