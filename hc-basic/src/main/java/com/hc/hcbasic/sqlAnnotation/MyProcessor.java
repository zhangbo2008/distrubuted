package com.hc.hcbasic.sqlAnnotation;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.LinkedHashSet;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes(value = {"MyProcessor"})
public class MyProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }

    /**
     * 在这个方法里面你必须指定哪些注解应该被注解处理器注册。注意，它的返回值是一个String集合，
     * 包含了你的注解处理器想要处理的注解类型的全称。换句话说，你在这里定义你的注解处理器要处理哪些注解。
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add("MyProcessor");
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }
}
