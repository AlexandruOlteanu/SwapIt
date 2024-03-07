package com.swapit.commons.service;

import com.swapit.commons.service.impl.UrlGeneratorServiceImpl;

public interface UrlGeneratorService {
    String getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier api);
}
