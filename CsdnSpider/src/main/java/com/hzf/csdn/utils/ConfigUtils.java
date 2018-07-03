package com.hzf.csdn.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {

    private ConfigUtils() {
    }

    private static Logger logger = Logger.getLogger(ConfigUtils.class.getName());
    private static Properties props = null;

    static {
        loadLog4j();
        loadConfig();
    }

    //加载config
    private synchronized static void loadConfig() {
        String proFilePath = System.getProperty("user.dir")
                + File.separator + "config"
                + File.separator + "config.properties";
        try (InputStream in = new FileInputStream(proFilePath)) {
            props = new Properties();
            props.load(in);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    //加载log4j
    private synchronized static void loadLog4j() {
        String logFilePath = System.getProperty("user.dir")
                + File.separator + "config"
                + File.separator + "log4j.properties";
        PropertyConfigurator.configure(logFilePath);
    }

    public static String getProperty(String key) {
        if (props == null) loadConfig();
        return props.getProperty(key);
    }

}
