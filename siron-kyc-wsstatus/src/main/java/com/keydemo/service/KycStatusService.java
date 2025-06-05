package com.keydemo.service;

import com.keydemo.dto.ResponseError;
import com.keydemo.entity.KycStatusEntity;
import java.util.List;

public interface KycStatusService {

    KycStatusEntity addKycStatus(KycStatusEntity kycStatusEntity);

    List<KycStatusEntity> getAllKycStatus();

    KycStatusEntity updateKycStatus(String requestUUID, KycStatusEntity kycStatusEntity);

    ResponseError deleteKycStatus(String requestUUID);
}
