package com.knuipalab.dsmp.httpResponse.error.handler;

import com.knuipalab.dsmp.httpResponse.error.ErrorCode;
import com.knuipalab.dsmp.httpResponse.error.ErrorResponse;
import com.knuipalab.dsmp.httpResponse.error.handler.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //  ErrorCode 400 : BAD_REQUEST  ----------------------------------
    @ExceptionHandler(value = UserEmailBadRequestException.class)
    protected ResponseEntity<ErrorResponse> handleUserEmailBadRequestException(HttpServletRequest request, UserEmailBadRequestException userEmailBadRequestException) {
        log.error("ErrorExceptionURI : " + request.getRequestURI());
        log.error("handleUserEmailBadRequestException throw Exception : {}", ErrorCode.USER_EMAIL_BAD_REQUEST);
        return ErrorResponse.toResponseEntity(userEmailBadRequestException.getErrorCode(),request);
    }

    //  ErrorCode 401 : Un Authorized ----------------------------------
    @ExceptionHandler(value = UnAuthorizedAccessException.class)
    protected ResponseEntity<ErrorResponse> handleUnAuthorizedAccessException(HttpServletRequest request, UnAuthorizedAccessException unAuthorizedAccessException) {
        log.error("ErrorExceptionURI : " + request.getRequestURI());
        log.error("handleUnAuthorizedAccessException throw Exception : {}", ErrorCode.UNAUTHORIZED_ACCESS);
        return ErrorResponse.toResponseEntity(unAuthorizedAccessException.getErrorCode(),request);
    }

    //  ErrorCode 404 : Not Found ----------------------------------

    @ExceptionHandler(value = UserNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleUserNotFoundException(HttpServletRequest request, UserNotFoundException userNotFoundException)
    {
        log.error("ErrorExceptionURI : " + request.getRequestURI());
        log.error("handleUserNotFoundException throw Exception : {}", ErrorCode.USER_NOT_FOUND);
        return ErrorResponse.toResponseEntity(userNotFoundException.getErrorCode(),request);
    }

    @ExceptionHandler(value = MetaDataNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleMetaDataNotFoundException(HttpServletRequest request, MetaDataNotFoundException metaDataNotFoundException) {
        log.error("ErrorExceptionURI : " + request.getRequestURI());
        log.error("handleMetaDataNotFoundException throw Exception : {}", ErrorCode.METADATA_NOT_FOUND);
        return ErrorResponse.toResponseEntity(metaDataNotFoundException.getErrorCode(),request);
    }

    @ExceptionHandler(value = ProjectNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleProjectNotFoundException(HttpServletRequest request, ProjectNotFoundException projectNotFoundException) {
        log.error("ErrorExceptionURI : " + request.getRequestURI());
        log.error("handleProjectNotFoundException throw Exception : {}", ErrorCode.PROJECT_NOT_FOUND);
        return ErrorResponse.toResponseEntity(projectNotFoundException.getErrorCode(),request);
    }

    //  ErrorCode 500 : Internal Server error --------------------------
//    @ExceptionHandler(value = NullPointerException.class)
//    protected ResponseEntity<ErrorResponse> handleNullPointerException(HttpServletRequest request, NullPointerException nullPointerException) {
//        log.info("ErrorExceptionURI : " + request.getRequestURI());
//        log.error("handleNullPointerException throw Exception : {}", ErrorCode.NULL_POINTER);
//        return ErrorResponse.toResponseEntity(ErrorCode.NULL_POINTER,request);
//    }



}