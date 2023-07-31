package com.zerobase.lms.admin.service;

import com.zerobase.lms.admin.dto.BannerDto;
import com.zerobase.lms.admin.model.BannerInput;
import com.zerobase.lms.admin.model.BannerParam;

import java.util.List;

public interface BannerService {
    /**
     * 배너 목록
     */
    List<BannerDto> list(BannerParam parameter);

    /**
     * 배너 등록
     */
    boolean add(BannerInput parameter);

    /**
     * 배너 상세 정보
     */
    BannerDto getById(long id);

    /**
     * 배너 정보 수정
     */
    boolean set(BannerInput parameter);

    /**
     * 배너 내용 삭제
     */
    boolean del(String idList);

    /**
     * 배너 보여주기 목록
     */
    List<BannerDto> showList(BannerParam parameter);
}
