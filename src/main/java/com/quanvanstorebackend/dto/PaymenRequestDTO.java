package com.quanvanstorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymenRequestDTO {
    private List<Long> products;
    private List<Integer> quantity;
    private VNPayDTO vnPayDTO;
}
