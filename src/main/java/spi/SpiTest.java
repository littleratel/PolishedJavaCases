package spi;

import java.sql.Driver;
import java.util.ServiceLoader;

public class SpiTest {
    public static void main(String[] args) {
        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        for (Driver driver : serviceLoader) {
            driver.getMajorVersion();
        }
        System.out.println("END!!!");
    }
}
