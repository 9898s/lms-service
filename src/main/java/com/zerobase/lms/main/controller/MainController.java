package com.zerobase.lms.main.controller;

import com.zerobase.lms.admin.dto.BannerDto;
import com.zerobase.lms.admin.model.BannerParam;
import com.zerobase.lms.admin.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final BannerService bannerService;

    @RequestMapping("/")
    public String index(Model model, BannerParam parameter) {
        List<BannerDto> bannerList = bannerService.showList(parameter);

        model.addAttribute("list", bannerList);
        return "index";
    }

    @RequestMapping("/error/denied")
    public String errorDenied() {
        return "/error/denied";
    }
}
