package com.cbw.webmetrics.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * the class is design trans user config to props
 */
public class PropsUtil {

    private static Properties props;
    private static String filePath = "config.cfg";

    /**
     * the get method
     */
    public static Properties getProps() {
        props = new Properties();
        File f = new File(filePath);
        if (f.exists()) {
            try {
                props.load(new FileInputStream(filePath));
            } catch (IOException e) {
                Logger.getLogger("PropsUtil").warning("cannot load user config to props" + e);
            }
        } else {
            System.out.println("please prepare your config doc.");
        }
        return props;
    }
}