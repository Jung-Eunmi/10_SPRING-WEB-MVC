package com.ohgiraffers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
// 공통으로 들어가는 url 을 클래스에 매핑
@RequestMapping("/lecture/*")
public class LectureController {

    // 요청 받은 경로
    @GetMapping("expression")
    public ModelAndView expression(ModelAndView mv) {

        mv.addObject("member", new MemberDTO("정은미", 30, '여', "경기도 파주시"));

        mv.addObject("hello", "hi~<h2>타임리프</h2>");
        // 요청받은 것을 반환 해 줄 뷰에 대한 경로(우리가 지정 한 이름으로 templates 에 .html 로 만들어야 함)
        mv.setViewName("lecture/expression");

        return mv;
    }

    @GetMapping("conditional")
    public ModelAndView conditional(ModelAndView mv) {

        mv.addObject("num", 1);
        mv.addObject("str", "바나나");

        List<MemberDTO> memberList = new ArrayList<>();
        memberList.add(new MemberDTO("하츄핑", 4, '여', "서울시 노진구"));
        memberList.add(new MemberDTO("시진핑", 76, '남', "베이징 사천구"));
        memberList.add(new MemberDTO("티니핑", 8, '남', "서울시 광진구"));
        memberList.add(new MemberDTO("핑구", 4, '남', "서울시 핑구"));

        mv.addObject("memberList", memberList);

        mv.setViewName("lecture/conditional");

        return mv;
    }

    @GetMapping("etc")
    public ModelAndView etc(ModelAndView mv) {

        SearchCriteria criteria = new SearchCriteria(1, 10, 3);

        // key, value 형식으로 저장 가능하지만
        // key 를 작성하지 않을 시 클래스명 곧 키값이 된다.
        mv.addObject(criteria);

        List<MemberDTO> memberList = new ArrayList<>();
        memberList.add(new MemberDTO("하츄핑", 4, '여', "서울시 노진구"));
        memberList.add(new MemberDTO("시진핑", 76, '남', "베이징 사천구"));
        memberList.add(new MemberDTO("티니핑", 8, '남', "서울시 광진구"));
        memberList.add(new MemberDTO("핑구", 4, '남', "서울시 핑구"));

        mv.addObject("memberList", memberList);

        Map<String, MemberDTO> memberMap = new HashMap<>();
        memberMap.put("1", new MemberDTO("하츄핑", 4, '여', "서울시 노진구"));
        memberMap.put("2", new MemberDTO("시진핑", 76, '남', "베이징 사천구"));
        memberMap.put("3", new MemberDTO("티니핑", 8, '남', "서울시 광진구"));
        memberMap.put("4", new MemberDTO("핑구", 4, '여', "서울시 핑구"));

        mv.addObject("memberMap", memberMap);

        mv.setViewName("lecture/etc");

        return mv;
    }

    @GetMapping("fragment")
    public ModelAndView fragment(ModelAndView mv) {

        mv.addObject("test", "value");
        mv.addObject("test2", "value2");

        // setViewName 을 사용하면 자동으로 resources/templates 가 앞에 붙고 우리가 쓴 경로 끝에 .html 이 붙는다.
        mv.setViewName("lecture/fragment");

        return mv;
    }

}
