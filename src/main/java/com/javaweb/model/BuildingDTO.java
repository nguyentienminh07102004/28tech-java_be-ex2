package com.javaweb.model;

public class BuildingDTO {
  private String name;
  private Integer numberOfBasement;
  private String address;
  private String managerName;
  private String managerPhoneNumber;
  private Integer floorArea;
  private String rentArea;
  private String brokerageFee;
  private String serviceFee;
  private Integer rentPrice;
  private Integer freeSpace;
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Integer getNumberOfBasement() {
    return numberOfBasement;
  }
  public void setNumberOfBasement(Integer numberOfBasement) {
    this.numberOfBasement = numberOfBasement;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getManagerName() {
    return managerName;
  }
  public void setManagerName(String managerName) {
    this.managerName = managerName;
  }
  public String getManagerPhoneNumber() {
    return managerPhoneNumber;
  }
  public void setManagerPhoneNumber(String managerPhoneNumber) {
    this.managerPhoneNumber = managerPhoneNumber;
  }
  public Integer getFloorArea() {
    return floorArea;
  }
  public void setFloorArea(Integer floorArea) {
    this.floorArea = floorArea;
  }
  public String getRentArea() {
    return rentArea;
  }
  public void setRentArea(String rentArea) {
    this.rentArea = rentArea;
  }
  public String getBrokerageFee() {
    return brokerageFee;
  }
  public void setBrokerageFee(String brokerageFee) {
    this.brokerageFee = brokerageFee;
  }
  public String getServiceFee() {
    return serviceFee;
  }
  public void setServiceFee(String serviceFee) {
    this.serviceFee = serviceFee;
  }
  public Integer getRentPrice() {
    return rentPrice;
  }
  public void setRentPrice(Integer rentPrice) {
    this.rentPrice = rentPrice;
  }
  public Integer getFreeSpace() {
    return freeSpace;
  }
  public void setFreeSpace(Integer freeSpace) {
    this.freeSpace = freeSpace;
  }
  
}
