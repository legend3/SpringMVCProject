package org.legend.entity;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.util.Date;

public class Student {
    @NumberFormat(pattern = "###,#")//#:数字，,:占位符
    private Integer stuNo;
    private String stuName;
    private int stuAge;
    @Past(message = "超过了当前日期时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")//格式化:前端传递来的数据，将前端传过来的数据固定为：yyyy/MM/dd
    private Date stuBirthDate;
    @Email(message = "不是一个合法的邮箱地址")//自定义提示语
    private String Email;

    public void setStuNo(Integer stuNo) {
        this.stuNo = stuNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

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

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }

    public Date getStuBirthDate() {
        return stuBirthDate;
    }

    public void setStuBirthDate(Date stuBirthDate) {
        this.stuBirthDate = stuBirthDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuNo=" + stuNo +
                ", stuName='" + stuName + '\'' +
                ", stuAge='" + stuAge + '\'' +
                ", stuBirthDate='" + stuBirthDate + '\'' +
                '}';
    }
}
