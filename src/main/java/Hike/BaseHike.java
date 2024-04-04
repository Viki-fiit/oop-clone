package Hike;


import java.io.Serializable;
import java.util.ArrayList;

/**
 *The BaseHike class represents a basic hiking trail.
 *It contains information such as the name of the hike, the minimum age required to participate,
 *whether the hike is available, the list of weather conditions in which the hike is possible,
 *the difficulty level of the hike, and the list of types of terrain covered by the hike.
 *The class provides methods for setting and getting each attribute of a hike, as well as
 *methods for adding, removing and updating the list of weather conditions and types of terrain.
 *It also includes a static Builder class for creating instances of BaseHike.
 */
public class BaseHike implements Serializable{

    private String NAME;
    private int MIN;
    private boolean AVL;
    private ArrayList<Integer> WeatherOK = new ArrayList<Integer>();
    private int Difficulty;
    private ArrayList<Integer> Type = new ArrayList<Integer>();

  //  private ArrayList<BaseWaypoint> Waypoints = new ArrayList<BaseWaypoint>()
    /**
     * Constructs a BaseHike object with the specified attributes.
     * @param builder the builder object used to construct the BaseHike
     */
    public BaseHike(Builder builder){
        setNAME(builder.NAME);
        setMIN(builder.MIN);
        setType(builder.Type);
        setDifficulty(builder.Difficulty);
        setAVL(builder.AVL);
        setWeatherOK(builder.WeatherOK);
    }
    /**
     * Returns a new Builder object to build a BaseHike.
     * @return a new Builder object
     */
    public static Builder newHike(){
        return new Builder();
    }
    /**
     * Returns the name of the hike.
     * @return the name of the hike
     */
    public String getNAME(){
        return NAME;
    }
    /**
     * Returns the name of the hike.
     * @return the name of the hike
     */
    public String getName(){
        return NAME;
    }
    /**
     * Sets the name of the hike.
     * @param name the new name of the hike
     */
    public void setNAME(String name){
        this.NAME = name;
    }
    /**
     * Returns the time it takes to complete the hike.
     * @return the time it takes to complete the hike.
     */
    public int getMin(){
        return MIN;
    }
    /**
     * Sets the time it takes to complete the hike.
     * @param MIN the new the time it takes to complete the hike.
     */
    public void setMIN(int MIN){
        this.MIN = MIN;
    }
    /**
     * Returns true if the hike is available, false otherwise.
     * @return true if the hike is available, false otherwise
     */
    public boolean getAVL(){
        return AVL;
    }
    /**
     * Sets the availability of the hike.
     * @param AVL the new availability of the hike
     */
    public void setAVL(boolean AVL){
        this.AVL = AVL;
    }
    /**
     * Returns the list of weather conditions in which the hike is possible.
     * @return the list of weather conditions in which the hike is possible
     */
    public ArrayList<Integer> getWeatherOK() {
        return WeatherOK;
    }
    /**
     * Sets the list of weather conditions in which the hike is possible.
     * @param weathers the new list of weather conditions in which the hike is possible
     */
    public void setWeatherOK(ArrayList<Integer> weathers){
        this.WeatherOK.clear();
        this.WeatherOK.addAll(weathers);
    }

    public void setWeatherOK(int oldW, int newW){
        this.WeatherOK.set(this.WeatherOK.indexOf(oldW), newW);
    }

    public void AddWeather(int otherW){
        this.WeatherOK.add(otherW);
    }

    public void SubWeather(int otherW){
        this.WeatherOK.remove(this.WeatherOK.indexOf(otherW));
    }
    /**
     *Returns the difficulty of the hike.
     *@return The difficulty of the hike.
     */
    public int getDifficulty(){
        return Difficulty;
    }
    /**
     *Sets the difficulty of the hike.
     *@param diff The new difficulty to set.
     */

    public void setDifficulty(int diff){
        this.Difficulty = diff;
    }
    /**
     *Returns the ArrayList of types of the hike.
     *@return The ArrayList of types of the hike.
     */
    public ArrayList<Integer> getType(){
        return Type;
    }
    /**
     *Sets the ArrayList of types of the hike.
     *@param types The new ArrayList of types to set.
     */
    public void setType(ArrayList<Integer> types){
        this.Type.clear();
        this.Type.addAll(types);
    }
    public void setType(int i,int New){
        this.Type.set(i, New);

    }
    public void setType(int i){
        this.Type.add(i);
    }


    /**
     *The Builder class used to create instances of BaseHike.
     */
    public static class Builder implements Serializable{
        private String NAME;
        private int MIN;
        private boolean AVL;
        private ArrayList<Integer> WeatherOK = new ArrayList<Integer>();
        private int Difficulty;
        private ArrayList<Integer> Type = new ArrayList<Integer>();

      //  private ArrayList<BaseWaypoint> Waypoints = new ArrayList<BaseWaypoint>();
        /**
         Creates a new instance of Builder.
         */
        private Builder(){
        } /**
         *Sets the name of the hike.
         *@param name the name of the hike.
         *@return this Builder instance.
         */
        public Builder NAME(String name){
            this.NAME = name;
            return this;
        }
        /**
         *Sets the duration of the hike.
         *@param MIN the duration of the hike.
         *@return this Builder instance.
         */
        public Builder MIN(int MIN){
            this.MIN = MIN;
            return this;
        }
        /**
         *Sets whether the hike is available  or not.
         *@param AVL true if the hike is available, false otherwise.
         *@return this Builder instance.
         */
        public Builder AVL(boolean AVL){
            this.AVL = AVL;
            return this;
        }
        /**
         *Sets the weather conditions that the hike is available for.
         *@param weathers the list of weather conditions that the hike is available for.
         *@return this Builder instance.
         */
        public Builder WeatherOK(ArrayList<Integer> weathers){
            this.WeatherOK.addAll(weathers);
            return this;
        }
        /**
         *Sets the difficulty level of the hike.
         *@param diff the difficulty level of the hike.
         *@return this Builder instance.
         */
        public Builder Difficulty(int diff){
            this.Difficulty = diff;
            return this;
        }
        /**
         *Sets the types of the hike.
         *@param types the list of types of the hike.
         *@return this Builder instance.
         */
        public Builder Type(ArrayList<Integer> types){
            this.Type.addAll(types);
            return this;
        }
        /**
         *Adds a new type to the types of the hike.
         *@param type the type to be added.
         *@return this Builder instance.
         */
        public Builder Type(int type){
            this.Type.add(type);
            return this;
        }
        /**
         *Adds a new weather condition to the weather conditions that the hike is available for.
         *@param weather the weather condition to be added.
         *@return this Builder instance.
         */
       public Builder WeatherOK(int weather){
           this.WeatherOK.add(weather);
           return this;
       }
/*
        public Builder Waypoints(ArrayList<BaseWaypoint> WPS){
            this.Waypoints = WPS;
            return this;
        }*/
        /**
         *Builds a new BaseHike instance using the parameters set in this Builder instance.
         *@return a new BaseHike instance.
         */
        public BaseHike build(){
            return new BaseHike(this);
        }
    }





}
