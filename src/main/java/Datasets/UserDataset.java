package Datasets;

import java.io.*;
import java.util.Hashtable;
import java.util.Objects;
import java.util.Scanner;
/**
 * The UserDataset class manages the dataset for user login credentials.
 * It allows reading and writing user data to a file and provides a thread-safe singleton instance.
 */

public class UserDataset {

    private Hashtable<String, String> username_psswd = new Hashtable<>();
    /**
     * Returns the Hashtable that contains the username and password data.
     * @return Hashtable containing username and password data.
     */
    public Hashtable<String, String> getUserPsswdData(){
        return username_psswd;
    }
    private static UserDataset instance = null;
    /**
     * Private constructor for the UserDataset singleton instance.
     * Reads data from file on initialization.
     * @throws FileNotFoundException if the file does not exist.
     */

    private UserDataset() throws FileNotFoundException {
        readData();
    }
    /**
     * Returns a thread-safe singleton instance of the UserDataset class.
     * @return UserDataset instance.
     * @throws FileNotFoundException if the file does not exist.
     */
    public static synchronized UserDataset getInstance() throws FileNotFoundException {
        if (instance == null){
            instance = new UserDataset();
        }
        return instance;
    }

    /**
     * Reads user data from the file and stores it in the Hashtable.
     * @throws FileNotFoundException if the file does not exist.
     */
    public void readData() throws FileNotFoundException{
        File user_psswd_data = new File("src\\main\\java\\Datasets\\usersDataset");
        Scanner reader = new Scanner(user_psswd_data);

        boolean line = reader.hasNextLine();

        while(line){
            String username = reader.nextLine();
            if(Objects.equals(username, "\n")){
                return;
            }
            String password = reader.nextLine();

            username_psswd.put(username, password);

            line = reader.hasNextLine();
        }
        // reader.close();
    }

    /**
     * Writes user data from the Hashtable to the file.
     * @throws IOException if there is an error writing to the file.
     */
    public void writeData() throws IOException {
        UserDataset temp = UserDataset.getInstance();
        Hashtable<String, String> data = temp.getUserPsswdData();
       // System.out.println(data);

        File user_psswd_data = new File("src/main/java/Datasets/usersDataset");
        FileWriter writerI = new FileWriter(user_psswd_data);
        for(String key: data.keySet()){
            writerI.write(key+"\n");
            writerI.write(data.get(key) + "\n");
        }
        writerI.close();


    }

}
