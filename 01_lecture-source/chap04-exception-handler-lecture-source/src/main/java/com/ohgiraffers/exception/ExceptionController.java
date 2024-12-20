package com.ohgiraffers.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController {

    @GetMapping("nullpointer")
    public String nullPointerException() {

        String str = null;
        // null 값에 참조하게 되면 NullPointerException 이 발생
        System.out.println(str.charAt(0));

        return "/";
    }

    /* comment.
    *   @ExceptionHandler 어노테이션에 우리가 처리하고 싶은
    *   예외 클래스(NullPointerException 등)를 정의하면
    *   정의한 클래스의 예외가 발생 시 nullHandler 메소드가 동작하게된다. */
    @ExceptionHandler(NullPointerException.class)
    public String nullHandler(NullPointerException exception) {

        System.out.println("controller 레벨에서 NullPointer 예외 처리");

        return "error/nullPointer";
    }

    @GetMapping("userexception")
    public String userException() throws MemberNotFoundException {

        boolean check = true;
        if(check){
            throw new MemberNotFoundException("어디갔니..?");
        }

        return "/";
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public String userException(Model model, MemberNotFoundException exception) {

        model.addAttribute("exception", exception);

        return "error/memberNotFound";
    }
}
