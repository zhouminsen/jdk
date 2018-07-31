import lombok.Data;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoum on 2018/6/27.
 */
public class OtherTest {
    @Test
    public void test() {
        Double a = 1.0, b = 0.0;
        System.out.println(a / b);

        System.out.println(5 %3);
    }

    @Test
    public void test2() {
        List<Big> aa = new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            Big big = new Big();
            BigDecimal b = new BigDecimal("0.1");
            big.setMoeny(b);
            big.setName("zjw");
            aa.add(big);
        }

        System.out.println(aa.stream().map(e -> e.getMoeny()).reduce(BigDecimal::add).get());
        System.out.println(aa.stream().map(e -> e.getMoeny()).reduce((e, e2) -> e.add(e2)).get());
        System.out.println(aa.stream().map(e -> e.getMoeny()).reduce(BigDecimal.ZERO, BigDecimal::add));

    }

    @Data
    static  class Big {
        private BigDecimal moeny;
        private String name;

    }
}
