package com.swapit.product.service.impl;

import com.swapit.commons.domain.Product;
import com.swapit.commons.domain.ProductSpecifications;
import com.swapit.commons.domain.User;
import com.swapit.commons.repository.ProductRepository;
import com.swapit.commons.repository.ProductSpecificationsRepository;
import com.swapit.commons.repository.UserRepository;
import com.swapit.product.api.domain.request.ProductCreationRequest;
import com.swapit.product.service.ProductCreateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCreateServiceImpl implements ProductCreateService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductSpecificationsRepository productSpecificationsRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void createProduct(ProductCreationRequest request) throws Exception {
        Optional<User> user = userRepository.findUserByUsername(request.getUsername());
        if (user.isEmpty()) {
            throw new Exception("Error: User doesn't exist");
        }
        Product product = Product.builder()
                .user(user.get())
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(request.getCategory())
                .subcategory(request.getSubcategory())
                .build();
        productRepository.save(product);
        request.getProductSpecifications()
                .forEach(productSpecification -> productSpecificationsRepository.save(ProductSpecifications.builder()
                                        .product(product)
                                        .key(productSpecification.getKey())
                                        .value(productSpecification.getValue())
                                .build()));
    }
}
