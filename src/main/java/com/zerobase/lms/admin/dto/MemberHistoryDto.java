package com.zerobase.lms.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberHistoryDto {
    Long id;

    String userId;
    LocalDateTime logDt;
    String ip;
    String agent;

    public String getLogDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return logDt != null ? logDt.format(formatter) : "";
    }
}
