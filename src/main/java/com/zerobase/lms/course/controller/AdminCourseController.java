package com.zerobase.lms.course.controller;

import com.zerobase.lms.admin.model.MemberParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AdminCourseController {
    @GetMapping("/admin/course/list.do")
    public String list(Model model, MemberParam parameter) {
        return "admin/course/list";
    }
}
