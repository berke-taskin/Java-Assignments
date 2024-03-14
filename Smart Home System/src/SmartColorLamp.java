import java.time.LocalDateTime;
/*
* The SmartColorLamp class represents a smart color lamp device that can be controlled remotely.
* It extends the Devices class and inherits the device-related properties.
* It stores information about the brightness, temperature (in kelvin or color code),
* and switch time of the device.
*/
public class SmartColorLamp extends Devices{
    /*
    * The variables which will be accesed and defined in getters and setters.
    * You can check the comments below.
    */
    private String name;
    private String status = "Off";
    private int brightness = 100;
    private int kelvin = 4000;
    private String codeSTR;
    private String error = "false";
    private String current = "kelvin";
    private LocalDateTime switchTime = null;
    /**
    * Constructor for creating a new SmartColorLamp object.
    *
    * @param name the name of the color lamp.
    */
    public SmartColorLamp(String name) {
        this.name = name;
    }
    /**
    * Constructor for creating a new SmartColorLamp object.
    *
    * @param name the name of the color lamp.
    * @param status the status of the color lamp ("On" or "Off").
    */
    public SmartColorLamp(String name, String status) {
        this.name = name;
        if (status.equals("On") || status.equals("Off")){
            this.status = status;
        } else {
            // "ERROR: Erroneous command!"
            this.error="error1";
        }
    }
    /**
    * Constructor for creating a new SmartColorLamp object.
    *
    * @param name the name of the color lamp.
    * @param status the status of the color lamp ("On" or "Off").
    * @param kelvin the color temperature of the color lamp (in Kelvins).
    * @param brightness the brightness of the color lamp (in percentage, from 0 to 100).
    */
    public SmartColorLamp(String name, String status, int kelvin, int brightness) {
        this.name = name;
        if (status.equals("On") || status.equals("Off")){
            this.status = status;
        } else {
            // "ERROR: Erroneous command!"
            this.error="error1";
        }
        if (error.equals("false")){
            if (kelvin>=2000 && kelvin<=6500){
                this.kelvin = kelvin;
                this.current = "kelvin";
            } else {
                // "ERROR: Kelvin value must be in range of 2000K-6500K!"
                this.error="error2";
            }
        }
        if (error.equals("false")){
            if (brightness>=0 && brightness<=100){
                this.brightness = brightness;
            } else {
                // "ERROR: Brightness must be in range of 0%-100%!"
                this.error="error3";
            }
        }
    }
    /**
    * Constructor for creating a new SmartColorLamp object.
    *
    * @param name the name of the color lamp.
    * @param status the status of the color lamp ("On" or "Off").
    * @param code the color code of the color lamp (in hexadecimal format).
    * @param brightness the brightness of the color lamp (in percentage, from 0 to 100).
    */
    public SmartColorLamp(String name, String status, String code, int brightness) {
        this.name = name;
        if (status.equals("On") || status.equals("Off")){
            this.status = status;
        } else {
            // "ERROR: Erroneous command!"
            this.error="error1";
        }
        if (error.equals("false")){
            try {
                Long.decode(code);
                if (code.length()>8){
                    // "ERROR: Color code value must be in range of 0x0-0xFFFFFF!"
                    this.error="error4";
                }
                this.codeSTR = code;
                this.current = "code";
            } catch (Exception e){
                // "ERROR: Erroneous command!"
                this.error="error5";
            }
        }
        if (error.equals("false")){
            if (brightness>=0 && brightness<=100){
                this.brightness = brightness;
            } else {
                // "ERROR: Brightness must be in range of 0%-100%!"
                this.error="error3";
            }
        }
    }
    /**
    * Returns the name of the smart color lamp.
    *
    * @return The name of the smart color lamp.
    */
    public String getName() {
        return name;
    }
    /**
    * Returns the name of the smart color lamp.
    *
    * @return The name of the smart color lamp.
    */
    public void setName(String name) {
        this.name = name;
    }
    /**
    * Returns the current status of the smart color lamp.
    *
    * @return The current status of the smart color lamp, which can be "On" or "Off".
    */
    public String getStatus() {
        return status;
    }
    /**
    * Sets the current status of the smart color lamp.
    *
    * @param status The current status of the smart color lamp, which can be "On" or "Off".
    */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
    * Returns the brightness of the light emitted by the smart color lamp.
    *
    * @return The brightness of the light emitted by the smart color lamp, 
    * measured as a percentage of the maximum brightness.
    */
    public int getBrightness() {
        return brightness;
    }
    /**
    * Sets the brightness of the light emitted by the smart color lamp.
    *
    * @param brightness The brightness of the light emitted by the smart color lamp,
    * measured as a percentage of the maximum brightness.
    */
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }
    /**
    * Returns the color temperature of the light emitted by the smart color lamp.
    *
    * @return The color temperature of the light emitted by the smart color lamp, measured in Kelvin.
    */
    public int getKelvin() {
        return kelvin;
    }
    /**
    * Sets the color temperature of the light emitted by the smart color lamp.
    *
    * @param kelvin The color temperature of the light emitted by the smart color lamp, measured in Kelvin.
    */
    public void setKelvin(int kelvin) {
        this.kelvin = kelvin;
    }
    /**
    * Returns the color temperature of the light emitted by the smart color lamp.
    *
    * @return The color temperature of the light emitted by the smart color lamp, 
    * in string containing hexadecimal color code.
    */
    public String getCodeSTR() {
        return codeSTR;
    }
    /**
    * Sets the color temperature of the light emitted by the smart color lamp.
    *
    * @param codeSTR The color temperature of the light emitted by the smart color lamp, 
    * in string containing hexadecimal color code.
    */
    public void setCodeSTR(String codeSTR) {
        this.codeSTR = codeSTR;
    }
    /**
    * Returns the error status of the smart color lamp.
    *
    * @return The error status of the smart color lamp, which can be "true" or "false".
    */
    public String getError() {
        return error;
    }
    /**
    * Returns the string value of how temperature is stored, kelvin or color code.
    *
    * @return The string value of how temperature is stored, kelvin or color code.
    */
    public String getCurrent() {
        return current;
    }
    /**
    * Sets the string value of how temperature is stored, kelvin or color code.
    *
    * @param current The string value of how temperature is stored, kelvin or color code.
    */
    public void setCurrent(String current) {
        this.current = current;
    }
    /**
    * Returns the time at which the smart color lamp will be switched on or off.
    *
    * @return The time at which the smart color lamp will be switched on or off, stored as LocalDateTime.
    */
    public LocalDateTime getSwitchTime() {
        return switchTime;
    }
    /**
    * Sets the time at which the smart color lamp will be switched on or off.
    *
    * @param switchTime The time at which the smart color lamp will be switched on or off, stored as LocalDateTime.
    */
    public void setSwitchTime(LocalDateTime switchTime) {
        this.switchTime = switchTime;
    }
}
