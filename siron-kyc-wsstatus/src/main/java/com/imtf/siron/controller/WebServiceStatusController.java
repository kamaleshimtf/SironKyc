package com.imtf.siron.controller;

import com.imtf.siron.entity.WebServiceStatusEntity;
import com.imtf.siron.service.WebServiceStatusService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/api/v1/wsstatus")
public class WebServiceStatusController {

    private Logger logger = LoggerFactory.getLogger(WebServiceStatusController.class);

    @Inject
    WebServiceStatusService webServiceStatusService;

    @GET
    @RolesAllowed({"admin","user"})
    public Response getAllWebServiceStatus() {
        logger.info("Inside Controller : Getting all web service status");
        List<WebServiceStatusEntity> wsStatus = webServiceStatusService.getAllWebServiceStatus();
        return Response.ok(wsStatus).build();
    }

    @POST
    @RolesAllowed("admin")
    public Response addWebServiceStatus(WebServiceStatusEntity webServiceStatusEntity) {
        logger.info("Inside Controller : adding new web service status");
        return Response.status(RestResponse.Status.CREATED.getStatusCode())
                .entity(webServiceStatusService.addWebServiceStatus(webServiceStatusEntity))
                .build();
    }

    @PUT
    @RolesAllowed("admin")
    public Response updateWebServiceStatus(@QueryParam("requestUUID") String requestUUID, WebServiceStatusEntity webServiceStatusEntity) {
        logger.info("Inside Controller : updating web service status");
        return Response.ok(webServiceStatusService.updateWebServiceStatus(requestUUID, webServiceStatusEntity)).build();
    }

    @DELETE
    @RolesAllowed("admin")
    public Response deleteWebServiceStatus(@QueryParam("requestUUID") String requestUUID) {
        logger.info("Inside Controller : Deleting web service status");
        return Response.ok(webServiceStatusService.deleteWebServiceStatus(requestUUID)).build();
    }

}
