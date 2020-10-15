package com.fandou.coffee.learning.springcloud.common.support;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DoLog {
    String value() default "";
}
