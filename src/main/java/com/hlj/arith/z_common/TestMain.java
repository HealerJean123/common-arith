package com.hlj.arith.z_common;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author HealerJean
 * @ClassName TestMain
 * @date 2020/4/22  9:33.
 * @Description
 */
@Slf4j
public class TestMain {

    @Test
    public void test() {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println(combinationSum(candidates, target));
    }
    /**
    * @description
    * @params
    * @return
    * @author  HealerJean
    * @date    2020/9/15 16:44
    */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        method(0, candidates, target, res, linkedList);
        return res;
    }
    /**
    * @description
    * @params
    * @return
    * @author  HealerJean
    * @date    2020/9/15 16:49
    */
    public void method(int index, int[] candidates, int target, List<List<Integer>> res,LinkedList<Integer> linkedList ){
        if (target == 0){
            res.add(new ArrayList<>(linkedList));
        }
        for (int i = index; i < candidates.length; i++) {
            if (target >= candidates[i]){
                linkedList.add(candidates[i]);
                method(i, candidates, target-candidates[i], res, linkedList);
                linkedList.removeLast();
            }
        }
    }
}
