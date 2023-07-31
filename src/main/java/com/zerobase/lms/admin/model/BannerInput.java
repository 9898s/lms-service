package com.zerobase.lms.admin.model;

import lombok.Data;

@Data
public class BannerInput {
    long id;
    String bannerName;
    String bannerUrl;
    int openCase;
    int sortValue;
    boolean showYn;

    String filename;
    String urlFilename;

    // 삭제를 위한
    String idList;
}
