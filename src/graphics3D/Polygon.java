package graphics3D;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack
 */
public class Polygon {
    
    private List<Edge> edges = new ArrayList();
    
    public Polygon(Edge[] edges) {
        for (Edge e: edges) {
            this.edges.add(e);
        }
    }
    
    public void addEdge(Edge e) {
        edges.add(e);
    }
    
    public void removeEdge(Edge e) {
        edges.remove(e);
    }
    
    public List<Edge> getEdges() {
        return edges;
    }
    
    public List<Point3D> getPoints() {
        List<Point3D> points = new ArrayList();
        for (Edge e: edges) {
            if (points.contains(e.getPoints()[0])){
                points.add(e.getPoints()[0]);
            }
            if (points.contains(e.getPoints()[1])){
                points.add(e.getPoints()[1]);
            }
        }
        return points;
    }
    
}