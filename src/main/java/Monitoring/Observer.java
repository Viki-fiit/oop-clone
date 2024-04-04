package Monitoring;

import java.io.IOException;

public interface Observer {
    /**
     * This method is called by the subject to notify the observer of an update.
     *
     * @param which A string representing the updated information.
     * @throws IOException If an I/O error occurs.
     */
    void update(String which) throws IOException;


}
