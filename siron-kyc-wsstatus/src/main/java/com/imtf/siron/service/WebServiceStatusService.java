package com.imtf.siron.service;

import com.imtf.siron.dto.ResponseError;
import com.imtf.siron.entity.WebServiceStatusEntity;
import java.util.List;

public interface WebServiceStatusService {

    WebServiceStatusEntity addWebServiceStatus(WebServiceStatusEntity webServiceStatusEntity);

    List<WebServiceStatusEntity> getAllWebServiceStatus();

    WebServiceStatusEntity updateWebServiceStatus(String requestUUID, WebServiceStatusEntity webServiceStatusEntity);

    ResponseError deleteWebServiceStatus(String requestUUID);
}
