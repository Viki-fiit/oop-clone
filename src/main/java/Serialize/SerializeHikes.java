package Serialize;

import Hike.BaseHike;

import java.io.*;
import java.util.ArrayList;
/**
 *The SerializeHikes class provides methods for serializing and deserializing a list of BaseHike objects to and from a file.
 */
public class SerializeHikes implements Serializable{
    /**
     *Serializes a list of BaseHike objects to a file.
     *@param objects the list of BaseHike objects to be serialized
     *@throws IOException if there is an error writing to the file
     */
    public static void serialize(ArrayList<BaseHike> objects) throws IOException {
        FileOutputStream fileOut = new FileOutputStream("src/main/java/Datasets/hikes.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(objects);
        fileOut.close();

    }
    /**
     *Deserializes a list of BaseHike objects from a file.
     *@return the deserialized list of BaseHike objects
     *@throws IOException if there is an error reading from the file
     *@throws ClassNotFoundException if the class of the deserialized object cannot be found
     */

    public static ArrayList<BaseHike> deserialize() throws IOException, ClassNotFoundException {
        ArrayList<BaseHike> objs = null;
        FileInputStream fileIn = new FileInputStream("src/main/java/Datasets/hikes.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        objs = (ArrayList<BaseHike>) in.readObject();
        return objs;
    }

}
