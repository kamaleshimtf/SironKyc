package com.imtf.siron.exception.mapper;

import com.imtf.siron.dto.ResponseError;
import com.imtf.siron.exception.BadRequestException;
import com.imtf.siron.exception.ConflictException;
import com.imtf.siron.exception.NoContentException;
import com.imtf.siron.exception.NotFoundException;
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
       else if(exception instanceof NoContentException){
           return Response
                   .status(Response.Status.NO_CONTENT)
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
       else {
           return Response
                   .status(Response.Status.BAD_REQUEST)
                   .entity(new ResponseError(exception.getMessage()))
                   .build();
       }
    }
}
