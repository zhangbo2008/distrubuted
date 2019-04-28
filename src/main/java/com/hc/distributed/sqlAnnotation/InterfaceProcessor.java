package com.hc.distributed.sqlAnnotation;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.util.*;

public class InterfaceProcessor extends AbstractProcessor {

    private Types typeUtils;
    private Elements elementUtils;
    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        System.err.println("MyProcessor Run");
        super.init(env);
        elementUtils = env.getElementUtils();
        filer = env.getFiler();
        typeUtils = env.getTypeUtils();
        messager = env.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annoations, RoundEnvironment env) {
        System.err.println("MyProcessor Process");
        Map<String, MyAnnotatedClass> classmap = new HashMap<String, MyAnnotatedClass>();

        Set<? extends Element> elementSet = env.getElementsAnnotatedWith(MyAnnotation.class);
        // 获取可执行节点（函数）的方法，遍历所有标记了注解的语法元素
        for (Element e : elementSet) {
            if (e.getKind()!= ElementKind.METHOD) {
                error(e,"错误的注解类型，只有函数能够被该 @%s 注解处理", MyAnnotation.class.getSimpleName());
                return true;
            }

            ExecutableElement element = (ExecutableElement) e;
            // 将解析后的语法元素放置到自定义的数据结构中
            MyAnnotatedMethod mymethod = new MyAnnotatedMethod(element);
            String classname = mymethod.getSimpleClassName();

            // 将解析出的Class进行分类，同一类下的函数都生成一个接口
            MyAnnotatedClass myclass = classmap.get(classname);
            if (myclass == null) {
                PackageElement pkg = elementUtils.getPackageOf(element);
                myclass = new MyAnnotatedClass(pkg.getQualifiedName().toString(), classname);
                myclass.addMethod(mymethod);
                classmap.put(classname,myclass);
            } else
                myclass.addMethod(mymethod);

        }
        // 代码生成
        for (MyAnnotatedClass myclass : classmap.values()) {
            myclass.generateCode(elementUtils, filer);
        }
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> strings = new TreeSet<String>();
        strings.add("com.example.MyAnnotation");
        return strings;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private void error(Element e, String msg, Object... args) {
        messager.printMessage(
                Diagnostic.Kind.ERROR,
                String.format(msg, args),
                e);
    }
}
