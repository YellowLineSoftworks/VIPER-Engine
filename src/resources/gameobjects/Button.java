package resources.gameobjects;

import java.util.ArrayList;
import java.util.List;
import resources.GameObject;
import resources.Sprite;

/**
 * @author Xenith
 */
public abstract class Button extends GameObject {
    
    public static List<Button> buttons = new ArrayList();  
    public static List<Button> pressedButtons = new ArrayList();
    public int buttonType;
    private boolean pressed = false;
    
    public Button(int x, int y, Sprite unclicked, Sprite clicked, int buttonType) {
        super(x, y,new Sprite[]{unclicked, clicked});
        this.buttonType = buttonType;
        buttons.add(this);
    }
    
    public void buttonPressed() {
        pressed = true;
        pressedButtons.add(this);
        changeSprite(1);
    }
    
    public void resetButtonState() {
        pressed = false;
        changeSprite(0);
    }
    
    public void buttonReleased() {
        pressed = false;
        changeSprite(0);
        clicked(buttonType);
    }
    
    public abstract void clicked(int buttonType);
    
}