package com.hc.hcbasic.sqlAnnotation;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * asdas dsadasdas
 */
public class MyAnnotatedMethod {
    private ExecutableElement annotatedMethodElement;
    private String simpleMethodName;
    private String simpleClassName;

    private Class returnsType;
    private Class[] paramsType;

    public MyAnnotatedMethod(ExecutableElement annotatedMethodElement) {
        this.annotatedMethodElement = annotatedMethodElement;
        simpleMethodName = annotatedMethodElement.getSimpleName().toString();
        TypeElement parent = (TypeElement) annotatedMethodElement.getEnclosingElement();
        simpleClassName = parent.getQualifiedName().toString();

    }

    public ExecutableElement getAnnotatedMethodElement() {
        return annotatedMethodElement;
    }

    public String getSimpleMethodName() {
        return simpleMethodName;
    }

    public String getSimpleClassName() {
        return simpleClassName;
    }
}
