package resources.listener;

/**
 * A parent mouse listener class.
 * @author Jack
 * @version 1.4 Alpha
 */

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import resources.ResolutionCalculator;
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
    private ResolutionCalculator calc = null;
    
    /**
     * Override function.
     * @param evt 
     * @deprecated Use {@link mouseExited()} instead.
     */
    @Override
    public void mouseExited (MouseEvent evt) {
        button = evt.getButton();
        event = 1;
        MouseExited(evt);
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
        MouseEntered(evt);
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
        if(calc != null) {
            x = calc.reverseCalcForX(x);
            y = calc.reverseCalcForY(y);
        }
        boolean found = false;
        for(int c = 0; c < Button.buttons.size(); c++) {
            Button temp = Button.buttons.get(c);
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
        MouseReleased(evt);
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
        if(calc != null) {
            x = calc.reverseCalcForX(x);
            y = calc.reverseCalcForY(y);
        }
        for(Button temp: Button.buttons) {
            if (temp.currentSprite.x1 <= x && temp.currentSprite.x2 >= x) {
                if (temp.currentSprite.y1 <= y && temp.currentSprite.y2 >= y) {
                    temp.buttonPressed();
                }
            }
        }
        MousePressed(evt);
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
        MouseClicked(evt);
    }
    
    /**
     * Runs when the mouse exits the component.
     * @param MouseEvent The MouseEvent that occurred to trigger the function call.
     */
    public abstract void MouseExited(MouseEvent evt);
    
    /**
     * Runs when the mouse enters the component.
     * @param MouseEvent The MouseEvent that occurred to trigger the function call.
     */
    public abstract void MouseEntered(MouseEvent evt);
    
    /**
     * Runs when a mouse button is released.
     * @param MouseEvent The MouseEvent that occurred to trigger the function call.
     */
    public abstract void MouseReleased(MouseEvent evt);
    
    /**
     * Runs when a mouse button is clicked.
     * @param MouseEvent The MouseEvent that occurred to trigger the function call.
     */
    public abstract void MouseClicked(MouseEvent evt);
    
    /**
     * Runs when a mouse button is pressed.
     * @param MouseEvent The MouseEvent that occurred to trigger the function call.
     */
    public abstract void MousePressed(MouseEvent evt);
    
    /**
     * Causes the MouseListener to use a ResolutionCalculator to adjust mouse locations. Note: The x and y coordinates in the child class'
     * handling functions will not be affected by the ResolutionCalculator, and will have to adjust any X and Y coordinates accordingly.
     */
    public void addResolutionCalculator(ResolutionCalculator calc) {
        this.calc = calc;
    }
}