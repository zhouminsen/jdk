package com.zjw.jdk;

import com.zjw.jdk.util.UtilFuns;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/8/29.
 */
public class BigDecimalTest {

    @Test
    public void test() {
        BigDecimal poundage = new BigDecimal(1.34);
        BigDecimal poundage2 = new BigDecimal(1.12);
        System.out.println(poundage.setScale(2, BigDecimal.ROUND_HALF_UP));
        int precision = poundage.precision();
        System.out.println(poundage);
        System.out.println(poundage2);
        System.out.println(precision);

        BigDecimal a = new BigDecimal("0");
        a = a.add(null);
        System.out.println(a);
    }


    @Test
    public void test2() {
        BigDecimal bigDecimal = new BigDecimal("0.00300");
        String s = UtilFuns.numberFormatPercent(bigDecimal, 2);
        System.out.println("得到的百分比:" + s);
    }

    @Test
    public void test3() {
        BigDecimal max = new BigDecimal("100");
        BigDecimal up = new BigDecimal("120");
        BigDecimal min = new BigDecimal("10");
        BigDecimal down = new BigDecimal("1");
        if (max.compareTo(up) < 0) {
            max = up;
        }
        if (min.compareTo(down) > 0) {
            min = down;
        }

        max = max.add(new BigDecimal("30"));
        min = min.subtract(new BigDecimal("30"));
        System.out.println("max:" + max);
        System.out.println("min:" + min);
    }

    @Test
    public void test4() {
        BigDecimal amount=new BigDecimal("13");
        BigDecimal pre = amount;
        amount = new BigDecimal("14");
        System.out.println(pre);
    }


}
