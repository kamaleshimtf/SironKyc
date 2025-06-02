package com.keydemo.exception;

import com.keydemo.dto.ResponseError;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NoContentExceptionMapper implements ExceptionMapper<NoContentException> {

    @Override
    public Response toResponse(NoContentException e) {
        ResponseError responseError = new ResponseError(e.getMessage());
        return Response
                .status(Response.Status.NO_CONTENT)
                .entity(responseError)
                .build();
    }
}
