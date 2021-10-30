package org.legend.entity;

public class Student {
    private int stuNo;
    private String stuName;
    private int stuAge;
    private Address address;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Student{" +
                "stuNo=" + stuNo +
                ", stuName='" + stuName + '\'' +
                ", stuAge='" + stuAge + '\'' +
//                ", schoolAddress=" + address.getSchoolAddress() +
//                ", homeAddress=" + address.getHomeAddress() +
                '}';
    }
}
