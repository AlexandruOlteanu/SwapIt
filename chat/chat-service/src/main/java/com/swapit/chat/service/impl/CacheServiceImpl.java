package com.swapit.chat.service.impl;

import com.swapit.chat.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.swapit.commons.cache.ConfigConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheServiceImpl implements CacheService {

    @Override
    @CacheEvict(value = CACHE_SINGULAR_CONV_PREVIEW, key = "#conversationId + '_' + #userId")
    public void deleteCachedConversationPreview(Integer conversationId, Integer userId) {

    }

    @Override
    @CacheEvict(value = CACHE_CONVERSATIONS_PREVIEWS, key = "#userId")
    public void deleteAllCachedConversationPreview(Integer userId) {

    }

    @Override
    @CacheEvict(value = CACHE_CONVERSATION, key = "#conversationId")
    public void deleteCachedConversation(Integer conversationId) {

    }
}
