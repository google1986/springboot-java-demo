package com.htzw.annotation;

import org.aspectj.weaver.ast.Test;

/**
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/7/18 23:37
 */
class Teacher implements Cloneable{
    private String name;
    private Integer age;

    public Teacher(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

class Student2 implements Cloneable {
    private String name;
    private int age;
    private Teacher teacher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
public class Demo {

    public static void main(String[] args) throws CloneNotSupportedException {
        Teacher teacher = new Teacher("Tom", 26);

        Student2 student1 = new Student2();
        student1.setAge(18);
        student1.setName("Dream");
        student1.setTeacher(teacher);

        Student2 student2 = (Student2) student1.clone();

        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getTeacher().getName());
        System.out.println(student2.getTeacher().getAge());
        teacher.setName("Jack");
        System.out.println(student1.getTeacher().getName());
        System.out.println(student2.getTeacher().getName());
    }

}
