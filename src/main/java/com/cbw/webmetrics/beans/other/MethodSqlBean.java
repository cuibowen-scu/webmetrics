package com.cbw.webmetrics.beans.other;

public class MethodSqlBean {
    private String method_id;
    private String method_class;
    private String method_name;

    public MethodSqlBean(String method_id, String method_class, String method_name) {
        this.method_id = method_id;
        this.method_class = method_class;
        this.method_name = method_name;
    }

    public MethodSqlBean() {
    }

    public String getMethod_id() {
        return method_id;
    }

    public void setMethod_id(String method_id) {
        this.method_id = method_id;
    }

    public String getMethod_class() {
        return method_class;
    }

    public void setMethod_class(String method_class) {
        this.method_class = method_class;
    }

    public String getMethod_name() {
        return method_name;
    }

    public void setMethod_name(String method_name) {
        this.method_name = method_name;
    }

    @Override
    public String toString() {
        return "MethodSqlBean{" +
                "method_id=" + method_id +
                ", method_class='" + method_class + '\'' +
                ", method_name='" + method_name + '\'' +
                '}';
    }
}
