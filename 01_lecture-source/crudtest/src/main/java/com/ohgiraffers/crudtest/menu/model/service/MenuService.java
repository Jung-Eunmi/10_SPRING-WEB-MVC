package com.ohgiraffers.crudtest.menu.model.service;

import com.ohgiraffers.crudtest.menu.model.dao.MenuMapper;
import com.ohgiraffers.crudtest.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crudtest.menu.model.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {


    private final MenuMapper menuMapper;

    @Autowired
    public MenuService(MenuMapper menuMapper){
        this.menuMapper = menuMapper;
    }

    // 전체 조회
    public List<MenuDTO> allMenu() {

        return menuMapper.allMenu();
    }

    // 메뉴코드로 메뉴조회
    public MenuDTO oneMenu(int menuCode) {

        return menuMapper.oneMenu(menuCode);
    }

    public List<CategoryDTO> findAllCategory() {

        return menuMapper.findAllCategory();
    }

    @Transactional
    public void insertMenu(MenuDTO menu) {

        menuMapper.insertMenu(menu);
    }


}
