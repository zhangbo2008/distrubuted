package com.hc.hcbasic.sqlReflect;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface SqlParam {
    String value();
}
