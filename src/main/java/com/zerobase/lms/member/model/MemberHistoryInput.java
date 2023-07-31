package com.zerobase.lms.member.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Data
public class MemberHistoryInput {
    String userId;
    LocalDateTime logDt;
    String ip;
    String agent;
}
