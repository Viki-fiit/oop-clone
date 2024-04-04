package Other;

import Hike.BaseHike;
import Monitoring.Serialize;
import User.BaseUser;

import java.util.ArrayList;
import java.util.Arrays;
/**
 *The {@code GlobalVariables} class represents a collection of global variables used in the application.
 *It contains static fields and methods that can be accessed throughout the entire application.
 */

public class GlobalVariables {
    /**
 * An instance of the {@code Serialize} class used to handle serialization and deserialization of objects.
 */
    public static Serialize observer = Serialize.getInstance();
    /**
     * An array of strings representing different types of weather.
     */
    private static final String[] Weather = {"SNOWY", "SUNNY", "CLOUDY", "RAINY", "WINDY"};
    /**
     * An array of strings representing different types of hikes.
     */
    private static final String[] HikeType = {"CLIMB", "WALK", "HIKE", "LADDER", "CABLE CAR"};
    /**
     * An array of strings representing different levels of difficulty of hikes.
     */

    private static final String[] HikeLvl = {"NOVICE", "EASY", "MEDIUM", "HARD", "EXPERT"};

    /**
     * A string representing the label of the current weather.
     */
    public static String weatherLabel;
    /**
     * A list of all the users in the system.
     */
    public static ArrayList<BaseUser> allUsers = new ArrayList<>();
    /**
     * A list of all the hikes in the system.
     */
    public static ArrayList<BaseHike> allHikes = new ArrayList<>();
    /**
     * Returns an array list of strings representing different types of hikes.
     *
     * @param types An array list of integers representing the index of the hike type in the {@code HikeType} array.
     * @return An array list of strings representing different types of hikes.
     */
    public static ArrayList<String> getHikeTypes(ArrayList<Integer> types){
        ArrayList<String> stringTypes = new ArrayList<>();
        types.forEach((n) -> {stringTypes.add(HikeType[n]);});
        return stringTypes;
    }
    /**
     * Returns the string representation of a weather type based on the provided index.
     *
     * @param index The index of the weather type in the {@code Weather} array.
     * @return The string representation of the weather type.
     */
    public static String getWeather(int index){
        return Weather[index];
    }
    /**
     * Returns the string representation of a hike level based on the provided index.
     *
     * @param index The index of the hike level in the {@code HikeLvl} array.
     * @return The string representation of the hike level.
     */
    public static String getHikeLvl(int index){
        return HikeLvl[index];
    }
    /**
     * Returns the length of the {@code Weather} array.
     *
     * @return The length of the {@code Weather} array.
     */
    public static int getWeatherLength(){
        return Weather.length;
    }
    /**
     * Returns the index of the specified hike level in the {@code HikeLvl} array.
     *
     * @param checkIndex The hike level to search for.
     * @return The index of the hike level in the {@code HikeLvl} array.
     */
    public static int getIndex(String checkIndex) {
       return Arrays.asList(HikeLvl).indexOf(checkIndex.toUpperCase());
    }

}
