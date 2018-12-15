package com.cbw.webmetrics.utils;

import com.cbw.webmetrics.beans.other.MethodBean;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * the JsonUtil class is design to parse json to obj
 */
public class JsonUtil {

    static Gson gson = new Gson();

    /**
     * key<className>, value<List<MethodBean>>
     * MethodBean<methodId,methodName>
     */
    public static Map<String, List<MethodBean>> getMethodsFromJson(String methodsJson) {
        Map<String, List<MethodBean>> methodsMap = new HashMap<>();
        try {
            JsonObject jsonObject = new JsonParser().parse(methodsJson).getAsJsonObject();
            JsonArray jsonArray = jsonObject.getAsJsonArray("methods");
            for (JsonElement method : jsonArray) {
                String methodJson = method.toString();
                Map<String, String> jsonMap = gson.fromJson(methodJson, Map.class);
                String className = jsonMap.get("method_class");
                List<MethodBean> list = methodsMap.getOrDefault(className, new ArrayList<>());
                list.add(new MethodBean(Integer.parseInt(jsonMap.get("method_id")), jsonMap.get("method_name")));
                methodsMap.put(className, list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger("JsonUtil").warning("json parse error, and methodsJson is:" + methodsJson);
        }
        return methodsMap;
    }

    /**
     * key<className_methodName>, value<methodId>
     */
    public static Map<String, Integer> getMethodIdMapFromJson(String methodsJson) {
        Map<String, Integer> methodIdMap = new HashMap<>();
        try {
            JsonObject jsonObject = new JsonParser().parse(methodsJson).getAsJsonObject();
            JsonArray jsonArray = jsonObject.getAsJsonArray("methods");
            for (JsonElement method : jsonArray) {
                String methodJson = method.toString();
                Map<String, String> jsonMap = gson.fromJson(methodJson, Map.class);
                methodIdMap.put(CommonUtil.buildMKey(jsonMap.get("method_class"), jsonMap.get("method_name")), Integer.parseInt(jsonMap.get("method_id")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger("JsonUtil").warning("json parse error, and methodsJson is:" + methodsJson);
        }
        return methodIdMap;
    }

    public static String getCodeFromJson(String result) {
        Map jsonMap = gson.fromJson(result, Map.class);
        return (String) jsonMap.get("respCode");
    }
}