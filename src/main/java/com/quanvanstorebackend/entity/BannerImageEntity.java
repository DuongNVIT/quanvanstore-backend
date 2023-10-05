package com.quanvanstorebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "banner_image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BannerImageEntity extends BaseEntity{
    @Column(name = "url")
    private String url;

    @Column(name = "selected")
    private Boolean selected = false;
}
