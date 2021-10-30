package org.legend.entity;

public class Student {
    private int stuNo;
    private String stuName;
    private int stuAge;

    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getAge() {
        return stuAge;
    }

    public void setAge(int age) {
        stuAge = age;
    }



    @Override
    public String toString() {
        return "Student{" +
                "stuNo=" + stuNo +
                ", stuName='" + stuName + '\'' +
                ", stuAge='" + stuAge + '\'' +
                '}';
    }
}
