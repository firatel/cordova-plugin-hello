package com.example.plugin;

import android.Manifest;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Context;
import android.content.IntentFilter;
import android.content.Intent;
import android.app.Activity;

import java.lang.reflect.Method;

import com.telpo.tps550.api.printer.UsbThermalPrinter;
import com.telpo.tps550.api.magnetic.MagneticCard;
import com.telpo.tps550.api.TelpoException;
import java.lang.reflect.InvocationTargetException;
import com.telpo.tps550.api.util.SystemUtil;

//import com.telpo.tps550.api.printer.ThermalPrinter;
import android.os.BatteryManager;

import com.telpo.tps550.api.led.Led;

import com.telpo.tps550.api.printer.NoPaperException;
import com.telpo.tps550.api.printer.OverHeatException;
import android.content.BroadcastReceiver;
//import com.example.

public class Hello extends CordovaPlugin {

    private CallbackContext connectionCallbackContext;

    UsbThermalPrinter usbThermalPrinter = null;

    /***Printer Settings***/
    /*
        Alignment
        public static final int ALGIN_LEFT = 0;
        public static final int ALGIN_MIDDLE = 1;
        public static final int ALGIN_RIGHT = 2;
    */
    int alignment = 0;
    int leftIntent = 0;  //Distance from Left
    int lineSpace = 10;
    int fontSize = 30;

    @Override
    protected void pluginInitialize() {

    Context context = this.cordova.getActivity().getApplicationContext();
    usbThermalPrinter = new UsbThermalPrinter(context);
    }

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
            super.initialize(cordova, webView);
            this.connectionCallbackContext = null;
            IntentFilter filter = new IntentFilter();

    }

    String printText;
    String printResult;

    public final String STATUS_OK = "OK";
    public final String STATUS_ERROR_NO_PAPER = "ERROR_NO_PAPER";
    public final String STATUS_ERROR_OVERHEAT = "ERROR_OVERHEAT";
    public final String STATUS_ERROR_GENERAL = "ERROR_GENERAL";

    private Activity activity = null;
    private Context mContext = null;

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {


        if (action.equals("greet")) {

            String name = data.getString(0);
            String message = "Hello from inside Java!, " + name;
            callbackContext.success(message);

            return true;

        }
        else if(action.equals("info")) {

            String startRes = null;
            try{
                usbThermalPrinter.start(0); //Normal Speed
            } catch (Exception e)
            {
                e.printStackTrace();
                //printResult = e.toString();
                startRes = e.toString();

            }

            String ver = null;
            try{
                ver = usbThermalPrinter.getVersion();
            } catch (Exception e)
            {
                e.printStackTrace();
                //printResult = e.toString();
                ver = e.toString();

            }

            String resetRes = null;
            try{
                usbThermalPrinter.reset();
            } catch (Exception e)
            {
                e.printStackTrace();
                //printResult = e.toString();
                resetRes = e.toString();

            }

            String alignRes = null;
            try{
                usbThermalPrinter.setAlgin(UsbThermalPrinter.ALGIN_LEFT);
            } catch (Exception e)
            {
                e.printStackTrace();
                //printResult = e.toString();
                alignRes = e.toString();

            }

            String leftRes = null;
            try{
                usbThermalPrinter.setLeftIndent(0); //Distance from Left
            } catch (Exception e)
            {
                e.printStackTrace();
                //printResult = e.toString();
                leftRes = e.toString();

            }

            String lineSpaceRes = null;
            try{
                usbThermalPrinter.setLineSpace(0); //Line Space
            } catch (Exception e)
            {
                e.printStackTrace();
                //printResult = e.toString();
                lineSpaceRes = e.toString();

            }

            String textSizeRes = null;
            try{
                usbThermalPrinter.setTextSize(0); //Text Size
            } catch (Exception e)
            {
                e.printStackTrace();
                //printResult = e.toString();
                textSizeRes = e.toString();

            }

            String setGrayRes = null;
            try{
                usbThermalPrinter.setGray(1); //Grey Level
            } catch (Exception e)
            {
                e.printStackTrace();
                //printResult = e.toString();
                setGrayRes = e.toString();

            }

            String addStringRes = null;
            try{
                usbThermalPrinter.addString("Hello from Thermal Printer!"); //Add String Here
            } catch (Exception e)
            {
                e.printStackTrace();
                //printResult = e.toString();
                addStringRes = e.toString();

            }

            String printStringRes = null;
            try{
                usbThermalPrinter.printString();
            } catch (Exception e)
            {
                e.printStackTrace();
                //printResult = e.toString();
                printStringRes = e.toString();

            }

            String walkPaperRes = null;
            try{
                usbThermalPrinter.walkPaper(20); //Walk Paper by Line
            } catch (Exception e)
            {
                e.printStackTrace();
                //printResult = e.toString();
                walkPaperRes = e.toString();

            }


            int deviceType = SystemUtil.getDeviceType();
            String message = "Device Type: " + String.valueOf(deviceType)
            + "\nStart Error: " + startRes
            + "\nDevice Version: " + ver
            + "\nReset Error: " + resetRes
            + "\nAlign Left Error: " + alignRes
            + "\nLeft Indent Error: " + leftRes
            + "\nLine Space Error: " + lineSpaceRes
            + "\nText Size Error: " + textSizeRes
            + "\nSet Gray Level: " + setGrayRes
            + "\nAdd String Error: " + addStringRes
            + "\nPrint String Error: " + printStringRes
            + "\nWalk Paper Error: " + walkPaperRes
            ;

            callbackContext.success(message);

            return true;
        }
        else if(action.equals("setup"))
        {
          String name = data.getString(0);

          alignment = parseInt(data.getString(0));
          leftIntent = parseInt(data.getString(0));
          lineSpace = parseInt(data.getString(0));
          fontSize = parseInt(data.getString(0));

          return true;
        }
        else {

            return false;

        }
    }




     public static void open() throws TelpoException {
        MagneticCard.open();
     }

     public static void close() throws TelpoException {
         MagneticCard.close();
     }

     public String[] startReading() throws TelpoException {
         MagneticCard.startReading();
         String[] arr = MagneticCard.check(100000);
         PluginResult result = new PluginResult(PluginResult.Status.OK, Arrays.toString(arr));
         result.setKeepCallback(true);
         //connectionCallbackContext.sendPluginResult(result);
         return arr;
     }

     //private int print(String content, String sign, String logoPath) {
     private int print(String content) {
             if (getBatteryPercent() <= 5) {
                 return -2;
             } else {
                 //return startPrinting(content, sign, logoPath);
                 //return startPrinting(content);
                 return 0;
             }
    }


    private float getBatteryPercent() {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = this.activity.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {}
        }, ifilter);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        return (level / (float) 2.0) * 100;
    }

}
