package Monitoring;

import java.io.IOException;
/**
 *The Check class represents a monitoring check.
 */
public class Check {

    private Observer observer;
    private String toSerialize;
    /**
     * Sets the observer for the Check.
     * @param obs The observer to be set.
     */
    public void setObserver(Observer obs){
        observer = obs;
    }
    /**
     * Creates a new instance of Check.
     * @param obs The observer to be set.
     * @param serial The string to be serialized.
     * @throws IOException if there is an error updating the observer.
     */
    public Check(Observer obs, String serial) throws IOException {
        setObserver(obs);
        setToSerialize(serial);
        observer.update(toSerialize);
    }

    /**
     * Sets the string to be serialized.
     * @param toSerialize The string to be set.
     */
    public void setToSerialize(String toSerialize) {
        this.toSerialize = toSerialize;
    }
    /**
     * Returns the string to be serialized.
     * @return The string to be serialized.
     */
    public String getToSerialize() {
        return toSerialize;
    }


}
