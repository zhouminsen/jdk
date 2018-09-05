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
        System.out.println(poundage.setScale(2, BigDecimal.ROUND_HALF_UP ));
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
}
