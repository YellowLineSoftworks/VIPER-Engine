package game;

import graphics.BufferedDevice;
import graphics.awt.BufferedFrame;
import graphics.swing.BufferedJFrame;
import graphics3D.Room3D;
import java.util.ArrayList;
import java.util.List;
import resources.GameObject;

//VIPER is currently 2482 lines of code

/**
 * The application time handler. Manages FPS and game ticks.
 * @author Jack
 * @version 1.5 Alpha
 */
public abstract class Clock implements Runnable{
    
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
    public BufferedDevice[] bufferedDevices;
    private boolean initialized = false, threadRunning = true, enabled3D = false;
    private Thread main;
    private List<Room3D> rooms = new ArrayList();
    
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
         main = new Thread(this);
         main.start();
    }
    
    /**
     * Pauses the application's runtime.
     */
    public void pause() {
        threadRunning = false;
    }
    
    /**
     * Resumes the application's runtime.
     */
    public void unpause() {
        main = new Thread(this);
        threadRunning = true;
        main.start();
    }
    
    /**
     * Adds a BufferedDevice to the display system.
     * @param device The device to add.
     */
    public void addBufferedDevice(BufferedDevice device) {
        BufferedDevice[] bufferedDevicesNew = new BufferedDevice[bufferedDevices.length + 1];
        int c = 0;
        for(BufferedDevice temp: bufferedDevices) {
            bufferedDevicesNew[c] = temp;
            c++;
        }
        bufferedDevicesNew[bufferedDevices.length] = device;
        bufferedDevices = bufferedDevicesNew;
    }
    
    /**
     * Removes a BufferedDevice from the display system.
     * @param device The device to remove.
     */
    public void removeBufferedDevice(BufferedDevice device) {
        BufferedDevice[] bufferedDevicesNew = new BufferedDevice[bufferedDevices.length - 1];
        int c = 0;
        for(BufferedDevice temp: bufferedDevices) {
            if (temp != device) {
                bufferedDevicesNew[c] = temp;
                c++;
            }
        }
        bufferedDevices = bufferedDevicesNew;
    }

    @Override
    public void run() {
        
        long lastLoopTime = System.nanoTime();
        long OPTIMAL_TIME = 0;
        if (fpsCap != 0) {OPTIMAL_TIME = 1000000000 / fpsCap;}
        long lastFpsTime = 0;
        int fps = 0;
        if (!initialized) {
           init();
           initialized = true;
        }
        do {
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
             for (int x = 0; x < GameObject.objects.size(); x++) {
                GameObject obj = GameObject.objects.get(x);
                if (obj.movementSpeed > 0){
                    obj.movementCounterX += Math.cos(obj.movementAngle * (Math.PI/180)) * obj.movementSpeed;
                    obj.movementCounterY += Math.sin(obj.movementAngle * (Math.PI/180)) * obj.movementSpeed;
                    obj.move(obj.location.x + (int)obj.movementCounterX, obj.location.y + (int)obj.movementCounterY);
                    obj.movementCounterX = obj.movementCounterX - (int)obj.movementCounterX;
                    obj.movementCounterY = obj.movementCounterY - (int)obj.movementCounterY;
                }
             }
             //Run the game tick
             tick();
             //Draw all 3D images inside of the buffereddevices, if necessary
             for (Room3D r: rooms) {
                 r.render();
             }
             // draw everything
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
        } while (threadRunning);
    
    }
    
    /**
     * Enables 3D rendering for the selected room.
     * @param room The room to enable 3D for.
     */
    public void enable3D(Room3D room) {
        
        rooms.add(room);
        enabled3D = true;
        
    }
    
}