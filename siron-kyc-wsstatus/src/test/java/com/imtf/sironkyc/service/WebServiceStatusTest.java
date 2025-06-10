package com.imtf.sironkyc.service;

import com.imtf.sironkyc.entity.WebServiceStatusEntity;
import com.imtf.sironkyc.exception.BadRequestException;
import com.imtf.sironkyc.repository.WebServiceStatusRepository;
import com.imtf.sironkyc.service.impl.WebServiceStatusServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.imtf.sironkyc.constant.TestConstant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class WebServiceStatusTest {

    @Mock
    private WebServiceStatusRepository webServiceStatusRepository;

    @InjectMocks
    private WebServiceStatusServiceImpl webServiceStatusService;

    public WebServiceStatusEntity webServiceStatusEntity;

    @BeforeEach
    public void setUp() {
        webServiceStatusEntity = new WebServiceStatusEntity();
        webServiceStatusEntity.setWsRequestUuid(WS_REQUEST_UUID);
        webServiceStatusEntity.setWsMethodName(KYC_SCORE_TASK);
        webServiceStatusEntity.setRequestTimeStart(REQUEST_TIME_START);
        webServiceStatusEntity.setRequestTimeEnd(REQUEST_TIME_END);
        webServiceStatusEntity.setReturnCode(RETURN_CODE);
        webServiceStatusEntity.setScoringDir(SCORING_DIR);
        webServiceStatusEntity.setScoringTimeStart(SCORING_TIME_START);
        webServiceStatusEntity.setScoringTimeEnd(SCORING_TIME_END);
        webServiceStatusEntity.setRelatingCustomers(RATING_CUSTOMER);
        webServiceStatusEntity.setAsyncRequestId(ASYNC_REQUEST_ID);
        webServiceStatusEntity.setTbqFormId(TBQ_FORM_ID);
        webServiceStatusEntity.setServerName(SERVER_NAME);
    }

    @Test
    public void getAllWebServiceStatusReturnsAllStatuses() {
        List<WebServiceStatusEntity> webServiceStatusList = new ArrayList<>();
        webServiceStatusList.add(webServiceStatusEntity);

        when(webServiceStatusRepository.listAll()).thenReturn(webServiceStatusList);
        List<WebServiceStatusEntity> webServiceStatusEntityList = webServiceStatusService.getAllWebServiceStatus();

        assertEquals(webServiceStatusList.size(), webServiceStatusEntityList.size());
    }

    @Test
    public void addAllWebServiceStatusReturnWebServiceStatusEntity() {
        List<WebServiceStatusEntity> webServiceStatusList = new ArrayList<>();
        webServiceStatusList.add(webServiceStatusEntity);

        when(webServiceStatusRepository.listAll()).thenReturn(webServiceStatusList);

        List<WebServiceStatusEntity> webServiceStatusEntityList = webServiceStatusService.getAllWebServiceStatus();

        assertEquals(webServiceStatusList.size(), webServiceStatusEntityList.size());
        assertNotEquals("dfkvfjvhrivh", webServiceStatusEntityList.get(0).getWsRequestUuid());
        assertNull(webServiceStatusEntityList.get(0).getScoringDir());
    }

}
