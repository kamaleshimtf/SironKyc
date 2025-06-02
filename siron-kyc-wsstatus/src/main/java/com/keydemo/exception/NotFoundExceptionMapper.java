package com.keydemo.exception;

import com.keydemo.dto.ResponseError;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {
        ResponseError responseError = new ResponseError(exception.getMessage());
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(responseError)
                .build();
    }
}
