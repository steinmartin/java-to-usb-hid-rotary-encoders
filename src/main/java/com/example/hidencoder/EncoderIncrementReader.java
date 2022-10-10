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

    }

    @Override
    public String toString() {
        return "EncoderIncrementReader{" +
                "hidDevice=" + hidDevice.getSerialNumber() +
                '}';
    }
}
