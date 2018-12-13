package com.cbw.webmetrics.beans;

/**
 * user server args, include cpu, mem and so on.
 */
public class ServerPerformanceBean {
    private int projectId;
    private String timeMin;
    private double cpuUsage;
    private double memUsage;
    private double diskUsage;
    private String ifWarning;

    public ServerPerformanceBean(int projectId, String timeMin, double cpuUsage, double memUsage, double diskUsage) {
        this.projectId = projectId;
        this.timeMin = timeMin;
        this.cpuUsage = cpuUsage;
        this.memUsage = memUsage;
        this.diskUsage = diskUsage;
    }

    public ServerPerformanceBean(int projectId, String timeMin, double cpuUsage, double memUsage, double diskUsage, String ifWarning) {
        this.projectId = projectId;
        this.timeMin = timeMin;
        this.cpuUsage = cpuUsage;
        this.memUsage = memUsage;
        this.diskUsage = diskUsage;
        this.ifWarning = ifWarning;
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

    public String getIfWarning() {
        return ifWarning;
    }

    public void setIfWarning(String ifWarning) {
        this.ifWarning = ifWarning;
    }

    @Override
    public String toString() {
        return "ServerPerformanceBean{" +
                "projectId=" + projectId +
                ", timeMin='" + timeMin + '\'' +
                ", cpuUsage=" + cpuUsage +
                ", memUsage=" + memUsage +
                ", diskUsage=" + diskUsage +
                ", ifWarning='" + ifWarning + '\'' +
                '}';
    }
}
