package resources.gameobjects;

import graphics.BufferedDevice;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import resources.GameObject;

/**
 * The Button parent class.
 * @author Jack
 * @version 1.3 Alpha
 */
public abstract class Button extends GameObject {
    
    /**
     * A list of all the buttons in the application.
     */
    public static List<Button> buttons = new ArrayList();
    /**
     * A list of all the currently depressed buttons in the application.
     */
    public static List<Button> pressedButtons = new ArrayList();
    /**
     * The type of the button. This corresponds to the function that 
     * pressing the button runs.
     */
    public String buttonType;
    private boolean pressed = false;
    
    /**
     * Creates a new Button.
     * @param x The x-coordinate of the top-left corner of the button.
     * @param y The y-coordinate of the top-left corner of the button.
     * @param unclicked The image to display when the button is not being clicked.
     * @param clicked The image to display when the button is being clicked.
     * @param buttonType The button type. Corresponds to the function that pressing
     * the button runs.
     * @param device The BufferedDevice that contains the button.
     */
    public Button(int x, int y, Image unclicked, Image clicked, String buttonType, BufferedDevice device) {
        super(x, y, new Image[]{unclicked, clicked}, device);
        this.buttonType = buttonType;
        buttons.add(this);
    }
    
    /**
     * Creates a new Button.
     * @param x The x-coordinate of the top-left corner of the button.
     * @param y The y-coordinate of the top-left corner of the button.
     * @param endx The x-coordinate of the bottom-right corner of the button.
     * @param endy The y-coordinate of the bottom-right corner of the button.
     * @param unclicked The image to display when the button is not being clicked.
     * @param clicked The image to display when the button is being clicked.
     * @param buttonType The button type. Corresponds to the function that pressing
     * the button runs.
     * @param device The BufferedDevice that contains the button.
     */
    public Button(int x, int y, int endx, int endy, Image unclicked, Image clicked, String buttonType, BufferedDevice device) {
        super(x, y, endx, endy, new Image[]{unclicked, clicked}, device);
        this.buttonType = buttonType;
        buttons.add(this);
    }
    
    /**
     * Creates a new Button.
     * @param x The x-coordinate of the top-left corner of the button.
     * @param y The y-coordinate of the top-left corner of the button.
     * @param endx The x-coordinate of the bottom-right corner of the button.
     * @param endy The y-coordinate of the bottom-right corner of the button.
     * @param srcx1 The x-coordinate of the top-left corner of the source button.
     * @param srcy1 The y-coordinate of the top-left corner of the source button.
     * @param srcx2 The x-coordinate of the bottom-right corner of the source button.
     * @param srcy2 The y-coordinate of the bottom-right corner of the source button.
     * @param unclicked The image to display when the button is not being clicked.
     * @param clicked The image to display when the button is being clicked.
     * @param buttonType The button type. Corresponds to the function that pressing
     * the button runs.
     * @param device The BufferedDevice that contains the button.
     */
    public Button(int x, int y, int endx, int endy, int srcx1, int srcy1, int srcx2, int srcy2, Image unclicked, Image clicked, String buttonType, BufferedDevice device) {
        super(x, y, endx, endy, srcx1, srcy1, srcx2, srcy2, new Image[]{unclicked, clicked}, device);
        this.buttonType = buttonType;
        buttons.add(this);
    }
    
    /**
     * Executes upon the button being pressed.
     */
    public void buttonPressed() {
        pressed = true;
        pressedButtons.add(this);
        changeSprite(1);
    }
    
    /**
     * Resets the button to it's unpressed state without executing the {@link clicked()} function.
     */
    public void resetButtonState() {
        pressed = false;
        changeSprite(0);
    }
    
    /**
     * Executes upon the button being released.
     */
    public void buttonReleased() {
        pressed = false;
        changeSprite(0);
        clicked(buttonType);
    }
    
    /**
     * Executes upon the button being clicked.
     * @param buttonType The type of the button that was clicked.
     */
    public abstract void clicked(String buttonType);
    
}