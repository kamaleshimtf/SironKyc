package com.imtf.sironkyc.service.impl;

import com.imtf.sironkyc.entity.WebServiceStatusEntity;
import com.imtf.sironkyc.exception.IOException;
import com.imtf.sironkyc.service.WebServiceStatusFileService;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.imtf.sironkyc.constant.WebServiceStatusFileConstant.*;


@ApplicationScoped
public class WebServiceStatusFileServiceImpl implements WebServiceStatusFileService {

    private final Logger logger = LoggerFactory.getLogger(WebServiceStatusFileServiceImpl.class);

    File webServiceStatusFolder = new File(WEB_SERVICE_STATUS_FOLDER_NAME);
    File webServiceStatusFile = new File(webServiceStatusFolder, WEB_SERVICE_STATUS_FILE_NAME);

    public String createWebServiceStatusFile(List<WebServiceStatusEntity> webServiceStatusEntityList) throws IOException {

        try {
            if (webServiceStatusFile.exists()) {

                boolean webServiceStatusFileDeleteCheck = webServiceStatusFile.delete();
                logger.info(webServiceStatusFileDeleteCheck ? "Inside Service: File Deleted successfully" : "File not deleted or does not exist");
            }
            if (!webServiceStatusFolder.exists()) {

                boolean webServiceFolderCreate = webServiceStatusFolder.mkdir();
                logger.info(webServiceFolderCreate ? "Inside Service : File created" : "Folder not created");
            }
            if(webServiceStatusFile.createNewFile()) {
                return writeWebServiceStatusFile(webServiceStatusEntityList);
            }
        }
        catch (Exception e) {
            logger.error("Inside Service : Exception occurred while creating web service status file {}",
                    e.getMessage());
            return "Error: " + e.getMessage();
        }
        return "File Not Found";
    }
    public String writeWebServiceStatusFile(List<WebServiceStatusEntity> webServiceStatusEntityList){

        try {
           FileWriter webServiceStatusFileWriter = new FileWriter(webServiceStatusFile);

            webServiceStatusFileWriter.write("wsRequestUuid,wsMethodName,requestTimeStart,requestTimeEnd,returnCode,scoringDir," +
                    "scoringTimeStart,scoringTimeEnd,relatingCustomers,asyncRequestId," +
                    "tbqFormId,serverName\n");

            for(WebServiceStatusEntity webServiceStatusEntity : webServiceStatusEntityList){
                webServiceStatusFileWriter.write(webServiceStatusEntity.wsRequestUuid + ","
                        + webServiceStatusEntity.wsMethodName + ","
                        + webServiceStatusEntity.requestTimeStart + ","
                        + webServiceStatusEntity.requestTimeEnd + ","
                        + webServiceStatusEntity.returnCode + ","
                        + webServiceStatusEntity.scoringDir + ","
                        + webServiceStatusEntity.scoringTimeStart + ","
                        + webServiceStatusEntity.scoringTimeEnd + ","
                        + webServiceStatusEntity.relatingCustomers + ","
                        + webServiceStatusEntity.asyncRequestId + ","
                        + webServiceStatusEntity.tbqFormId + ","
                        + webServiceStatusEntity.serverName + "\n"
                );
            }

            logger.info("Inside Service : File written in csv file: {}" , webServiceStatusFile.getAbsolutePath());
            webServiceStatusFileWriter.close();
            return webServiceStatusFileToZip();
        }
        catch (Exception e) {
            logger.error("Inside Service : Exception occurred while writing web service status file {}",
                    e.getMessage());
            return "Error: " + e.getMessage();
        }
    }

    public String webServiceStatusFileToZip(){

        try {

            if (!webServiceStatusFile.exists()) {
                logger.error("Inside Service :  File does not exist while converting to zip file");
                return "file not found";
            }

            FileOutputStream webServiceStatusFileOutputStream = new FileOutputStream(WEB_SERVICE_STATUS_ZIP_FILE_NAME);
            ZipOutputStream zipOutputStream = new ZipOutputStream(webServiceStatusFileOutputStream);
            FileInputStream fileInputStream = new FileInputStream(webServiceStatusFile);

            ZipEntry zipEntry = new ZipEntry(WEB_SERVICE_STATUS_FILE_NAME);
            zipOutputStream.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fileInputStream.read(bytes)) != -1) {
                zipOutputStream.write(bytes, 0, length);
            }

            zipOutputStream.closeEntry();
            fileInputStream.close();
            zipOutputStream.close();
            webServiceStatusFileOutputStream.close();

            return webServiceStatusFile.getAbsolutePath();
        }
        catch (Exception e) {
            logger.error("Inside Service : Exception occurred while Converting web service status Zip file {}",
                    e.getMessage());
            return "Error: " + e.getMessage();
        }
    }
}
