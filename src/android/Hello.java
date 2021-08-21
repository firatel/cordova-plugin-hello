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

import com.telpo.tps550.api.printer.UsbThermalPrinter;
import com.telpo.tps550.api.magnetic.MagneticCard;
import com.telpo.tps550.api.TelpoException;

//import com.telpo.tps550.api.printer.ThermalPrinter;
import android.os.BatteryManager;

import com.telpo.tps550.api.printer.NoPaperException;
import com.telpo.tps550.api.printer.OverHeatException;
import android.content.BroadcastReceiver;
//import com.example.

public class Hello extends CordovaPlugin {

    private CallbackContext connectionCallbackContext;
    //Context context = this.cordova.getActivity().getApplicationContext();

    @Override
    protected void pluginInitialize() {
    }

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
            super.initialize(cordova, webView);
            this.connectionCallbackContext = null;
            IntentFilter filter = new IntentFilter();
            //filter.addAction(ReaderMonitor.ACTION_ICC_PRESENT);
            //webView.getContext().registerReceiver(mReceiver, filter);
    }

    UsbThermalPrinter mUsbThermalPrinter = new UsbThermalPrinter(webView.getContext());
    String printText;
    String printResult;

    public final String STATUS_OK = "OK";
    public final String STATUS_ERROR_NO_PAPER = "ERROR_NO_PAPER";
    public final String STATUS_ERROR_OVERHEAT = "ERROR_OVERHEAT";
    public final String STATUS_ERROR_GENERAL = "ERROR_GENERAL";

    private Activity activity = null;

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("greet")) {

            String name = data.getString(0);
            String message = "Hello from inside Java!, " + name;
            callbackContext.success(message);

            return true;

        }
        else if(action.equals("info")) {

          //console.log("Context: ", context);
          //Print
          //print("Hello Printer!!!");
/*
                              try {
                                  int res = startPrinting("Hello from Printer!");
                                  callbackContext.success(res);
                              } catch (Exception ex) {
                                  System.out.println("in telpo exception" + ex);
                              }
*/

//Print
                try{
                    mUsbThermalPrinter.reset();
                    mUsbThermalPrinter.setAlgin(UsbThermalPrinter.ALGIN_LEFT);
                    mUsbThermalPrinter.setLeftIndent(0); //Distance from Left
                    mUsbThermalPrinter.setLineSpace(0); //Line Space
                    mUsbThermalPrinter.setTextSize(30); //Text Size
                    mUsbThermalPrinter.setGray(1); //Grey Level

                    printText = "Hello, from Firatel! \n";

                    mUsbThermalPrinter.addString(printText);
                    mUsbThermalPrinter.printString();
                    mUsbThermalPrinter.walkPaper(10); //Walk Paper by Line

                } catch (Exception e)
                {
                    e.printStackTrace();
                    printResult = e.toString();
                    //console.log("Error: ", printResult);
/*
                    alertDialog.setTitle("Print Error");
                    alertDialog.setMessage(printResult);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }
                    );
                    alertDialog.show();
*/
                }

            String printerName = data.getString(0);
            String message = "Printer Info: Telpo320 USB Thermal Printer. Printer Called: " + printerName + ". Error: " + printResult;
            callbackContext.success(message);

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

     //private int startPrinting(String content, String signImageDataUrl, String logoPath) {

/*     private int startPrinting(String content) {
        try {
                  UsbThermalPrinter.reset();
                  UsbThermalPrinter.setAlgin(UsbThermalPrinter.ALGIN_LEFT);
                  UsbThermalPrinter.setLeftIndent(0); //Distance from Left
                  UsbThermalPrinter.setLineSpace(0); //Line Space
                  UsbThermalPrinter.setTextSize(30); //Text Size
                  UsbThermalPrinter.setGray(1); //Grey Level

                  printText = "Hello, from Firatel! \n";

                  UsbThermalPrinter.addString(printText);
                  UsbThermalPrinter.printString();
                  UsbThermalPrinter.walkPaper(10); //Walk Paper by Line
            return 0;
        } catch (NoPaperException ex) {
            ex.printStackTrace();
            return -1;
        } catch (OverHeatException ex) {
            ex.printStackTrace();
            return -3;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -4;
        } finally {
            UsbThermalPrinter.stop();
        }
    }
*/
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

/*
    public void print(String res) {

        String stringToPrint = res;
        String status = STATUS_OK;

        try {
            UsbThermalPrinter.start(0);
            UsbThermalPrinter.reset();
            UsbThermalPrinter.setMonoSpace(true);
            UsbThermalPrinter.setGray(15);

            UsbThermalPrinter.addString(stringToPrint.replaceAll(" ","  "));
            UsbThermalPrinter.printString();

            UsbThermalPrinter.walkPaper(20);
        } catch (Exception e) {
            e.printStackTrace();
            String result = e.toString();

            if (result.equals("com.telpo.tps550.api.printer.NoPaperException")) {
                status = STATUS_ERROR_NO_PAPER;
            } else if (result.equals("com.telpo.tps550.api.printer.OverHeatException")) {
                status = STATUS_ERROR_OVERHEAT;
            } else {
                status = STATUS_ERROR_GENERAL;
            }
        } finally {
            UsbThermalPrinter.stop();
        }

        //JSObject ret = new JSObject();
        //ret.put("status", status);
        //call.success(ret);
    }
*/
}
