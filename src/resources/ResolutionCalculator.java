package resources;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * A ResolutionCalculator, given a source resolution, can calculate dimensions for any target resolution.
 * @author Jack
 * @version 1.5 Alpha
 */
public class ResolutionCalculator {
    
    private int width, height, resWidth, resHeight;
    private int xshift = 0, yshift = 0;
    private float Xproportion, Yproportion;
    
    /**
     * Creates a new ResolutionCalculator.
     * @param width The width of the target resolution.
     * @param height The height of the target resolution.
     */
    public ResolutionCalculator(int width, int height) {
        this.width = width;
        this.height = height;
        GraphicsConfiguration config = new Frame().getGraphicsConfiguration();
        resWidth = config.getBounds().width;
        resHeight = config.getBounds().height;
        Xproportion = resWidth/(float)width;
        Yproportion = resHeight/(float)height;
        float aspectRatio = (float)width/(float)height;
        if (Xproportion > Yproportion) {
            int adjWidth = (int)(aspectRatio*resHeight);
            yshift = Math.abs((width - adjWidth)/2);
            Yproportion = resHeight/((float)height + 2*yshift);
        } else if (Xproportion < Yproportion) {
            int adjHeight = (int)(aspectRatio*resWidth);
            xshift = Math.abs((height - adjHeight)/2);
            Xproportion = resWidth/((float)width + 2*xshift);
        }
    }
    
    /**
     * Converts the entered x-value from the target resolution to the screen resolution.
     * @param x The x-value to convert.
     * @return The converted x-value.
     */
    public int calcForX(int x) {
        return (int)(Xproportion * (float)x) + xshift;
    }
    
    /**
     * Converts the entered y-value from the target resolution to the screen resolution.
     * @param y The y-value to convert.
     * @return The converted y-value.
     */
    public int calcForY(int y) {
        return (int)(Yproportion * (float)y) + yshift;
    }
    
    /**
     * Converts the entered point from the target resolution to the screen resolution.
     * @param p The point to convert.
     * @return The converted point.
     */
    public Point calcForPoint(Point p) {
        return new Point((int)(Xproportion * (float)p.x) + xshift,(int)(Yproportion * (float)p.y) + yshift);
    }
    
    /**
     * Converts the entered points from the target resolution to the screen resolution.
     * @param p The top or leftmost point of the rectangle.
     * @param p2 The lower or rightmost point of the rectangle.
     * @return The converted rectangle.
     */
    public Rectangle calcForRectangle(Point p, Point p2) {
        Point temp1 = new Point((int)(Xproportion * (float)p.x),(int)(Yproportion * (float)p.y));
        Point temp2 = new Point((int)(Xproportion * (float)p2.x),(int)(Yproportion * (float)p2.y));
        p.x += xshift;
        p.y += yshift;
        p2.x += xshift;
        p2.y += yshift;
        return new Rectangle(temp1, new Dimension(temp2.x - temp1.x, temp2.y - temp1.y));
    }
    
    /**
     * Converts the entered x-value from the screen resolution to the target resolution.
     * @param x The x-value to convert.
     * @return The converted x-value.
     */
    public int reverseCalcForX(int x) {
        return (int)(((float)x-xshift)/Xproportion);
    }
    
    /**
     * Converts the entered y-value from the screen resolution to the target resolution.
     * @param y The y-value to convert.
     * @return The converted y-value.
     */
    public int reverseCalcForY(int y) {
        return (int)(((float)y-yshift)/Yproportion);
    }
    
    /**
     * Converts the entered point from the screen resolution to the target resolution.
     * @param p The point to convert.
     * @return The converted point.
     */
    public Point reverseCalcForPoint(Point p) {
        return new Point((int)(((float)p.x - xshift)/Xproportion),(int)(((float)p.y - yshift)/Yproportion));
    }
    
    /**
     * Converts the entered points from the screen resolution to the target resolution.
     * @param p The top or leftmost point of the rectangle.
     * @param p2 The lower or rightmost point of the rectangle.
     * @return The converted rectangle.
     */
    public Rectangle reverseCalcForRectangle(Point p, Point p2) {
        Point temp1 = new Point((int)(((float)p.x - xshift)/Xproportion),(int)(((float)p.y - yshift)/Yproportion));
        Point temp2 = new Point((int)(((float)p2.x - xshift)/Xproportion),(int)(((float)p2.y - yshift)/Yproportion));
        return new Rectangle(temp1, new Dimension(temp2.x - temp1.x, temp2.y - temp1.y));
    }
    
    /**
     * Recalculates the screen resolution and proportions.
     */
    public void recalculate() {
        GraphicsConfiguration config = new Frame().getGraphicsConfiguration();
        resWidth = config.getBounds().width;
        resHeight = config.getBounds().height;
        Xproportion = resWidth/(float)width;
        Yproportion = resHeight/(float)height;
    }
    
}