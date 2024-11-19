package com.ohgiraffers.thymeleaf;


import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SearchCriteria {
// 검색 조건 정보 관련 클래스
    private int startPage;      // 시작 페이지 번호
    private int endPage;        // 마지막 페이지 번호
    private int currentPage;    // 현재 페이지 번호

}
