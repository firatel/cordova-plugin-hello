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

            String printerName = data.getString(0);
            String message = "Printer Info: Telpo320 USB Thermal Printer. Printer Called: " + printerName;
            callbackContext.success(message);

            return true;
        }
        else {

            return false;

        }
    }
}
