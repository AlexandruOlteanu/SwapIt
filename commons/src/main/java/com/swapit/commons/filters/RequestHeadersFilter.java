package com.swapit.commons.filters;

import com.swapit.commons.model.ThreadLocalContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.swapit.commons.utils.Constants.USER_ID_HEADER;

@Component
@Slf4j
public class RequestHeadersFilter extends OncePerRequestFilter {

    private static final List<String> mandatoryHeaders = List.of(USER_ID_HEADER);

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            Map<String, String> headersMap = createHeadersMap(request);
            ThreadLocalContext.setContext(headersMap);
            if (!headersMap.isEmpty()) {
                log.info("Extracted the following headers for current context : {}", headersMap);
            }
            filterChain.doFilter(request, response);
        } finally {
            ThreadLocalContext.removeContext();
        }
    }

    private Map<String, String> createHeadersMap(HttpServletRequest request) {
        var headers = mandatoryHeaders.stream().filter(header -> request.getHeader(header) != null)
                .collect(Collectors.toMap(Function.identity(), request::getHeader));
        return headers;
    }
}
