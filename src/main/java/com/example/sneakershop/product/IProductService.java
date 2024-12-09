package com.example.sneakershop.product;

import java.util.List;

public interface IProductService {

  Product getProductById(Long id);

  List<Product> getAllProducts();

  Product createProduct(Product product);

  Product updateProduct(Long id, Product product);

  void deleteProduct(Long id);
}
