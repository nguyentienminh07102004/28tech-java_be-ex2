package com.javaweb.model;

import java.sql.Timestamp;
import java.util.List;

public class ErrorResponeDTO {
  private String error;
  private List<String> dateils;
  private Timestamp timestamp;
  private String status;
  public String getError() {
    return error;
  }
  public Timestamp getTimestamp() {
    return timestamp;
  }
  public void setTimestamp(Timestamp localDateTime) {
    this.timestamp = localDateTime;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public void setError(String error) {
    this.error = error;
  }
  public List<String> getDateils() {
    return dateils;
  }
  public void setDateils(List<String> dateils) {
    this.dateils = dateils;
  }
}
