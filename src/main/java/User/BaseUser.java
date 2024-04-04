package User;

import java.io.Serializable;
import java.util.ArrayList;

public class BaseUser extends AbstractUser implements Serializable{

    private ArrayList<Integer> PreffType = new ArrayList<>();
    private int Lvl;

    /**
     * Constructs a new BaseUser object with the specified username and password.
     *
     * @param username the user's username
     * @param password the user's password
     */
    public BaseUser(String username, String password) {
        super(username, password);
    }
    /**
     * Adds the specified list of types to the user's preferred types.
     *
     * @param types the list of types to add
     */
    public void setPreffType(ArrayList<Integer> types){
        PreffType.addAll(types);
    }
    /**
     * Adds the specified type to the user's preferred types.
     *
     * @param addtype the type to add
     */
    public void setPreffType(int addtype){
        PreffType.add(addtype);
    }
    public void unsetPreffType(int subtype){
        PreffType.remove(subtype);
    }

    public void unsetPreffType(){
        PreffType.clear();
    }
    /**
     * Returns the list of preferred types associated with the user.
     *
     * @return the list of preferred types associated with the user
     */
    public ArrayList<Integer> getPreffType() {
        return PreffType;
    }
    /**
     * Sets the user's level of experience to the specified level.
     *
     * @param lvl the user's level
     */
    public void setLvl(int lvl) {
        Lvl = lvl;
    }
    /**
     * Returns the user's level of experience.
     *
     * @return the user's level of experience
     */
    public int getLvl() {
        return Lvl;
    }
}
