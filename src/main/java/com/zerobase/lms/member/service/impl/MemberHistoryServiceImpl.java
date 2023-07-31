package com.zerobase.lms.member.service.impl;

import com.zerobase.lms.admin.dto.MemberHistoryDto;
import com.zerobase.lms.admin.mapper.MemberHistoryMapper;
import com.zerobase.lms.member.entity.MemberHistory;
import com.zerobase.lms.member.model.MemberHistoryInput;
import com.zerobase.lms.member.repository.MemberHistoryRepository;
import com.zerobase.lms.member.service.MemberHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberHistoryServiceImpl implements MemberHistoryService {
    private final MemberHistoryRepository memberHistoryRepository;

    private final MemberHistoryMapper memberHistoryMapper;

    @Override
    public boolean save(MemberHistoryInput parameter) {
        MemberHistory memberHistory = MemberHistory.builder()
                .userId(parameter.getUserId())
                .logDt(parameter.getLogDt())
                .ip(parameter.getIp())
                .agent(parameter.getAgent())
                .build();

        memberHistoryRepository.save(memberHistory);
        return true;
    }

    @Override
    public List<MemberHistoryDto> list(String userId) {
        return memberHistoryMapper.selectList(userId);
    }
}
