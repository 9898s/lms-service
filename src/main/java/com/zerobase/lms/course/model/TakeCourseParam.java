package com.zerobase.lms.course.model;

import com.zerobase.lms.admin.model.CommonParam;
import lombok.Data;

@Data
public class TakeCourseParam extends CommonParam {
    long id;
    String status;
}
