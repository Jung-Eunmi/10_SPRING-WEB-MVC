package com.ohgiraffers.crudtest.menu.model.dao;

import com.ohgiraffers.crudtest.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crudtest.menu.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    // 전체 조회
    List<MenuDTO> allMenu();

    // 메뉴코드로 메뉴조회
    MenuDTO oneMenu(int menuCode);

    // 메뉴 추가
    List<CategoryDTO> findAllCategory();

    void insertMenu(MenuDTO menu);


}
