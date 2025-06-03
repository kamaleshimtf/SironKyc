package com.keydemo.exception;

import com.keydemo.dto.ResponseError;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConflictMapper implements ExceptionMapper<ConflictException> {

    @Override
    public Response toResponse(ConflictException e) {
        ResponseError responseError = new ResponseError(e.getMessage());
        return Response
                .status(Response.Status.CONFLICT)
                .entity(responseError)
                .build();
    }
}
