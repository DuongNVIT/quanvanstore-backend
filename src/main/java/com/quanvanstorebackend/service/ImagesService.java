package com.quanvanstorebackend.service;

import com.quanvanstorebackend.entity.ImagesEntity;
import com.quanvanstorebackend.repository.ImagesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagesService extends BaseService<ImagesRepository, ImagesEntity> {
    public ImagesEntity uploadImage(ImagesEntity imageToAdd) {
        return save(imageToAdd);
    }

    public void updateSelected(Long imageId) {
        ImagesEntity imagesEntity = findById(imageId);
        imagesEntity.setSelected(!imagesEntity.getSelected());
        update(imagesEntity);
    }

    public void deleteImage(Long imageId) {
        ImagesEntity imagesEntity = findById(imageId);
        imagesEntity.setDeleted(true);
        update(imagesEntity);
    }

    public List<ImagesEntity> getAllSelectedImage() {
        return repository.findBySelectedTrueAndDeletedFalse();
    }

    public List<ImagesEntity> getAll() {
        return findAllAndDeletedFalse();
    }
}
