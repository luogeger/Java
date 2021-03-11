package com.first.jmm;

import java.lang.instrument.Instrumentation;


public class AgentObjectSize {

    private static Instrumentation inst;

    public static void premain(String agentArgs, Instrumentation _inst) {
        inst = _inst;
    }


    public static long sizeOf(Object obj) {
        return inst.getObjectSize(obj);

    }
}
