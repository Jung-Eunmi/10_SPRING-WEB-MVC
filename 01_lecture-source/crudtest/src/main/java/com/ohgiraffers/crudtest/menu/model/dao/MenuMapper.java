package com.ohgiraffers.crudtest.menu.model.dao;

import com.ohgiraffers.crudtest.menu.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<MenuDTO> allMenu();

    MenuDTO oneMenu(int menuCode);

}
