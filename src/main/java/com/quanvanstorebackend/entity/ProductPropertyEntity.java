package com.quanvanstorebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_property")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPropertyEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @Column(name = "product_id")
    private Long productId;

}
