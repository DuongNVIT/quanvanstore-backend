package com.quanvanstorebackend.service;

import com.quanvanstorebackend.entity.GeneralInforEntity;
import com.quanvanstorebackend.repository.GeneralInforRepository;
import org.springframework.stereotype.Service;

@Service
public class GeneralInforService extends BaseService<GeneralInforRepository, GeneralInforEntity> {
    public GeneralInforEntity uploadInfor(GeneralInforEntity generalInforEntity) {
        return save(generalInforEntity);
    }

    public void updateInfor(GeneralInforEntity generalInforEntity) {
        update(generalInforEntity);
    }

    public GeneralInforEntity getInfor() {
        return repository.findFirstByDeletedFalse();
    }
}
