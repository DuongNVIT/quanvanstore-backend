package com.quanvanstorebackend.service;

import com.quanvanstorebackend.dto.filter.ProductFilterSearch;
import com.quanvanstorebackend.entity.ProductEntity;
import com.quanvanstorebackend.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends BaseService<ProductRepository, ProductEntity>{


    public List<ProductEntity> getAllProducts(ProductFilterSearch productFilterSearch, Pageable pageable) {
        String bookName = productFilterSearch.getName();
        Long categoryId = productFilterSearch.getCategoryId();
        Integer startPrice = productFilterSearch.getStartPrice();
        Integer endPrice = productFilterSearch.getEndPrice();
        System.out.println(bookName);
        System.out.println(categoryId);
        System.out.println(startPrice);
        System.out.println(endPrice);
        Specification<ProductEntity> productSpec = Specification.where(hasTitleLike(bookName))
                .and(hasCategoryId(categoryId))
                .and(hasStartPrice(startPrice))
                .and(hasEndPrice(endPrice))
                .and(deletedFalse(false));
        Page<ProductEntity> productPage = repository.findAll(productSpec, pageable);

        System.out.println("Vào đây nha");
        return productPage.getContent();
    }

    public ProductEntity addNewProduct(ProductEntity product) {
        return save(product);
    }



    public ProductEntity getProduct(Long productId) {
        return repository.findById(productId).get();
    }

    private Specification<ProductEntity> hasTitleLike(String productName) {
        return ((root, query, criteriaBuilder) -> {
            if(productName == null || productName.equals("")) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("name"), "%" + productName + "%");
        });
    }

    private Specification<ProductEntity> hasCategoryId(Long categoryId) {
        return ((root, query, criteriaBuilder) -> {
            if(categoryId == null || categoryId.equals("")) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("categoryId"), categoryId);
        });
    }

    private Specification<ProductEntity> hasStartPrice(Integer startPrice) {
        return ((root, query, criteriaBuilder) -> {
            if(startPrice == null || startPrice.equals("")) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThan(root.get("newPrice"), startPrice);
        });
    }

    private Specification<ProductEntity> hasEndPrice(Integer endPrice) {
        return ((root, query, criteriaBuilder) -> {
            if(endPrice == null || endPrice.equals("")) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThan(root.get("newPrice"), endPrice);
        });
    }

    private Specification<ProductEntity> deletedFalse(Boolean deleted) {
        return ((root, query, criteriaBuilder) -> {
            if(deleted == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("deleted"), deleted);
        });
    }

    public void deleteProduct(Long productId) {
        delete(productId);
    }
}
