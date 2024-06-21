package com.javaweb.customerException;

public class FeildRequireException extends RuntimeException {
  public FeildRequireException() {
    super("Feild Require is null");
  }
  
  public FeildRequireException(String exceptionMessage) {
    super(exceptionMessage);
  }
}