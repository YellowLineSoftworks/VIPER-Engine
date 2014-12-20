package graphics3D;

/**
 * @author Jack
 */
public class Point3D {
    
    public double x;
    public double y;
    public double z;
    
    public Point3D(double x, double y, double z){
        
        this.x = x;
        this.y = y;
        this.z = z;
        
    }

    public double[] getLocation() {
        
        return new double[]{x, y, z};
        
    }

    public void setLocation(int x, int y, int z) {
        
        this.x = x;
        this.y = y;
        this.z = z;
    
    }
    
    public void addVector(Vector3D v) {
        
         x = v.getDirection()[0] + getLocation()[0];
         y = v.getDirection()[1] + getLocation()[1];
         z = v.getDirection()[2] + getLocation()[2];
        
    }
    
    public void subtractVector(Vector3D v) {
        
         x = v.getDirection()[0] - getLocation()[0];
         y = v.getDirection()[1] - getLocation()[1];
         z = v.getDirection()[2] - getLocation()[2];
        
    }
    
    //These methods rotate the point around the origin
    public void rotateXY(double degrees) {
        
        double rad = Math.toRadians(degrees);
        double a = x, b = y, c = z;
        x = Math.cos(rad)*a + Math.sin(rad)*-1*b;
        y = Math.sin(rad)*a + Math.cos(rad)*b;
        
    }
    
    public void rotateXZ(double degrees) {
        
        double rad = Math.toRadians(degrees);
        double a = x, b = y, c = z;
        x = Math.cos(rad)*a + Math.sin(rad)*c;
        z = Math.sin(rad)*-1*a + Math.cos(rad)*c;
        
    }
    
    public void rotateYZ(double degrees) {
        
        double rad = Math.toRadians(degrees);
        double a = x, b = y, c = z;
        y = Math.cos(rad)*b + Math.sin(rad)*-1*c;
        z = Math.sin(rad)*b + Math.cos(rad)*c;
        
    }
    
}