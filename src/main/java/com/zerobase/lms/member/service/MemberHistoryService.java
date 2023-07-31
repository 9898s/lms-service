package com.zerobase.lms.member.service;


import com.zerobase.lms.admin.dto.MemberHistoryDto;
import com.zerobase.lms.member.model.MemberHistoryInput;

import java.util.List;

public interface MemberHistoryService {
    /**
     * 로그인 기록 저장
     */
    boolean save(MemberHistoryInput parameter);

    /**
     * 회원 목록 리턴(관리자에서만 사용 가능)
     */
    List<MemberHistoryDto> list(String userId);
}
