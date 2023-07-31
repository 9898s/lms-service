package com.zerobase.lms.admin.dto;

import com.zerobase.lms.admin.entity.Banner;
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
public class BannerDto {
    Long id;
    String bannerName;
    String bannerUrl;
    int openCase;
    int sortValue;
    boolean showYn;
    LocalDateTime regDt;

    String filename;
    String urlFilename;

    long totalCount;
    long seq;

    public String getRegDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return regDt != null ? regDt.format(formatter) : "";
    }

    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .bannerName(banner.getBannerName())
                .bannerUrl(banner.getBannerUrl())
                .openCase(banner.getOpenCase())
                .sortValue(banner.getSortValue())
                .showYn(banner.isShowYn())
                .regDt(banner.getRegDt())
                .filename(banner.getFilename())
                .urlFilename(banner.getUrlFilename())
                .build();
    }
}
