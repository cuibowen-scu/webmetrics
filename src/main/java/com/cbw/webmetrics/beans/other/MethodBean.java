package com.cbw.webmetrics.beans.other;

/**
 * the method bean, methodId is the id from our website that the user offer us, methodName is user project method name
 */
public class MethodBean {

    private int methodId;
    private String methodName;

    public MethodBean(int methodId, String methodName) {
        this.methodId = methodId;
        this.methodName = methodName;
    }

    public int getMethodId() {
        return methodId;
    }

    public void setMethodId(int methodId) {
        this.methodId = methodId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return "Method{" +
                "methodId=" + methodId +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}