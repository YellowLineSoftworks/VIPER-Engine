package graphics3D;

/**
 * @author Jack
 */
public class Vector3D {

    private double[] direction;
    
    public Vector3D(double x, double y, double z){
        
        direction = new double[]{x,y,z};
        
    }

    public double[] getDirection() {
        
        return direction;
        
    }

    public void setDirection(int x, int y, int z) {
        
        direction = new double[]{x,y,z};
        
    }
    
    public void addVector(Vector3D v) {
        
        direction[0] = v.getDirection()[0] + getDirection()[0];
        direction[1] = v.getDirection()[1] + getDirection()[1];
        direction[2] = v.getDirection()[2] + getDirection()[2];
        
    }
    
    public void subtractVector(Vector3D v) {
        
        direction[0] = v.getDirection()[0] - getDirection()[0];
        direction[1] = v.getDirection()[1] - getDirection()[1];
        direction[2] = v.getDirection()[2] - getDirection()[2];
        
    }
    
    public void rotateXY(double degrees) {
        
        double rad = Math.toRadians(degrees);
        double a = direction[0], b = direction[1], c = direction[2];
        direction[0] = Math.cos(rad)*a + Math.sin(rad)*-1*b;
        direction[1] = Math.sin(rad)*a + Math.cos(rad)*b;
        
    }
    
    public void rotateXZ(double degrees) {
        
        double rad = Math.toRadians(degrees);
        double a = direction[0], b = direction[1], c = direction[2];
        direction[0] = Math.cos(rad)*a + Math.sin(rad)*c;
        direction[2] = Math.sin(rad)*-1*a + Math.cos(rad)*c;
        
    }
    
    public void rotateYZ(double degrees) {
        
        double rad = Math.toRadians(degrees);
        double a = direction[0], b = direction[1], c = direction[2];
        direction[1] = Math.cos(rad)*b + Math.sin(rad)*-1*c;
        direction[2] = Math.sin(rad)*b + Math.cos(rad)*c;
        
    }
    
    /**
     * Scales the vector.
     * @param x The factor to multiply the x-axis size by.
     * @param y The factor to multiply the y-axis size by.
     * @param z The factor to multiply the z-axis size by.
     */
    public void scale(double x, double y, double z) {
        
        double a = direction[0], b = direction[1], c = direction[2];
        direction[0] = x*a;
        direction[1] = y*b;
        direction[2] = z*c;
        
    }
    
}