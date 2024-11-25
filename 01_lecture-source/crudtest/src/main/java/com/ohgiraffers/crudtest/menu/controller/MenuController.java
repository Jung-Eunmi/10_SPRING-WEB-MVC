package com.ohgiraffers.crudtest.menu.controller;

import com.ohgiraffers.crudtest.menu.model.dto.MenuDTO;
import com.ohgiraffers.crudtest.menu.model.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/menu/*")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/all")
    public String allMenu(Model model){

        List<MenuDTO> menuList = menuService.allMenu();
        for(MenuDTO menu : menuList ){
            System.out.println("menu = " + menu);}

        model.addAttribute("menuList", menuList);

        return "/menu/all";
    }

    @GetMapping("/code")
    public void menuCode(){}

    @PostMapping("/code")
    public String menu(int menuCode, Model model){

        MenuDTO menu = menuService.oneMenu(menuCode);
        model.addAttribute("menu", menu);
        System.out.println("menu = " + menu);
        return "/menu/menuByCode";
    }

    @GetMapping("/update")
    public void newMenu(){}




}
