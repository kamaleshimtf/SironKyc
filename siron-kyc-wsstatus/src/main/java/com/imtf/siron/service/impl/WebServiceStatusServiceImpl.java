package com.imtf.siron.service.impl;

import com.imtf.siron.dto.ResponseError;
import com.imtf.siron.entity.WebServiceStatusEntity;
import com.imtf.siron.exception.BadRequestException;
import com.imtf.siron.exception.ConflictException;
import com.imtf.siron.exception.NoContentException;
import com.imtf.siron.exception.NotFoundException;
import com.imtf.siron.repository.WebServiceStatusRepository;
import com.imtf.siron.service.WebServiceStatusService;
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

        List<WebServiceStatusEntity> allWebServiceStatus = webServiceStatusRepository.listAll();
        logger.info("Inside Service : Get All Web Service Status");

        if (allWebServiceStatus.isEmpty()) {
            logger.error("Inside Service : Webservice status data is empty, throwing NoContentException");
            throw new NoContentException("Webservice status data is empty");
        }

        return allWebServiceStatus;
    }

    @Transactional
    public WebServiceStatusEntity updateWebServiceStatus(String requestUUID, WebServiceStatusEntity webServiceStatusEntity) {

        if (webServiceStatusEntity == null) {
            logger.error("Inside Service : Webservice status data is empty, throwing BadRequestException");
            throw new BadRequestException("Webservice status entity is Empty");
        }

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

        if (webServiceStatusEntity == null) {
            logger.error("Inside Service : Webservice status entity is Empty");
            throw new BadRequestException("WebService status Entity is empty");
        }

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
