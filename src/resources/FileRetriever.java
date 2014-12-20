package resources;

import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * FileRetrievers retrieve files from the game storage folder.
 * @author Jack
 * @version 1.5 Alpha
 */
public class FileRetriever {
    
    private String folderLocation;
    
    /**
     * Creates a new FileRetriever for files within the current jar.
     */
    public FileRetriever() {
        folderLocation = "";
    }
    
    /**
     * Creates a new FileRetriever.
     * @param folderLocation Location of the game storage folder.
     */
    public FileRetriever(String folderLocation) {
        this.folderLocation = folderLocation;
    }
    
    /**
     * Either retrieves a file from the game storage folder or from the current jar, depending on
     * which constructor was used. Creates a file if none matching the provided file name exist.
     * @param fileName The name of the target file with the filetype extension included. 
     * Example: res\\example.png for a file in a folder
     *          res/example.png for a file in the res package in your program
     * @return The target file.
     */
    public File retrieveFile(String fileName) {
        if (!folderLocation.equals("")){
            return new File(folderLocation + "\\" + fileName);
        } else {
            return new File(this.getClass().getResource("/" + fileName).getPath());
        }
    }
    
    /**
     * Either retrieves an image from the game storage folder or from the current jar, depending on
     * which constructor was used.
     * @param fileName The name of the target image with the filetype extension included. 
     * Example: res\\example.png for an image in a folder
     *          res/example.png for an image in the res package in your program
     * @return The target image.
     */
    public Image retrieveImage(String fileName) {
        try {
            if (!folderLocation.equals("") && new File(folderLocation + "\\" + fileName).exists()){
                return ImageIO.read(new File(folderLocation + "\\" + fileName));
            } else {
                return ImageIO.read(new File(this.getClass().getResource("/" + fileName).getPath()));
            }
        } catch (Exception e) {
            System.err.println("Error retrieving image.");
            e.printStackTrace();
            return null;
        }
    }
    
}