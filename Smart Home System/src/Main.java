import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.text.DecimalFormat;
/*
* The Main class contains the main method and other different methods to process for the Smart Home System.
*/
public class Main {
    /**
    * Reads a file and returns its content as an array of strings.
    *
    * @param path the path of the file to read.
    * @param discardEmptyLines if true, empty lines will be removed from the array.
    * @param trim if true, leading and trailing whitespaces will be removed from each line of the array.
    * @return an array of strings containing the content of the reading file.
    */
        public static String[] readFile(String path, boolean discardEmptyLines, boolean trim) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            if (discardEmptyLines) {
                lines.removeIf(line -> line.trim().equals(""));
            }
            if (trim) {
                lines.replaceAll(String::trim);
            }
            return lines.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
    * Writes a string to a file.
    *
    * @param path the path of the file to write.
    * @param content the content to write to the file.
    * @param append if true, the content will be appended to the end of the file.
    * @param newLine if true, a newline character will be added at the end of the content.
    */
    public static void writeFile(String path, String content, boolean append, boolean newLine) {
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(path, append));
            ps.print(content + (newLine ? "\n" : ""));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.flush();
                ps.close();
            }
        }
    }
    /**
    * Handles error for a Smart Lamp by setting the element at the given index 
    * in the given arrays of Smart Lamps and Device Names to null.
    *
    * @param list the array of Smart Lamps.
    * @param names the array of device names.
    * @param index the index of the Smart Lamp that has the error.
    * @param nameIndex the index of the name of the device that has the error.
    */
    public static void SmartLampError(SmartLamp[] list, String[] names, int index, int nameIndex){
        list[index] = null;
        names[nameIndex] = null;
    }
    /**
    * Handles error for a Smart Plug by setting the element at the given index 
    * in the given arrays of Smart Plugs and Device Names to null.
    *
    * @param list the array of Smart Plugs.
    * @param names the array of device names.
    * @param index the index of the Smart Plug that has the error.
    * @param nameIndex the index of the name of the device that has the error.
    */
    public static void SmartPlugError(SmartPlug[] list, String[] names, int index, int nameIndex){
        list[index] = null;
        names[nameIndex] = null;
    }
    /**
    * Handles error for a Smart Camera by setting the element at the given index 
    * in the given arrays of Smart Cameras and Device Names to null.
    *
    * @param list the array of Smart Cameras.
    * @param names the array of device names.
    * @param index the index of the Smart Camera that has the error.
    * @param nameIndex the index of the name of the device that has the error.
    */
    public static void SmartCameraError(SmartCamera[] list, String[] names, int index, int nameIndex){
        list[index] = null;
        names[nameIndex] = null;
    }
    /**
    * Handles error for a Smart Color Lamp by setting the element at the given index 
    * in the given arrays of Smart Color Lamps and Device Names to null.
    *
    * @param list the array of Smart Color Lamps.
    * @param names the array of device names.
    * @param index the index of the Smart Color Lamp that has the error.
    * @param nameIndex the index of the name of the device that has the error.
    */
    public static void SmartColorLampError(SmartColorLamp[] list, String[] names, int index, int nameIndex){
        list[index] = null;
        names[nameIndex] = null;
    }
    /**
    * Removes a device with the given name from the list of devices, updates its status and prints the information accordingly.
    *
    * @param devices the Devices object containing the lists of all devices.
    * @param time the Time object containing the time values.
    * @param name the name of the device to be removed.
    * @param names the array of device names.
    * @param outPath the path of the output file to write the device information to.
    */
    public static void RemoveDevice(Devices devices, Time time, String name, String[] names, String outPath){
        //Removing Smart Lamp
        for (int i=0; i<devices.lampList.length; i++){
            if (devices.lampList[i]!=null){
                SmartLamp lamp = devices.lampList[i];
                if (lamp.getName().equals(name)){
                    ChangeStatus(devices, time, name, "Off", outPath, true);
                    Info(devices, name, outPath);
                    devices.lampList[i] = null;
                    for (int j=0; j<names.length; j++){
                        if (names[j]!=null){
                            if (names[j].equals(name)){
                                names[j] = null;
                            }
                        }
                    }
                }
            }
        }
        //Removing Smart Color Lamp
        for (int i=0; i<devices.colorList.length; i++){
            if (devices.colorList[i]!=null){
                SmartColorLamp colorlamp = devices.colorList[i];
                if (colorlamp.getName().equals(name)){
                    ChangeStatus(devices, time, name, "Off", outPath, true);
                    Info(devices, name, outPath);
                    devices.colorList[i] = null;
                    for (int j=0; j<names.length; j++){
                        if (names[j]!=null){
                            if (names[j].equals(name)){
                                names[j] = null;
                            }
                        }
                    }
                }
            }
        }
        //Removing Smart Plug
        for (int i=0; i<devices.plugList.length; i++){
            if (devices.plugList[i]!=null){
                SmartPlug plug = devices.plugList[i];
                if (plug.getName().equals(name)){
                    ChangeStatus(devices, time, name, "Off", outPath, true);
                    Info(devices, name, outPath);
                    devices.plugList[i] = null;
                    for (int j=0; j<names.length; j++){
                        if (names[j]!=null){
                            if (names[j].equals(name)){
                                names[j] = null;
                            }
                        }
                    }
                }
            }
        }
        //Removing Smart Camera
        for (int i=0; i<devices.cameraList.length; i++){
            if (devices.cameraList[i]!=null){
                SmartCamera camera = devices.cameraList[i];
                if (camera.getName().equals(name)){
                    ChangeStatus(devices, time, name, "Off", outPath, true);
                    Info(devices, name, outPath);
                    devices.cameraList[i] = null;
                    for (int j=0; j<names.length; j++){
                        if (names[j]!=null){
                            if (names[j].equals(name)){
                                names[j] = null;
                            }
                        }
                    }
                }
            }
        }
    }
    /**
    * Changes the name of a specified device in the list of devices, and updates the name in the list of device names.
    *
    * @param devices the Devices object containing the lists of all devices.
    * @param name the current name of the device to be renamed.
    * @param names the list of all device names.
    * @param New the new name to be given to the device.
    */
    public static void ChangeName(Devices devices, String name, String[] names, String New){
        //Changing Smart Lamp Name
        for (int i=0; i<devices.lampList.length; i++){
            if (devices.lampList[i]!=null){
                SmartLamp lamp = devices.lampList[i];
                if (lamp.getName().equals(name)){
                    devices.lampList[i].setName(New);
                    for (int j=0; j<names.length; j++){ 
                        if (names[j]!=null){
                            if (names[j].equals(name)){
                                names[j] = New;
                            }
                        }
                    }
                }
            }
        }
        //Changing Smart Color Lamp Name
        for (int i=0; i<devices.colorList.length; i++){
            if (devices.colorList[i]!=null){
                SmartColorLamp colorlamp = devices.colorList[i];
                if (colorlamp.getName().equals(name)){
                    devices.colorList[i].setName(New);
                    for (int j=0; j<names.length; j++){
                        if (names[j]!=null){
                            if (names[j].equals(name)){
                                names[j] = New;
                            }
                        }
                    }
                }
            }
        }
        //Changing Smart Plug Name
        for (int i=0; i<devices.plugList.length; i++){
            if (devices.plugList[i]!=null){
                SmartPlug plug = devices.plugList[i];
                if (plug.getName().equals(name)){
                    devices.plugList[i].setName(New);
                    for (int j=0; j<names.length; j++){
                        if (names[j]!=null){
                            if (names[j].equals(name)){
                                names[j] = New;
                            }
                        }
                    }
                }
            }
        }
        //Changing Smart Camera Name
        for (int i=0; i<devices.cameraList.length; i++){
            if (devices.cameraList[i]!=null){
                SmartCamera camera = devices.cameraList[i];
                if (camera.getName().equals(name)){
                    devices.cameraList[i].setName(New);
                    for (int j=0; j<names.length; j++){
                        if (names[j]!=null){
                            if (names[j].equals(name)){
                                names[j] = New;
                            }
                        }
                    }
                }
            }
        }
    }
    /**
    * Changes the status of a specified device in the list of devices.
    *
    * @param devices the Devices object containing the lists of all devices.
    * @param time the Time object containing the time values.
    * @param name the name of the device which will have status change.
    * @param status the supposed new status of the device after change.
    * @param outPath the path of the output file to write the correct output to.
    * @param boolean if the reason of status change is due to device removal.
    */
    public static void ChangeStatus(Devices devices, Time time, String name, String status, String outPath, boolean removed){
        //Changing Smart Lamp Status
        for (int i=0; i<devices.lampList.length; i++){
            if (devices.lampList[i]!=null){
                SmartLamp lamp = devices.lampList[i];
                if (lamp.getName().equals(name)){
                    String old = devices.lampList[i].getStatus();
                    if (old.equals("On")){
                        //If device is already on
                        if (status.equals("On")){
                            writeFile(outPath,"ERROR: This device is already switched on!", true,true);
                        }
                        //If everything is correct
                        else if (status.equals("Off")){
                            devices.lampList[i].setStatus(status);
                            devices.lampList[i].setSwitchTime(null);
                        } else {
                            writeFile(outPath,"ERROR: Erroneous command!", true,true);
                        }
                    }   
                    else if (old.equals("Off")){
                        //If device is already off and not removed
                        if (status.equals("Off") && removed==false){
                            writeFile(outPath,"ERROR: This device is already switched off!", true,true);
                        }
                        //If everything is correct
                        else if (status.equals("On")){
                            devices.lampList[i].setStatus(status);
                            devices.lampList[i].setSwitchTime(null);
                        } else if (removed==false) {
                            writeFile(outPath,"ERROR: Erroneous command!", true,true);
                        }
                    }   
                }
            }
        }
        //Changing Smart Color Lamp Status
        for (int i=0; i<devices.colorList.length; i++){
            if (devices.colorList[i]!=null){
                SmartColorLamp colorlamp = devices.colorList[i];
                if (colorlamp.getName().equals(name)){
                    String old = devices.colorList[i].getStatus();
                    if (old.equals("On")){
                        //If device is already on
                        if (status.equals("On")){
                            writeFile(outPath,"ERROR: This device is already switched on!", true,true);
                        }
                        //If everything is correct
                        else if (status.equals("Off")){
                            devices.colorList[i].setStatus(status);
                            devices.colorList[i].setSwitchTime(null);
                        } else {
                            writeFile(outPath,"ERROR: Erroneous command!", true,true);
                        }
                    }   
                    else if (old.equals("Off")){
                        //If device is already off and not removed
                        if (status.equals("Off") && removed==false){
                            writeFile(outPath,"ERROR: This device is already switched off!", true,true);
                        }
                        //If everything is correct
                        else if (status.equals("On")){
                            devices.colorList[i].setStatus(status);
                            devices.colorList[i].setSwitchTime(null);
                        } else if (removed==false){
                            writeFile(outPath,"ERROR: Erroneous command!", true,true);
                        }
                    }   
                }
            }
        }
        //Changing Smart Plug Status
        for (int i=0; i<devices.plugList.length; i++){
            if (devices.plugList[i]!=null){
                SmartPlug plug = devices.plugList[i];
                if (plug.getName().equals(name)){
                    String old = devices.plugList[i].getStatus();
                    if (old.equals("On")){
                        //If device is already on
                        if (status.equals("On")){
                            writeFile(outPath,"ERROR: This device is already switched on!", true,true);
                        }
                        //If everything is correct
                        else if (status.equals("Off")){
                            devices.plugList[i].setSecondTime(time.getCurrentTime());
                            Duration duration = Duration.between(devices.plugList[i].getFirstTime(), devices.plugList[i].getSecondTime());
                            double diff = duration.toMinutes();
                            devices.plugList[i].setEnergy(devices.plugList[i].getEnergy()+(devices.plugList[i].getVoltage()*devices.plugList[i].getAmpere()*(diff/60)));
                            devices.plugList[i].setStatus(status);
                            devices.plugList[i].setSwitchTime(null);
                        } else {
                            writeFile(outPath,"ERROR: Erroneous command!", true,true);
                        }
                    }   
                    else if (old.equals("Off")){
                        //If device is already off and not removed
                        if (status.equals("Off") && removed==false){
                            writeFile(outPath,"ERROR: This device is already switched off!", true,true);
                        }
                        //If everything is correct
                        else if (status.equals("On")){
                            devices.plugList[i].setFirstTime(time.getCurrentTime());
                            devices.plugList[i].setStatus(status);
                            devices.plugList[i].setSwitchTime(null);
                        } else if (removed==false) {
                            writeFile(outPath,"ERROR: Erroneous command!", true,true);
                        }
                    }   
                }
            }
        }
        //Changing Smart Camera Status
        for (int i=0; i<devices.cameraList.length; i++){
            if (devices.cameraList[i]!=null){
                SmartCamera camera = devices.cameraList[i];
                if (camera.getName().equals(name)){
                    String old = devices.cameraList[i].getStatus();
                    if (old.equals("On")){
                        //If device is already on
                        if (status.equals("On")){
                            writeFile(outPath,"ERROR: This device is already switched on!", true,true);
                        }
                        //If everything is correct
                        else if (status.equals("Off")){
                            devices.cameraList[i].setSecondTime(time.getCurrentTime());
                            Duration duration = Duration.between(devices.cameraList[i].getFirstTime(), devices.cameraList[i].getSecondTime());
                            double diff = duration.toMinutes();
                            devices.cameraList[i].setMb(devices.cameraList[i].getMb()+devices.cameraList[i].getMbpm()*diff);
                            devices.cameraList[i].setStatus(status);
                            devices.cameraList[i].setSwitchTime(null);
                        } else {
                            writeFile(outPath,"ERROR: Erroneous command!", true,true);
                        }
                    }   
                    else if (old.equals("Off")){
                        //If device is already off and not removed
                        if (status.equals("Off") && removed==false){
                            writeFile(outPath,"ERROR: This device is already switched off!", true,true);
                        }
                        //If everything is correct
                        else if (status.equals("On")){
                            devices.cameraList[i].setFirstTime(time.getCurrentTime());
                            devices.cameraList[i].setStatus(status);
                            devices.cameraList[i].setSwitchTime(null);
                        } else if (removed==false){
                            writeFile(outPath,"ERROR: Erroneous command!", true,true);
                        }
                    }   
                }
            }
        }
        //Removing switch time value after status change
        for (int i=0; i<devices.switchList.length;i++){
            if (devices.switchList[i]!=null && name.equals(devices.switchList[i].split("&")[0])){
                devices.switchList[i]=devices.switchList[i]+"&changed";
            }
        }
    }
    /**
    * Searches for the device with specified name in the Devices object and retrieves its information.
    * The retrieved information is a string and will be written to the output file at the specified path.
    *
    * @param devices the Devices object containing the lists of all devices.
    * @param name the name of the device to retrieve the information for.
    * @param outPath the path of the output file to write the device information to.
    */
    public static void Info(Devices devices, String name, String outPath){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
        DecimalFormat df = new DecimalFormat("0.00");
        //For Smart Lamp
        for (int i=0; i<devices.lampList.length; i++){
            if (devices.lampList[i]!=null && devices.lampList[i].getName().equals(name)){
                SmartLamp lamp = devices.lampList[i];
                String switchTime = null;
                //If there is switch time value it is formatted
                if (lamp.getSwitchTime()!=null){
                    switchTime = lamp.getSwitchTime().format(dateTimeFormatter);
                }
                devices.lampList[i].setInfo("Smart Lamp "+name+" is "+lamp.getStatus().toLowerCase()+" and its kelvin value is "+lamp.getKelvin()
                +"K with "+lamp.getBrightness()+"% brightness, and its time to switch its status is "+switchTime+".");
                writeFile(outPath,devices.lampList[i].getInfo(), true,true);
            }
        }
        //For Smart Color Lamp
        for (int i=0; i<devices.colorList.length; i++){
            if (devices.colorList[i]!=null && devices.colorList[i].getName().equals(name)){
                SmartColorLamp colorlamp = devices.colorList[i];
                String switchTime = null;
                //If there is switch time value it is formatted
                if (colorlamp.getSwitchTime()!=null){
                    switchTime = colorlamp.getSwitchTime().format(dateTimeFormatter);
                }
                //If Kelvin
                if (colorlamp.getCurrent().equals("kelvin")){
                    devices.colorList[i].setInfo("Smart Color Lamp "+name+" is "+colorlamp.getStatus().toLowerCase()+" and its color value is "+colorlamp.getKelvin()
                +"K with "+colorlamp.getBrightness()+"% brightness, and its time to switch its status is "+switchTime+".");
                }
                //If Color Code
                if (colorlamp.getCurrent().equals("code")){
                    devices.colorList[i].setInfo("Smart Color Lamp "+name+" is "+colorlamp.getStatus().toLowerCase()+" and its color value is "+colorlamp.getCodeSTR()
                +" with "+colorlamp.getBrightness()+"% brightness, and its time to switch its status is "+switchTime+".");
                }
                writeFile(outPath,devices.colorList[i].getInfo(), true,true);
            }
        }  
        //For Smart Plug
        for (int i=0; i<devices.plugList.length; i++){
            if (devices.plugList[i]!=null && devices.plugList[i].getName().equals(name)){
                SmartPlug plug = devices.plugList[i];
                String switchTime = null;
                //If there is switch time value it is formatted
                if (plug.getSwitchTime()!=null){
                    switchTime = plug.getSwitchTime().format(dateTimeFormatter);
                }
                devices.plugList[i].setInfo("Smart Plug "+name+" is "+ plug.getStatus().toLowerCase()+" and consumed "+df.format(plug.getEnergy())
                +"W so far (excluding current device), and its time to switch its status is "+switchTime+".");
                writeFile(outPath,devices.plugList[i].getInfo(), true,true);
            }
        }     
        //For Smart Camera
        for (int i=0; i<devices.cameraList.length; i++){
            if (devices.cameraList[i]!=null && devices.cameraList[i].getName().equals(name)){
                SmartCamera camera = devices.cameraList[i];
                String switchTime = null;
                //If there is switch time value it is formatted
                if (camera.getSwitchTime()!=null){
                    switchTime = camera.getSwitchTime().format(dateTimeFormatter);
                }
                devices.cameraList[i].setInfo("Smart Camera "+name+" is "+ camera.getStatus().toLowerCase()+" and used "+df.format(camera.getMb())
                +" MB of storage so far (excluding current status), and its time to switch its status is "+switchTime+".");
                writeFile(outPath,devices.cameraList[i].getInfo(), true,true);            
            }
        }  
    } 
    /**
    * Writes the correct ZReport of devices to the output file at the specified path.
    * ZReport is a report which has the current time value, and the information of all devices in the correct order.
    * Correct order of this ZReport is the devices with switch times, the devices that changed their status with switch time,
    * and the devices without any switch time.
    *
    * @param switchController the SwitchController object containing certain methods which will be used.
    * @param devices the Devices object containing the lists of all devices.
    * @param time the Time object containing the time values.
    * @param outPath the path of the output file to write the device information to.
    */
    public static void ZReport(SwitchController switchController, Devices devices, Time time, String outPath){
        //LocalDateTime is formatted to write the correct output
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
        writeFile(outPath,"Time is:\t"+time.getCurrentTime().format(dateTimeFormatter), true,true);
        String[] switchList = devices.switchList;
        String[] nameList = devices.deviceNames;
        //First the devices with switch times
        for (int i=0; i<switchList.length;i++){
            //If Smart Lamp
            for (int j=0; j<devices.lampList.length;j++){ 
                if (switchList[i]!=null && devices.lampList[j]!=null){
                    SmartLamp lamp = devices.lampList[j];
                    String name = switchList[i].split("&")[0];
                    if (lamp.getSwitchTime()!=null && name.equals(lamp.getName())){
                        Info(devices, name, outPath);
                    }
                }
            }
            //If Smart Color Lamp
            for (int j=0; j<devices.colorList.length;j++){ 
                if (switchList[i]!=null && devices.colorList[j]!=null){
                    SmartColorLamp color = devices.colorList[j];
                    String name = switchList[i].split("&")[0];
                    if (color.getSwitchTime()!=null && name.equals(color.getName())){
                        Info(devices, name, outPath);
                    }
                }
            }
            //If Smart Plug
            for (int j=0; j<devices.plugList.length;j++){ 
                if (switchList[i]!=null && devices.plugList[j]!=null){
                    SmartPlug plug = devices.plugList[j];
                    String name = switchList[i].split("&")[0];
                    if (plug.getSwitchTime()!=null && name.equals(plug.getName())){
                        Info(devices, name, outPath);
                    }
                }
            }
            //If Smart Camera
            for (int j=0; j<devices.cameraList.length;j++){ 
                if (switchList[i]!=null && devices.cameraList[j]!=null){
                    SmartCamera camera = devices.cameraList[j];
                    String name = switchList[i].split("&")[0];
                    if (camera.getSwitchTime()!=null && name.equals(camera.getName())){
                        Info(devices, name, outPath);
                    }
                }
            }
        }
        //Second the devices that changed status with switch time
        switchController.SwitchTimeInfoSort(devices);
        for (int i=switchList.length-1; i>=0;i--){
            //If Smart Lamp
            for (int j=0; j<devices.lampList.length;j++){ 
                if (switchList[i]!=null && devices.lampList[j]!=null){
                    SmartLamp lamp = devices.lampList[j];
                    String name = switchList[i].split("&")[0];
                    if (switchList[i].split("&").length>=3 && name.equals(lamp.getName())){
                        Info(devices, name, outPath);
                    }
                }
            }
            //If Smart Color Lamp
            for (int j=0; j<devices.colorList.length;j++){ 
                if (switchList[i]!=null && devices.colorList[j]!=null){
                    SmartColorLamp color = devices.colorList[j];
                    String name = switchList[i].split("&")[0];
                    if (switchList[i].split("&").length>=3 && name.equals(color.getName())){
                        Info(devices, name, outPath);
                    }
                }
            }
            //If Smart Plug
            for (int j=0; j<devices.plugList.length;j++){ 
                if (switchList[i]!=null && devices.plugList[j]!=null){
                    SmartPlug plug = devices.plugList[j];
                    String name = switchList[i].split("&")[0];
                    if (switchList[i].split("&").length>=3 && name.equals(plug.getName())){
                        Info(devices, name, outPath);
                    }
                }
            }
            //If Smart Camera
            for (int j=0; j<devices.cameraList.length;j++){ 
                if (switchList[i]!=null && devices.cameraList[j]!=null){
                    SmartCamera camera = devices.cameraList[j];
                    String name = switchList[i].split("&")[0];
                    if (switchList[i].split("&").length>=3 && name.equals(camera.getName())){
                        Info(devices, name, outPath);
                    }
                }
            }
        }
        //Third the devices without any switch time
        for (int i=0; i<nameList.length;i++){
            boolean error=false;
            for (int j=0; j<switchList.length;j++){
                if (switchList[j]!=null && nameList[i]!=null){
                    if (switchList[j].split("&")[0].equals(nameList[i])){
                        error=true;
                    }
                }
            }
            if (error==false){
                //If Smart Lamp
                for (int j=0; j<devices.lampList.length;j++){ 
                    if (nameList[i]!=null && devices.lampList[j]!=null){
                        SmartLamp lamp = devices.lampList[j];
                        String name = nameList[i];
                        if (name.equals(lamp.getName())){
                            Info(devices, name, outPath);
                        }
                    }
                }
                //If Smart Color Lamp
                for (int j=0; j<devices.colorList.length;j++){ 
                    if (nameList[i]!=null && devices.colorList[j]!=null){
                        SmartColorLamp color = devices.colorList[j];
                        String name = nameList[i];
                        if (name.equals(color.getName())){
                            Info(devices, name, outPath);
                        }
                    }
                }
                //If Smart Plug
                for (int j=0; j<devices.plugList.length;j++){ 
                    if (nameList[i]!=null && devices.plugList[j]!=null){
                        SmartPlug plug = devices.plugList[j];
                        String name = nameList[i];
                        if (name.equals(plug.getName())){
                            Info(devices, name, outPath);
                        }
                    }
                }
                //If Smart Camera
                for (int j=0; j<devices.cameraList.length;j++){ 
                    if (nameList[i]!=null && devices.cameraList[j]!=null){
                        SmartCamera camera = devices.cameraList[j];
                        String name = nameList[i];
                        if (name.equals(camera.getName())){
                            Info(devices, name, outPath);
                        }
                    }
                }
            }
        }
    }
    public static void main (String[] args){
        //args[0] should be input.txt and args[1] should be output.txt
        String[] lines = readFile(args[0],true,true);
        String outPath = args[1];
        //Index and boolean variables are created which will be used later
        int lampIndex = 0;
        int plugIndex = 0;
        int cameraIndex = 0;
        int colorIndex = 0;
        int nameIndex = 0;
        int switchIndex = 0;
        boolean error = false;
        //Objects are created which will be used later
        Devices devices = new Devices();
        SwitchController SwitchController = new SwitchController(devices);
        Time time = new Time();
        writeFile(outPath,"",false,false);
        for (String line : lines){
            error = false;
            writeFile(outPath,"COMMAND: "+line, true,true);
            String[] split = line.split("\t");
            //SET INITIAL TIME
            if (line == lines[0]){
                //If the first command is not "SetInitialTime"
                if (!split[0].equals("SetInitialTime")){
                    writeFile(outPath,"ERROR: First command must be set initial time! Program is going to terminate!", true,true);
                    System.exit(1);
                }
                //Setting initial time
                else if (split[0].equals("SetInitialTime")){
                    if (split.length==2){
                        try {
                            String replaced = split[1].replace("_","T");
                            time.setCurrentTime(LocalDateTime.parse(replaced));;
                            writeFile(outPath,"SUCCESS: Time has been set to "+split[1]+"!", true,true);
                        } catch (Exception e){
                            writeFile(outPath,"ERROR: Format of the initial date is wrong! Program is going to terminate!", true,true);
                            System.exit(1);
                        }
                    } else {
                        writeFile(outPath,"ERROR: First command must be set initial time! Program is going to terminate!", true,true);
                        System.exit(1);
                    }
                }
            }
            //ADD SMART LAMP
            else if (split[0].equals("Add") && split[1].equals("SmartLamp")){
                SmartLamp SmartLamp = new SmartLamp(split[2]);
                if (split.length>6){
                    error = true;
                    writeFile(outPath,"ERROR: Erroneous command!", true,true);
                }
                for (String name : devices.deviceNames){
                    if (name!=null && name.equals(SmartLamp.getName())){
                        writeFile(outPath,"ERROR: There is already a smart device with same name!", true,true);
                        error = true;
                        break;
                    }
                }
                //If there is no device with same name
                if (error==false){
                    devices.lampList[lampIndex] = SmartLamp;
                    devices.deviceNames[nameIndex] = SmartLamp.getName();
                    if (split.length>3 && error==false){
                        //Status
                        if (split[3].equals("On") || split[3].equals("Off")){
                            SmartLamp.setStatus(split[3]);
                        } else {
                            SmartLampError(devices.lampList, devices.deviceNames, lampIndex, nameIndex);
                            error = true;
                            writeFile(outPath,"ERROR: Erroneous command!", true,true);
                        }
                    }
                    if (split.length>4 && error==false){
                        if (split.length==5){
                            SmartLampError(devices.lampList, devices.deviceNames, lampIndex, nameIndex);
                            error = true;
                            writeFile(outPath,"ERROR: Erroneous command!", true,true);
                        } else {
                            //Kelvin and Brightness
                            int kelvin = Integer.parseInt(split[4]);
                            int brightness = Integer.parseInt(split[5]);
                            if (kelvin>=2000 && kelvin<=6500){
                                SmartLamp.setKelvin(kelvin);
                            }
                            else if (kelvin<2000 || kelvin>6500){
                                SmartLampError(devices.lampList, devices.deviceNames, lampIndex, nameIndex);
                                error = true;
                                writeFile(outPath,"ERROR: Kelvin value must be in range of 2000K-6500K!", true,true);;
                            }
                            if (brightness>=0 && brightness<=100 && error==false){
                                SmartLamp.setBrightness(brightness);
                            }
                            else if ((brightness < 0 || brightness > 100) && error==false){
                                SmartLampError(devices.lampList, devices.deviceNames, lampIndex, nameIndex);
                                error = true;
                                writeFile(outPath,"ERROR: Brightness must be in range of 0%-100%!", true,true);;
                            }
                        }
                    }
                    if (error==false){
                    lampIndex += 1;
                    nameIndex += 1;
                    }
                }
            }
            //ADD SMART PLUG
            else if (split[0].equals("Add") && split[1].equals("SmartPlug")){
                SmartPlug SmartPlug = new SmartPlug(split[2]);
                if (split.length>5){
                    error = true;
                    writeFile(outPath,"ERROR: Erroneous command!", true,true);
                }
                for (String name : devices.deviceNames){
                    if (name!=null && name.equals(SmartPlug.getName())){
                        writeFile(outPath,"ERROR: There is already a smart device with same name!", true,true);
                        error = true;
                        break;
                    }
                }
                //If there is no device with same name
                if (error==false){
                    devices.plugList[plugIndex] = SmartPlug;
                    devices.deviceNames[nameIndex] = SmartPlug.getName();
                    if (split.length>3 && error==false){
                        //Status
                        if (split[3].equals("On") || split[3].equals("Off")){
                            SmartPlug.setStatus(split[3]);
                        } else {
                            SmartPlugError(devices.plugList, devices.deviceNames, plugIndex, nameIndex);
                            error = true;
                            writeFile(outPath,"ERROR: Erroneous command!", true,true);
                        }
                    }
                    if (split.length>4 && error==false){
                        //Ampere
                        double ampere = Double.parseDouble(split[4]);
                        if (ampere>0){
                            if (SmartPlug.getStatus().equals("On")){
                                SmartPlug.setFirstTime(time.getCurrentTime());
                            }
                            SmartPlug.setAmpere(ampere);
                        }
                        else if (ampere<=0){
                            SmartPlugError(devices.plugList, devices.deviceNames, plugIndex, nameIndex);
                            error = true;
                            writeFile(outPath,"ERROR: Ampere value must be a positive number!", true,true);;
                        }
                    }
                    if (error==false){
                    plugIndex += 1;
                    nameIndex += 1;
                    }
                }
            }
            //ADD SMART CAMERA
            else if (split[0].equals("Add") && split[1].equals("SmartCamera")){
                SmartCamera SmartCamera = new SmartCamera(split[2]);
                if (split.length<4 || split.length>5){
                    error = true;
                    writeFile(outPath,"ERROR: Erroneous command!", true,true);
                }
                for (String name : devices.deviceNames){
                    if (name!=null && name.equals(SmartCamera.getName()) && error==false){
                        writeFile(outPath,"ERROR: There is already a smart device with same name!", true,true);
                        error = true;
                        break;
                    }
                }
                //If there is no device with same name
                if (error==false){
                    devices.cameraList[cameraIndex] = SmartCamera;
                    devices.deviceNames[nameIndex] = SmartCamera.getName();
                    double mbpm = Double.parseDouble(split[3]);
                    //Megabyte per minute
                    if (mbpm>0){
                        SmartCamera.setMbpm(mbpm);
                        if (split.length>4 && error==false){
                            //Status
                            if (split[4].equals("On") || split[4].equals("Off")){
                                SmartCamera.setStatus(split[4]);
                                if (split[4].equals("On")){
                                SmartCamera.setFirstTime(time.getCurrentTime());
                                }
                            } else {
                                SmartCameraError(devices.cameraList, devices.deviceNames, cameraIndex, nameIndex);
                                error = true;
                                writeFile(outPath,"ERROR: Erroneous command!", true,true);
                            }
                        }
                    } else {
                        SmartCameraError(devices.cameraList, devices.deviceNames, cameraIndex, nameIndex);
                        error = true;
                        writeFile(outPath,"ERROR: Megabyte value must be a positive number!", true,true);;
                    }
                    if (error==false){
                    cameraIndex += 1;
                    nameIndex += 1;
                    }
                }
            }
            //ADD SMART COLOR LAMP
            else if (split[0].equals("Add") && split[1].equals("SmartColorLamp")){
                if (split.length<3 || split.length==5 || split.length>6){
                    error = true;
                    writeFile(outPath,"ERROR: Erroneous command!", true,true);
                }
                for (String name : devices.deviceNames){
                    if (name!=null && name.equals(split[2]) && error==false){
                        writeFile(outPath,"ERROR: There is already a smart device with same name!", true,true);
                        error = true;
                        break;
                    }
                }
                //If there is no device with same name
                if (error==false){
                    //Only name
                    if (split.length==3){
                        SmartColorLamp SmartColorLamp = new SmartColorLamp(split[2]);
                        devices.colorList[colorIndex] = SmartColorLamp;
                        devices.deviceNames[nameIndex] = SmartColorLamp.getName();
                    }
                    //Name and Status
                    if (split.length==4){
                        SmartColorLamp SmartColorLamp = new SmartColorLamp(split[2], split[3]);
                        if (SmartColorLamp.getError().equals("false")){
                            devices.colorList[colorIndex] = SmartColorLamp;
                            devices.deviceNames[nameIndex] = SmartColorLamp.getName();
                        }
                        else if (SmartColorLamp.getError().equals("error1")){
                            SmartColorLampError(devices.colorList, devices.deviceNames, colorIndex, nameIndex);
                            error = true;
                            writeFile(outPath,"ERROR: Erroneous command!", true,true);
                        }
                    }
                    //Name, Status, Kelvin/ColorCode and Brightness
                    if (split.length==6){
                        try{
                            //Kelvin
                            SmartColorLamp SmartColorLamp = new SmartColorLamp(split[2], split[3], Integer.parseInt(split[4]), Integer.parseInt(split[5]));
                            if (SmartColorLamp.getError().equals("false")){
                                devices.colorList[colorIndex] = SmartColorLamp;
                                devices.deviceNames[nameIndex] = SmartColorLamp.getName();
                            }
                            else if (SmartColorLamp.getError().equals("error2")){
                                SmartColorLampError(devices.colorList, devices.deviceNames, colorIndex, nameIndex);
                                error = true;
                                writeFile(outPath,"ERROR: Kelvin value must be in range of 2000K-6500K!", true,true);
                            }
                            else if (SmartColorLamp.getError().equals("error3")){
                                SmartColorLampError(devices.colorList, devices.deviceNames, colorIndex, nameIndex);
                                error = true;
                                writeFile(outPath,"ERROR: Brightness must be in range of 0%-100%!", true,true);
                            }
                        } catch (Exception e) {
                            if (split[4].charAt(1)=='x'){
                                //Color Code
                                SmartColorLamp SmartColorLamp = new SmartColorLamp(split[2], split[3], split[4], Integer.parseInt(split[5]));
                                if (SmartColorLamp.getError().equals("false")){
                                    devices.colorList[colorIndex] = SmartColorLamp;
                                    devices.deviceNames[nameIndex] = SmartColorLamp.getName();
                                }
                                else if (SmartColorLamp.getError().equals("error4")){
                                    SmartColorLampError(devices.colorList, devices.deviceNames, colorIndex, nameIndex);
                                    error = true;
                                    writeFile(outPath,"ERROR: Color code value must be in range of 0x0-0xFFFFFF!", true,true);
                                }
                                else if (SmartColorLamp.getError().equals("error5")){
                                    SmartColorLampError(devices.colorList, devices.deviceNames, colorIndex, nameIndex);
                                    error = true;
                                    writeFile(outPath,"ERROR: Erroneous command!", true,true);
                                }
                            }
                        }
                    }
                }
                if (error==false){
                    colorIndex += 1;
                    nameIndex += 1;
                }
            }
            //REMOVE DEVICE
            else if (split[0].equals("Remove")){
                if (split.length==2){
                    error=true;
                    for (String name : devices.deviceNames){
                        if (name!=null && name.equals(split[1])){
                            error=false;
                        }
                    }
                    //If there is a device with given name
                    if (error==false){
                        writeFile(outPath,"SUCCESS: Information about removed smart device is as follows:", true,true);
                        RemoveDevice(devices, time, split[1], devices.deviceNames, outPath);
                    } else {
                        writeFile(outPath,"ERROR: There is not such a device!", true,true);
                    }
                } else {
                    writeFile(outPath,"ERROR: Erroneous command!", true,true);
                }
            }
            //CHANGE NAME
            else if (split[0].equals("ChangeName")){
                if (split.length==3){
                    if (split[1].equals(split[2])){
                        writeFile(outPath,"ERROR: Both of the names are the same, nothing changed!", true,true); 
                    } else {
                        String Old = split[1];
                        String New = split[2];
                        //If there is already a device with the new name
                        for (String name : devices.deviceNames){
                            if (name != null && name.equals(New)){
                                error=true;
                                writeFile(outPath,"ERROR: There is already a smart device with same name!", true,true);
                            }
                        }
                        //If there is no device with the new name
                        if (error==false){
                            ChangeName(devices, Old, devices.deviceNames, New);
                        }
                    }
                } else {
                    writeFile(outPath,"ERROR: Erroneous command!", true,true); 
                }
            }
            //SWITCH ON/OFF
            else if (split[0].equals("Switch")){
                if (split.length==3){
                    String device = split[1];
                    String status = split[2];
                    error=true;
                    //If there is such a device
                    for (String name : devices.deviceNames){
                        if (name != null && name.equals(device)){
                            error=false;
                            ChangeStatus(devices, time, device, status, outPath, false);
                        }
                    }
                    if (error==true){
                        writeFile(outPath,"ERROR: There is not such a device!", true,true);
                    }

                } else {
                    writeFile(outPath,"ERROR: Erroneous command!", true,true);
                }
            }
            //SET KELVIN
            else if (split[0].equals("SetKelvin")){
                if (split.length==3){
                    String name = split[1];
                    int kelvin = Integer.parseInt(split[2]);
                    error=true;
                    //If Smart Lamp
                    for (int i=0; i<devices.lampList.length; i++){
                        if (devices.lampList[i]!=null && devices.lampList[i].getName().equals(name)){
                            error=false;
                            if (kelvin>=2000 && kelvin<=6500){
                                devices.lampList[i].setKelvin(kelvin);
                            }
                            else if (kelvin<2000 || kelvin>6500){
                                writeFile(outPath,"ERROR: Kelvin value must be in range of 2000K-6500K!", true,true);;
                            }
                        }
                    }
                    //If Smart Color Lamp
                    for (int i=0; i<devices.colorList.length; i++){
                        if (devices.colorList[i]!=null && devices.colorList[i].getName().equals(name)){
                            error=false;
                            if (kelvin>=2000 && kelvin<=6500){
                                devices.colorList[i].setKelvin(kelvin);
                            }
                            else if (kelvin<2000 || kelvin>6500){
                                writeFile(outPath,"ERROR: Kelvin value must be in range of 2000K-6500K!", true,true);;
                            }
                        }
                    }
                    if (error==true){
                        writeFile(outPath,"ERROR: This device is not a smart lamp!", true,true);
                    }
                } else {
                    writeFile(outPath,"ERROR: Erroneous command!", true,true);
                }
            }
            //SET BRIGHTNESS
            else if (split[0].equals("SetBrightness")){
                if (split.length==3){
                    String name = split[1];
                    int brightness = Integer.parseInt(split[2]);
                    error=true;
                    //If Smart Lamp
                    for (int i=0; i<devices.lampList.length; i++){
                        if (devices.lampList[i]!=null && devices.lampList[i].getName().equals(name)){
                            error=false;
                            if (brightness>=0 && brightness<=100){
                                devices.lampList[i].setBrightness(brightness);
                            }
                            else if (brightness<0 || brightness>100){
                                writeFile(outPath,"ERROR: Brightness must be in range of 0%-100%!", true,true);;
                            }
                        }
                    }
                    //If Smart Color Lamp
                    for (int i=0; i<devices.colorList.length; i++){
                        if (devices.colorList[i]!=null && devices.colorList[i].getName().equals(name)){
                            error=false;
                            if (brightness>=0 && brightness<=100){
                                devices.colorList[i].setBrightness(brightness);
                            }
                            else if (brightness<0 || brightness>100){
                                writeFile(outPath,"ERROR: Brightness must be in range of 0%-100%!", true,true);;
                            }
                        }
                    }
                    if (error==true){
                        writeFile(outPath,"ERROR: This device is not a smart lamp!", true,true);
                    }
                } else {
                    writeFile(outPath,"ERROR: Erroneous command!", true,true);
                }
            }
            //SET COLOR CODE
            else if (split[0].equals("SetColorCode")){
                if (split.length==3){
                    String name = split[1];
                    String color = split[2];
                    error=true;
                    for (int i=0; i<devices.colorList.length; i++){
                        //If Smart Color Lamp
                        if (devices.colorList[i]!=null && devices.colorList[i].getName().equals(name)){
                            error=false;
                            try {
                                Long.decode(color);
                                if (color.length()>8){
                                    writeFile(outPath,"ERROR: Color code value must be in range of 0x0-0xFFFFFF", true,true);
                                }
                                devices.colorList[i].setCodeSTR(color);
                                devices.colorList[i].setCurrent("code");
                            } catch (Exception e){
                                writeFile(outPath,"ERROR: Erroneous command!", true,true);
                            }
                        }
                    }
                    if (error==true){
                        writeFile(outPath,"ERROR: This device is not a smart color lamp!", true,true);
                    }
                } else {
                    writeFile(outPath,"ERROR: Erroneous command!", true,true);
                }
            }
            //SET WHITE
            else if (split[0].equals("SetWhite")){
                if (split.length==4){
                    String name = split[1];
                    int kelvin = Integer.parseInt(split[2]);
                    int brightness = Integer.parseInt(split[3]);                    
                    error=true;
                    boolean error2=false;
                    //If Smart Lamp
                    for (int i=0; i<devices.lampList.length; i++){
                        if (devices.lampList[i]!=null && devices.lampList[i].getName().equals(name)){
                            error=false;
                            if (kelvin<2000 || kelvin>6500){
                                error2 = true;
                                writeFile(outPath,"ERROR: Kelvin value must be in range of 2000K-6500K!", true,true);;
                            }
                            if ((brightness < 0 || brightness > 100) && error2==false){
                                writeFile(outPath,"ERROR: Brightness must be in range of 0%-100%!", true,true);;
                            } 
                            //If everything is correct
                            if (kelvin>=2000 && kelvin<=6500 && brightness>=0 && brightness<=100){
                                devices.lampList[i].setKelvin(kelvin);
                                devices.lampList[i].setBrightness(brightness);
                            }
                        }
                    }
                    //If Smart Color Lamp
                    for (int i=0; i<devices.colorList.length; i++){
                        if (devices.colorList[i]!=null && devices.colorList[i].getName().equals(name)){
                            error=false;
                            if (kelvin<2000 || kelvin>6500){
                                error2 = true;
                                writeFile(outPath,"ERROR: Kelvin value must be in range of 2000K-6500K!", true,true);;
                            }
                            if ((brightness < 0 || brightness > 100) && error2==false){
                                writeFile(outPath,"ERROR: Brightness must be in range of 0%-100%!", true,true);;
                            } 
                            //If everything is correct
                            if (kelvin>=2000 && kelvin<=6500 && brightness>=0 && brightness<=100){
                                devices.colorList[i].setKelvin(kelvin);
                                devices.colorList[i].setBrightness(brightness);
                            }
                        }
                    }
                    if (error==true){
                        writeFile(outPath,"ERROR: This device is not a smart lamp!", true,true);
                    }
                } else {
                    writeFile(outPath,"ERROR: Erroneous command!", true,true);
                }
            }
            //SET COLOR
            else if (split[0].equals("SetColor")){
                if (split.length==4){
                    String name = split[1];
                    String color = split[2];
                    int brightness = Integer.parseInt(split[3]);
                    int index = 0;
                    error=true;
                    boolean error2=false;
                    boolean error3=false;
                    for (int i=0; i<devices.colorList.length; i++){
                        //If Smart Color Lamp
                        if (devices.colorList[i]!=null && devices.colorList[i].getName().equals(name)){
                            error=false;
                            try {
                                index = i;
                                Long.decode(color);
                                if (color.length()>8){
                                    error3=true;
                                    error2=true;
                                    writeFile(outPath,"ERROR: Color code value must be in range of 0x0-0xFFFFFF", true,true);
                                }
                                if ((brightness < 0 || brightness > 100) && error2==false){
                                    error3=true;
                                    writeFile(outPath,"ERROR: Brightness must be in range of 0%-100%!", true,true);;
                                }
                            } catch (Exception e){
                                error3=true;
                                writeFile(outPath,"ERROR: Erroneous command!", true,true);
                            }
                        }
                    }
                    if (error==true){
                        writeFile(outPath,"ERROR: This device is not a smart color lamp!", true,true);
                    }
                    //If everything is correct
                    else if (error3==false){
                        devices.colorList[index].setCodeSTR(color);
                        devices.colorList[index].setCurrent("code");
                        devices.colorList[index].setBrightness(brightness);
                    }
                } else {
                    writeFile(outPath,"ERROR: Erroneous command!", true,true);
                }
            }
            //PLUG IN
            else if (split[0].equals("PlugIn")){
                if (split.length==3){
                    String name = split[1];
                    double ampere = Double.parseDouble(split[2]);
                    error=true;
                    //For only Smart Plug
                    for (int i=0; i<devices.plugList.length; i++){
                        if (devices.plugList[i]!=null && devices.plugList[i].getName().equals(name)){
                            error=false;
                            //If Ampere value is not set before
                            if (ampere>0){
                                if (devices.plugList[i].getAmpere()==0){
                                    devices.plugList[i].setAmpere(ampere);
                                    if (devices.plugList[i].getStatus().equals("On")){
                                        devices.plugList[i].setFirstTime(time.getCurrentTime());                             
                                    }
                                }
                                else {
                                    writeFile(outPath,"ERROR: There is already an item plugged in to that plug!", true,true);
                                }
                            } else {
                                writeFile(outPath,"ERROR: Ampere value must be a positive number!", true,true);;
                            }
                        }
                    }
                    if (error==true){
                        writeFile(outPath,"ERROR: This device is not a smart plug!", true,true);
                    }
                } else {
                    writeFile(outPath,"ERROR: Erroneous command!", true,true);
                }
            }
            //PLUG OUT
            else if (split[0].equals("PlugOut")){
                if (split.length==2){
                    String name = split[1];;
                    error=true;
                    //For only Smart Plug
                    for (int i=0; i<devices.plugList.length; i++){
                        if (devices.plugList[i]!=null && devices.plugList[i].getName().equals(name)){
                            error=false;
                            //If Ampere value is set before
                            if (devices.plugList[i].getAmpere()>0){
                                devices.plugList[i].setAmpere(0);
                                if (devices.plugList[i].getStatus().equals("On")){
                                    devices.plugList[i].setSecondTime(time.getCurrentTime());
                                    Duration duration = Duration.between(devices.plugList[i].getFirstTime(), devices.plugList[i].getSecondTime());
                                    double diff = duration.toMinutes();
                                    devices.plugList[i].setEnergy(devices.plugList[i].getEnergy()+(devices.plugList[i].getVoltage()*devices.plugList[i].getAmpere()*(diff/60)));
                                }
                            } else {
                                writeFile(outPath,"ERROR: This plug has no item to plug out from that plug!", true,true);
                            }
                        }
                    }
                    if (error==true){
                        writeFile(outPath,"ERROR: This device is not a smart plug!", true,true);
                    }
                } else {
                    writeFile(outPath,"ERROR: Erroneous command!", true,true);
                }
            }
            //ZREPORT
            else if (split[0].equals("ZReport")){
                ZReport(SwitchController, devices, time, outPath);
            }
            //SET TIME
            else if (split[0].equals("SetTime")){
                if (split.length==2){
                    try {
                        String replaced = split[1].replace("_","T");
                        LocalDateTime newTime = LocalDateTime.parse(replaced);
                        //If the new time is after the current time
                        if (newTime.isAfter(time.getCurrentTime())){
                            time.setCurrentTime(LocalDateTime.parse(replaced));;
                            SwitchController.SwitchTimeDue(devices, time, outPath);
                        }
                        //If the new time is before the current time
                        else if (newTime.isBefore(time.getCurrentTime())){
                            writeFile(outPath,"ERROR: Time cannot be reversed!", true,true);
                        }
                        //If the new time is equal with the current time
                        else if (newTime.isEqual(time.getCurrentTime())){
                            writeFile(outPath,"ERROR: There is nothing to change!", true,true);
                        }
                    } catch (Exception e){
                        writeFile(outPath,"ERROR: Time format is not correct!", true,true);
                    }
                } else {
                    writeFile(outPath,"ERROR: Erroneous command!", true,true);
                }
            }     
            //SKIP MINUTES
            else if (split[0].equals("SkipMinutes")){
                if (split.length==2){
                    try {
                        int minute = Integer.parseInt(split[1]);
                        //If minute is positive
                        if (minute>0){
                            time.setCurrentTime(time.getCurrentTime().plusMinutes(minute));
                            SwitchController.SwitchTimeDue(devices, time, outPath);
                        }
                        //If minute is zero
                        else if (minute==0){
                            writeFile(outPath,"ERROR: There is nothing to skip!", true,true);
                        } 
                        //If minute is negative
                        else {
                            writeFile(outPath,"ERROR: Time cannot be reversed!", true,true);
                        }
                    } catch (Exception e){
                        writeFile(outPath,"ERROR: Erroneous command!", true,true);
                    }
                } else {
                    writeFile(outPath,"ERROR: Erroneous command!", true,true);
                }
            }
            //SET SWITCH TIME
            else if (split[0].equals("SetSwitchTime")){
                if (split.length==3){
                    String name = split[1];
                    String switchTime = split[2].replace("_","T");
                    LocalDateTime newTime = LocalDateTime.parse(switchTime);
                    if (newTime.isAfter(time.getCurrentTime()) || newTime.isEqual(time.getCurrentTime())){
                        //For Smart Lamp
                        for (int i=0; i<devices.lampList.length; i++){
                            if (devices.lampList[i]!=null && devices.lampList[i].getName().equals(name)){
                                devices.lampList[i].setSwitchTime(LocalDateTime.parse(switchTime));
                                devices.switchList[switchIndex] = name+"&"+switchTime;
                                switchIndex += 1;
                            }
                        }
                        //For Smart Color Lamp
                        for (int i=0; i<devices.colorList.length; i++){
                            if (devices.colorList[i]!=null && devices.colorList[i].getName().equals(name)){
                                devices.colorList[i].setSwitchTime(LocalDateTime.parse(switchTime));
                                devices.switchList[switchIndex] = name+"&"+switchTime;
                                switchIndex += 1;
                            }
                        }
                        //For Smart Plug
                        for (int i=0; i<devices.plugList.length; i++){
                            if (devices.plugList[i]!=null && devices.plugList[i].getName().equals(name)){
                                devices.plugList[i].setSwitchTime(LocalDateTime.parse(switchTime));
                                devices.switchList[switchIndex] = name+"&"+switchTime;
                                switchIndex += 1;
                            }
                        }
                        //For Smart Camera
                        for (int i=0; i<devices.cameraList.length; i++){
                            if (devices.cameraList[i]!=null && devices.cameraList[i].getName().equals(name)){
                                devices.cameraList[i].setSwitchTime(LocalDateTime.parse(switchTime));
                                devices.switchList[switchIndex] = name+"&"+switchTime;
                                switchIndex += 1;
                            }
                        }
                        if (newTime.isEqual(time.getCurrentTime())){
                            SwitchController.SwitchTimeDue(devices, time, outPath);
                        }
                        //Sorting Switch Times
                        SwitchController.SwitchTimeSort(devices);
                    } 
                    else if (newTime.isBefore(time.getCurrentTime())){
                        writeFile(outPath,"ERROR: Switch time cannot be in the past!", true,true);
                    }
                } else {
                    writeFile(outPath,"ERROR: Erroneous command!", true,true);
                }
            }
            //NOP
            else if (split[0].equals("Nop")){
                String[] list = devices.switchList;
                int Count = 0;
                //Number of nulls
                for (String s : list){
                    if (s==null){
                        Count+=1;
                    }
                }
                //If whole list is null
                if (Count==100){
                    writeFile(outPath,"ERROR: There is nothing to switch!", true,true);
                } 
                //If there is at least one device with switch time
                else {
                    for (int i=0;i<devices.switchList.length;i++){
                        if (devices.switchList[i]!=null){
                            String timeSTR = devices.switchList[i].split("&")[1];
                            if (devices.switchList[i].split("&").length==2){
                                LocalDateTime switchTime = LocalDateTime.parse(timeSTR);
                                time.setCurrentTime(switchTime);
                                SwitchController.SwitchTimeDue(devices, time, outPath);
                                break;
                            }
                        }
                    }
                }
            }
            //UNKNOWN COMMAND
            else{
                writeFile(outPath,"ERROR: Erroneous command!", true,true);
            }
        }
        //ZReport Ending if the last command is not ZReport
        if (!lines[lines.length-1].equals("ZReport")){
            writeFile(outPath,"ZReport:", true,true);
            ZReport(SwitchController, devices, time, outPath);
        }
    }
}   