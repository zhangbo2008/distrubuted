package com.hc.distributed.sqlReflect;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface SqlParam {
    String value();
}
