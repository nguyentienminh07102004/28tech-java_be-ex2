package com.javaweb.ControllerAdvice;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.customerException.FeildRequireException;
import com.javaweb.model.ErrorResponeDTO;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ArithmeticException.class)
  public ResponseEntity<Object> handleArithmeticException(ArithmeticException ex, WebRequest request) {
    ErrorResponeDTO errorResponeDTO = new ErrorResponeDTO();
    errorResponeDTO.setError(ex.getMessage());
    List<String> details = new ArrayList<String>();
    details.add("Không thể chia cho 0");
    errorResponeDTO.setDateils(details);
    return new ResponseEntity<Object>(errorResponeDTO, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(FeildRequireException.class)
  public ResponseEntity<Object> handleFeildRequireException(FeildRequireException ex, WebRequest request) {
    ErrorResponeDTO errorResponeDTO = new ErrorResponeDTO();
    errorResponeDTO.setError(ex.getMessage());
    List<String> details = new ArrayList<String>();
    details.add("Check lại dữ liệu bị null đó");
    errorResponeDTO.setDateils(details);
    errorResponeDTO.setTimestamp(new Timestamp(System.currentTimeMillis()));
    errorResponeDTO.setStatus("Bad gateway");
    return new ResponseEntity<>(errorResponeDTO, HttpStatus.BAD_GATEWAY);
  }

}
