package com.example.demo.controllers;

import com.example.demo.Validation.TockenValid;
import com.example.demo.Validation.ValidationEmail;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.requests.AddProductsRequest;
import com.example.demo.requests.UserRequest;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class UserController {
@Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("user/take")
    public ResponseEntity takeproduct(@RequestParam Integer id) {

        Product productRequest = productService.getProduct(id);
        if (productRequest == null) {
            return new ResponseEntity("Product not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(productRequest);
    }

    @GetMapping("/user/get")
    public ResponseEntity GetByID(@RequestParam Integer id) {
        User userRequest = userService.getUser(id);
            if (userRequest == null) {
                return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
            }
        return ResponseEntity.ok(userRequest);
    }

    @PostMapping("/user/addProducts")
    public ResponseEntity addProducts(@RequestBody AddProductsRequest request) {
        userService.addProducts(request.getUserid(), request.getProductsIds());
        return ResponseEntity.ok().body("");
    }

    @PostMapping("/user/deleteProducts")
    public ResponseEntity DeleteProducts(@RequestBody AddProductsRequest request) {
        userService.DeleteProduct(request.getUserid(), request.getProductsIds());
        return ResponseEntity.ok().body("");
    }


}





