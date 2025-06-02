package com.keydemo.controller;

import com.keydemo.entity.KycStatusEntity;
import com.keydemo.service.KycStatusService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/v1/wsstatus")
public class KycStatusController {

    @Inject
    KycStatusService kycStatusService;

    @GET
    @RolesAllowed({"admin","user"})
    public Response getKycStatus() {
        List<KycStatusEntity> wsStatus = kycStatusService.getAllKycStatus();
        return Response.ok(wsStatus).build();
    }

    @PUT
    @RolesAllowed("admin")
    public Response getKycStatus(@QueryParam("requestUUID") String requestUUID, KycStatusEntity kycStatusEntity) {
        return Response.ok(kycStatusService.updateKycStatus(requestUUID,kycStatusEntity)).build();
    }

    @GET
    @Path("hello")
    public String hello() {
        return "hello";
    }
}
