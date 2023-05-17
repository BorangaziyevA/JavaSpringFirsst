package com.example.demo.entity;

import com.example.demo.requests.ProductRequest;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="products")
@Data
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String name;
    private String price;
    private Date date;

public Product(ProductRequest productRequest)
{

    this.name=productRequest.getName();
    this.date=productRequest.getDate();
    this.price=productRequest.getPrice();


}
}
