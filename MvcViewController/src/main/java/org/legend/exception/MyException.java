package org.legend.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ControllerAdvice注释的类，处理任何类中的异常
 * 1.充当springmvc项目中专门处理异常的类
 * 2.mvc能扫描到此bean
 */
@ControllerAdvice
public class MyException {//不是控制器，仅仅是用于处理异常
    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView handlerArithmeticException(Exception e) {
        System.out.println(e + "======" + "g该@ControllerAdvice中的异常处理方法，可以处理任何类中的异常");
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("e", e);
        return mv;
    }
}
