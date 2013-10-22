package game;

import graphics.BufferedFrame;

/**
 * @author Xenith
 */
public abstract class clock {
    
    public boolean gameRunning = true;
    public int fpsCap = 60;
    public int fps = 0;
    
    public abstract void init();
    
    public abstract void tick();
    
    public void start(int fpsCap) {
         this.fpsCap = fpsCap;
         gameRunning = true;
         long lastLoopTime = System.nanoTime();
         final long OPTIMAL_TIME = 1000000000 / fpsCap; 
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
                System.out.println("FPS: "+fps+"");
                this.fps = fps;
                lastFpsTime = 0;
                fps = 0;
             }
             //Run the game tick
             tick();
             // draw everyting
             BufferedFrame.frame.render();
             long sleepTime = OPTIMAL_TIME - (System.nanoTime() - lastLoopTime);
             if (sleepTime < 999999 && sleepTime <= 0) {
                 try{Thread.sleep(5, (int)sleepTime);}catch(Exception e){System.out.println(e.getMessage());};
             } else {
                 try{Thread.sleep(sleepTime/970000);}catch(Exception e){System.out.println(e.getMessage());};
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
