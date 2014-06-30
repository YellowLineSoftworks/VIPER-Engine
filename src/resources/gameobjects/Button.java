package resources.gameobjects;

import graphics.BufferedDevice;
import java.awt.Image;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import resources.GameObject;

/**
 * The Button parent class.
 * @author Jack
 * @version 1.4 Alpha
 */
public class Button extends GameObject {
    
    /**
     * A list of all the buttons in the application.
     */
    public static List<Button> buttons = new ArrayList();
    /**
     * A list of all the currently depressed buttons in the application.
     */
    public static List<Button> pressedButtons = new ArrayList();
    private Method method;
    private Object referenceObject = this;
    private Object[] args = new Object[]{};
    private boolean pressed = false;
    
    /**
     * Creates a new Button.
     * @param x The x-coordinate of the top-left corner of the button.
     * @param y The y-coordinate of the top-left corner of the button.
     * @param method The method to run when the button is pressed.
     * @param device The BufferedDevice that contains the button.
     */
    public Button(int x, int y, Method method, BufferedDevice device) {
        super(x, y, new Image[0], device);
        this.method = method;
        buttons.add(this);
    }
    
    /**
     * Creates a new Button.
     * @param x The x-coordinate of the top-left corner of the button.
     * @param y The y-coordinate of the top-left corner of the button.
     * @param unclicked The image to display when the button is not being clicked.
     * @param clicked The image to display when the button is being clicked.
     * @param method The method to run when the button is pressed.
     * @param device The BufferedDevice that contains the button.
     */
    public Button(int x, int y, Image unclicked, Image clicked, Method method, BufferedDevice device) {
        super(x, y, new Image[]{unclicked, clicked}, device);
        draw();
        this.method = method;
        buttons.add(this);
    }
    
    /**
     * Creates a new Button.
     * @param x The x-coordinate of the top-left corner of the button.
     * @param y The y-coordinate of the top-left corner of the button.
     * @param unclicked The image to display when the button is not being clicked.
     * @param clicked The image to display when the button is being clicked.
     * @param method The method to run when the button is pressed.
     * @param object The object to call the method from.
     * @param args The method arguments.
     * @param device The BufferedDevice that contains the button.
     */
    public Button(int x, int y, Image unclicked, Image clicked, Method method, Object object, Object[] args, BufferedDevice device) {
        super(x, y, new Image[]{unclicked, clicked}, device);
        draw();
        this.method = method;
        this.referenceObject = object;
        this.args = args;
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
     * @param method The method to run when the button is pressed.
     * @param device The BufferedDevice that contains the button.
     */
    public Button(int x, int y, int endx, int endy, Image unclicked, Image clicked, Method method, BufferedDevice device) {
        super(x, y, endx, endy, new Image[]{unclicked, clicked}, device);
        draw();
        this.method = method;
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
     * @param method The method to run when the button is pressed.
     * @param object The object to call the method from.
     * @param args The method arguments.
     * @param device The BufferedDevice that contains the button.
     */
    public Button(int x, int y, int endx, int endy, Image unclicked, Image clicked, Method method, Object object, Object[] args, BufferedDevice device) {
        super(x, y, endx, endy, new Image[]{unclicked, clicked}, device);
        draw();
        this.method = method;
        this.referenceObject = object;
        this.args = args;
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
     * @param method The method to run when the button is pressed.
     * @param device The BufferedDevice that contains the button.
     */
    public Button(int x, int y, int endx, int endy, int srcx1, int srcy1, int srcx2, int srcy2, Image unclicked, Image clicked, Method method, BufferedDevice device) {
        super(x, y, endx, endy, srcx1, srcy1, srcx2, srcy2, new Image[]{unclicked, clicked}, device);
        draw();
        this.method = method;
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
     * @param method The method to run when the button is pressed.
     * @param object The object to call the method from.
     * @param args The method arguments.
     * @param device The BufferedDevice that contains the button.
     */
    public Button(int x, int y, int endx, int endy, int srcx1, int srcy1, int srcx2, int srcy2, Image unclicked, Image clicked, Method method, Object object, Object[] args, BufferedDevice device) {
        super(x, y, endx, endy, srcx1, srcy1, srcx2, srcy2, new Image[]{unclicked, clicked}, device);
        draw();
        this.method = method;
        this.referenceObject = object;
        this.args = args;
        buttons.add(this);
    }
    
    /**
     * Executes upon the button being pressed.
     */
    public void buttonPressed() {
        pressed = true;
        pressedButtons.add(this);
        changeDisplayedSprite(1);
    }
    
    /**
     * Resets the button to it's unpressed state without executing the {@link clicked()} function.
     */
    public void resetButtonState() {
        pressed = false;
        changeDisplayedSprite(0);
    }
    
    /**
     * Executes upon the button being released.
     */
    public void buttonReleased() {
        pressed = false;
        changeDisplayedSprite(0);
        try{method.invoke(referenceObject, args);} catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
            System.err.println("Error invoking method " + method.getName() + " in class " + method.getDeclaringClass().getSimpleName());
            System.err.println(e.getMessage()); 
        }
    }
    
    /**
     * Removes the button.
     */
    @Override
    public void destroy() {
        super.destroy();
        buttons.remove(this);
        if (pressedButtons.contains(this)) {
            pressedButtons.remove(this);
        }
    }
    
    /**
     * Destroys all buttons.
     */
    public static void clear() {
        for (int c = 0; c < buttons.size(); c++) {
            buttons.get(c).destroy();
        }
    }
}