package com.ohgiraffers.crudtest.menu.model.service;

import com.ohgiraffers.crudtest.menu.model.dao.MenuMapper;
import com.ohgiraffers.crudtest.menu.model.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {


    private final MenuMapper menuMapper;

    @Autowired
    public MenuService(MenuMapper menuMapper){
        this.menuMapper = menuMapper;
    }
    public List<MenuDTO> allMenu() {

        return menuMapper.allMenu();
    }

    public MenuDTO oneMenu(int menuCode) {

        return menuMapper.oneMenu(menuCode);
    }

}
