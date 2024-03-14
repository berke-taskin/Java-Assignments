/**
* The SwitchTime interface defines methods for sorting and managing the switch time of smart devices.
*/
public interface SwitchTime {
    /**
    * Constructor for SwitchController class.
    *
    * @param devices an instance of the Devices class to be assigned to this SwitchController object.
    */
    public void SwitchTimeSort(Devices devices);
    /**
    * Changes the position of devices with the same switch time in the list.
    *
    * @param devices the Devices object containing the list of devices and their switch times.
    */
    public void SwitchTimeInfoSort(Devices devices);
    /**
    * Changes the status of any device if its switch time is due.
    *
    * @param devices the Devices object containing the list of devices and their switch times.
    * @param time an instance of the Time class representing the current time.
    * @param outPath a string representing the path to the output file where the changes in device status will be recorded.
    */
    public void SwitchTimeDue(Devices devices, Time time, String outPath);
}
