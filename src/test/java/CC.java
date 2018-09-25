import com.alibaba.fastjson.JSON;

/**
 * Created by zhoum on 2018/8/2.
 */
public class CC {

    public static void main(String[] args) {
        Object parse = JSON.parse("{\"routing\":\"APP\",\"alias\":[\"92c542b05c814bc458b940c6ca45811a\"],\"plantform\":3,\"message\":\"运单号:300044611993,金额:89元\"}");
        System.out.println(parse);
    }
}
