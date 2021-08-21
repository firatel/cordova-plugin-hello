package com.example.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import com.telpo.tps550.api.printer.UsbThermalPrinter;
//import com.example.

public class Hello extends CordovaPlugin {

    UsbThermalPrinter usbThermalPrinter;
    String printText;
    String printResult;


    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("greet")) {

            String name = data.getString(0);
            String message = "Hello from inside Java!, " + name;
            callbackContext.success(message);

            return true;

        }
        else if(action.equals("info")) {

                                    //Print
                                    try{
                                        usbThermalPrinter.reset();
                                        usbThermalPrinter.setAlgin(UsbThermalPrinter.ALGIN_LEFT);
                                        usbThermalPrinter.setLeftIndent(0); //Distance from Left
                                        usbThermalPrinter.setLineSpace(0); //Line Space
                                        usbThermalPrinter.setTextSize(30); //Text Size
                                        usbThermalPrinter.setGray(1); //Grey Level

                                        printText = "Hello, from Firatel! \n";

                                        usbThermalPrinter.addString(printText);
                                        usbThermalPrinter.printString();
                                        usbThermalPrinter.walkPaper(10); //Walk Paper by Line

                                    } catch (Exception e)
                                    {
                                        e.printStackTrace();
                                        printResult = e.toString();

                                    }

            String printerName = data.getString(0);
            String message = "Printer Info: Telpo320 USB Thermal Printer. Printer Called: " + printerName + ". \n Result: " + printResult;
            callbackContext.success(message);

            return true;
        }
        else {

            return false;

        }
    }
}
