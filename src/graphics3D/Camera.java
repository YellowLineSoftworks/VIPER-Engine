package graphics3D;

/**
 * @author Jack
 */
public class Camera {
    
    private Point3D startP1, startP2, endP1, endP2, location;
    private Room3D room;
    private Vector3D direction;
    
    public Camera(Room3D room, int range, Vector3D direction, Point3D location) {
        this.room = room;
        this.location = location;
        this.direction = direction;
        double devWidth =  room.getDevice().getSize().width;
        double devHeight = room.getDevice().getSize().height;
        startP1 = new Point3D((-1*devWidth/2) + location.x, (devHeight/2) + location.y, 0);
        startP2 = new Point3D((devWidth/2) + location.x, (-1*devHeight/2) + location.y, 0);
    }
    
}