package com.ohgiraffers.crud.menu.model.dao;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/* comment.
*   Mybatis 의 전용 어노테이션으로 Repository 의
*   더 구체적인 기능(쿼리문이 있는 xml 파일을 읽을 수 있는 기능)을 가진 어노테이션이다. */
@Mapper
public interface MenuMapper {

    List<MenuDTO> findAllMenus();

    List<CategoryDTO> findAllCategory();
}
