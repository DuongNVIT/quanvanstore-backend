package com.quanvanstorebackend.service;

import com.quanvanstorebackend.entity.BannerImageEntity;
import com.quanvanstorebackend.repository.BannerImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerImageService extends BaseService<BannerImageRepository, BannerImageEntity> {
    public BannerImageEntity uploadBanner(BannerImageEntity bannerImage) {
        return save(bannerImage);
    }

    public List<BannerImageEntity> getAllBanner() {
        return findAllAndDeletedFalse();
    }

    public void updateBanner(Long bannerId) {
        BannerImageEntity activeBanner = repository.findBySelectedTrueAndDeletedFalse();
        if(activeBanner != null) {
            activeBanner.setSelected(false);
            update(activeBanner);
        }
        BannerImageEntity newActiveBanner = findById(bannerId);
        newActiveBanner.setSelected(true);
        update(newActiveBanner);
    }

    public BannerImageEntity getCurrentBanner() {
        return repository.findBySelectedTrueAndDeletedFalse();
    }
}
