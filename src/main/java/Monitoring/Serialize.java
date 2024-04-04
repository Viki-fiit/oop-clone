package Monitoring;

import java.io.IOException;
import java.util.Objects;

import Other.GlobalVariables;
import Serialize.*;
/**
 *The Serialize class is a Singleton class that implements the Observer interface. It provides a single instance that is responsible for serializing the User and Hike objects into files.
 */
public class Serialize implements Observer{
    /**
     *The instance variable holds the single instance of the Serialize class.
     */
    private static Serialize instance = null;

    /**
     *The constructor is private and therefore inaccessible from outside the class.
     */
    private Serialize(){

    }
    /**
     *The getInstance() method returns the single instance of the Serialize class. If no instance exists, it creates a new one.
     *@return the single instance of the Serialize class.
     */
    public static synchronized Serialize getInstance(){
        if(instance == null){
            instance = new Serialize();
        }
        return instance;
    }
    /**
     *The update() method updates the serialization of the User and Hike objects. It is called by the Check class when a change is made to one of these objects.
     *@param which - a string indicating which object to update. It can be "all", "users", or "hikes".
     *@throws IOException if there is an error during the serialization process.
     */
    @Override
    public void update(String which) throws IOException {
        if (Objects.equals(which, "all")){
            SerializeUsers.serialize(GlobalVariables.allUsers);
            SerializeHikes.serialize(GlobalVariables.allHikes);
        }
        else if (Objects.equals(which, "users")){
            SerializeUsers.serialize(GlobalVariables.allUsers);
        }
        else if (Objects.equals(which, "hikes")){
            SerializeHikes.serialize(GlobalVariables.allHikes);
        }
    }


}
