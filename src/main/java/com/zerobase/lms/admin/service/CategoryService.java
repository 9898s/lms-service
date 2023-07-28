package com.zerobase.lms.admin.service;

import com.zerobase.lms.admin.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> list();

    /**
     * 카테고리 신규 추가
     */
    boolean add(String categoryName);

    /**
     * 카테고리 수정
     */
    boolean update(CategoryDto parameter);

    /**
     * 카테고리 삭제
     */
    boolean del(long id);
}