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

    private static String projectId;

    public static String getProjectId() {
        return projectId;
    }

    public static void setProjectId(String projectId) {
        PropsUtil.projectId = projectId;
    }
}