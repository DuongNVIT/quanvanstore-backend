package com.quanvanstorebackend.service;

import com.quanvanstorebackend.entity.NewsEntity;
import com.quanvanstorebackend.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService extends BaseService<NewsRepository, NewsEntity> {
    public NewsEntity addNews(NewsEntity newsEntityToAdd) {
        return save(newsEntityToAdd);
    }

    public List<NewsEntity> getAllNews() {
        return findAllAndDeletedFalse();
    }

    public NewsEntity getOneNews(Long newsId) {
        return findById(newsId);
    }

    public List<NewsEntity> getAllForAdmin() {
        return null;
    }

    public void deleteNews(Long newsId) {
        delete(newsId);
    }

    public void updateNews(NewsEntity newsEntity) {
        save(newsEntity);
    }
}
