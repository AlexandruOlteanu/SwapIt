package com.swapit.apiGateway.service;


import com.swapit.apiGateway.api.service.ApiGatewayService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "apiGateway")
public interface ApiGatewayPublicService extends ApiGatewayService {

}
