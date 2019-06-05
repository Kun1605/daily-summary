package me.w1992wishes.easy.collection.array;

/**
 * @author w1992wishes 2019/6/4 14:04
 */
public class Solution {

    /**
     * Q: Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
     * <p>
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     * <p>
     * S: 使用快慢指针来记录遍历的坐标，最开始时两个指针都指向第一个数字，如果两个指针指的数字相同，则快指针向前走一步，
     * <p>
     * 如果不同，则两个指针都向前走一步，这样当快指针走完整个数组后，慢指针当前的坐标加1就是数组中不同数字的个数
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int pre = 0;
        int cur = 0;
        int n = nums.length;
        while (cur < n) {
            if (nums[pre] != nums[cur]) {
                nums[++pre] = nums[cur];
            }
            cur++;
        }
        return pre + 1;
    }


    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * <p>
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
     * <p>
     * 简单的方法就是一旦第二天的价格比前一天的高，就在前一天买入第二天卖出，但是这个会违反“不能同一天买卖的规则”
     */
    public int maxProfit1(int[] prices) {
        int total = 0;
        for (int i = 0; i < prices.length - 1; ++i) {
            if (prices[i] < prices[i + 1]) {
                total += prices[i + 1] - prices[i];
            }
        }
        return total;
    }

    /**
     * 低谷买，高峰卖就可以获得最大利润
     */
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        if (len <= 1) {
            return 0;
        }

        int i = 0;
        int total = 0;
        while (i < len - 1) {
            int buy, sell;
            //寻找递减区间的最后一个值（局部最小点）
            while (i + 1 < len && prices[i + 1] < prices[i]) {
                i++;
            }
            //局部最小点作为买入点
            buy = i;

            //找下一个点(卖出点至少为下一个点）
            i++;
            //不满足。。继续往下找递增区间的最后一个值（局部最高点）
            while (i < len && prices[i] >= prices[i - 1]) {
                i++;
            }
            //设置卖出点
            sell = i - 1;
            //计算总和
            total += prices[sell] - prices[buy];
        }
        return total;
    }

}