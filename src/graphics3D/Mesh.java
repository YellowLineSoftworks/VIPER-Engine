package graphics3D;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack
 */
public class Mesh {
    
    private List<Polygon> polygons = new ArrayList();
    
    public Mesh(Polygon[] polygons) {
        
        for (Polygon poly: polygons) {
            this.polygons.add(poly);
        }
        
    }
    
    public void addPolygon(Polygon p) {
        
        polygons.add(p);
        
    } 
    
    public void removePolygon(Polygon p) {
        
        polygons.remove(p);
        
    }
    
    public List<Polygon> getPolygons() {
        
        return polygons;
        
    }
    
    public List<Edge> getEdges() {
        
        List<Edge> edges = new ArrayList();
        for (Polygon p: polygons) {
            for (Edge e: p.getEdges()) {
                edges.add(e);
            }
        }
        return edges;
        
    }
    
    public List<Point3D> getPoints() {
        
        List<Point3D> points = new ArrayList();
        for (Polygon p: polygons) {
            for (Edge e: p.getEdges()) {
                for (Point3D point: e.getPoints()) {
                    points.add(point);
                }
            }
        }
        return points;
        
    }
}