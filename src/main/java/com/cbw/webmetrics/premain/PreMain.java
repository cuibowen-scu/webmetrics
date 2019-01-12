package com.cbw.webmetrics.premain;


import com.cbw.webmetrics.agent.Agent;
import com.cbw.webmetrics.utils.PropsUtil;

import java.lang.instrument.Instrumentation;

/**
 * add an agent to interrupt user project method
 */
public class PreMain {
    public static void premain(String agentOps, Instrumentation inst) {
        System.out.println(agentOps);
        PropsUtil.setProjectId(agentOps);
        // Add Transformer
        inst.addTransformer(new Agent());
    }
}