package com.hc.distributed.sqlReflect;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * asdas dsadasdas
 */
public class DaoMapperAnnotationdProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<>();
        set.add(DaoMapper.class.getCanonicalName());
        return Collections.unmodifiableSet(set);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }
}
