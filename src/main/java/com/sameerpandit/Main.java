package com.sameerpandit;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;

public class Main {
    public static void main(String[] args){
        ServiceLocator locator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
        Driver driver = locator.getService(Driver.class);
        driver.run();
    }
}
