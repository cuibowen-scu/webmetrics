package com.cbw.webmetrics.handler;

import com.cbw.webmetrics.beans.db.CostMethodBean;
import com.cbw.webmetrics.beans.db.TimeCostBean;
import com.cbw.webmetrics.beans.db.UserProjectBean;
import com.cbw.webmetrics.utils.CommonUtil;
import com.cbw.webmetrics.utils.DBUtil;
import com.cbw.webmetrics.utils.DateUtil;
import com.cbw.webmetrics.utils.PropsUtil;

import java.util.Map;
import java.util.Properties;

/**
 * the class is design to handle user time cost
 */
public class CostHanlder {

    private static Map<String, Integer> methodIdMap = CommonUtil.getMethodIdMap(DBUtil.getMethodsJson(Integer.parseInt(PropsUtil.getProjectId())));

    /**
     * return the costBean before user method start
     */
    public static TimeCostBean getStartCostBean(String className, String methodName) {
        int projectId = Integer.parseInt(PropsUtil.getProjectId());
        int methodId = methodIdMap.get(CommonUtil.buildMKey(className, methodName));
        long startNanoTime = DateUtil.getNanoTime();
        String startUserTime = DateUtil.getUserTime();
        return new TimeCostBean(projectId, methodId, className, methodName, startNanoTime, startUserTime);
    }

    /**
     * return the costBean after user method finished
     */
    public static void processCost(TimeCostBean costBean) {
        long endNanoTime = DateUtil.getNanoTime();
        int cost = (int) ((endNanoTime - costBean.getStartNanoTime()) / 1000000);  //ms

        UserProjectBean userProjectInfo = DBUtil.getUserProjectInfo(costBean.getProjectId());
        CostMethodBean costMethodBean = DBUtil.getCostMethodInfo(costBean.getProjectId(), costBean.getMethodId());

        costBean.setEndNanoTime(endNanoTime);
        costBean.setCost(cost);
        costBean.setIfCostNeedWarn(costMethodBean.getIfCostNeedWarn());
        costBean.setCostWarnNum(costMethodBean.getCostWarnNum());

        if ("no".equals(costBean.getIfCostNeedWarn())) {
            costBean.setIfWarned("no");
        } else {
            costBean.setIfWarned(cost > costBean.getCostWarnNum() ? "yes" : "no");
        }

        checkCostWarn(userProjectInfo, costBean);
        DBUtil.saveTimeCostToDB(costBean, userProjectInfo);
    }

    /**
     * check if need warn and warn work
     *
     * @param userProjectInfo
     * @param costBean
     */
    private static void checkCostWarn(UserProjectBean userProjectInfo, TimeCostBean costBean) {
        if ("yes".equals(costBean.getIfWarned())) {
            String phone = userProjectInfo.getPhone();
            String email = DBUtil.getUserInfoByPhone(phone);
            String message = "<<<<" + costBean.toString() + " >>>>";
            MessageHandler.sendMessage(phone, message);
            MailHandler.sendEmail(email, message);
        }
    }
}