package com.ohgiraffers.hendlermethod;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
@RequestMapping("/request/*")
// 4-2 @SessionAttribute 이용한 값 유지
@SessionAttributes("id")
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
        String menuName = request.getParameter("name");
        int menuPrice = Integer.parseInt(request.getParameter("price"));
        int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));

        String message = menuName + "을/를 신규 메뉴 목록" + categoryCode + "번 카테고리에 " +
                         menuPrice + "원으로 등록 했습니다.";

        // 사용자에게 전달해줄 값이 있을 때 모델에 담아서 view 로 보내줌
        model.addAttribute("message", message);

        return "request/printResult";
    }

    @GetMapping("modify")
    public void modify() {}

    /* comment.
    *   @RequestParam
    *    - 화면에서 요청하는 값을 담아주는 어노테이션이다.
    *      담을 매개변수 앞에 작성하게 되며 form 의 name 속성과 매개변수명을 일치시켜야한다.
    *      만약에 일치시키기 싫으면 @RequestParam("from name 속성") String 사용하고 싶은 변수명을 사용하면된다.
    *      name 속성이 일치하지 않을 때 400-bad request 에러가 발생한다.
    *      이는 required 속성의 기본 값이 ture 이기 때문이다.
    *      이때 required 속성의 값을 false 로 바꿔주게 된다면
    *      해당하는 name 속성이 일치하지 않더라도 error 를 발생시키지 않고 null 로 처리하게된다. */
    @PostMapping("modify")
    public String modifyMenu(Model model, @RequestParam(required = false) String name,
                                          @RequestParam(defaultValue = "0") int price) {

        String message = name + "의 가격을 " + price + "로 수정했습니다.";

        model.addAttribute("message", message);

        return "request/printResult";
    }

    /* comment.
    *   요청 파라미터가 여러 개인 경우 각각 담는 것이 아닌 Map 을 사용해서 한 번에 담을 수 있다.
    *   Map 의 키는 form 태그의 name 속성 값이 된다.
    * */
    @PostMapping("modifyAll")
    public String modifyAll(Model model, @RequestParam Map<String, String> parameters) {

        String menuName = parameters.get("modifyName2");
        int menuPrice = Integer.parseInt(parameters.get("modifyPrice2"));

        String message = menuName + "의 가격을 " + menuPrice + "로 수정했습니다.";

        model.addAttribute("message", message);

        return "request/printResult";
    }

    @GetMapping("search")
    public void search(){}

    // 값을 꺼내올 것이 많을 때 쓰는 방법! 제일 중요
    /* comment.
    *   지금은 요청하는 parameter 가 몇 개 안되서 
    *   @RequestParam 어노테이션으로 사용해도 간단하게 작성 할 수 있다.
    *   하지만 받아 올 데이터가 많아진다면 관리 할 변수나, key 값이 많아 질 수 밖에 없다.
    *   @ModelAttribute 객체를 생성하여 요청되는 값을 필드와 form 태그의 name 속성과 비교하여 값을 넣어준다.
    *   ( form 태그의 name 속성과 필드 이름을 일치시켜줘야 @ModelAttribute 에서 값을 가져와줄 수 있다)
    *   @ModelAttribute 담은 값은 view 페이지에서 타입(자료형) 앞글자를 소문자로 한
    *   네이밍 규칙으로 사용할 수 있다.(MenuDTO -> menuDTO)
    *   다른 이름을 사용하고 싶다면 @ModelAttribute("사용할 값") 이렇게 지정도 할 수 있다.
    * */
    @PostMapping("search")
    public String searchMenu(@ModelAttribute MenuDTO menu) {

        System.out.println("menu = " + menu);
        
        return "request/searchResult";
    }

    @GetMapping("login")
    public void login() {}

    /* comment. HttpSession 객체 이용해서 요청 값 저장하기 */
    @PostMapping("login1")
    public String sessionTest(HttpSession session, @RequestParam String id) {

        session.setMaxInactiveInterval(60*60*24);
        session.setAttribute("id", id);

        return "request/loginResult";
    }

    @GetMapping("logout1")
    public String logout1(HttpSession session){
        // 세션 강제 만료
        session.invalidate();

        return "request/loginResult";
    }

    /* comment.
    *   @SessionAttributes 를 이용한 session 에 값 담기
    *   클래스 레벨에 @SessionAttributes 을 사용하여
    *   session 에 담을 key 값을 설정해두면
    *   Model 영역에 해당 key 로 값이 추가되는 경우 자동으로 session 에 등록해준다. */
    @PostMapping("login2")
    public String sessionTest2(Model model, @RequestParam String id) {

                        // 클래스에 @SessionAttributes 어노테이션에 부여한 이름과 동일해야함
        model.addAttribute("id", id);

        return "request/loginResult";
    }

    /* comment.
    *   SessionAttributes 방식은 session 의 상태를 관리하는
    *   SessionStatus 객체의 setComplete() 메소드를 사용해야 세션을 강제로 만료시킬 수 있다. */
    @GetMapping("logout2")
    public String logout2(SessionStatus sessionStatus){

        sessionStatus.setComplete();

        return "request/loginResult";
    }

    @GetMapping("body")
    public void body() {}


}
