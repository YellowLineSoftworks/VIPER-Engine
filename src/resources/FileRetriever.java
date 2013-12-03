package resources;

import java.io.File;

/**
 * FileRetrievers retrieve files from the game storage folder.
 * @author Jack
 * @version 1.4 Alpha
 */
public class FileRetriever {
    
    private String folderLocation;
    
    /**
     * Creates a new FileRetriever.
     * @param folderLocation Location of the game storage folder.
     */
    public FileRetriever(String folderLocation) {
        this.folderLocation = folderLocation;
    }
    
    /**
     * Retrieves a file from the game storage folder. Creates a file if none matching the provided file name exist.
     * @param fileName The name of the target file with the filetype extension included. 
     * @return The target file.
     */
    public File retrieveFile(String fileName) {
        return new File(folderLocation + "\\" + fileName);
    }
    
}