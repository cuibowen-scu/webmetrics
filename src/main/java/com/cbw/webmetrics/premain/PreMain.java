package com.cbw.webmetrics.premain;


import com.cbw.webmetrics.agent.Agent;

import java.lang.instrument.Instrumentation;

/**
 * add an agent to interrupt user project method
 */
public class PreMain {
    public static void premain(String agentOps, Instrumentation inst) {
        // Add Transformer
        inst.addTransformer(new Agent());
    }
}