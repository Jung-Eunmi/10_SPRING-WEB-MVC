package com.ohgiraffers.crud.menu.model.dto;

import lombok.*;
import org.apache.ibatis.type.Alias;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Alias("category")
public class CategoryDTO {

    private int code;
    private String name;
    private int refCategoryCode;

}
