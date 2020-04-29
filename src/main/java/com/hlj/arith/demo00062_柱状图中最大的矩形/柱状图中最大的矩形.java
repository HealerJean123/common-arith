package com.hlj.arith.demo00062_柱状图中最大的矩形;

import java.util.Stack;

/**
作者：HealerJean
题目：柱状图中最大的矩形
 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 解题思路：
*/
public class 柱状图中最大的矩形 {


    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            // 栈顶元素不是第一个元素 -1 且数组呈下降关系时，什么时候结束呢？
            // 显然是当栈顶元素为 -1 或者 heights[i] ≥ heights[stack.peek()] 跳出循环直接压栈
            while (stack.peek() != -1 && heights[i] < heights[stack.peek()]) {
                // 将栈中的序号弹出，计算最大面积
                maxArea = Math.max(heights[stack.pop()] * (i - stack.peek() - 1), maxArea);
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            maxArea = Math.max(heights[stack.pop()] * (heights.length - stack.peek() - 1), maxArea);
        }
        return maxArea;
    }


}
