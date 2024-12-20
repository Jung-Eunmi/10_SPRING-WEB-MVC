package com.ohgiraffers.crudtest.menu.controller;

import com.ohgiraffers.crudtest.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crudtest.menu.model.dto.MenuDTO;
import com.ohgiraffers.crudtest.menu.model.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private final MenuService menuService;
    private final MessageSource messageSource;

    @Autowired
    public MenuController(MenuService menuService, @Qualifier("messageSource") MessageSource messageSource) {
        this.menuService = menuService;
        this.messageSource = messageSource;
    }

    // 전체 메뉴 조회
    @GetMapping("all")
    public String allMenu(Model model){

        List<MenuDTO> menuList = menuService.allMenu();
        for(MenuDTO menu : menuList ){
            System.out.println("menu = " + menu);}

        model.addAttribute("menuList", menuList);

        return "/menu/all";
    }

    // 코드로 메뉴 조회
    @GetMapping("code")
    public void menuCode(){}

    @PostMapping("code")
    public String menu(int menuCode, Model model){

        MenuDTO menu = menuService.oneMenu(menuCode);
        model.addAttribute("menu", menu);
        System.out.println("menu = " + menu);
        return "/menu/menuByCode";
    }

    // 메뉴 추가
    @GetMapping("insert")
    public void insertPage(){}

    @GetMapping(value = "category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findCategoryList(){
        return menuService.findAllCategory();
    }

    @PostMapping("insert")
    public String insertMenu(@ModelAttribute MenuDTO menu, RedirectAttributes rttr, Locale locale) {

        menuService.insertMenu(menu);

        return "redirect:/menu/all";
    }

    // 메뉴 삭제
    @GetMapping("delete")
    public void delete(){}



}
