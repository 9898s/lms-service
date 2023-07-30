package com.zerobase.lms.course.controller;

import com.zerobase.lms.common.model.ResponseResult;
import com.zerobase.lms.common.model.ResponseResultHeader;
import com.zerobase.lms.course.model.ServiceResult;
import com.zerobase.lms.course.model.TakeCourseInput;
import com.zerobase.lms.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class ApiCourseController extends BaseController {
    private final CourseService courseService;

    @PostMapping("/api/course/req.api")
    public ResponseEntity<?> course(Model model, @RequestBody TakeCourseInput parameter, Principal principal) {
        parameter.setUserId(principal.getName());

        ServiceResult result = courseService.req(parameter);
        if (!result.isResult()) {
            ResponseResult responseResult = new ResponseResult(false, result.getMessage());
            return ResponseEntity.ok().body(responseResult);
        }
        ResponseResult responseResult = new ResponseResult(true);
        return ResponseEntity.ok().body(responseResult);
    }
}
