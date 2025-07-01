package com.imtf.sironkyc.exception.mapper;

import com.imtf.sironkyc.dto.ResponseError;
import com.imtf.sironkyc.exception.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
       if(exception instanceof BadRequestException){
           return Response
                   .status(Response.Status.BAD_REQUEST)
                   .entity(new ResponseError(exception.getMessage()))
                   .build();
       }
       else if(exception instanceof NotFoundException){
           return Response
                   .status(Response.Status.NOT_FOUND)
                   .entity(new ResponseError(exception.getMessage()))
                   .build();
       }
       else if(exception instanceof ConflictException){
           return Response
                   .status(Response.Status.CONFLICT)
                   .entity(new ResponseError(exception.getMessage()))
                   .build();
       }
       else if(exception instanceof IOException){
           return Response
                   .status(Response.Status.FORBIDDEN)
                   .entity(new ResponseError(exception.getMessage()))
                   .build();
       }
       else {
           return Response
                   .status(Response.Status.BAD_REQUEST)
                   .entity(new ResponseError(exception.getMessage()))
                   .build();
       }
    }
}
