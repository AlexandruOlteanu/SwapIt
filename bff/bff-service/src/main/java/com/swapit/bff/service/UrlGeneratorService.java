package com.swapit.bff.service;

import com.swapit.bff.service.impl.UrlGeneratorServiceImpl;

public interface UrlGeneratorService {

    String getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier api);

}
