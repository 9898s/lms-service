package com.zerobase.lms.course.model;

import com.zerobase.lms.admin.model.CommonParam;
import lombok.Data;

@Data
public class TakeCourseInput extends CommonParam {
    long courseId;
    String userId;
}
