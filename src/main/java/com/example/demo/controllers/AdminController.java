package com.example.demo.controllers;

import com.example.demo.Validation.TockenValid;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.requests.ProductRequest;
import com.example.demo.repository.ProductRepository;
import com.example.demo.requests.UserRequest;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
@Autowired
    UserRepository userRepository;
    @Autowired
    private ProductService productService;
    private UserService userService;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/addAccount")
    public ResponseEntity add(@RequestBody UserRequest user) {
        boolean result = userService.saveUser(user);
        if (result) {
            return new ResponseEntity("User added", HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().body("User can't added");

    }
    @PostMapping("/prod")
    public Product create(@RequestBody ProductRequest product) {
        return productService.create(product);
    }

    @GetMapping("/take")
    public ResponseEntity takeproduct(@RequestParam Integer id) {

        Product productRequest = productService.getProduct(id);
        if (productRequest == null) {
            return new ResponseEntity("Product not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(productRequest);
    }
    @PutMapping("/us/update/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody User user, HttpServletRequest request) {
        Optional<User> userData = userRepository.findById(id);
            if (userData.isPresent()) {
                User user1 = userData.get();
                user1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                user1.setName(user.getName());
                user1.setEmail(user.getEmail());
                user1.setAge(user.getAge());
                user1.setGender(user.getGender());
                return new ResponseEntity<>(userRepository.save(user1), HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTutorial(@PathVariable("id") long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/prod/update/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody Product product, HttpServletRequest request) {
        Optional<Product> productData = productRepository.findById(id);
            if (productData.isPresent()) {
                Product product1 = productData.get();
                product1.setPrice(product.getPrice());
                product1.setName(product.getName());
                return new ResponseEntity<>(productRepository.save(product1), HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }

    }
    @GetMapping("/delete")
    public ResponseEntity delete(@RequestParam Integer id) {

        Product productRequest = productService.delete(id);
        if (productRequest == null) {
            return new ResponseEntity("Product not found", HttpStatus.NOT_FOUND);
        }
        return  ResponseEntity.ok(productRequest);

    }
    }




