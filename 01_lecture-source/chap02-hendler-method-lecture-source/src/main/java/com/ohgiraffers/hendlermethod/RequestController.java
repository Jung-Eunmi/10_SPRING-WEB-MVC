package com.ohgiraffers.hendlermethod;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/request/*")
public class RequestController {

    /* title. 요청 시 값을 전달 받는 방법 */

    /* comment.
    *   view 페이지를 보여주는 방식
    *    1. String 타입 반환값에 view 파일 이름 작성
    *    2. 메소드의 반환 타입을 void
    *      - 반환 타입을 void 로 하게 되면 요청 url 이 view 의 이름이 된다.
    * */

    @GetMapping("regist")
    public void regist() {

    }

}
