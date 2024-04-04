package User;

import Datasets.UserDataset;

import java.io.IOException;
import java.io.Serializable;
/**
 * The UserInfo class represents user information such as username and password.
 */
public class UserInfo implements Serializable {
    private String userName;
    private String password;

    /**
     * Constructs a new UserInfo object with the specified username and password.
     *
     * @param userName the user's username
     * @param password the user's password
     */

    public UserInfo(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
/*
    public void Set(String name, String psswd) throws IOException {
        UserDataset temp = UserDataset.getInstance();
        this.userName = name;
        this.password = psswd;
        UserDataset.addUser();

    }*/
    /**
     * Returns the user's username.
     *
     * @return the user's username
     */
    public String GetUserName(){
        return userName;
    }
    /**
     * Returns the user's password.
     *
     * @return the user's password
     */
    public String GetPassword(){
        return password;
    }

    /**
     * Sets user's username into specified value
     * @param name provided value
     */
    public void setUserName(String name){
        userName = name;
    }
    /**
     * Sets user's password into specified value
     * @param psswd provided value
     */
    public void setPassword(String psswd){
        password = psswd;
    }


}
