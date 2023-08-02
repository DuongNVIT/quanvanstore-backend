package com.quanvanstorebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rating")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingEntity extends BaseEntity{

    @Column(name = "rate")
    private Integer rate;

    @Column(name = "content")
    private String content;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_id")
    private Long productId;


}
