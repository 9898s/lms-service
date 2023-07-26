package com.zerobase.lms.member.service;

import com.zerobase.lms.member.model.MemberInput;

public interface MemberService {
    boolean register(MemberInput parameter);

    /**
     * uuid에 해당하는 계정을 활성화 함.
     */
    boolean emailAuth(String uuid);
}
