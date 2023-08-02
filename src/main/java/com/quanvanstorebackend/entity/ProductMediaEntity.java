package com.quanvanstorebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_media")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductMediaEntity extends BaseEntity{

    @Column(name = "type")
    private String type;

    @Column(name = "url")
    private String url;

    @Column(name = "product_id")
    private Long productId;

}
