package com.zjw.jdk.lambda;

import lombok.Data;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

/**
 * Created by zhoum on 2018/3/14.
 */
public class ReduceTest {

    /**
     * 普通计算
     */
    @Test
    public void test() {
        int sumAll = Arrays.asList(1, 2, 3, 4).stream().reduce(0,
                Integer::sum);
        System.out.println(sumAll);
    }

    /**
     * 统计流
     */
    @Test
    public void test2() {
        IntSummaryStatistics intSummaryStatistics = Arrays.asList(1, 2, 3, 4).stream().mapToInt(e -> e).summaryStatistics();
        System.out.println(intSummaryStatistics.getSum());
    }

    /**
     * 测试实体计算
     */
    @Test
    public void test3() {
        List<Big> aa = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Big big = new Big();
            BigDecimal b = new BigDecimal("0.1");
            big.setMoney(b);
            big.setName("zjw");
            aa.add(big);
        }

        System.out.println(aa.stream().map(e -> e.getMoney()).reduce(BigDecimal::add).get());
        System.out.println(aa.stream().map(e -> e.getMoney()).reduce((e, e2) -> e.add(e2)).get());
        System.out.println(aa.stream().map(e -> e.getMoney()).reduce(BigDecimal.ZERO, BigDecimal::add));
        System.out.println(aa.stream().map(Big::getMoney).reduce(BigDecimal.ZERO, (e, e2) -> e.add(e2)));

    }


    @Data
    private  class Big {
        private BigDecimal money;
        private String name;

    }
}
