package com.doyouknow.project.common;

import org.springframework.data.domain.Page;

public class Pagenation {

    public static PagingButton getPagingButtonInfo(Page page){

        /*공부용 주석*/
        /*Page 객체에서 페이지 번호 가져와서 사용, 0부터 시작하기 때문에 1을 더하기*/
        int currentPage = page.getNumber() + 1;
        /*기본 버튼 개수 설정*/
        int defaultButtonCount = 10;

        /*시작 페이지 계산 = 계산된 값에서 1을 빼고 기본 버튼 개수를 곱한 후 1을 더해 실제 시작 페이지를 구합 */
        int startPage = (int) (Math.ceil((double) currentPage / defaultButtonCount)- 1)*defaultButtonCount+1;
        /*끝 페이지 계산*/
        int endPage = startPage + defaultButtonCount -1;

        /*끝 페이지 번호가 전체 페이지 수를 초과하지 않도록 조정*/
        if(page.getTotalPages() < endPage) endPage = page.getTotalPages();

        /* 전체페이지 수가 0이면서 끝페이지가 0일 경우, 끝 페이지를 시작 페이지와 동일하게 설정*/
        if(page.getTotalPages() == 0 && endPage == 0) endPage = startPage;

        /*결과 값 반환*/
        return new PagingButton((currentPage), startPage, endPage);
    }
}
