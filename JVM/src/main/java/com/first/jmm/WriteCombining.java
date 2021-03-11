package com.first.jmm;

/**
 * 合并写
 */
public class WriteCombining {

    public static final int ITERATIONS = Integer.MAX_VALUE;

    public static final int ITEMS = 1 << 24;

    public static final int MASK = ITEMS - 1;

    public static final byte[] ARRAY1 = new byte[ITEMS];
    public static final byte[] ARRAY2 = new byte[ITEMS];
    public static final byte[] ARRAY3 = new byte[ITEMS];
    public static final byte[] ARRAY4 = new byte[ITEMS];
    public static final byte[] ARRAY5 = new byte[ITEMS];
    public static final byte[] ARRAY6 = new byte[ITEMS];

    /**
     * 0 Single loop duration (ns):10084205700
     * 1 Single loop duration (ns):9837461800
     * 2 Single loop duration (ns):9762265500
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            /*
                0 Single loop duration (ns):10084205700
                1 Single loop duration (ns):9837461800
                2 Single loop duration (ns):9762265500
            */
//            System.out.println(i + " Single loop duration (ns):" + runCaseSingle());
            System.out.println(i + " Split loop duration (ns):" + runCaseSplit());
        }
    }

    private static long runCaseSingle() {
        long start = System.nanoTime();
        int i = ITERATIONS;

        while (--i != 0) {
            int slot = i & MASK;
            byte b = (byte) i;
            ARRAY1[slot] = b;
            ARRAY2[slot] = b;
            ARRAY3[slot] = b;
            ARRAY4[slot] = b;
            ARRAY5[slot] = b;
            ARRAY6[slot] = b;
        }

        return System.nanoTime() - start;
    }

    private static long runCaseSplit() {
        long start = System.nanoTime();
        int i = ITERATIONS;

        while (--i != 0) {
            int slot = i & MASK;
            byte b = (byte) i;
            ARRAY1[slot] = b;
            ARRAY2[slot] = b;
            ARRAY3[slot] = b;
        }

        i = ITERATIONS;
        while (--i != 0) {
            int slot = i & MASK;
            byte b = (byte) i;
            ARRAY4[slot] = b;
            ARRAY5[slot] = b;
            ARRAY6[slot] = b;
        }

        return System.nanoTime() - start;

    }

}






















