package com.first.stream.lambda.b;

import java.util.Arrays;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        int i = apple(5, num -> num * 2);
        System.out.println(i);


        int i1 = apple(5, Arrays.asList(1, 2, 3, 4));
        System.out.println(i1);
    }

    private static int apple(int i, TempFunction function) {
        int temp = function.temp(i);
        return filter(i, function);
    }

    private static int apple(int i, List<Integer> nums) {
        int sum = nums.stream().mapToInt(Integer::valueOf).sum();
//        return sum + 100;
        return filter(i, nums);
    }

    private static int filter(int i, Object obj) {
        if (obj instanceof List) {
         return ((List<Integer>)obj).stream().mapToInt(Integer::valueOf).sum();
        }

        if (obj instanceof TempFunction) {
            return ((TempFunction) obj).temp(i);
        }

        return 0;

        /**
         * getByCondition - getByLambda
         *
         * getData
         *      1. get
         *      2. filter
         *      3. return
         *
         */
    }


}
