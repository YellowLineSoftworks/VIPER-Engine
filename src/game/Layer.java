package game;

import graphics.BufferedDevice;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import resources.GameObject;
import resources.Sprite;

/**
 * A Layer is a collection of GameObjects that holds a specific position in the draw order. Similar to layer systems in image editors.
 * They are drawn (or not drawn) based on whether or not the GameObject is set to draw. If an object is added to a Layer, it will stop
 * drawing on the normal interface. Layer visibility can be toggled, but is enabled by default. The Layer contains a single array which
 * houses all of the Sprites and GameObjects, and discriminates between the two by means of a second array of integers representing
 * the indexes that are Sprites.
 * @author Jack
 * @version 1.5 Alpha
 */
public class Layer {
    
    /**
     * The layer identifier number.
     */
    public int id;
    
    /**
     * A list containing all the created layers.
     */
    public static List<Layer> layers = new ArrayList();
    private List<GameObject> objects = new ArrayList();
    private static int idCounter = 0;
    private boolean visible = true;
    private BufferedDevice device;
    private int[] spriteIndexes = new int[0];
    protected boolean displayed = false;
    protected List<Room> rooms = new ArrayList();
    
    /**
     * Creates a new empty layer.
     * @param device The BufferedDevice to display the layer on.
     */
    public Layer(BufferedDevice device) {
        this.device = device;
        id = idCounter;
        idCounter++;
        layers.add(this);
    }
    
    /**
     * Creates a new layer and initializes it with the given objects.
     * @param device The BufferedDevice to display the layer on.
     * @param objects The objects to add to the layer.
     */
    public Layer(GameObject[] objects, BufferedDevice device) {
        this.device = device;
        this.objects.addAll(Arrays.asList(objects));
        id = idCounter;
        idCounter++;
        layers.add(this);
    }
    
    /**
     * Add an object to the layer.
     * @param object The object to add.
     * @return The index of the object in the layer.
     */
    public int add(GameObject object) {
        objects.add(object);
        if (displayed && rooms.contains(Room.displayedRoom)) {
            Layer[] layers = Room.displayedRoom.getLayers();
            int counter = 0;
            for (Layer l: layers) {
                if (l == this) {
                    break;
                } else {
                    counter+=l.getAllItems().length;
                }
            }
            device.drawImage(object.currentSprite, counter);
        }
        return objects.size() - 1;
    }
    
    /**
     * Add an object to the layer at the given index.
     * @param object The object to add.
     * @param index The index to add it to in the layer.
     * @return The index of the object in the layer.
     */
    public int add(GameObject object, int index) {
        objects.add(index, object);
        for (int c = 0; c < spriteIndexes.length; c++) {
            if(spriteIndexes[c] >= index) {
                spriteIndexes[c]++;
            }
        }
        if (displayed && rooms.contains(Room.displayedRoom)) {
            Layer[] layers = Room.displayedRoom.getLayers();
            int counter = 0;
            for (Layer l: layers) {
                if (l == this) {
                    break;
                } else {
                    counter+=l.getAllItems().length;
                }
            }
            device.drawImage(object.currentSprite, counter);
        }
        return index;
    }
    
    /**
     * Add a Sprite to the layer.
     * @param sprite The sprite to add.
     * @return The index of the object in the layer.
     */
    public int add(Sprite sprite) {
        objects.add(sprite.toGameObject(device));
        int[] temp = new int[spriteIndexes.length + 1];
        System.arraycopy(spriteIndexes, 0, temp, 0, spriteIndexes.length);
        temp[spriteIndexes.length] = objects.size() - 1;
        spriteIndexes = temp;
        if (displayed && rooms.contains(Room.displayedRoom)) {
            Layer[] layers = Room.displayedRoom.getLayers();
            int counter = 0;
            for (Layer l: layers) {
                if (l == this) {
                    break;
                } else {
                    counter+=l.getAllItems().length;
                }
            }
            device.drawImage(sprite, counter);
        }
        return objects.size() - 1;
    }
    
    /**
     * Add a Sprite to the layer at the given index.
     * @param sprite The sprite to add.
     * @param index The index to add it to in the layer.
     * @return The index of the object in the layer.
     */
    public int add(Sprite sprite, int index) {
        objects.add(index, sprite.toGameObject(device));
        int[] temp = new int[spriteIndexes.length + 1];
        boolean added = false;
        int tempCounter = 0;
        int indexToInsertValue = 0;
        for (int a : spriteIndexes) {
            if (a < index) {
                indexToInsertValue++;
            }
        }
        //Insert the index into the spriteIndexes array at the appropriate point - ordered by position in the array, so by index size.
        for (int c = 0; c < spriteIndexes.length; c++) {
            if (!(tempCounter == indexToInsertValue)){
                temp[tempCounter] = spriteIndexes[c];
            } else {
                temp[tempCounter] = index;
                tempCounter++;
                temp[tempCounter] = spriteIndexes[c];
            }
            tempCounter++;
        }
        if (indexToInsertValue == spriteIndexes.length) {
            temp[spriteIndexes.length] = index;
        }
        //Increment all indexes after the inserted index by one, because something has just been added
        for (int c = 0; c < temp.length; c++) {
            if (c > indexToInsertValue) {
                temp[c]++;
            }
        }
        spriteIndexes = temp;
        if (displayed && rooms.contains(Room.displayedRoom)) {
            Layer[] layers = Room.displayedRoom.getLayers();
            int counter = 0;
            for (Layer l: layers) {
                if (l == this) {
                    break;
                } else {
                    counter+=l.getAllItems().length;
                }
            }
            device.drawImage(sprite, counter);
        }
        return index;
    }
    
    /**
     * Removes the GameObject with the given ID from the layer.
     * @param id The ID of the object to remove (GameObject.id).
     */
    public void removeObject(int id) {
        boolean isSprite = false;
        for(int y: spriteIndexes) {
            if (y == id) {
                isSprite = true;
            }
        }
        if (!isSprite) {
            for (int i = 0; i < objects.size(); i++) {
                if (objects.get(i).id == id) {
                    for (int c = 0; c < spriteIndexes.length; c++) {
                        if(spriteIndexes[c] > i) {
                            spriteIndexes[c]--;
                        }
                    }
                    device.removeImage(objects.get(i).currentSprite.id);
                    objects.remove(i);
                }
            }
        } else {
            System.err.println("Error in Layer.removeObject(): Given ID is a Sprite.");
        }
    }
    
    /**
     * Removes the given GameObject from the layer.
     * @param object The GameObject to remove.
     */
    public void removeObject(GameObject object) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).equals(object)) {
                boolean isSprite = false;
                for(int y: spriteIndexes) {
                    if (y == i) {
                        isSprite = true;
                    }
                }
                if (!isSprite) {
                    for (int c = 0; c < spriteIndexes.length; c++) {
                        if(spriteIndexes[c] > i) {
                            spriteIndexes[c]--;
                        }
                    }
                    device.removeImage(objects.get(i).currentSprite.id);
                    objects.remove(i);
                }
            }
        }
    }
    
    /**
     * Removes the Sprite with the given ID from the layer.
     * @param id The ID of the object to remove (GameObject.id).
     */
    public void removeSprite(int id) {
        boolean isSprite = false;
        for(int y: spriteIndexes) {
            if (y == id) {
                isSprite = true;
            }
        }
        if (isSprite) {
            for (int i = 0; i < objects.size(); i++) {
                if (objects.get(i).id == id) {
                    for (int c = 0; c < spriteIndexes.length; c++) {
                        if(spriteIndexes[c] == i) {
                            int[] temp = new int[spriteIndexes.length - 1];
                            int tempCounter = 0;
                            for (int z = 0; z < spriteIndexes.length; z++) {
                                if (spriteIndexes[z] != i) {
                                    temp[tempCounter] = spriteIndexes[z];
                                    tempCounter++;
                                }
                            }
                            spriteIndexes = temp;
                        }
                    }
                    device.removeImage(objects.get(i).currentSprite.id);
                    objects.remove(i);
                }
            }
        } else {
            System.err.println("Error in Layer.removeSprite(): Given ID is a GameObject.");
        }
    }
    
    /**
     * Removes the given Sprite from the layer.
     * @param sprite The Sprite to remove.
     */
    public void removeSprite(Sprite sprite) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).currentSprite.equals(sprite)) {
                boolean isSprite = false;
                for(int y: spriteIndexes) {
                    if (y == i) {
                        isSprite = true;
                    }
                }
                if (isSprite) {
                    int[] temp = new int[spriteIndexes.length - 1];
                    int tempCounter = 0;
                    for (int z = 0; z < spriteIndexes.length; z++) {
                        if (spriteIndexes[z] != i) {
                            temp[tempCounter] = spriteIndexes[z];
                            tempCounter++;
                        }
                    }
                    spriteIndexes = temp;
                    for (int c = 0; c < spriteIndexes.length; c++) {
                        if(spriteIndexes[c] > i) {
                            spriteIndexes[c]--;
                        }
                    }
                    device.removeImage(objects.get(i).currentSprite.id);
                    objects.remove(i);
                }
            }
        }
    }
    
    /**
     * Get an array of all the GameObjects in the layer.
     * @return A GameObject[] containing all of the GameObjects in the layer.
     */
    public GameObject[] getObjects() {
        List<GameObject> actualObjects = new ArrayList();
        for (int c = 0; c < objects.size(); c++)  {
            boolean add = true;
            for(int index: spriteIndexes){
                if (c == index) {
                    add = false;
                }
            }
            if (add) {
                actualObjects.add(objects.get(c));
            }
        }
        GameObject[] ret = new GameObject[actualObjects.size()];
        for(int c = 0; c < actualObjects.size(); c++) {
            ret[c] = actualObjects.get(c);
        }
        return ret;
    }
    
    /**
     * Get an array of all the Sprites in the layer.
     * @return A Sprite[] containing all of the Sprites in the layer.
     */
    public Sprite[] getSprites() {
        List<Sprite> spritesInList = new ArrayList();
        for (int c = 0; c < objects.size(); c++)  {
            boolean add = false;
            for(int index: spriteIndexes){
                if (c == index) {
                    add = true;
                }
            }
            if (add) {
                spritesInList.add(objects.get(c).currentSprite);
            }
        }
        Sprite[] ret = new Sprite[spritesInList.size()];
        for(int c = 0; c < spritesInList.size(); c++) {
            ret[c] = spritesInList.get(c);
        }
        return ret;
    }
    
    /**
     * Gets the GameObject with the given ID.
     * @param id The ID of the GameObject to retrieve (GameObject.id).
     * @return The GameObject with the given ID.
     */
    public GameObject getObject(int id) {
        List<GameObject> actualObjects = new ArrayList();
        for (int c = 0; c < objects.size(); c++)  {
            boolean add = true;
            for(int index: spriteIndexes){
                if (c == index) {
                    add = false;
                }
            }
            if (add) {
                actualObjects.add(objects.get(c));
            }
        }
        for (GameObject g: actualObjects) {
            if (g.id == id) {
                return g;
            }
        }
        System.err.println("Error in Layer.getObject(): GameObject not found.");
        return null;
    }
    
    /**
     * Get all GameObjects and Sprites, with the Sprites translated into GameObjects.
     * @return All of the added GameObjects and Sprites, with the Sprites translated into GameObjects.
     */
    public GameObject[] getAllItems() {
        return (GameObject[])objects.toArray();
    }
    
    /**
     * Get an array containing the indexes in the array returned by {@link #getAllItems() getAllItems()} 
     * that reference sprites translated into GameObjects.
     * @return An array of integers representing the sprite indexes.
     */
    public int[] getSpriteIndexes() {
        return spriteIndexes;
    }
    
    /**
     * Gets the Sprite with the given ID.
     * @param id The ID of the Sprite to retrieve (Sprite.id).
     * @return The Sprite with the given ID.
     */
    public Sprite getSprite(int id) {
        List<Sprite> sprites = new ArrayList();
        for (int c = 0; c < objects.size(); c++)  {
            boolean add = false;
            for(int index: spriteIndexes){
                if (c == index) {
                    add = true;
                }
            }
            if (add) {
                sprites.add(objects.get(c).currentSprite);
            }
        }
        for (Sprite s: sprites) {
            if (s.id == id) {
                return s;
            }
        }
        System.err.println("Error in Layer.getSprite(): Sprite not found.");
        return null;
    }
    
    /**
     * Retrieves the number of objects in the array.
     * @return The number of objects in the array.
     */
    public int size() {
        return objects.size();
    }
    
    /**
     * Sets the layer visibility. Enabled by default.
     * @param visible Whether or not the layer should be visible.
     */
    public void setVisible(boolean visible) {
        if (visible) {
            display();
        } else {
            for (GameObject obj: objects) {
                device.removeImage(obj.currentSprite.id);
            }
        }
        this.visible = visible;
    }
    
    /**
     * Checks the current layer visibility settings.
     * @return Whether or not the layer is visible.
     */
    public boolean isVisible() {
        return visible;
    }
    
    /**
     * Displays the layer.
     */
    public void display() {
        if (isVisible()) {
            for (GameObject object : objects) {
                object.draw();
            }
        }
    }
    
    /**
     * Clears the layer of all GameObjects and Sprites.
     */
    public void clear() {
        for (GameObject obj: objects) {
            device.removeImage(obj.currentSprite.id);
        }
        objects = new ArrayList();
        spriteIndexes = new int[0];
    }
    
}