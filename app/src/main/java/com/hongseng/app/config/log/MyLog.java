package com.hongseng.app.config.log;

import java.lang.annotation.*;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-02-04 16:07
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    String value() default "";
}
