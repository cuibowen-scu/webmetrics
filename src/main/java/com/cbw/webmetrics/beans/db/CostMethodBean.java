package com.cbw.webmetrics.beans.db;

/**
 * db use, method bean.
 */
public class CostMethodBean {
    private int projectId;
    private int methodId;
    private String methodClass;
    private String methodName;
    private String ifCostNeedWarn;
    private int costWarnNum;

    public CostMethodBean(int projectId, int methodId, String methodClass, String methodName, String ifCostNeedWarn, int costWarnNum) {
        this.projectId = projectId;
        this.methodId = methodId;
        this.methodClass = methodClass;
        this.methodName = methodName;
        this.ifCostNeedWarn = ifCostNeedWarn;
        this.costWarnNum = costWarnNum;
    }

    public CostMethodBean() {
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getMethodId() {
        return methodId;
    }

    public void setMethodId(int methodId) {
        this.methodId = methodId;
    }

    public String getMethodClass() {
        return methodClass;
    }

    public void setMethodClass(String methodClass) {
        this.methodClass = methodClass;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getIfCostNeedWarn() {
        return ifCostNeedWarn;
    }

    public void setIfCostNeedWarn(String ifCostNeedWarn) {
        this.ifCostNeedWarn = ifCostNeedWarn;
    }

    public int getCostWarnNum() {
        return costWarnNum;
    }

    public void setCostWarnNum(int costWarnNum) {
        this.costWarnNum = costWarnNum;
    }

    @Override
    public String toString() {
        return "CostMethodBean{" +
                "projectId=" + projectId +
                ", methodId=" + methodId +
                ", methodClass='" + methodClass + '\'' +
                ", methodName='" + methodName + '\'' +
                ", ifCostNeedWarn='" + ifCostNeedWarn + '\'' +
                ", costWarnNum=" + costWarnNum +
                '}';
    }
}
