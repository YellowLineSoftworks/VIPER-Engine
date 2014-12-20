package graphics3D;

import game.Clock;
import graphics.BufferedDevice;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack
 */
public class Room3D {
    
    private static List<Room3D> rooms = new ArrayList();
    private static Room3D currentRoom = null;
    private List<Mesh> meshes = new ArrayList();
    private List<Polygon> polygons = new ArrayList();
    private List<Edge> edges = new ArrayList();
    private List<Point3D> points = new ArrayList();
    private BufferedDevice device;
    
    public Room3D(BufferedDevice device) {
        device.set3DEnabled(true);
        this.device = device;
        rooms.add(this);
        if(currentRoom == null) {
            currentRoom = this;
        }
    }
    
    public Room3D(BufferedDevice device, Clock clock) {
        device.set3DEnabled(true);
        this.device = device;
        rooms.add(this);
        if(currentRoom == null) {
            currentRoom = this;
        }
        clock.enable3D(this);
    }
    
    public void setCurrentRoom() {
        currentRoom = this;
    }

    public static List<Room3D> getRooms() {
        return rooms;
    }

    public List<Mesh> getMeshes() {
        return meshes;
    }

    public List<Polygon> getPolygons() {
        return polygons;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Point3D> getPoints() {
        return points;
    }
    
    public void addMesh(Mesh m) {
        meshes.add(m);
    }
    
    public void addPolygon(Polygon p) {
        polygons.add(p);
    }
    
    public void addEdge(Edge e) {
        edges.add(e);
    }
    
    public void addPoint(Point3D p) {
        points.add(p);
    }
    
    public void removeMesh(Mesh m) {
        meshes.remove(m);
    }
    
    public void removePolygon(Polygon p) {
        polygons.remove(p);
    }
    
    public void removeEdge(Edge e) {
        edges.remove(e);
    }
    
    public void removePoint(Point3D p) {
        points.remove(p);
    }
    
    public void destroy() {
        rooms.remove(this);
        if (rooms.size() >= 1) {
            currentRoom = rooms.get(0);
        }
    }
    
    public void render() {
        //device.get3DCanvas.image = whatever the final render looks like.
    }
    
    protected BufferedDevice getDevice() {
        return device;
    }
    
}