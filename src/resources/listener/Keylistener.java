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
  public synchronized void keyPressed( KeyEvent e ) {
    key = e.getKeyCode();
    keydown = true;
    keyPressed();
  }
  
  /**
   * Override function.
   * @param e 
   * @deprecated Use {@link keyReleased()} instead.
   */
  @Override
  public synchronized void keyReleased( KeyEvent e ) {
    key = e.getKeyCode();
    keydown = false;
    keyReleased();
  }
  
  /**
   * Override function.
   * @param e 
   * @deprecated Use {@link keyTyped()} instead.
   */
  @Override
  public void keyTyped( KeyEvent e ) {
    key = e.getKeyCode();
    keyTyped();
  }
  
  /**
   * Runs when a key is pressed.
   */
  public abstract void keyPressed();
  
  /**
   * Runs when a key is released.
   */
  public abstract void keyReleased();
  
  /**
   * Runs when a key is typed.
   */
  public abstract void keyTyped();
    
}