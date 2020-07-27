package com.htzw.annotation.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/7/17 9:20
 */
public class Demo {
    public static void main(String[] args) {
        int[] nums = {2, 11, 7, 15};
        int[] ints = twoSum(nums, 9);
        System.out.println(ints.toString());
    }
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
