package org.legend.converter;


import org.legend.entity.Student;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class MyConverter implements Converter<String, Student> {
    @Override
    public Student convert(String source) {//source(2-zs-23)字符串转为Student
        //source接受前端传来的String:2-zs-23
        String[] array = source.split("-");

        Student student = new Student();
//        student.setStuNo(Integer.parseInt(array[0]));
//        student.setStuName(array[1]);
//        student.setAge(Integer.parseInt(array[2]));
        return student;
    }
}
