package com.cbw.webmetrics.beans.db;

/**
 * to save params that user upload
 */
public class ParamsBean {
    private int projectId;
    private int methodId;
    private String methodClass;
    private String methodName;
    private String time_s;
    private String content;

    public ParamsBean(int projectId, int methodId, String methodClass, String methodName, String time_s, String content) {
        this.projectId = projectId;
        this.methodId = methodId;
        this.methodClass = methodClass;
        this.methodName = methodName;
        this.time_s = time_s;
        this.content = content;
    }

    public ParamsBean() {
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

    public String getTime_s() {
        return time_s;
    }

    public void setTime_s(String time_s) {
        this.time_s = time_s;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ParamsBean{" +
                "projectId=" + projectId +
                ", methodId=" + methodId +
                ", methodClass='" + methodClass + '\'' +
                ", methodName='" + methodName + '\'' +
                ", time_s='" + time_s + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}