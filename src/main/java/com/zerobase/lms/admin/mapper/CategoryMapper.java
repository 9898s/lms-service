package com.zerobase.lms.admin.mapper;

import com.zerobase.lms.admin.dto.CategoryDto;
import com.zerobase.lms.admin.dto.MemberDto;
import com.zerobase.lms.admin.model.MemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<CategoryDto> select(CategoryDto parameter);
}
