package org.legend.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "second")
public class SecondSpringMVC {
    @RequestMapping(value = "testExceptionHandler")
    public String testExceptionHandler() {
        System.out.println(1/0);//数学异常
        return "success";
    }
    @RequestMapping(value = "testExceptionHandler2")
    public String testExceptionHandler2() {
        int[] nums = new int[2];
        System.out.println(nums[2]);//越界异常
        return "success";
    }

    /**
     * 类中所有方法发生@ExceptionHandler中指定的异常，都会被@ExceptionHandler标识的handlerArithmeticException方法捕获，
     * 异常信息放入handlerArithmeticException方法的参数中
     *
     * "相当于一个try...catch..."
     * @return
     */
//    @ExceptionHandler({ArithmeticException.class})//数学异常
//    public String handlerArithmeticException(ArithmeticException e) {
//        System.out.println(e);
//        return "error";
//    }
    @ExceptionHandler({ArithmeticException.class, ArrayIndexOutOfBoundsException.class})//数学异常、越界异常
    public ModelAndView handlerArithmeticException(Exception e) {//多个一起收;(只能有e参数！)
        System.out.println("异常 :" + e);
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("e", e);
        return mv;
    }
}
