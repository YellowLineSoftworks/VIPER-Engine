package game;

import graphics.BufferedDevice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import resources.GameObject;
import resources.Sprite;

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
    private Layer defaultLayer;
    private List<Layer> layers = new ArrayList();
    private HashMap layerNames = new HashMap();
    private BufferedDevice device;
    protected static Room displayedRoom;
    
    /**
     * Creates a new room.
     * @param device The device to display the room on.
     */
    public Room(BufferedDevice device) {
        this.device = device;
        defaultLayer = new Layer(this.device);
        layers.add(defaultLayer);
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
     * Adds a GameObject to the default layer. The default layer is on top.
     * @param sprite The sprite to add.
     */
    public void addSprite(Sprite sprite) {
        defaultLayer.add(sprite);
    }
    
    /**
     * Adds a layer with the given name.
     * @param layer The layer to add.
     * @param name The layer name. This will be used to reference the layer.
     * @return The index of the added layer.
     */
    public int addLayer(Layer layer, String name) {
        layer.rooms.add(this);
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
     * Removes an object from the Room.
     * @param id The ID of the object to remove (GameObject.id).
     */
    public void removeObject(int id) {
        for (int c = 0; c < layers.size(); c++) {
            layers.get(c).removeObject(id);
        }
    }
    
    
    
    /**
     * Removes an object from the Room.
     * @param object The object to remove.
     */
    public void removeObject(GameObject object) {
        for (int c = 0; c < layers.size(); c++) {
            layers.get(c).removeObject(object);
        }
    }
    
    /**
     * Adds the given sprite to the layer at the given index.
     * @param sprite The sprite to add.
     * @param index The index of the layer to add it to.
     * @return The index of the added sprite in the selected layer.
     */
    public int addSpriteToLayer(Sprite sprite, int index) {
        return layers.get(index).add(sprite);
    }
    
    /**
     * Adds the given sprite to the given layer.
     * @param sprite The sprite to add.
     * @param layer The layer to add the given sprite to.
     * @return The index of the added sprite in the selected layer.
     */
    public int addSpriteToLayer(Sprite sprite, Layer layer) {
        return layer.add(sprite);
    }
    
    /**
     * Adds the given sprite to the layer with the given name.
     * @param sprite The sprite to add.
     * @param string The name of the layer to add the given sprite to.
     * @return The index of the added sprite in the selected layer.
     */
    public int addSpriteToLayer(Sprite sprite, String string) {
        return layers.get((int)layerNames.get(sprite)).add(sprite);
    }
    
    /**
     * Adds the given sprite to the given position in the layer at the given index.
     * @param sprite The sprite to add.
     * @param index The index of the layer to add it to.
     * @param layerIndex The index to put the sprite at in the selected layer.
     * @return The index of the added sprite in the selected layer.
     */
    public int addSpriteToLayer(Sprite sprite, int index, int layerIndex) {
        return layers.get(index).add(sprite, layerIndex);
    }
    
    /**
     * Adds the given sprite to the given layer at the given index in the layer.
     * @param sprite The sprite to add.
     * @param layer The layer to add the given sprite to.
     * @param layerIndex The index to put the sprite at in the selected layer.
     * @return The index of the added sprite in the selected layer.
     */
    public int addSpriteToLayer(Sprite sprite, Layer layer, int layerIndex) {
        return layer.add(sprite, layerIndex);
    }
    
    /**
     * Adds the given sprite to the layer with the given name at the given index in the layer.
     * @param sprite The sprite to add.
     * @param string The name of the layer to add the given sprite to.
     * @param layerIndex The index to put the sprite at in the selected layer.
     * @return The index of the added sprite in the selected layer.
     */
    public int addSpriteToLayer(Sprite sprite, String string, int layerIndex) {
        return layers.get((int)layerNames.get(sprite)).add(sprite, layerIndex);
    }
    
    
    /**
     * Removes an sprite from the Room.
     * @param id The ID of the sprite to remove (Sprite.id).
     */
    public void removeSprite(int id) {
        for (int c = 0; c < layers.size(); c++) {
            layers.get(c).removeSprite(id);
        }
    }
    
    /**
     * Removes a sprite from the room.
     * @param sprite The sprite to remove.
     */
    public void removeSprite(Sprite sprite) {
        for (int c = 0; c < layers.size(); c++) {
            layers.get(c).removeSprite(sprite);
        }
    }
    
    /**
     * Gets the current layers.
     * @return The current layers as a Layer[].
     */
    public Layer[] getLayers() {
        return (Layer[])layers.toArray();
    }
    
    /**
     * Gets the total number of GameObjects in all the layers.
     * @return The total number of GameObjects in all the layers.
     */
    public int getNumOfObjects() {
        int counter = 0;
        for (Layer l: layers) {
            counter += l.getObjects().length;
        }
        return counter;
    }
    
    /**
     * Gets the total number of displayed GameObjects in all the layers.
     * @return The total number of displayed GameObjects in all the layers.
     */
    public int getNumOfDisplayedObjects() {
        int counter = 0;
        for (Layer l: layers) {
            if (l.isVisible()) {
                for (GameObject obj: l.getObjects()) {
                    if (obj.currentSprite != null) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }
    
    /**
     * Displays the room on the current BufferedDevice.
     */
    public void display() {
        displayedRoom = this;
        for (Layer l: Layer.layers) {
            l.displayed = false;
        }
        device.clear();
        for(Layer l: layers) {
            l.displayed = true;
            l.display();
        }
        defaultLayer.display();
    }
    
}