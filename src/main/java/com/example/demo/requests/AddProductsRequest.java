package com.example.demo.requests;

import com.sun.istack.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class AddProductsRequest {

    private Integer userid;

    private List<Integer> productsIds;
}
