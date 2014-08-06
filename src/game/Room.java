package game;

import graphics.BufferedDevice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import resources.GameObject;

/**
 * Rooms are collections of Layers and GameObjects. The class contains functions to add and remove objects, as well as switch the displayed room.
 * @author Jack
 * @version 1.4 Alpha
 */
public class Room {
    
    /**
     * A list of all the rooms created.
     */
    public static List<Room> rooms = new ArrayList();
    private Layer defaultLayer = new Layer();
    private List<Layer> layers = new ArrayList();
    private HashMap layerNames = new HashMap();
    
    /**
     * Creates a new room.
     */
    public Room() {
        rooms.add(this);
    }
    
    /**
     * Adds a GameObject to the default layer. The default layer is on top.
     * @param object The object to add.
     */
    public void addObject(GameObject object) {
        defaultLayer.add(object);
    }
    
    /**
     * Adds a layer with the given name.
     * @param layer The layer to add.
     * @param name The layer name. This will be used to reference the layer.
     * @return The index of the added layer.
     */
    public int addLayer(Layer layer, String name) {
        layers.add(layer);
        layerNames.put(name, layers.size() - 1);
        return layers.size() - 1;
    }
    
    /**
     * Adds the given object to the layer at the given index.
     * @param object The object to add.
     * @param index The index of the layer to add it to.
     * @return The index of the added object in the selected layer.
     */
    public int addObjectToLayer(GameObject object, int index) {
        return layers.get(index).add(object);
    }
    
    /**
     * Adds the given object to the given layer.
     * @param object The object to add.
     * @param layer The layer to add the given object to.
     * @return The index of the added object in the selected layer.
     */
    public int addObjectToLayer(GameObject object, Layer layer) {
        return layer.add(object);
    }
    
    /**
     * Adds the given object to the layer with the given name.
     * @param object The object to add.
     * @param string The name of the layer to add the given object to.
     * @return The index of the added object in the selected layer.
     */
    public int addObjectToLayer(GameObject object, String string) {
        return layers.get((int)layerNames.get(object)).add(object);
    }
    
    /**
     * Adds the given object to the given position in the layer at the given index.
     * @param object The object to add.
     * @param index The index of the layer to add it to.
     * @param layerIndex The index to put the object at in the selected layer.
     * @return The index of the added object in the selected layer.
     */
    public int addObjectToLayer(GameObject object, int index, int layerIndex) {
        return layers.get(index).add(object, layerIndex);
    }
    
    /**
     * Adds the given object to the given layer at the given index in the layer.
     * @param object The object to add.
     * @param layer The layer to add the given object to.
     * @param layerIndex The index to put the object at in the selected layer.
     * @return The index of the added object in the selected layer.
     */
    public int addObjectToLayer(GameObject object, Layer layer, int layerIndex) {
        return layer.add(object, layerIndex);
    }
    
    /**
     * Adds the given object to the layer with the given name at the given index in the layer.
     * @param object The object to add.
     * @param string The name of the layer to add the given object to.
     * @param layerIndex The index to put the object at in the selected layer.
     * @return The index of the added object in the selected layer.
     */
    public int addObjectToLayer(GameObject object, String string, int layerIndex) {
        return layers.get((int)layerNames.get(object)).add(object, layerIndex);
    }
    
    /**
     * Erases whatever is on the screen and draws all the objects in the room.
     */
    public void display() {
       GameObject.clear();
       
    }
    
}