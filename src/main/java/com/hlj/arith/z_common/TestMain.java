package com.hlj.arith.z_common;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

/**
 * @author HealerJean
 * @ClassName TestMain
 * @date 2020/4/22  9:33.
 * @Description
 */
@Slf4j
public class TestMain {


    @Test
    public void test1() {
        System.out.println("现在的待还额度");
        Long sum = 920000000L + 2980000000L + 1700000000L + 1400000000L + 4121520000L + 1274000000L + 0L + 305603800L;
        System.out.println(sum);
    }

    @Test
    public void test2() {

        Long departLoan =
+ 920000000L
+ 2500000000L
+ 1700000000L
+ 480000000L
+ 602000000L
+ 323000000L
+ 300000000L
+ 219000000L
+ 202000000L
+ 151000000L
+ 506000000L
+ 212000000L
+305603800L
+1274000000L
                ;


        System.out.println("departLoan：" +departLoan); //9694603800L
        Long shengyu =  45000000000L  -25000000000L -departLoan ;
        System.out.println(shengyu);


    }

    @Test
    public void test3(){

        Long companyLoan =
920000000L+
2500000000L+
1700000000L+
480000000L+
602000000L+
323000000L+
300000000L+
219000000L+
202000000L+
151000000L+
506000000L+
212000000L+
305603800L+
1274000000L
        ;
        System.out.println(companyLoan); //9694603800
    }
}
