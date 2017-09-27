package com.inesv.digiccy.dto.auth;

public class ResourceDto {
  private Integer id;

  private String type;

  private String value;

  private Integer parent;

  private Integer state;

  private Integer common;

  private String checked;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Integer getParent() {
    return parent;
  }

  public void setParent(Integer parent) {
    this.parent = parent;
  }

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  public String getChecked() {
    return checked;
  }

  public void setChecked(String checked) {
    this.checked = checked;
  }

  public Integer getCommon() {
    return common;
  }

  public void setCommon(Integer common) {
    this.common = common;
  }
}
