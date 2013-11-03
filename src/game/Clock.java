package game;

import graphics.BufferedDevice;
import graphics.awt.BufferedFrame;
import graphics.swing.BufferedJFrame;

/**
 * @author Xenith
 * Currently 911 lines of code
 */
public abstract class Clock {
    
    public boolean gameRunning = true;
    public int fpsCap = 60;
    public int fps = 0;
    private BufferedDevice[] bufferedDevices;
    
    public abstract void init();
    
    public abstract void tick();
    
    public void start(int fpsCap, BufferedDevice[] frames) {
         bufferedDevices = frames;
         start(fpsCap);
    }
    
    public void start(int fpsCap, BufferedFrame frame) {
         bufferedDevices = new BufferedDevice[]{frame};
         start(fpsCap);
    }
    
    public void start(int fpsCap, BufferedJFrame frame) {
         bufferedDevices = new BufferedDevice[]{frame};
         start(fpsCap);
    }
    
    public void start(int fpsCap) {
         if (bufferedDevices == null) {
             bufferedDevices = new BufferedDevice[]{new BufferedFrame(0,0, 500, 500, "")};
         }
         this.fpsCap = fpsCap;
         gameRunning = true;
         long lastLoopTime = System.nanoTime();
         long OPTIMAL_TIME = 0;
         if (fpsCap != 0) {OPTIMAL_TIME = 1000000000 / fpsCap;}
         long lastFpsTime = 0;
         int fps = 0;
         init();
         while (gameRunning)
         {
             //Check the current time, save it
             long now = System.nanoTime();
             long updateLength = now - lastLoopTime;
             lastLoopTime = now;
             lastFpsTime += updateLength;
             fps++;
             //If a second has passed since the last update, update the fps and reset the counters
             if (lastFpsTime >= 1000000000)
             {
                this.fps = fps;
                lastFpsTime = 0;
                fps = 0;
             }
             //Run the game tick
             tick();
             // draw everyting
             for (int c = 0; c < bufferedDevices.length; c++) {
                 bufferedDevices[c].render();
             }
             long sleepTime = OPTIMAL_TIME - (System.nanoTime() - lastLoopTime);
             if (fpsCap != 0) {
             //Sleep until the next tick is scheduled to start
             if (sleepTime < 999999 && sleepTime >= 0) {
                 try{Thread.sleep(5, (int)sleepTime);}catch(Exception e){System.out.println(e.getMessage());};
             } else if (sleepTime >= 0) {
                 try{Thread.sleep(sleepTime/970000);}catch(Exception e){System.out.println(e.getMessage());};
             }
             }
        }
    }
    
    public void pause() {
        gameRunning = false;
    }
    
    public void unpause() {
        start(fpsCap);
    }
    
}
