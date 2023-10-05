package com.quanvanstorebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "general_infor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralInforEntity extends BaseEntity{

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;
}
