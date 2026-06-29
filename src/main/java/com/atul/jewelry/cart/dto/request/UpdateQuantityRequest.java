package com.atul.jewelry.cart.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateQuantityRequest {

    @NotBlank
    private String cartItemId;

    @Min(1)
    private Integer quantity;

}