package com.zerobase.lms.admin.service;

import com.zerobase.lms.admin.dto.BannerDto;
import com.zerobase.lms.admin.entity.Banner;
import com.zerobase.lms.admin.mapper.BannerMapper;
import com.zerobase.lms.admin.model.BannerInput;
import com.zerobase.lms.admin.model.BannerParam;
import com.zerobase.lms.admin.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    @Override
    public List<BannerDto> list(BannerParam parameter) {
        long totalCount = bannerMapper.selectListCount(parameter);
        List<BannerDto> list = bannerMapper.selectList(parameter);

        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (BannerDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }
        return list;
    }

    @Override
    public boolean add(BannerInput parameter) {
        Banner banner = Banner.builder()
                .bannerName(parameter.getBannerName())
                .bannerUrl(parameter.getBannerUrl())
                .openCase(parameter.getOpenCase())
                .sortValue(parameter.getSortValue())
                .showYn(parameter.isShowYn())
                .regDt(LocalDateTime.now())
                .filename(parameter.getFilename())
                .urlFilename(parameter.getUrlFilename())
                .build();

        bannerRepository.save(banner);
        return true;
    }

    @Override
    public BannerDto getById(long id) {
        return bannerRepository.findById(id).map(BannerDto::of).orElse(null);
    }

    @Override
    public boolean set(BannerInput parameter) {
        Optional<Banner> optionalBanner = bannerRepository.findById(parameter.getId());
        if (!optionalBanner.isPresent()) {
            return false;
        }

        Banner banner = optionalBanner.get();
        banner.setBannerName(parameter.getBannerName());
        banner.setBannerUrl(parameter.getBannerUrl());
        banner.setOpenCase(parameter.getOpenCase());
        banner.setSortValue(parameter.getSortValue());
        banner.setShowYn(parameter.isShowYn());
        banner.setFilename(parameter.getFilename());
        banner.setUrlFilename(parameter.getUrlFilename());
        bannerRepository.save(banner);
        return true;
    }

    @Override
    public boolean del(String idList) {
        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }

                if (id > 0) {
                    bannerRepository.deleteById(id);
                }
            }
        }
        return true;
    }

    @Override
    public List<BannerDto> showList(BannerParam parameter) {
        return bannerMapper.selectShowList(parameter);
    }
}
