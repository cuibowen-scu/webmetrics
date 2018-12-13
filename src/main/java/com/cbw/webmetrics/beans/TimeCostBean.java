package com.cbw.webmetrics.beans;

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
    private int cost;
    private String ifWarning;

    public TimeCostBean(int projectId, int methodId, String className, String methodName, long startNanoTime, String startUserTime) {
        this.projectId = projectId;
        this.methodId = methodId;
        this.className = className;
        this.methodName = methodName;
        this.startNanoTime = startNanoTime;
        this.startUserTime = startUserTime;
    }

    public TimeCostBean(int projectId, int methodId, String className, String methodName, long startNanoTime, String startUserTime, long endNanoTime, int cost) {
        this.projectId = projectId;
        this.methodId = methodId;
        this.className = className;
        this.methodName = methodName;
        this.startNanoTime = startNanoTime;
        this.startUserTime = startUserTime;
        this.endNanoTime = endNanoTime;
        this.cost = cost;
    }

    public TimeCostBean(int projectId, int methodId, String className, String methodName, long startNanoTime, String startUserTime, long endNanoTime, int cost, String ifWarning) {
        this.projectId = projectId;
        this.methodId = methodId;
        this.className = className;
        this.methodName = methodName;
        this.startNanoTime = startNanoTime;
        this.startUserTime = startUserTime;
        this.endNanoTime = endNanoTime;
        this.cost = cost;
        this.ifWarning = ifWarning;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getIfWarning() {
        return ifWarning;
    }

    public void setIfWarning(String ifWarning) {
        this.ifWarning = ifWarning;
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
                ", cost=" + cost +
                ", ifWarning='" + ifWarning + '\'' +
                '}';
    }
}
