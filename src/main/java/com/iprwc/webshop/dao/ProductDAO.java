package com.iprwc.webshop.dao;

import com.iprwc.webshop.model.Product;
import com.iprwc.webshop.model.User;
import com.iprwc.webshop.repositories.ProductRepository;
import com.iprwc.webshop.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductDAO {

    private final ProductRepository productRepository;

    public ProductDAO(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Returns a product object with a specific id.
     * If there is no product with the specified id, returns null
     *
     * @param id the id of the product
     * @return product
     */
    public Product show(Integer id) {
        Optional<Product> product = this.productRepository.findById(id);

        return product.orElse(null);
    }

    public Product store(Product product){
        return productRepository.save(product);
    }

    public Product update(Product product) {
        this.productRepository.update(product.getTitle(),  product.getDescription(), product.getThumbnailUri(), product.getCategory(), product.getId());
        return product;
    }
}