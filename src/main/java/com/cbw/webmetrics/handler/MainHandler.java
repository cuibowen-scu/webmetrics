package com.cbw.webmetrics.handler;

import com.cbw.webmetrics.beans.db.TimeCostBean;
import com.cbw.webmetrics.utils.CommonUtil;
import com.cbw.webmetrics.utils.DBUtil;
import com.cbw.webmetrics.utils.PropsUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * the MainHandler contains Entrance of interrupt function
 */
public class MainHandler {

    private static Map<String, Integer> methodIdMap = CommonUtil.getMethodIdMap(DBUtil.getMethodsJson(Integer.parseInt(PropsUtil.getProjectId())));
    private static Map<String, TimeCostBean> costCache = new HashMap<>();

    /**
     * do our interrupt before user method
     */
    public static void before(String className, String methodName) {
        // before user method start, the cost part, store the time info to the cache, reduce user project total cost
        TimeCostBean costBean = CostHanlder.getStartCostBean(className, methodName);
        costCache.put(CommonUtil.buildCostCacheKey(Integer.parseInt(PropsUtil.getProjectId()), methodIdMap.get(CommonUtil.buildMKey(className, methodName))), costBean);
    }

    /**
     * do our interrupt after user method
     */
    public static void after(String className, String methodName) {
        // after user method finished, the cost part, get the time info from the cache, calculate time cost, then store to DB
        TimeCostBean oldCostBean = costCache.get(CommonUtil.buildCostCacheKey(Integer.parseInt(PropsUtil.getProjectId()), methodIdMap.get(CommonUtil.buildMKey(className, methodName))));
        CostHanlder.processCost(oldCostBean);
    }
}