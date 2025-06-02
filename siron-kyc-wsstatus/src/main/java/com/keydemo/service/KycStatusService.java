package com.keydemo.service;

import com.keydemo.entity.KycStatusEntity;
import com.keydemo.exception.NoContentException;
import com.keydemo.exception.NotFoundException;
import com.keydemo.repository.KycStatusRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class KycStatusService {

    @Inject
    KycStatusRepository kycStatusRepository;
    
    public List<KycStatusEntity> getAllKycStatus() {
        List<KycStatusEntity> allKycStatusList = kycStatusRepository.listAll();
        if(allKycStatusList.isEmpty()){
            throw new NoContentException("Kyc status data is empty");
        }
        return kycStatusRepository.listAll();
    }

    @Transactional
    public KycStatusEntity updateKycStatus(String requestUUID, KycStatusEntity kycStatusEntity){
        KycStatusEntity oldKycStatus = kycStatusRepository.find("wsRequestUuid", requestUUID).firstResult();
        if(oldKycStatus == null){
            throw new NotFoundException("KYC record not found");
        }
        oldKycStatus.setWsRequestUuid(kycStatusEntity.getWsRequestUuid());
        oldKycStatus.setWsMethodName(kycStatusEntity.getWsMethodName());
        oldKycStatus.setRequestTimeStart(kycStatusEntity.getRequestTimeStart());
        oldKycStatus.setRequestTimeEnd(kycStatusEntity.getRequestTimeEnd());
        oldKycStatus.setReturnCode(kycStatusEntity.getReturnCode());
        oldKycStatus.setScoringTimeStart(kycStatusEntity.getScoringTimeStart());
        oldKycStatus.setScoringTimeEnd(kycStatusEntity.getScoringTimeEnd());
        oldKycStatus.setRelatingCustomers(kycStatusEntity.getRelatingCustomers());
        oldKycStatus.setAsyncRequestId(kycStatusEntity.getAsyncRequestId());
        oldKycStatus.setTbqFormId(kycStatusEntity.getTbqFormId());
        oldKycStatus.setServerName(kycStatusEntity.getServerName());
        kycStatusRepository.persist(oldKycStatus);
        return oldKycStatus;
    }
}
