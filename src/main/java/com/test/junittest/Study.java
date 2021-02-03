package com.test.junittest;


import lombok.Data;

@Data
public class Study {

  private int limit;
  private String name;

  public Study(int limit, String name) {
    this.limit = limit;
    this.name = name;
  }

  public Study(int limit) {
    this.limit = limit;
  }
}
