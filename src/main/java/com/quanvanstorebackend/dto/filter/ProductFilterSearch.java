package com.quanvanstorebackend.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilterSearch {

    private String name;
    private Long categoryId;
    private Integer startPrice;
    private Integer endPrice;
}
