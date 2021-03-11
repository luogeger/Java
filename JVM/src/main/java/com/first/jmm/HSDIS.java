package com.first.jmm;

/**
 * hsdis 将VM翻译后的byteCode反汇编
 *
 * -server -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=compileonly,*HSDIS.getInstance
 *
 *
 * CompilerOracle: compileonly *HSDIS.getInstance
 * Could not load hsdis-amd64.dll; library not loadable; PrintAssembly is disabled
 * Java HotSpot(TM) 64-Bit Server VM warning: PrintAssembly is enabled; turning on DebugNonSafepoints to gain additional output
 */
public class HSDIS {
    private static volatile HSDIS instance = null;

    public static HSDIS getInstance() {
        if (instance == null) {
            instance = new HSDIS();
        }

        return instance;
    }

    public static void main(String[] args) {
        HSDIS.getInstance();
    }

}
