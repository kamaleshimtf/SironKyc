package com.imtf.sironkyc.service.impl;

import com.imtf.sironkyc.dto.ResponseError;
import com.imtf.sironkyc.entity.WebServiceStatusEntity;
import com.imtf.sironkyc.exception.ConflictException;
import com.imtf.sironkyc.exception.NotFoundException;
import com.imtf.sironkyc.repository.WebServiceStatusRepository;
import com.imtf.sironkyc.service.WebServiceStatusService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class WebServiceStatusServiceImpl implements WebServiceStatusService {

    private final Logger logger = LoggerFactory.getLogger(WebServiceStatusServiceImpl.class);

    @Inject
    private WebServiceStatusRepository webServiceStatusRepository;

    public List<WebServiceStatusEntity> getAllWebServiceStatus() {
        logger.info("Inside Service : Get All Web Service Status");
        return webServiceStatusRepository.listAll();
    }

    @Transactional
    public WebServiceStatusEntity updateWebServiceStatus(String requestUUID, WebServiceStatusEntity webServiceStatusEntity) {

        WebServiceStatusEntity oldWebServiceStatus = webServiceStatusRepository.find("wsRequestUuid", requestUUID).firstResult();
        if (oldWebServiceStatus == null) {
            logger.warn("Inside Service : Webservice status data was not found for WsRequestUuid: {}" , requestUUID);
            throw new NotFoundException("Webservice status record not found");
        }

        oldWebServiceStatus.setWsRequestUuid(webServiceStatusEntity.getWsRequestUuid());
        oldWebServiceStatus.setWsMethodName(webServiceStatusEntity.getWsMethodName());
        oldWebServiceStatus.setRequestTimeStart(webServiceStatusEntity.getRequestTimeStart());
        oldWebServiceStatus.setRequestTimeEnd(webServiceStatusEntity.getRequestTimeEnd());
        oldWebServiceStatus.setReturnCode(webServiceStatusEntity.getReturnCode());
        oldWebServiceStatus.setScoringTimeStart(webServiceStatusEntity.getScoringTimeStart());
        oldWebServiceStatus.setScoringTimeEnd(webServiceStatusEntity.getScoringTimeEnd());
        oldWebServiceStatus.setRelatingCustomers(webServiceStatusEntity.getRelatingCustomers());
        oldWebServiceStatus.setAsyncRequestId(webServiceStatusEntity.getAsyncRequestId());
        oldWebServiceStatus.setTbqFormId(webServiceStatusEntity.getTbqFormId());
        oldWebServiceStatus.setServerName(webServiceStatusEntity.getServerName());

        webServiceStatusRepository.persist(oldWebServiceStatus);
        logger.info("Inside Service : Webservice status updated successfully with WsRequestUuid: {}", requestUUID);

        return oldWebServiceStatus;
    }

    @Transactional
    public WebServiceStatusEntity addWebServiceStatus(WebServiceStatusEntity webServiceStatusEntity) {

        WebServiceStatusEntity oldWebServiceStatus = webServiceStatusRepository.find("wsRequestUuid",
                webServiceStatusEntity.getWsRequestUuid()).firstResult();

        if (oldWebServiceStatus != null) {
            logger.warn("Inside Service : Webservice status record already exist with WsRequestUuid: {}",
                    oldWebServiceStatus.getWsRequestUuid());

            throw new ConflictException("Webservice status record already exist");
        }

        webServiceStatusRepository.persist(webServiceStatusEntity);

        logger.info("Inside Service : Webservice status record added successfully with wsRequestUuid: {}",
                webServiceStatusEntity.getWsRequestUuid());

        return webServiceStatusEntity;
    }

    @Transactional
    public ResponseError deleteWebServiceStatus(String requestUUID) {

        WebServiceStatusEntity oldWebServiceStatus = webServiceStatusRepository.find("wsRequestUuid", requestUUID).firstResult();

        if (oldWebServiceStatus == null) {
            logger.warn("Inside Service : Webservice status record not found with wsRequestUuid : {}" , requestUUID);
            throw new NotFoundException("Webservice status record not found");
        }

        webServiceStatusRepository.delete(oldWebServiceStatus);
        logger.info("Inside Service : Successfully deleted Web service status with WsRequestUuid: {}" , requestUUID);

        return new ResponseError("Deleted successfully : " + oldWebServiceStatus.getWsRequestUuid());
    }
}
