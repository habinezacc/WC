package webcr;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * This method serialize and deserialize the hashmap
 * @author habineza
 */

public class HashSerializer {
    public HashSerializer(){}
    
    /**
     * This method return a de-serialize an object from the file.
     * @param serialPath The relative path of the serial file containing the object
     * @return Object 
     */
    public static Object deSerialize(String serialPath) {
        Object obj = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(serialPath));
            try {
                obj = in.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(WebCr.class.getName()).log(Level.SEVERE, null, ex);
            }
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(WebCr.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
    
    /**
     * This method return a de-serialize a hash map from the file.
     * @param outputPath The relative filename to output the serialized object
     * @param obj object to be serialized
     */
     public static void serialize(Object obj, String outputPath) {
        String path = outputPath + ".ser";
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in " + path);
        } catch (IOException ex) {
            Logger.getLogger(WebCr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
