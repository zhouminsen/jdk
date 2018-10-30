package com.zjw.jdk.exception;

import org.junit.Test;

/**
 * Created by Administrator on 2018/9/29.
 */
public class ExceptionTest {

    @Test
    public void test() throws PayException {
        try {
            subTest();
        } catch (PayException e) {
            System.out.println(e);
        }
    }

    public void subTest() throws PayException {
        try {
            String aa = null;
            aa.toString();
        } catch (Exception e) {
            throw new PayException("对账异常" + e, e);
        }


    }


    public class PayException extends Exception {
        /**
         * serialVersionUID:（用一句话描述这个变量表示什么）
         *
         * @since Ver 1.1
         */

        private static final long serialVersionUID = -7113587451944555482L;

        public PayException() {
        }

        public PayException(String message) {
            super(message);
        }

        public PayException(String message, Throwable cause) {
            super(message, cause);
        }

        public PayException(Throwable cause) {
            super(cause);
        }

        public PayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }
}
