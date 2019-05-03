package com.hc.hcbasic.sqlAnnotation;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * asdas dsadasdas
 */
public class MyAnnotatedClass {
    private String className;
    private String packageName;
    private List<MyAnnotatedMethod> methods = new LinkedList<MyAnnotatedMethod>();

    public MyAnnotatedClass(String packageName, String className) {
        this.className = className;
        this.packageName = packageName;
    }

    public void generateCode(Elements elementUtils, Filer filer) {
        TypeSpec.Builder myinterface = TypeSpec.interfaceBuilder("I" + className)
                .addModifiers(Modifier.PUBLIC);
        for (MyAnnotatedMethod m : methods) {
            MethodSpec.Builder mymethod =
                    MethodSpec.methodBuilder(m.getSimpleMethodName())
                            .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
                            .returns(TypeName.get(m.getAnnotatedMethodElement().getReturnType()));

            int i = 1;
            for (VariableElement e : m.getAnnotatedMethodElement().getParameters()) {
                mymethod.addParameter(TypeName.get(e.asType()),"param"+String.valueOf(i));
                ++i;
            }
            myinterface.addMethod(mymethod.build());
        }
        JavaFile javaFile = JavaFile.builder(packageName, myinterface.build()).build();
        try {
            javaFile.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addMethod(MyAnnotatedMethod mymethod) {
        methods.add(mymethod);
    }
}
