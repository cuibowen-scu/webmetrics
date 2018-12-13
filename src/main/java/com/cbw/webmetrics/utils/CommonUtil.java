package com.cbw.webmetrics.utils;

import java.util.Map;

/**
 * some method all used
 */
public class CommonUtil {
    /**
     * build method key, className_methodName, the map value is method id
     */
    public static String buildMKey(String className, String methodName) {
        return className + "_" + methodName;
    }

    /**
     * build cost cache key, projectId_methodId
     */
    public static String buildCostCacheKey(int projectId, int methodId) {
        return projectId + "_" + methodId;
    }

    /**
     * get methodIdMap from user json, key is className_methodName, value is method id
     */
    public static Map<String, Integer> getMethodIdMap(String methodsJson) {
        return JsonUtil.getMethodIdMapFromJson(methodsJson);
    }
}