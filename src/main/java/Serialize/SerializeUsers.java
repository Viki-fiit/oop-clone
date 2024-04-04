package Serialize;

import Hike.BaseHike;
import User.BaseUser;

import java.io.*;
import java.util.ArrayList;
/**
 * The SerializeUsers class provides static methods to serialize and deserialize an ArrayList of BaseUser objects.
 */
public class SerializeUsers implements Serializable {
    /**
     * Serializes an ArrayList of BaseUser objects to a file named "users.ser".
     *
     * @param objects the ArrayList of BaseUser objects to serialize
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public static void serialize(ArrayList<BaseUser> objects) throws IOException {
        FileOutputStream fileOut = new FileOutputStream("src/main/java/Datasets/users.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(objects);
        fileOut.close();

    }
    /**
     * Deserializes an ArrayList of BaseUser objects from a file named "users.ser".
     *
     * @return the deserialized ArrayList of BaseUser objects
     * @throws IOException if an I/O error occurs while reading from the file
     * @throws ClassNotFoundException if the class of the serialized objects cannot be found
     */

    public static ArrayList<BaseUser> deserialize() throws IOException, ClassNotFoundException {
        ArrayList<BaseUser> objs = null;
        FileInputStream fileIn = new FileInputStream("src/main/java/Datasets/users.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        objs = (ArrayList<BaseUser>) in.readObject();
        return objs;
    }


}
