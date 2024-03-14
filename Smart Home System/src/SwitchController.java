import java.time.LocalDateTime;
/**
* SwitchController class is responsible for controlling the switches of different devices based on their switch time.
* It implements the SwitchTime interface.
*/
public class SwitchController implements SwitchTime {
    protected Devices devices;
    /**
    * Constructor for SwitchController class.
    *
    * @param devices an instance of the Devices class to be assigned to this SwitchController object.
    */
    public SwitchController(Devices devices) {
        this.devices = new Devices();
    }
    /**
    * Sorts the switch time list of the devices in ascending order.
    *
    * @param devices the Devices object containing the list of devices and their switch times.
    */
    public void SwitchTimeSort(Devices devices){
        String[] switchList = devices.switchList;
        //Bubble Sort with Switch Time
        for(int i=0; i<switchList.length-1;i++){
            for(int j=0; j<switchList.length-1-i;j++){
                if (switchList[j]!=null && switchList[j+1]!=null){
                    LocalDateTime switch1 = LocalDateTime.parse(switchList[j].split("&")[1]);
                    LocalDateTime switch2 = LocalDateTime.parse(switchList[j+1].split("&")[1]);
                    //Comparing two switch times and swapping accordingly
                    int value = switch1.compareTo(switch2);
                    if (value>0){
                        String temp=switchList[j];
                        switchList[j]=switchList[j+1];
                        switchList[j+1]=temp;
                    }
                }
            }
        }
    }
    /**
    * Changes the position of devices with the same switch time in the list.
    *
    * @param devices the Devices object containing the list of devices and their switch times.
    */
    public void SwitchTimeInfoSort(Devices devices){
        String[] switchList = devices.switchList;
        //Iterating through the list
        for(int j=0; j<switchList.length;j++){
            if (switchList[j]!=null && switchList[j+1]!=null){
                if (switchList[j].split("&").length==3 && switchList[j+1].split("&").length==3){
                    LocalDateTime switch1 = LocalDateTime.parse(switchList[j].split("&")[1]);
                    LocalDateTime switch2 = LocalDateTime.parse(switchList[j+1].split("&")[1]);
                    int value = switch1.compareTo(switch2);
                    //Swapped if their switch times are same
                    if (value==0){
                        String temp=switchList[j]+"&marked";
                        switchList[j]=switchList[j+1]+"&marked";
                        switchList[j+1]=temp;
                    }
                }
            }
        }
    }
    /**
    * Changes the status of any device if its switch time is due.
    *
    * @param devices the Devices object containing the list of devices and their switch times.
    * @param time an instance of the Time class representing the current time.
    * @param outPath a string representing the path to the output file where the changes in device status will be recorded.
    */
    public void SwitchTimeDue(Devices devices, Time time, String outPath){
        String[] switchList = devices.switchList;
        for (int i=0; i<switchList.length-1;i++){
            if (switchList[i]!=null){
                String name = switchList[i].split("&")[0];
                String timeSTR = switchList[i].split("&")[1];
                String status = new String();
                if (switchList[i].split("&").length==2){
                    LocalDateTime switchTime = LocalDateTime.parse(timeSTR);
                    int value = time.getCurrentTime().compareTo(switchTime);
                    //If Switch Time is due
                    if (value>=0){
                        //Finding new status if it is a smart lamp
                        for (SmartLamp lamp:devices.lampList){
                            if (lamp!=null && lamp.getName().equals(name)){
                                String temp = lamp.getStatus();
                                if (temp.equals("On")){
                                    status="Off";
                                }
                                else if (temp.equals("Off")){
                                    status="On";
                                }
                            }
                            }
                        //Finding new status if it is a smart color lamp
                        for (SmartColorLamp color:devices.colorList){
                            if (color!=null && color.getName().equals(name)){
                                String temp = color.getStatus();
                                if (temp.equals("On")){
                                    status="Off";
                                }
                                else if (temp.equals("Off")){
                                    status="On";
                                }
                            }
                        }
                        //Finding new status if it is a smart plug
                        for (SmartPlug plug:devices.plugList){
                            if (plug!=null && plug.getName().equals(name)){
                                String temp = plug.getStatus();
                                if (temp.equals("On")){
                                    status="Off";
                                }
                                else if (temp.equals("Off")){
                                    status="On";
                                }
                            }
                        }
                        //Finding new status if it is a smart camera
                        for (SmartCamera camera:devices.cameraList){
                            if (camera!=null && camera.getName().equals(name)){
                                String temp = camera.getStatus();
                                if (temp.equals("On")){
                                    status="Off";
                                }
                                else if (temp.equals("Off")){
                                    status="On";
                                }
                            }
                        }
                        Main.ChangeStatus(devices, time,name, status, outPath, false);
                    }
                }
            }
        }
    }
}