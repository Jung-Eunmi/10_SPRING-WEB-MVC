package com.ohgiraffers.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
/* comment.
*   클래스 레벨에 RequestMapping 어노테이션 사용 가능하다.
*   공통되는 URL 을 설정해두고 *(와일드카드) 를 이용하면
*   조금 더 포괄적인 URL 패턴을 설정 할 수 있다.
* */
@RequestMapping("/order/*")
public class ClassMappingController {

    /* 1. Class 레벨에 매핑하기 */
    @GetMapping("/regist")
    public String registOrder(Model model){

        model.addAttribute("message", "GET 방식의 주문 등록 핸들러 메소드 호출됨...");

        return "mappingResult";
    }

    /* 2. 여러 URL 매핑하기 */
    // modify, delete 동시에 처리
    @RequestMapping(value = {"modify", "delete"}, method = RequestMethod.POST)
    public String modifyAndDelete(Model model){

        model.addAttribute("message",
                "POST 방식의 수정, 삭제 둘 다 처리하는 핸들러 메소드 호출됨...");

        return "mappingResult";
    }

    /* 3. Path Variable  (url 경로를 타고 오는 값) */
    /* comment.
    *   @PathVariable 어노테이션을 통해 요청 URL 로부터 변수를 받아 올 수 있다.
    *   path variable 로 전달 되는 {변수명} 값은 반드시 매개변수명과 일치해야 한다.
    *   만약 동일하지 않으면 @PathVariable("이름") 을 설정해야 한다.
    * */
                    // 변수로 담아야 함
    @GetMapping("/detail/{orderNo}")
                                           // URL 에 지정한 변수명과 일치 시켜야 함
    public String orderDetail(Model model, @PathVariable int orderNo){

        model.addAttribute("message", orderNo + "번 주문 상세 조회 핸들러 메소드 호출됨...");

        return "mappingResult";
    }

// URL 에 지정한 변수명과 일치 시키고 싶지 않을 때
//    @GetMapping("/detail/{orderNo}")
//    public String orderDetail(Model model, @PathVariable("orderNO") int no){
//
//        model.addAttribute("message", no + "번 주문 상세 조회 핸들러 메소드 호출됨...");
//
//        return "mappingResult";
//    }
    
}