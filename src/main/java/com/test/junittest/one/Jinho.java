package com.test.junittest.one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
public class Jinho extends Ab{

  private String name;

  @Override
  Ab print() {
    Jinho jinho = new Jinho("jinho");
    System.out.println("jinho = " + jinho);
    return jinho;
  }

  public static void main(String[] args) {
    Student student = new Student(Arrays.asList(Fruit.BANANA, Fruit.APPLE));

    List<Fruit> fruitList = student.getFruitList();

    List<String> collect = fruitList.stream()
        .flatMap(fruit -> fruit.getCode().stream())
        .collect(Collectors.toList());
    System.out.println("collect = " + collect);
  }
}

@Getter
@AllArgsConstructor
class Student {

  List<Fruit> fruitList;
}

@Getter
@RequiredArgsConstructor
@ToString
enum Fruit {
  BANANA("바나나", Arrays.asList("1", "2")),
  APPLE("사과", Arrays.asList("4", "6"));

  private final String name;
  private final List<String> code;
}