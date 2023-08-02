package com.quanvanstorebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "old_price")
    private Integer oldPrice;

    @Column(name = "new_price")
    private Integer newPrice;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "description")
    private String description;

    @Column(name = "thumbnail")
    private String thumbnail;


}
