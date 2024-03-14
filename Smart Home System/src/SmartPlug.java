import java.time.LocalDateTime;
/*
* The SmartPlug class represents a smart plug device that can be controlled remotely.
* It extends the Devices class and inherits the device-related properties.
* It stores information about the voltage, current (in amperes), energy usage,
* and switch time of the device.
*/
public class SmartPlug extends Devices{
    /*
    * The variables which will be accesed and defined in getters and setters.
    * You can check the comments below.
    */
    private String name;
    private String status = "Off"; 
    private int voltage = 220;
    private double ampere;
    private double energy;
    private LocalDateTime switchTime;
    private LocalDateTime firstTime;
    private LocalDateTime secondTime;
    /**
    * Constructor for creating a new SmartPlug object.
    *
    * @param name The name of the smart plug.
    */
    public SmartPlug(String name) {
        this.name = name;
    }
    /**
    * Returns the name of the smart plug.
    *
    * @return The name of the smart plug.
    */
    public String getName() {
        return name;
    }
    /**
    * Sets the name of the smart plug.
    *
    * @param name The name of the smart plug.
    */
    public void setName(String name) {
        this.name = name;
    }
    /**
    * Returns the current status of the smart plug.
    *
    * @return The current status of the smart plug, which can be "On" or "Off".
    */
    public String getStatus() {
        return status;
    }
    /**
    * Sets the current status of the smart plug.
    *
    * @param status The current status of the smart plug, which can be "On" or "Off".
    */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
    * Returns the voltage of the smart plug.
    *
    * @return the voltage value, always 220V in this system.
    */
    public int getVoltage() {
        return voltage;
    }
    /**
    * Returns the current in amperes flowing through the smart plug.
    *
    * @return the current value in amperes.
    */
    public double getAmpere() {
        return ampere;
    }
    /**
    * Sets the current in amperes flowing through the smart plug.
    *
    * @param ampere the current value in amperes to be set.
    */
    public void setAmpere(double ampere) {
        this.ampere = ampere;
    }
    /**
    * Returns the total energy consumed by the smart plug.
    *
    * @return the total energy value in W.
    */
    public double getEnergy() {
        return energy;
    }
    /**
    *Sets the total energy consumed by the smart plug.
    *
    * @param energy the total energy value in W to be set.
    */
    public void setEnergy(double energy) {
        this.energy = energy;
    }
    /**
    * Returns the time at which the smart plug will be switched on or off.
    *
    * @return The time at which the smart plug will be switched on or off, stored as LocalDateTime.
    */
    public LocalDateTime getSwitchTime() {
        return switchTime;
    }
    /**
    * Sets the time at which the smart plug will be switched on or off.
    *
    * @param switchTime The time at which the smart plug will be switched on or off, stored as LocalDateTime.
    */ 
    public void setSwitchTime(LocalDateTime switchTime) {
        this.switchTime = switchTime;
    }
    /**
    * Returns the start time of a period of time during which energy usage is being measured.
    *
    * @return start time of energy usage measurement period.
    */ 
    public LocalDateTime getFirstTime() {
        return firstTime;
    }
    /**
    * Sets the start time of a period of time during which energy usage is being measured.
    *
    * @param firstTime start time of energy usage measurement period.
    */
    public void setFirstTime(LocalDateTime firstTime) {
        this.firstTime = firstTime;
    }
    /**
    * Returns the end time of a period of time during which energy usage is being measured.
    *
    * @return end time of energy usage measurement period.
    */
    public LocalDateTime getSecondTime() {
        return secondTime;
    }
    /**
    * Sets the end time of a period of time during which energy usage is being measured.
    *
    * @param secondTime end time of energy usage measurement period.
    */
    public void setSecondTime(LocalDateTime secondTime) {
        this.secondTime = secondTime;
    }
}
