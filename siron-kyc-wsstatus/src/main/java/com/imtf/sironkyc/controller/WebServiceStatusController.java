package com.imtf.sironkyc.controller;

import com.imtf.sironkyc.entity.WebServiceStatusEntity;
import com.imtf.sironkyc.service.WebServiceStatusFileService;
import com.imtf.sironkyc.service.WebServiceStatusService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.List;
import static com.imtf.sironkyc.constant.WebServiceStatusFileConstant.WEB_SERVICE_STATUS_ZIP_FILE_NAME;

@Path("/api/v1/wsstatus")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WebServiceStatusController {

    private final Logger logger = LoggerFactory.getLogger(WebServiceStatusController.class);

    @Inject
    private WebServiceStatusService webServiceStatusService;

    @Inject
    private WebServiceStatusFileService webServiceStatusFileService;

    @GET
    @RolesAllowed({"admin", "user"})
    public Response getAllWebServiceStatus() {

        logger.info("Inside Controller : Getting all web service status");
        List<WebServiceStatusEntity> webServiceStatusList = webServiceStatusService.getAllWebServiceStatus();

        if (webServiceStatusList.isEmpty()) {
            logger.info("Inside Controller : WebService Status List is empty");
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        return Response.ok(webServiceStatusList).build();
    }

    @POST
    @RolesAllowed("admin")
    public Response addWebServiceStatus(WebServiceStatusEntity webServiceStatusEntity) {
        logger.info("Inside Controller : adding new web service status");

        if (webServiceStatusEntity == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Web Service Status entity is Empty")
                    .build();
        }

        return Response.status(RestResponse.Status.CREATED.getStatusCode())
                .entity(webServiceStatusService.addWebServiceStatus(webServiceStatusEntity))
                .build();
    }

    @PUT
    @RolesAllowed("admin")
    public Response updateWebServiceStatus(@QueryParam("requestUUID") String requestUUID, WebServiceStatusEntity webServiceStatusEntity) {

        logger.info("Inside Controller : updating web service status");

        if (requestUUID == null || webServiceStatusEntity == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("RequestUUID or WebServiceStatusEntity is Empty")
                    .build();
        }

        return Response.ok(webServiceStatusService.updateWebServiceStatus(requestUUID, webServiceStatusEntity)).build();
    }

    @DELETE
    @RolesAllowed("admin")
    public Response deleteWebServiceStatus(@QueryParam("requestUUID") String requestUUID) {
        logger.info("Inside Controller : Deleting web service status");
        return Response.ok(webServiceStatusService.deleteWebServiceStatus(requestUUID)).build();
    }

    @GET
    @Path("/file")
    @Produces("application/zip")
    public Response getAllWebServiceStatusFile() {
        logger.info("Inside Controller : Getting all web service status file");

        List<WebServiceStatusEntity> webServiceStatusList = webServiceStatusService.getAllWebServiceStatus();
        String webServiceStatusFileCreate = webServiceStatusFileService.createWebServiceStatusFile(webServiceStatusList);
        File webServiceStatusFile = new File(WEB_SERVICE_STATUS_ZIP_FILE_NAME);

        if (!webServiceStatusFile.exists()) {
            return Response.status(404)
                    .entity("File not found")
                    .build();
        }

        return Response.ok(webServiceStatusFile)
                .header("Content-Disposition", "attachment; filename=\"" + WEB_SERVICE_STATUS_ZIP_FILE_NAME + "\"")
                .build();
    }

    @GET
    @Path("/returncode")
    public Response getWebServiceStatusReturnCodeWithReturnCode(@QueryParam("code") Integer returnCode) {

        List<WebServiceStatusEntity> webServiceStatusList = webServiceStatusService.getWebServiceStatusByReturnCode(returnCode);
         logger.info("Inside Controller : Getting web service status file");

        if (webServiceStatusList.isEmpty()) {
            logger.info("Inside Controller : WebService Status List with returncode is empty");
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(webServiceStatusList).build();
    }

}
