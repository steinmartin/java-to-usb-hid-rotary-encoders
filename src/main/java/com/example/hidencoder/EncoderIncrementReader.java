package com.example.hidencoder;

import org.hid4java.HidDevice;
import org.hid4java.HidException;
import org.hid4java.HidManager;
import org.hid4java.HidServices;
import org.hid4java.HidServicesListener;
import org.hid4java.HidServicesSpecification;
import org.hid4java.event.HidServicesEvent;

public class EncoderIncrementReader implements HidServicesListener {


    private static EncoderIncrementReader encoderIncrementReader;

    private HidDevice hidDevice;

    public static void main(String[] args) throws HidException {

        // Configure to use custom specification
        HidServicesSpecification hidServicesSpecification = new HidServicesSpecification();
        // Use manual start feature to get immediate attach events
        hidServicesSpecification.setAutoStart(false);

        // Get HID services
        HidServices hidServices = HidManager.getHidServices(hidServicesSpecification);

        encoderIncrementReader = new EncoderIncrementReader();
        hidServices.addHidServicesListener(encoderIncrementReader);

        // Manually start the services to get attachment event
        hidServices.start();
        encoderIncrementReader.hidDevice = hidServices.getHidDevice(Integer.parseInt("16C0",16), Integer.parseInt( "0486", 16), "11621060");
        encoderIncrementReader.readData();
        hidServices.shutdown();

    }

    @Override
    public void hidDeviceAttached(HidServicesEvent hidServicesEvent) {
        HidDevice hidDevice = hidServicesEvent.getHidDevice();



    }

    @Override
    public void hidDeviceDetached(HidServicesEvent hidServicesEvent) {

    }

    @Override
    public void hidFailure(HidServicesEvent hidServicesEvent) {

    }

    public void readData() {
        System.out.println("Martin, the HID: " + this);

        Byte[] data = null;
        while (true) {
            data = hidDevice.read(16,1000);
            System.out.print("Martin, the HID data: " + data[0]);
            System.out.print(" " + data[1]);
            System.out.print(" " + data[2]);
            System.out.print(" " + data[3]);
            System.out.print(" " + data[4]);
            System.out.print(" " + data[5]);
            System.out.print(" " + data[6]);
            System.out.print(" " + data[7]);
            System.out.print(" " + data[8]);
            System.out.print(" " + data[9]);
            System.out.print(" " + data[10]);
            System.out.print(" " + data[11]);
            System.out.print(" " + data[12]);
            System.out.print(" " + data[13]);
            System.out.print(" " + data[14]);
            System.out.println(" " + data[15]);


        }

    }

    @Override
    public String toString() {
        return "EncoderIncrementReader{" +
                "hidDevice=" + hidDevice.getSerialNumber() +
                '}';
    }
}
