package com.swapit.chat.service;


import com.swapit.chat.api.service.ChatService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "chat")
public interface ChatPublicService extends ChatService {

}
