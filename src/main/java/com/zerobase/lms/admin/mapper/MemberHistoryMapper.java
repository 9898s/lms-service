package com.zerobase.lms.admin.mapper;

import com.zerobase.lms.admin.dto.MemberHistoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberHistoryMapper {
    List<MemberHistoryDto> selectList(String userId);
}
