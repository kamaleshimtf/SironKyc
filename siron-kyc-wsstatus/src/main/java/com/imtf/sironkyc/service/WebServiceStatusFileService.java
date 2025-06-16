package com.imtf.sironkyc.service;


import com.imtf.sironkyc.entity.WebServiceStatusEntity;

import java.util.List;

public interface WebServiceStatusFileService {

   String createWebServiceStatusFile(List<WebServiceStatusEntity> webServiceStatusEntityList);

   String writeWebServiceStatusFile(List<WebServiceStatusEntity> webServiceStatusEntityList);

   String webServiceStatusFileToZip();
}
