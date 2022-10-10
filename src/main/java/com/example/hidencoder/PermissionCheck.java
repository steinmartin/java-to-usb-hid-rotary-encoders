package com.example.hidencoder;

import org.hid4java.*;

public class PermissionCheck {


    public static void main(String[] args) throws HidException {
        HidServicesSpecification hidServicesSpecification = new HidServicesSpecification();
        hidServicesSpecification.setAutoShutdown(true);
        hidServicesSpecification.setScanInterval(1000);
        hidServicesSpecification.setPauseInterval(5000);
        hidServicesSpecification.setScanMode(ScanMode.SCAN_AT_FIXED_INTERVAL_WITH_PAUSE_AFTER_WRITE);
        HidServices hidServices = HidManager.getHidServices(hidServicesSpecification);
        hidServices.start();
        System.out.printf(" %-3s  %-3s - %-30s - %-20s - %-15s - %-10s\n", "VID", "PID", "Description", "Manf", "Serial Num", "Can open");
        for (HidDevice hidDevice : hidServices.getAttachedHidDevices()) {
            String  manf = hidDevice.getManufacturer();
            //manf = (manf != null) ? manf : "";
            String prod = hidDevice.getProduct();
            int vendor = hidDevice.getVendorId();
            int product = hidDevice.getProductId();
            String serialNum = hidDevice.getSerialNumber();
            serialNum = (serialNum != null) ? serialNum : "";
            System.out.printf("%04X:%04X - %-30s - %-20s - %-15s", vendor, product, prod, manf, serialNum);
            if (hidDevice.isOpen()) {
                System.out.print(" - open");
            } else if (hidDevice.open() && hidDevice.isOpen()) {
                hidDevice.setNonBlocking(true);
                System.out.print(" - yes");
                hidDevice.close();
            } else {
                System.out.print(" - no");
            }
            System.out.println();
        }
        hidServices.shutdown();
    }
}

