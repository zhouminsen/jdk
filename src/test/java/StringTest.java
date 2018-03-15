import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by zhoum on 2018/1/9.
 */
public class StringTest {

    @Test
    public void  split() {
        String s = "a,b,n,d ";
        String[] split = s.split(",");
        System.out.println(split.length);
        System.out.println(Arrays.toString(split));
        String[] strings = StringUtils.splitPreserveAllTokens(s, ",");
        System.out.println(strings.length);
        System.out.println(Arrays.toString(strings));
    }
}
