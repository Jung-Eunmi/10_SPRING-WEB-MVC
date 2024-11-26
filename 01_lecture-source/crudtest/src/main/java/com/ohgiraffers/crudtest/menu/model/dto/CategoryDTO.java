package com.ohgiraffers.crudtest.menu.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CategoryDTO {
    private int code;
    private String name;
    private int refCategoryCode;
}
