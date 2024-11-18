package com.ohgiraffers.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class HandleInterceptor implements HandlerInterceptor {

    private final MenuService menuService;

    /* comment.
    *   Interceptor 는 Spring 에서 제공하는 기능이기 때문에
    *   우리가 Bean 으로 등록해 둔 객체들을 언제든 참조(의존성주입=@Autowired) 할 수 있다.
    * */

    @Autowired
    public HandleInterceptor(MenuService menuService) {
        this.menuService = menuService;
    }

    /* 전처리 메소드 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("preHandler 호출 됨...");

        long startTime = System.currentTimeMillis();

        request.setAttribute("startTime", startTime);

        /* comment.
        *   true 값을 리턴하면 Interceptor 의 다음 계층 인 Controller 를 호출하게 된다.
        *   false 값을 리턴하면 Controller 의 핸들러 메소드를 호출하지 않게 된다. */
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("postHandler 호출 됨...");

        long startTime = (long) request.getAttribute("startTime");

        long endTime = System.currentTimeMillis();

        modelAndView.addObject("interval", endTime-startTime);
    }

    /* 가장 마지막 view 가 렌더링 된 이후 동작하는 메소드 */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("afterCompletion 호출 됨...");

        menuService.method();
    }

}
