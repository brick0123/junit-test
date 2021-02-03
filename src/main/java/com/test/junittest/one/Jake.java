package com.test.junittest.one;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Jake extends Ab {

  private String name;

  @Override
  Ab print() {
    Jake jake = new Jake("jake");
    System.out.println("jake = " + jake);
    return jake;
  }
}
