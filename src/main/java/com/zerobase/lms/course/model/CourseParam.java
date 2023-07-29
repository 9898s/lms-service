package com.zerobase.lms.course.model;

import com.zerobase.lms.admin.model.CommonParam;
import lombok.Data;

@Data
public class CourseParam extends CommonParam {
    long id; // course.id
    long categoryId;
}
