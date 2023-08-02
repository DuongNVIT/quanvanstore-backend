package com.quanvanstorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VNPayDTO {
    private String orderInfor;
    private String orderType;
    private String txnRef;
    private int amount;
    private String bankCode;
}
