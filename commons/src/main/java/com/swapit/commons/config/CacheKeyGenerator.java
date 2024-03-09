package com.swapit.commons.config;

import com.swapit.commons.model.ThreadLocalContext;
import lombok.NonNull;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

import static com.swapit.commons.utils.Constants.USER_ID_HEADER;

@Component("cacheKeyGenerator")
public class CacheKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(@NonNull Object target, @NonNull Method method, @NonNull Object... params) {
        String userId = ThreadLocalContext.getContext().get(USER_ID_HEADER);
        return String.join("_", target.getClass().getSimpleName(), method.getName(), StringUtils.arrayToDelimitedString(params, "_"), userId);
    }
}
