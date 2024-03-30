package com.swapit.commons.generator;

import com.swapit.commons.generator.impl.UrlGeneratorServiceImpl;

public interface UrlGeneratorService {
    String getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier api);
}
