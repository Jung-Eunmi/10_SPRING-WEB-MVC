package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/menu/*")
public class MenuController {

    // MenuService 와 @Autowired 로 상호 연관이 생김
    private final MenuService menuService;
    /* bean 으로 등록 한 메세지 소스 사용 */
    private final MessageSource messageSource;

    @Autowired
    public MenuController(MenuService menuService, MessageSource messageSource) {
        this.menuService = menuService;
        this.messageSource = messageSource;
    }

    @GetMapping("list")
    public String findMenuList(Model model) {


        // 전체 메뉴 조회는 MenuDTO 타입이 여러 개 이기 때문에 List
        List<MenuDTO> menuList = menuService.findAllMenus();

        // DB 조회 값이 잘 들어있는지 확인용
        for(MenuDTO menu : menuList) {
            System.out.println("menu = " + menu);
        }

        model.addAttribute("menuList", menuList);

        return "menu/list";
    }

    @GetMapping("regist")
    public void registPage() {}

    // java 가 아닌 javascript 로 보내야하기 때문에 따로 지정해줘야 함
    /* comment. json -> 자바스크립트 객체 표기법을 의미한다.
    *           ex) name : 3 이런 식으로 String 값으로 데이터가 넘어간다. */
    @GetMapping(value="category", produces="application/json; charset=UTF-8")
    /* comment.
    *   @ResponseBody 어노테이션
    *    기존의 Controller 의 역할은 view 를 마지막에 리턴하는 것이 의무이다.
    *    하지만, @ResponseBody 는 view 를 리턴하는 의무가 아닌 데이터를 리턴할 수 있게 만든다. */
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {
        return menuService.findAllCategory();
    }

    @PostMapping("regist")
    public String registMenu(@ModelAttribute MenuDTO newMenu, RedirectAttributes rttr, Locale locale) {
        /* comment.
        *   @ModelAttribute : form 태그로 묶어서 넘어오는 값을 클래스 자료형에
        *   담기위해 작성하는 어노테이션
        *   RedirectAttributes : 리다이렉트 시 저장할 값이 있으면 사용하는 객체 */

        menuService.registMenu(newMenu);
        rttr.addFlashAttribute("successMessage",
                messageSource.getMessage("regist", new Object[]{newMenu.getName(), newMenu.getPrice()}, locale));

        return "redirect:/menu/list";
    }

    @GetMapping("code")
    public String menuByCode(@RequestParam String code, Model model) {

        MenuDTO menu = menuService.selectMenuCode(code);

        model.addAttribute("menu", menu);

        return "/menu/code";
    }
}
