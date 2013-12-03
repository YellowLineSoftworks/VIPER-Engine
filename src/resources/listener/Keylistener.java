package resources.listener;

/**
 * A parent key listener class.
 * @author Jack
 * @version 1.4 Alpha
 */

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public abstract class Keylistener implements KeyListener {
  
  /**
   * Describes whether or not a key is currently depressed.
   */
  public boolean keydown = false;
  /**
   * The KeyCode of the last key pressed.
   */
  public int key = -1;
  
  /**
   * Override function.
   * @param e 
   * @deprecated Use {@link keyPressed()} instead.
   */
  @Override
  public void keyPressed( KeyEvent e ) {
    key = e.getKeyCode();
    keydown = true;
    KeyPressed(e);
  }
  
  /**
   * Override function.
   * @param e 
   * @deprecated Use {@link keyReleased()} instead.
   */
  @Override
  public void keyReleased( KeyEvent e ) {
    key = e.getKeyCode();
    keydown = false;
    KeyReleased(e);
  }
  
  /**
   * Override function.
   * @param e 
   * @deprecated Use {@link keyTyped()} instead.
   */
  @Override
  public void keyTyped( KeyEvent e ) {
    key = e.getKeyCode();
    KeyTyped(e);
  }
  
  /**
   * Runs when a key is pressed.
   * @param e The KeyEvent that is passed in.
   */
  public abstract void KeyPressed(KeyEvent e);
  
  /**
   * Runs when a key is released.
   * @param e The KeyEvent that is passed in.
   */
  public abstract void KeyReleased(KeyEvent e);
  
  /**
   * Runs when a key is typed.
   * @param e The KeyEvent that is passed in.
   */
  public abstract void KeyTyped(KeyEvent e);
    
}