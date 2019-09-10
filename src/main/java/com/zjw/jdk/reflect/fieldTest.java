package com.zjw.jdk.reflect;

import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by zhoum on 2018/7/6.
 */
public class fieldTest {
    public static void main(String[] args) throws ClassNotFoundException {
        OrderTableDO orderTableDO = new OrderTableDO();
        orderTableDO.setAddress("湖北省武汉市武昌区徐东大街");
        orderTableDO.setOrderDate(new Date());
        System.out.println(orderTableDO.getByField("order"));
    }


    @Data
    private static class OrderTableDO implements Serializable {

        private static final long serialVersionUID = 4332267039193202191L;
        /**
         * id
         */
        private Integer id;

        /**
         *
         */
        private Date orderDate;

        /**
         *
         */
        private Date orderTime;

        /**
         *
         */
        private String orderId;

        /**
         *
         */
        private String address;
        /**
         *
         */
        private String apiId;
        private String orderName;

        public Object getByField(String field) {
            String str = "";
            String[] split = field.split("_");
            str = split[0];
            if (split.length > 0) {
                for (int i = 1; i < split.length; i++) {
                    String item = split[i];
                    char[] charArray = item.toCharArray();
                    String newStr = "";
                    for (int j = 0; j < charArray.length; j++) {
                        char e = charArray[j];
                        if (j == 0) {
                            newStr += e;
                            newStr = newStr.toUpperCase();
                        } else {
                            newStr += e;
                        }
                    }
                    str += newStr;
                }
            }
            try {
                Field declaredField = this.getClass().getDeclaredField(str);
                Object o = declaredField.get(this);
                return o;
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
