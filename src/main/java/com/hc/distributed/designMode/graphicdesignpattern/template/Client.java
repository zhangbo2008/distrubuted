package com.hc.distributed.designMode.graphicdesignpattern.template;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 1:05
 */
public class Client {
    public static void main(String[] args) {
        Student student = new Student();
        student.prepareGotoSchool();

        Teacher teacher = new Teacher();
        teacher.prepareGotoSchool();
    }
}
