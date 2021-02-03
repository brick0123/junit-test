package com.test.junittest.one;


public class Two extends AbstractClass {

  private String name;

  public Two(int age, String name) {
    super(age);
    this.name = name;
  }

  public Two(String name) {
    this.name = name;
  }

  public static void main(String[] args) {
    Two ke = new Two(5, "ke");
    System.out.println(ke.getAge());
  }

}
