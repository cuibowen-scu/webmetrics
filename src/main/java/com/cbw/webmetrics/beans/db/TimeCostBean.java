package com.cbw.webmetrics.beans.db;

/**
 * the class is design to store user timecost table info
 */
public class TimeCostBean {
    private int projectId;
    private int methodId;
    private String className;
    private String methodName;
    private long startNanoTime;
    private String startUserTime;
    private long endNanoTime;

    private String ifCostNeedWarn;
    private int costWarnNum;
    private int cost;
    private String ifWarned;

    public TimeCostBean(int projectId, int methodId, String className, String methodName, long startNanoTime, String startUserTime) {
        this.projectId = projectId;
        this.methodId = methodId;
        this.className = className;
        this.methodName = methodName;
        this.startNanoTime = startNanoTime;
        this.startUserTime = startUserTime;
    }

    public TimeCostBean(int projectId, int methodId, String className, String methodName, long startNanoTime, String startUserTime, long endNanoTime, String ifCostNeedWarn, int costWarnNum, int cost, String ifWarned) {
        this.projectId = projectId;
        this.methodId = methodId;
        this.className = className;
        this.methodName = methodName;
        this.startNanoTime = startNanoTime;
        this.startUserTime = startUserTime;
        this.endNanoTime = endNanoTime;
        this.ifCostNeedWarn = ifCostNeedWarn;
        this.costWarnNum = costWarnNum;
        this.cost = cost;
        this.ifWarned = ifWarned;
    }

    public TimeCostBean() {
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public long getStartNanoTime() {
        return startNanoTime;
    }

    public void setStartNanoTime(long startNanoTime) {
        this.startNanoTime = startNanoTime;
    }

    public String getStartUserTime() {
        return startUserTime;
    }

    public void setStartUserTime(String startUserTime) {
        this.startUserTime = startUserTime;
    }

    public long getEndNanoTime() {
        return endNanoTime;
    }

    public void setEndNanoTime(long endNanoTime) {
        this.endNanoTime = endNanoTime;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getIfWarned() {
        return ifWarned;
    }

    public void setIfWarned(String ifWarned) {
        this.ifWarned = ifWarned;
    }

    @Override
    public String toString() {
        return "TimeCostBean{" +
                "projectId=" + projectId +
                ", methodId=" + methodId +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", startNanoTime=" + startNanoTime +
                ", startUserTime='" + startUserTime + '\'' +
                ", endNanoTime=" + endNanoTime +
                ", ifCostNeedWarn='" + ifCostNeedWarn + '\'' +
                ", costWarnNum=" + costWarnNum +
                ", cost=" + cost +
                ", ifWarned='" + ifWarned + '\'' +
                '}';
    }
}
