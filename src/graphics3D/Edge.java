package graphics3D;

/**
 * @author Jack
 */
public class Edge {
    
    private Point3D p1, p2;
    
    public Edge(Point3D p1, Point3D p2) {
        
        this.p1 = p1;
        this.p2 = p2;
        
    }

    public Point3D[] getPoints() {
        
        return new Point3D[]{p1, p2};
        
    }

    public void setPoints(Point3D p1, Point3D p2) {
        
        this.p1 = p1;
        this.p2 = p2;
        
    }
    
}