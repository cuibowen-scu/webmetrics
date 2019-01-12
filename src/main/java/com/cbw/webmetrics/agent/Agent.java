package com.cbw.webmetrics.agent;

import com.cbw.webmetrics.beans.other.MethodBean;
import com.cbw.webmetrics.handler.ServerHandler;
import com.cbw.webmetrics.utils.DBUtil;
import com.cbw.webmetrics.utils.JsonUtil;
import com.cbw.webmetrics.utils.PropsUtil;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * The Agent class is mainly using to interrupt user's project
 */
public class Agent implements ClassFileTransformer {

    private static volatile AtomicInteger sysTime = new AtomicInteger(1);

    /**
     * the interrupt main method
     */
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        // wake the timer monitor only once when user project start-up, do not open when testing
        if (sysTime.intValue() < 1) {
            sysTime.incrementAndGet();
            ServerHandler.handle(PropsUtil.getProjectId());
        }
        Map<String, List<MethodBean>> methodsMap = getAllMethods(DBUtil.getMethodsJson(Integer.parseInt(PropsUtil.getProjectId())));
        className = className.replace("/", ".");
        if (methodsMap.containsKey(className)) {
            List<MethodBean> methods = methodsMap.get(className);
            for (MethodBean method : methods) {
                try {
                    CtClass ctClass = ClassPool.getDefault().get(className);
                    CtMethod ctMethod = ctClass.getDeclaredMethod(method.getMethodName());
                    ctMethod.insertBefore("com.cbw.webmetrics.handler.MainHandler.before(Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[1].getMethodName());");
                    ctMethod.insertAfter("com.cbw.webmetrics.handler.MainHandler.after(Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[1].getMethodName());");
                    return ctClass.toBytecode();
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.getLogger("Agent").warning("agent error, class is: " + className + ", method is: " + method.toString() + e);
                }
            }
        }
        return null;
    }

    /**
     * get all method to a map, the key is user class name, and value is MethodBean<methodId,methodName>
     */
    private Map<String, List<MethodBean>> getAllMethods(String methodsJson) {
        return JsonUtil.getMethodsFromJson(methodsJson);
    }
}