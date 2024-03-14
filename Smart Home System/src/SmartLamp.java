import java.time.LocalDateTime;
/*
* The SmartLamp class represents a smart lamp device that can be controlled remotely.
* It extends the Devices class and inherits the device-related properties.
* It stores information about the brightness, temperature (in kelvin), and switch time of the device.
*/
public class SmartLamp extends Devices {
    /*
    * The variables which will be accesed and defined in getters and setters.
    * You can check the comments below.
    */
    private String name;
    private String status = "Off";
    private int kelvin = 4000;
    private int brightness = 100;
    private LocalDateTime switchTime = null;
    /**
    * Constructor for creating a new SmartLamp object.
    *
    * @param name The name of the smart lamp.
    */
    public SmartLamp(String name) {
        this.name = name;
    }
    /**
    * Returns the name of the smart lamp.
    *
    * @return The name of the smart lamp.
    */
    public String getName() {
        return name;
    }
    /**
    * Returns the temperature of the light emitted by the smart lamp.
    *
    * @return The temperature of the light emitted by the smart lamp, measured in Kelvin.
    */
    public int getKelvin() {
        return kelvin;
    }
    /**
    * Returns the brightness of the light emitted by the smart lamp.
    *
    * @return The brightness of the light emitted by the smart lamp,
    * measured as a percentage of the maximum brightness.
    */
    public int getBrightness() {
        return brightness;
    }
    /**
    * Returns the current status of the smart lamp.
    *
    * @return The current status of the smart lamp, which can be "On" or "Off".
    */
    public String getStatus() {
        return status;
    }
    /**
    * Returns the time at which the smart lamp will be switched on or off.
    *
    * @return The time at which the smart lamp will be switched on or off, stored as LocalDateTime.
    */
    public LocalDateTime getSwitchTime() {
        return switchTime;
    }
    /**
    * Sets the name of the smart lamp.
    *
    * @param name The name of the smart lamp.
    */
    public void setName(String name) {
        this.name = name;
    }
    /**
    * Sets the color temperature of the light emitted by the smart lamp.
    *
    * @param kelvin The color temperature of the light emitted by the smart lamp, measured in Kelvin.
    */
    public void setKelvin(int kelvin) {
        this.kelvin = kelvin;
    }
    /**
    * Sets the brightness of the light emitted by the smart lamp.
    *
    * @param brightness The brightness of the light emitted by the smart lamp,
    * measured as a percentage of the maximum brightness.
    */
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }
    /**
    * Sets the current status of the smart lamp.
    *
    * @param status The current status of the smart lamp, which can be "On" or "Off".
    */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
    * Sets the time at which the smart lamp will be switched on or off.
    *
    * @param switchTime The time at which the smart lamp will be switched on or off, stored as LocalDateTime.
    */
    public void setSwitchTime(LocalDateTime switchTime) {
        this.switchTime = switchTime;
    }   
}
