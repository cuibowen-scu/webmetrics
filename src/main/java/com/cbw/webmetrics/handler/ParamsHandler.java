package com.cbw.webmetrics.handler;

import com.cbw.webmetrics.beans.db.CostMethodBean;
import com.cbw.webmetrics.beans.db.ParamsBean;
import com.cbw.webmetrics.beans.db.UserProjectBean;
import com.cbw.webmetrics.utils.DBUtil;
import com.cbw.webmetrics.utils.DateUtil;
import com.cbw.webmetrics.utils.PropsUtil;

import java.util.Properties;

/**
 * the ParamsHandler is design to receive user project args and store them to db.
 */
public class ParamsHandler {

    /**
     * the entrance method, max 4096 bytes
     */
    public static void upload(int methodId, String methodArgs) {
        if (4096 < methodArgs.length()) {
            return;
        }
        String time = DateUtil.getUserTime();
        int projectId = Integer.parseInt(PropsUtil.getProjectId());
        UserProjectBean userProjectInfo = DBUtil.getUserProjectInfo(projectId);
        CostMethodBean costMethodInfo = DBUtil.getCostMethodInfo(projectId, methodId);
        ParamsBean paramsBean = new ParamsBean(projectId, methodId, costMethodInfo.getMethodClass(),
                costMethodInfo.getMethodName(), time, methodArgs);
        DBUtil.saveParamsToDB(paramsBean, userProjectInfo);
    }
}