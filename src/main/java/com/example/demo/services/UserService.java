package com.example.demo.services;

import java.util.*;

import com.example.demo.Validation.ValidationEmail;
import com.example.demo.entity.Product;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.requests.UserRequest;

import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository  rolerepository;
  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired
  private ProductRepository productRepository;
  private Map<String, String> tokens = new HashMap<>();
  private Map<String, UserRequest> users = new HashMap<>();





public User saveUserReg(User user)
{
  Role userRole=rolerepository.findByName("ROLE_USER");
  user.setRole( userRole);
  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
  return userRepository.save(user);
}



  public boolean saveUser(UserRequest user) {

    User exist = userRepository.findByEmail(user.getEmail());
    if (exist != null) {
      return false;
    }

    User temp = new User(user.getName(), user.getEmail(), user.getEmail());
    return true;
  }

  public boolean checkEmail(String email) {
    User temp = userRepository.findByEmail(email);
    if (temp == null) {
      return false;
    }
    return true;
  }



  public boolean addProducts(Integer userId, List<Integer> productIds) {
    Optional<User> userOptional = userRepository.findById(userId.longValue());
    User user = userOptional.orElse(null);
    if (user == null) {
      return false;
    }
    List<Product> products = new ArrayList<>();
    productIds.forEach(id -> productRepository.findById(id.longValue()).ifPresent(p -> products.add(p)));
    user.setProducts(products);
    userRepository.save(user);
    return true;
  }

  public boolean DeleteProduct(Integer userid, List<Integer> productsIds) {
    Optional<User> userOptional = userRepository.findById(userid.longValue());
    User user = userOptional.orElse(null);
    if (user == null) {
      return false;
    }
    List<Product> products = new ArrayList<>();
    productsIds.forEach(id -> productRepository.findById(id.longValue()).ifPresent(p -> products.remove(p)));
    user.setProducts(products);
    userRepository.save(user);
    return true;
  }


  public User getUser(Integer id) {
    return userRepository.findById(id.longValue()).orElse(null);
  }

  public User getEM(String email) {
    return userRepository.findByEmail(email);
  }
  public User getLogin (String name)
  {
    return  userRepository.findByName(name);
  }



  public boolean check(UserRequest user) {
    if (!ValidationEmail.validate(user.getEmail())) {
      return true;
    } else
      return false;
  }
  public User findByLoginandPassword(String login,String password) {
    User user = getLogin(login);
    if (user != null) {
      if (bCryptPasswordEncoder.matches(password, user.getPassword())) ;
      return user;
    }
    return null;
  }
}




