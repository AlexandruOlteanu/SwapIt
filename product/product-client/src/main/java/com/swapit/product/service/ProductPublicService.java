package com.swapit.product.service;


import com.swapit.product.api.service.ProductService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "product")
public interface ProductPublicService extends ProductService {

}
