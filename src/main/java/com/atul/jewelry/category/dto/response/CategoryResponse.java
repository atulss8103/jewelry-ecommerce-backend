package com.atul.jewelry.category.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponse {

    private String id;

    private String name;

    private String description;

}