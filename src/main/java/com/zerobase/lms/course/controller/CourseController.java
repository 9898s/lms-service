package com.zerobase.lms.course.controller;

import com.zerobase.lms.admin.dto.CategoryDto;
import com.zerobase.lms.admin.service.CategoryService;
import com.zerobase.lms.course.dto.CourseDto;
import com.zerobase.lms.course.model.CourseParam;
import com.zerobase.lms.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CourseController extends BaseController {
    private final CourseService courseService;
    private final CategoryService categoryService;

    @GetMapping("/course")
    public String list(Model model, CourseParam parameter) {
        List<CourseDto> list = courseService.frontList(parameter);
        model.addAttribute("list", list);

        int courseTotalCount = 0;
        List<CategoryDto> categoryList = categoryService.frontList(CategoryDto.builder().build());
        if (categoryList != null) {
            for (CategoryDto x : categoryList) {
                courseTotalCount += x.getCourseCount();
            }
        }

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("courseTotalCount", courseTotalCount);
        return "course/index";
    }
}
