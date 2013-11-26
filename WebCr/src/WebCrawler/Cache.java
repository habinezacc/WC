package WebCrawler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ****************************************************************
 *
 * Cache: class is used to implement caching
 *
 * @author Ivy wainaina Concorde Habineza and Vivens Mutangana
 * @see DocumentSearch
 * @see IndexSearch
 *
 *****************************************************************
 */
public class Cache implements Serializable
{

    private HashMap<String, Set<URL>> documentCache;
    private HashMap<String, LinkedList<URL>> indexCache;

    /**
     * ********************************************************
     * This is the constructor for cache class it initialize documentCache and
     * indexCache
     *
     **********************************************************
     */
    public Cache()
    {
        this.documentCache = new HashMap<>();
        this.indexCache = new HashMap<>();

    }

    /**
     * *****************************************************************************
     * METHOD: readOject is used to de- serialize an object from the file.
     *
     * @param serialPath The relative path of the serial file containing the
     *
     * @return Object after being deserialized
     * **************************************************************************
     */
    public static Object readObject(String serialPath)
    {
        Object obj = null;
        try
        {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(serialPath));
            try
            {
                obj = in.readObject();
            } catch (ClassNotFoundException ex)
            {
                Logger.getLogger(Cache.class.getName()).log(Level.SEVERE, null, ex);
            }
            in.close();
        } catch (IOException ex)
        {
            Logger.getLogger(Cache.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    /**
     * This method return a de-serialize a hash map from the file.
     *
     * @param outputPath The relative filename to output the serialized object
     * @param obj object to be serialized
     */
    public static void writeObject(Object obj, String outputPath)
    {
        String path = outputPath + ".ser";
        try
        {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(obj);
            out.close();
        } catch (IOException ex)
        {

        }
    }
    /****************************************************************************************
     *
     * METHOD: getDocumentHashMap()
     *
     * @author V. Mutangana, I. Wainaina, C. Habineza
     *
     * Purpose : This method is used to return the hashMap of documents and their contents from disk
     *
     * @return HashMap
     * ***************************************************************************************/
    public static HashMap<String, Set<URL>> getDocumentHashMap()
    {
        return (HashMap<String, Set<URL>>) readObject("DocumentCache.ser");
       
    }
    /****************************************************************************************
     *
     * METHOD: getIndexCache()
     *
     * @author V. Mutangana, I. Wainaina, C. Habineza
     *
     * Purpose : This method is used to return the hashMap of topics and their contents from disk
     *
     * @return HashMap
     * ***************************************************************************************/
    public static HashMap<String, LinkedList<URL>> getIndexCache()
    {
        return (HashMap<String, LinkedList<URL>>) readObject("indexCache.ser");
    }//end of getIndexCache
    
     /****************************************************************************************
     *
     * METHOD: isFileEmpty()
     *
     * @author V. Mutangana, I. Wainaina, C. Habineza
     *
     * Purpose : This method is used to check if the files used to serialize object are empty to avoid getting empty objects
     *
     * @return true or false
     * ***************************************************************************************/

    public static boolean isFileEmpty(String file)
    {
        try
        {
            BufferedReader br;
            try
            {
                br = new BufferedReader(new FileReader(file));

                if (br.readLine() == null)
                {
                    return true;
                }
            } catch (FileNotFoundException ex)
            {
                Logger.getLogger(Cache.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IOException ex)
        {
            Logger.getLogger(Cache.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
