/**
*   The Devices class represents a collection of smart devices in the Smart Home System.
*   The Devices class contains arrays to store the names and switch times of each device. 
*   It also contains separate arrays for each type of device, including
*   SmartLamps, SmartPlugs, SmartCameras, and SmartColorLamps.
*/
public class Devices {
    /*
    * A string variable to store additional information about the device.
    */
    public String info;
    /*
    * An array of strings to store the names of each device in the system.
    */
    public String[] deviceNames = new String [100];
    /*
    * An array of SmartLamp objects to store information about each smart lamp in the system.
    */
    public SmartLamp[] lampList = new SmartLamp [100];
    /*
    * An array of SmartPlug objects to store information about each smart plug in the system.
    */
    public SmartPlug[] plugList = new SmartPlug [100];
    /*
    * An array of SmartCamera objects to store information about each smart camera in the system.
    */
    public SmartCamera[] cameraList = new SmartCamera [100];
    /*
     An array of SmartColorLamp objects to store information about each smart color lamp in the system.
    */
    public SmartColorLamp[] colorList = new SmartColorLamp [100];
    /*
    *An array of strings to store the switch times of each device in the system.
    */
    public String[] switchList = new String [100]; 
    /**
    * Gets the full information about the device.
    *
    * @return A string containing full information about the device.
    */
    public String getInfo() {
        return info;
    }
    /**
    * Sets the full information about the device.
    *
    * @param info A string containing full information about the device.
    */
    public void setInfo(String info) {
        this.info = info;
    }
}
