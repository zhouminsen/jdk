package com.zjw.jdk;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhoum on 2018/1/9.
 */
public class StringTest {

    @Test
    public void split() {
        String s = "a,b,n,d ";
        String[] split = s.split(",");
        System.out.println(split.length);
        System.out.println(Arrays.toString(split));
        String[] strings = StringUtils.splitPreserveAllTokens(s, ",");
        System.out.println(strings.length);
        System.out.println(Arrays.toString(strings));
        System.out.println(77 / 2);
    }


    @Test
    public void intern() {
        String s1 = new StringBuilder("ja").append("va1").toString();
        System.out.println(s1.intern() == s1);
    }

    @Test
    public void test() {
        String a = "1";
        String a2 = "2";
        String a3 = "3";
        String s = a + a2 + a3;
        System.out.println(s);
    }

    @Test
    public void test2() {
        String aa = "{\"qrCode\":\"https://qr.alipay.com/bax07306eoxwbe02utje0039 \n" +
                "\n" +
                "\",\"transactionId\":\"0_20180711170141468\"}";
        Map<String, String> bb = new HashMap<>();
        bb.put("123456789012", aa);
        System.out.println(JSON.toJSONString(bb));
    }

    @Test
    public void test3() {
        Map<String, Object> resultMap = JSON.parseObject("{\"trade_state\":\"1\",\"trans_channel\":\"alipay_qr_p\",\"partner\":\"12038387\",\"out_trade_no\":\"0_20181017142404367\",\"transaction_id\":\"20181017666330404\",\"total_fee\":\"10.02\",\"fee_type\":\"1\",\"time_end\":\"20181017 14:22:24\",\"returnMsg\":\"{}\",\"service\":\"pay_service\",\"service_version\":\"1.1\",\"input_charset\":\"UTF-8\",\"sign_type\":\"MD5\",\"sign\":\"2aa9f62418a71b963d9660cb5e9a3cb2\",\"returnSerNo\":\"18101714222415636095\",\"retCode\":\"0000\",\"retMsg\":\"ACQ.SECONDARY_MERCHANT_NOT_MATCH-商户号归属不正确，请核对\"}");
        PayResultModel resultModel = new PayResultModel();
        if (resultMap.get("trade_state") != null) {
            int trade_state = Integer.parseInt(resultMap.get("trade_state").toString());
            if (trade_state == 1) {
                if (resultMap.get("retMsg") != null) {
                    resultModel.set(false, (String) resultMap.get("retMsg"));
                    System.out.println(resultModel);
                }
                resultModel.set(false, (String) resultMap.get("returnMsg"));
                System.out.println(resultModel);
            }
        }
    }

    public class PayResultModel implements Serializable {

        /**
         * @since Ver 1.1
         */

        private static final long serialVersionUID = -4762604305909648021L;

        public PayResultModel() {
            success = true;
        }

        public PayResultModel(Boolean success, String result) {
            this.success = success;
            this.result = result;
        }

        /**
         * 是否支付成功
         */
        private Boolean success;

        /**
         * 返回的信息
         */
        private String result;


        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public void set(boolean success, String result) {
            this.success = success;
            this.result = result;
        }

        @Override
        public String toString() {
            return "PayResultModel{" +
                    "success=" + success +
                    ", result='" + result + '\'' +
                    '}';
        }
    }
}
