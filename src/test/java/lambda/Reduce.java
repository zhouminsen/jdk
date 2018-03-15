package lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;

/**
 * Created by zhoum on 2018/3/14.
 */
public class Reduce {

    @Test
    public void test() {
        int sumAll = Arrays.asList(1, 2, 3, 4).stream().reduce(0,
                Integer::sum);
        System.out.println(sumAll);
    }

    @Test
    public void test2() {
        IntSummaryStatistics intSummaryStatistics = Arrays.asList(1, 2, 3, 4).stream().mapToInt(e -> e).summaryStatistics();
        System.out.println(intSummaryStatistics.getSum());
    }

}
