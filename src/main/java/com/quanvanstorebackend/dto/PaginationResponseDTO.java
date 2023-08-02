package com.quanvanstorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponseDTO {
    private int totalPages;
    private int totalItems;
    private int page;
    private int size;
    private Object data;
}
