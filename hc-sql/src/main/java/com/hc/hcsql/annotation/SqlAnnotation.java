package com.hc.hcsql.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SqlAnnotation {
    @AliasFor("sql")
    String value() default "";

    @AliasFor("value")
    String sql() default "";
}