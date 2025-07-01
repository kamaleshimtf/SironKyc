package com.imtf.sironkyc.service;

import com.imtf.sironkyc.dto.ResponseError;
import com.imtf.sironkyc.entity.WebServiceStatusEntity;
import java.util.List;

public interface WebServiceStatusService {

    WebServiceStatusEntity addWebServiceStatus(WebServiceStatusEntity webServiceStatusEntity);

    List<WebServiceStatusEntity> getAllWebServiceStatus();

    WebServiceStatusEntity updateWebServiceStatus(String requestUUID, WebServiceStatusEntity webServiceStatusEntity);

    ResponseError deleteWebServiceStatus(String requestUUID);

    List<WebServiceStatusEntity> getWebServiceStatusByReturnCode(Integer returnCode);
}
