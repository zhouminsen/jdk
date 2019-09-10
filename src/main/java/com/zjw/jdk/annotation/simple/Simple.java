package com.zjw.jdk.annotation.simple;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2019-08-04.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Simple {
    String value() default "默认值是什么：什么都没有";

    String name() default "默认名称是什么：周家伟";
}
