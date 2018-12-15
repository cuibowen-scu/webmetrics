package com.cbw.webmetrics.beans.db;

public class UserProjectBean {
    private String phone;
    private int projectId;
    private String projectName;

    private String hostname;
    private String port;
    private String username;
    private String password;
    private String dbName;

    private String ifCpuNeedWarn;
    private String ifMemNeedWarn;
    private String ifDiskNeedWarn;

    private double cpuWarnNum;
    private double memWarnNum;
    private double diskWarnNum;

    public UserProjectBean(String phone, int projectId, String projectName, String hostname, String port, String username, String password, String dbName, String ifCpuNeedWarn, String ifMemNeedWarn, String ifDiskNeedWarn, double cpuWarnNum, double memWarnNum, double diskWarnNum) {
        this.phone = phone;
        this.projectId = projectId;
        this.projectName = projectName;
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
        this.dbName = dbName;
        this.ifCpuNeedWarn = ifCpuNeedWarn;
        this.ifMemNeedWarn = ifMemNeedWarn;
        this.ifDiskNeedWarn = ifDiskNeedWarn;
        this.cpuWarnNum = cpuWarnNum;
        this.memWarnNum = memWarnNum;
        this.diskWarnNum = diskWarnNum;
    }

    public UserProjectBean() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
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

    @Override
    public String toString() {
        return "UserProjectBean{" +
                "phone='" + phone + '\'' +
                ", projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", hostname='" + hostname + '\'' +
                ", port='" + port + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dbName='" + dbName + '\'' +
                ", ifCpuNeedWarn='" + ifCpuNeedWarn + '\'' +
                ", ifMemNeedWarn='" + ifMemNeedWarn + '\'' +
                ", ifDiskNeedWarn='" + ifDiskNeedWarn + '\'' +
                ", cpuWarnNum=" + cpuWarnNum +
                ", memWarnNum=" + memWarnNum +
                ", diskWarnNum=" + diskWarnNum +
                '}';
    }
}