package com.cbw.webmetrics.handler;

import com.cbw.webmetrics.beans.TimeCostBean;
import com.cbw.webmetrics.utils.CommonUtil;
import com.cbw.webmetrics.utils.DateUtil;
import com.cbw.webmetrics.utils.PropsUtil;

import java.util.Map;
import java.util.Properties;

/**
 * the class is design to handle user time cost
 */
public class CostHanlder {

    private static Properties props = PropsUtil.getProps();
    private static Map<String, Integer> methodIdMap = CommonUtil.getMethodIdMap(props.getProperty("methods"));

    /**
     * return the costBean before user method start
     */
    public static TimeCostBean getStartCostBean(String className, String methodName) {
        int projectId = Integer.parseInt(props.getProperty("projectId"));
        int methodId = methodIdMap.get(CommonUtil.buildMKey(className, methodName));
        long startNanoTime = DateUtil.getNanoTime();
        String startUserTime = DateUtil.getUserTime();
        return new TimeCostBean(projectId, methodId, className, methodName, startNanoTime, startUserTime);
    }

    /**
     * return the costBean after user method finished
     */
    public static TimeCostBean getEndCostBean(TimeCostBean oldCostBean) {
        long endNanoTime = DateUtil.getNanoTime();
        int cost = (int) ((endNanoTime - oldCostBean.getStartNanoTime()) / 1000000);  //ms
        oldCostBean.setEndNanoTime(endNanoTime);
        oldCostBean.setCost(cost);
        return oldCostBean;
    }
}
