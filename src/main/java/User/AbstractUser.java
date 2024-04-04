package User;

import java.io.Serial;
import java.io.Serializable;
/**
 * The AbstractUser class is an abstract class that represents a user in the application.
 */
abstract class AbstractUser implements Serializable {
    /**
     * The UserInfo object that stores the user's information.
     */
    private UserInfo info;

    /**
     * Returns the user's username.
     *
     * @return the user's username
     */
    public String getUsername() {
        return info.GetUserName();
    }
    /**
     * Returns the user's password.
     *
     * @return the user's password
     */
    public String getPassword() {
        return info.GetPassword();
    }

    /**
     * Constructs a new AbstractUser object with the specified username and password.
     *
     * @param username the user's username
     * @param password the user's password
     */
    public AbstractUser(String username, String password) {
        this.info = new UserInfo(username, password);
    }

    /**
     * Sets UserInfo info username into provided value
     * @param name provided username.
     */
    public void setUsername(String name){
        info.setUserName(name);
    }
    /**
     * Sets UserInfo info password into provided value
     * @param psswd provided password.
     */
    public void setPassword(String psswd){
        info.setPassword(psswd);
    }
}