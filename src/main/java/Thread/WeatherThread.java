package Thread;


import Other.GlobalVariables;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
/**
 * The WeatherThread class provides a thread that periodically updates the weather in the application.
 */
public class WeatherThread {
    /**
     * The timer that schedules the periodic updates.
     */
    static Timer timer;
    /**
     * Constructs a new WeatherThread object and starts the timer.
     */
    public WeatherThread(){
        timer = new Timer(true);
        timer.schedule(new SwitchWeather(), 0,3000);
    }
    /**
     * A TimerTask that updates the weather.
     */
   static class SwitchWeather extends TimerTask{
        /**
         * Executes the task to update the weather.
         */
        @Override
        public void run() {
            setWeather();
        }
        /**
         * Updates the weather to a random value.
         */
        public void setWeather(){
            int weatherMax = GlobalVariables.getWeatherLength();
            Random random = new Random();
            int newWeather = random.nextInt(weatherMax);
            GlobalVariables.weatherLabel = ("Current weather: " + GlobalVariables.getWeather(newWeather));
            //System.out.println("Weather set: "+ GlobalVariables.weatherLabel);
        }
    }

}
