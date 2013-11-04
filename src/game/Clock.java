package game;

import graphics.BufferedDevice;
import graphics.awt.BufferedFrame;
import graphics.swing.BufferedJFrame;

//VIPER is currently 2338 lines of code

/**
 * The application time handler. Manages FPS and game ticks.
 * @author Jack
 * @version 1.3 Alpha
 */
public abstract class Clock {
    
    /**
     * Describes the application's runtime state
     */
    public boolean applicationRunning = true;
    /**
     * The application's current FPS cap.
     */
    public int fpsCap = 60;
    /**
     * The FPS the application is achieving.
     */
    public int fps = 0;
    /**
     * The BufferedDevices being used.
     */
    private BufferedDevice[] bufferedDevices;
    
    /**
     * This function runs when you start the application. Put your
     * initialization code in the override for this function in
     * the child class.
     */
    public abstract void init();
    
    /**
     * This function executes every time a frame is generated. Put
     * your continuous game code in the override for this function
     * in the child class.
     */
    public abstract void tick();
    
    /**
     * Starts the application.
     * @param fpsCap The application's FPS generation cap.
     * @param frames The BufferedDevices that you are using 
     * (BufferedJPanels, BufferedJFrames, etc.)
     */
    public void start(int fpsCap, BufferedDevice[] frames) {
         bufferedDevices = frames;
         start(fpsCap);
    }
    
    /**
     * Starts the application with a custom BufferedFrame.
     * @param fpsCap The application's FPS generation cap.
     * @param frame The custom BufferedFrame you are using.
     */
    public void start(int fpsCap, BufferedFrame frame) {
         bufferedDevices = new BufferedDevice[]{frame};
         start(fpsCap);
    }
    
    /**
     * Starts the application with a custom BufferedJFrame.
     * @param fpsCap The application's FPS generation cap.
     * @param frame The custom BufferedJFrame you are using.
     */
    public void start(int fpsCap, BufferedJFrame frame) {
         bufferedDevices = new BufferedDevice[]{frame};
         start(fpsCap);
    }
    
    /**
     * Starts the game with an automatically generated 
     * BufferedJFrame.
     * @param fpsCap The application's FPS generation cap.
     */
    public void start(int fpsCap) {
         if (bufferedDevices == null) {
             bufferedDevices = new BufferedDevice[]{new BufferedJFrame(0,0, 750, 750, "VIPER 1.3 Alpha")};
         }
         this.fpsCap = fpsCap;
         applicationRunning = true;
         long lastLoopTime = System.nanoTime();
         long OPTIMAL_TIME = 0;
         if (fpsCap != 0) {OPTIMAL_TIME = 1000000000 / fpsCap;}
         long lastFpsTime = 0;
         int fps = 0;
         init();
         while (applicationRunning)
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
                 try{Thread.sleep(5, (int)sleepTime);}catch(InterruptedException e){System.out.println(e.getMessage());};
             } else if (sleepTime >= 0) {
                 try{Thread.sleep(sleepTime/970000);}catch(InterruptedException e){System.out.println(e.getMessage());};
             }
             }
        }
    }
    
    /**
     * Pauses the application's runtime.
     */
    public void pause() {
        applicationRunning = false;
    }
    
    /**
     * Resumes the application's runtime.
     */
    public void unpause() {
        start(fpsCap);
    }
    
}
