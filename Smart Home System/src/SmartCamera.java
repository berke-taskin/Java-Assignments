import java.time.LocalDateTime;
/*
* The SmartCamera class represents a smart camera device that can be controlled remotely.
* It extends the Devices class and inherits the device-related properties.
* It stores information about the total megabyte stored, megabyte per minute, and switch time of the device.
*/
public class SmartCamera extends Devices {
    /*
    * The variables which will be accesed and defined in getters and setters.
    * You can check the comments below.
    */
    private String name;
    private double mbpm;
    private double mb;
    private String status = "Off";
    private LocalDateTime switchTime = null;
    private LocalDateTime firstTime;
    private LocalDateTime secondTime;
    /**
    * Constructor for creating a new SmartCamera object.
    *
    * @param name The name of the smart camera.
    */
    public SmartCamera(String name) {
        this.name = name;
    }
    /**
    * Returns the name of the smart camera.
    *
    * @return The name of the smart camera.
    */
    public String getName() {
        return name;
    }
    /**
    * Sets the name of the smart camera.
    *
    * @param name The name of the smart camera.
    */
    public void setName(String name) {
        this.name = name;
    }
    /**
    * Returns the megabyte stored per minute by the smart camera.
    *
    * @return the megabyte per minute value in Mbpm.
    */
    public double getMbpm() {
        return mbpm;
    }
    /**
    *Sets the megabyte stored per minute by the smart camera.
    *
    * @param mbpm the megabyte per minute value in Mbpm to be set.
    */
    public void setMbpm(double mbpm) {
        this.mbpm = mbpm;
    }
    /**
    * Returns the current status of the smart camera.
    *
    * @return The current status of the smart camera, which can be "On" or "Off".
    */
    public String getStatus() {
        return status;
    }
    /**
    * Sets the current status of the smart camera.
    *
    * @param status The current status of the smart camera, which can be "On" or "Off".
    */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
    * Returns the time at which the smart camera will be switched on or off.
    *
    * @return The time at which the smart camera will be switched on or off, stored as LocalDateTime.
    */
    public LocalDateTime getSwitchTime() {
        return switchTime;
    }
    /**
    * Sets the time at which the smart camera will be switched on or off.
    *
    * @param switchTime The time at which the smart camera will be switched on or off, stored as LocalDateTime.
    */ 
    public void setSwitchTime(LocalDateTime switchTime) {
        this.switchTime = switchTime;
    }   
    /**
    * Returns the total megabyte stored by the smart camera.
    *
    * @return the total megabyte value in MB.
    */
    public double getMb() {
        return mb;
    }
    /**
    *Sets the total megabyte stored by the smart camera.
    *
    * @param mb the total megabyte value in MB to be set.
    */
    public void setMb(double mb) {
        this.mb = mb;
    } 
    /**
    * Returns the start time of a period of time during which megabyte usage is being measured.
    *
    * @return start time of megabyte usage measurement period.
    */ 
    public LocalDateTime getFirstTime() {
        return firstTime;
    }
    /**
    * Sets the start time of a period of time during which megabyte usage is being measured.
    *
    * @param firstTime start time of megabyte usage measurement period.
    */
    public void setFirstTime(LocalDateTime firstTime) {
        this.firstTime = firstTime;
    }
    /**
    * Returns the end time of a period of time during which megabyte usage is being measured.
    *
    * @return end time of megabyte usage measurement period.
    */
    public LocalDateTime getSecondTime() {
        return secondTime;
    }
    /**
    * Sets the end time of a period of time during which megabyte usage is being measured.
    *
    * @param secondTime end time of megabyte usage measurement period.
    */
    public void setSecondTime(LocalDateTime secondTime) {
        this.secondTime = secondTime;
    }
}
