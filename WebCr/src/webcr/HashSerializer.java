package webcr;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import webcr.WebCr;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chabineza
 */
public class HashSerializer {
    public HashSerializer(){}
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
     public static void serialize(Object obj, String outputPath) {
        String path = outputPath + ".ser";
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(path);
            ObjectOutputStream out;

            out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in " + path);
            fileOut.close();
        } catch (IOException ex) {
            Logger.getLogger(WebCr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
