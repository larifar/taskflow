package com.taskflow.exception;

import com.taskflow.dto.ExceptionDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
@ControllerAdvice
public class ExceptionsController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ExceptionTaskFlow.class)
    public ResponseEntity<String> handleCustomException(ExceptionTaskFlow ex){
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ExceptionDto dto = new ExceptionDto("Não há dados no body", HttpStatus.BAD_REQUEST.name());
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ExceptionDto objectDto = new ExceptionDto();

        String msg = "";

        if (ex instanceof MethodArgumentNotValidException){
            List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
            for (ObjectError error : list) {
                msg += error.getDefaultMessage() + "\n";
            }
        } else{
            msg = ex.getMessage();
        }
        objectDto.setMsg(msg);
        objectDto.setCode(statusCode.toString());

        return new ResponseEntity<>(objectDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
