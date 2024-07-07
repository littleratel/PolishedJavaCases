package spi;

import java.sql.Driver;
import java.util.ServiceLoader;

public class SpiTest {
    public static void main(String[] args) {

        // null
        System.out.println(Driver.class.getClassLoader());

        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        for (Driver driver : serviceLoader) {
            // com.mysql.cj.jdbc.Driver
            System.out.println(driver.getClass().getName());
            // sun.misc.Launcher$AppClassLoader@7f31245a
            System.out.println(driver.getClass().getClassLoader());

            System.out.println(driver.getMajorVersion());
        }
        System.out.println("END!!!");
    }
}
