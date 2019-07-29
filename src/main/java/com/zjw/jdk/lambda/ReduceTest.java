package com.zjw.jdk.lambda;

import lombok.Data;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
        System.out.println(intSummaryStatistics.getMax());
    }

    /**
     * 测试实体计算
     */
    @Test
    public void test3() {
        List<Big> aa = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Big big = new Big(new BigDecimal("0.1"), "zjw");
            aa.add(big);
        }
        aa.stream().collect(Collectors.toList());
        System.out.println(aa.stream().map(e -> e.getMoney()).reduce(BigDecimal::add).get());
        System.out.println(aa.stream().map(e -> e.getMoney()).reduce((e, e2) -> e.add(e2)).get());
        System.out.println(aa.stream().map(e -> e.getMoney()).reduce(BigDecimal.ZERO, BigDecimal::add));
        System.out.println(aa.stream().map(Big::getMoney).reduce(BigDecimal.ZERO, (e, e2) -> e.add(e2)));

    }

    /**
     * 分组
     */
    @Test
    public void group() {
        List<Big> bigList = new ArrayList<>();
        bigList.add(new Big(BigDecimal.ONE, "周家伟"));
        bigList.add(new Big(BigDecimal.ONE, "周家伟"));
        bigList.add(new Big(BigDecimal.ZERO, "家伟"));
        bigList.add(new Big(BigDecimal.ONE, "周家伟"));
        bigList.add(new Big(BigDecimal.TEN, "周伟"));
        bigList.add(new Big(new BigDecimal("110"), "周家伟"));
        bigList.add(new Big(BigDecimal.ONE, "周家伟"));
        Map<String, List<Big>> collect = bigList.stream().collect(Collectors.groupingBy(Big::getName));
        System.out.println(collect);
    }


    @Data
    private class Big {

        public Big(BigDecimal money, String name) {
            this.money = money;
            this.name = name;
        }

        private BigDecimal money;
        private String name;

        public BigDecimal getMoney() {
            return money;
        }

        public void setMoney(BigDecimal money) {
            this.money = money;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
