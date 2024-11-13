package com.ohgiraffers.hendlermethod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/request/*")
public class RequestController {

    /* title. 요청 시 값을 전달 받는 방법 */

    /* comment.
    *   view 페이지를 보여주는 방식
    *    1. String 타입 반환값에 view 파일 이름 작성
    *    2. 메소드의 반환 타입을 void
    *      - 반환 타입을 void 로 하게 되면 요청 url 이 view 의 이름이 된다.
    *       (/request/regist(url) 와 templates(View)의 이름이 같아야한다.)
    * */

    /* comment. 메소드 반환 타입을 void */
    @GetMapping("regist")
    public void regist() {}

    /* comment.
    *   1. WebRequest 객체로 요청 파라미터 전달 받기
    *     - 매개 변수 선언부에 WebRequest 객체를 선언하면
    *       해당하는 핸들러 메소드가 호출 시 인자로 값을 전달해준다.
    *       (서블릿을 배울 때 doPost 메소드 내부에 HttpServletRequest)
    *       스프링 프레임 워크는 내부적으로 Servlet 을 사용하고 있기때문에
    *       HttpServletRequest 도 사용이 가능하다.
    *       다만 사용하지 않는 이유는 WebRequest 객체는 Servlet 에 종속적이지 않으나
    *       기능은 모두 포함하고 있기 때문에 추후에 Servlet 기능이 사라졌을 시
    *       적은 수정 범위로 교체할 수 있다는 장점이 있다.
    *       WebRequest 객체는 Spring 측에서 제공하기 때문에 Spring 친화적으로 많이 사용하는 편이다.
    * */
    @PostMapping("regist")
    public String registMenu(Model model, WebRequest request) {

    }

}
