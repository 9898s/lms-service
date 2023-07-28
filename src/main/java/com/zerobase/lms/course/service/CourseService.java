package com.zerobase.lms.course.service;

import com.zerobase.lms.course.dto.CourseDto;
import com.zerobase.lms.course.model.CourseInput;
import com.zerobase.lms.course.model.CourseParam;

import java.util.List;

public interface CourseService {
    /**
     * 강좌 등록
     */
    boolean add(CourseInput parameter);

    /**
     * 강좌 정보 수정
     */
    boolean set(CourseInput parameter);

    /**
     * 강좌 목록
     */
    List<CourseDto> list(CourseParam parameter);

    /**
     * 강좌 상세 정보
     */
    CourseDto getById(long id);
}
