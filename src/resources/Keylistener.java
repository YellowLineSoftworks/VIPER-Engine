package resources;

/**
 * 
 * @author YellowLineSoftworks
 */

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public abstract class Keylistener implements KeyListener {
  
  public boolean keydown = false;
  public int key = -1;
  
  @Override
  public synchronized void keyPressed( KeyEvent e ) {
    key = e.getKeyCode();
    keydown = true;
    keyPressed();
  }
  
  @Override
  public synchronized void keyReleased( KeyEvent e ) {
    key = e.getKeyCode();
    keydown = false;
    keyReleased();
  }
  
  @Override
  public void keyTyped( KeyEvent e ) {
    key = e.getKeyCode();
    keyTyped();
  }
  
  public abstract void keyPressed();
  
  public abstract void keyReleased();
  
  public abstract void keyTyped();
    
}