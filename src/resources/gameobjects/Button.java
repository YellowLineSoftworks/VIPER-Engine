package resources.gameobjects;

import java.awt.Image;
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
    public String buttonType;
    private boolean pressed = false;
    
    public Button(int x, int y, Image unclicked, Image clicked, String buttonType) {
        super(x, y, new Sprite[]{new Sprite(unclicked, x, y), new Sprite(clicked, x, y)});
        this.buttonType = buttonType;
        buttons.add(this);
    }
    
    public Button(int x, int y, int endx, int endy, Image unclicked, Image clicked, String buttonType) {
        super(x, y, new Sprite[]{new Sprite(unclicked, x, y, endx, endy), new Sprite(clicked, x, y, endx, endy)});
        this.buttonType = buttonType;
        buttons.add(this);
    }
    
    public Button(int x, int y, int endx, int endy, int srx1, int sry1, int srx2, int sry2, Image unclicked, Image clicked, String buttonType) {
        super(x, y, new Sprite[]{new Sprite(unclicked, x, y, endx, endy, srx1, sry1, srx2, sry2), new Sprite(clicked, x, y, endx, endy, srx1, sry1, srx2, sry2)});
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
    
    public abstract void clicked(String buttonType);
    
}