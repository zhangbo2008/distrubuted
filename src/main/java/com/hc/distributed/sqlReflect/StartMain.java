package com.hc.distributed.sqlReflect;

import java.lang.annotation.Annotation;

/**
 * asdas dsadasdas
 */
public class StartMain {
    public static void main(String[] args) {
        Annotation[] annotations = StartMain.class.getAnnotations();
//        Annotation[] annotations = UserDao.class.getAnnotationsByType(DaoMapper.class);
        for (Annotation annotation :
                annotations) {
            System.out.println(annotation);
        }
    }
}
