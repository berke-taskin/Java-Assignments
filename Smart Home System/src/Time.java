import java.time.LocalDateTime;
/**
* The Time class represents the time in the Smart Home System.
* It contains the current time value which is updated as the system runs.
*/
public class Time {
    private LocalDateTime currentTime;
    /**
     * Returns the current time in the Smart Home System.
     * 
     * @return the current time
     */
    public LocalDateTime getCurrentTime() {
        return currentTime;
    }
    /**
     * Sets the current time in the Smart Home System.
     * 
     * @param currentTime the current time
     */
    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }
}
