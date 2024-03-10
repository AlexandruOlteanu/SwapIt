package com.swapit.product.service.impl;

import com.swapit.apiGateway.api.dto.response.ProductDTO;
import com.swapit.product.domain.Product;
import com.swapit.product.mappers.ProductMapper;
import com.swapit.product.repository.ProductRepository;
import com.swapit.product.service.InternalRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InternalRequestServiceImpl implements InternalRequestService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProductsByUserId(Integer userId) {
        List<Product> products = productRepository.findAllByUserId(userId)
                .orElse(new ArrayList<>());
        return products.stream()
                .map(ProductMapper::toDTO)
                .toList();
    }
}
