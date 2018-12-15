package com.cbw.webmetrics.beans.db;

/**
 * user server args, include cpu, mem and so on.
 */
public class ServerPerformanceBean {
    private int projectId;
    private String timeMin;

    private String ifCpuNeedWarn;
    private String ifMemNeedWarn;
    private String ifDiskNeedWarn;

    private double cpuWarnNum;
    private double memWarnNum;
    private double diskWarnNum;

    private double cpuUsage;
    private double memUsage;
    private double diskUsage;

    private String ifCpuWarned;
    private String ifMemWarned;
    private String ifDiskWarned;

    public ServerPerformanceBean(int projectId, String timeMin, String ifCpuNeedWarn, String ifMemNeedWarn, String ifDiskNeedWarn, double cpuWarnNum, double memWarnNum, double diskWarnNum, double cpuUsage, double memUsage, double diskUsage) {
        this.projectId = projectId;
        this.timeMin = timeMin;
        this.ifCpuNeedWarn = ifCpuNeedWarn;
        this.ifMemNeedWarn = ifMemNeedWarn;
        this.ifDiskNeedWarn = ifDiskNeedWarn;
        this.cpuWarnNum = cpuWarnNum;
        this.memWarnNum = memWarnNum;
        this.diskWarnNum = diskWarnNum;
        this.cpuUsage = cpuUsage;
        this.memUsage = memUsage;
        this.diskUsage = diskUsage;
    }

    public ServerPerformanceBean() {
    }

    public ServerPerformanceBean(int projectId, String timeMin, String ifCpuNeedWarn, String ifMemNeedWarn, String ifDiskNeedWarn, double cpuWarnNum, double memWarnNum, double diskWarnNum, double cpuUsage, double memUsage, double diskUsage, String ifCpuWarned, String ifMemWarned, String ifDiskWarned) {
        this.projectId = projectId;
        this.timeMin = timeMin;
        this.ifCpuNeedWarn = ifCpuNeedWarn;
        this.ifMemNeedWarn = ifMemNeedWarn;
        this.ifDiskNeedWarn = ifDiskNeedWarn;
        this.cpuWarnNum = cpuWarnNum;
        this.memWarnNum = memWarnNum;
        this.diskWarnNum = diskWarnNum;
        this.cpuUsage = cpuUsage;
        this.memUsage = memUsage;
        this.diskUsage = diskUsage;
        this.ifCpuWarned = ifCpuWarned;
        this.ifMemWarned = ifMemWarned;
        this.ifDiskWarned = ifDiskWarned;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTimeMin() {
        return timeMin;
    }

    public void setTimeMin(String timeMin) {
        this.timeMin = timeMin;
    }

    public String getIfCpuNeedWarn() {
        return ifCpuNeedWarn;
    }

    public void setIfCpuNeedWarn(String ifCpuNeedWarn) {
        this.ifCpuNeedWarn = ifCpuNeedWarn;
    }

    public String getIfMemNeedWarn() {
        return ifMemNeedWarn;
    }

    public void setIfMemNeedWarn(String ifMemNeedWarn) {
        this.ifMemNeedWarn = ifMemNeedWarn;
    }

    public String getIfDiskNeedWarn() {
        return ifDiskNeedWarn;
    }

    public void setIfDiskNeedWarn(String ifDiskNeedWarn) {
        this.ifDiskNeedWarn = ifDiskNeedWarn;
    }

    public double getCpuWarnNum() {
        return cpuWarnNum;
    }

    public void setCpuWarnNum(double cpuWarnNum) {
        this.cpuWarnNum = cpuWarnNum;
    }

    public double getMemWarnNum() {
        return memWarnNum;
    }

    public void setMemWarnNum(double memWarnNum) {
        this.memWarnNum = memWarnNum;
    }

    public double getDiskWarnNum() {
        return diskWarnNum;
    }

    public void setDiskWarnNum(double diskWarnNum) {
        this.diskWarnNum = diskWarnNum;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public double getMemUsage() {
        return memUsage;
    }

    public void setMemUsage(double memUsage) {
        this.memUsage = memUsage;
    }

    public double getDiskUsage() {
        return diskUsage;
    }

    public void setDiskUsage(double diskUsage) {
        this.diskUsage = diskUsage;
    }

    public String getIfCpuWarned() {
        return ifCpuWarned;
    }

    public void setIfCpuWarned(String ifCpuWarned) {
        this.ifCpuWarned = ifCpuWarned;
    }

    public String getIfMemWarned() {
        return ifMemWarned;
    }

    public void setIfMemWarned(String ifMemWarned) {
        this.ifMemWarned = ifMemWarned;
    }

    public String getIfDiskWarned() {
        return ifDiskWarned;
    }

    public void setIfDiskWarned(String ifDiskWarned) {
        this.ifDiskWarned = ifDiskWarned;
    }

    @Override
    public String toString() {
        return "ServerPerformanceBean{" +
                "projectId=" + projectId +
                ", timeMin='" + timeMin + '\'' +
                ", ifCpuNeedWarn='" + ifCpuNeedWarn + '\'' +
                ", ifMemNeedWarn='" + ifMemNeedWarn + '\'' +
                ", ifDiskNeedWarn='" + ifDiskNeedWarn + '\'' +
                ", cpuWarnNum=" + cpuWarnNum +
                ", memWarnNum=" + memWarnNum +
                ", diskWarnNum=" + diskWarnNum +
                ", cpuUsage=" + cpuUsage +
                ", memUsage=" + memUsage +
                ", diskUsage=" + diskUsage +
                ", ifCpuWarned='" + ifCpuWarned + '\'' +
                ", ifMemWarned='" + ifMemWarned + '\'' +
                ", ifDiskWarned='" + ifDiskWarned + '\'' +
                '}';
    }
}