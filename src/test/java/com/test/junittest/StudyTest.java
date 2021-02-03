package com.test.junittest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class StudyTest {

  @Test
  @Tag("fast")
  void create() {
//    assumeTrue("LOCAL".equalsIgnoreCase(System.getenv("TEST_ENV")));

//    Study study = new Study();
//    assertNotNull(study);
  }

  @DisplayName("스터디 만들기")
  @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
  void repeatTest(RepetitionInfo repetitionInfo) {
    System.out.println("test " + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
  }

  @DisplayName("스터디 만들기")
  @ParameterizedTest(name = "{index} {displayName} name = {argumentsWithNames}, {0}")
  @ValueSource(strings = {"날씨가", "많이", "추워", "진다"})
  void parameterizedTest(String message) {
    System.out.println(message);
  }

  @DisplayName("스터디 만들기")
  @ParameterizedTest(name = "{index} {displayName} name = {argumentsWithNames}, {0}")
  @ValueSource(strings = {"날씨가", "많이", "추워", "진다"})
  @EmptySource
  @NullSource
  void parameterizedTest2(String message) {
    System.out.println(message);
  }

  @DisplayName("스터디 만들기")
  @ParameterizedTest(name = "{index} {displayName} name = {0}")
  @ValueSource(ints = {10, 20, 30})
  public void parameterizedTest3(@ConvertWith(StudyConverter.class) Study study) {
    System.out.println(study.getLimit());
  }

  @DisplayName("스터디 만들기")
  @ParameterizedTest(name = "{index} {displayName} name = {0}")
  @ValueSource(ints = {10, 20})
  void parameterizedTest4(@ConvertWith(StudyConverter.class) Study study) {
    System.out.println(study.getLimit());
  }

  @DisplayName("스터디 만들기")
  @ParameterizedTest(name = "{index} {displayName} name = {0}")
  @CsvSource({"10, '자바스터디'", "20, 스프링"})
  void parameterizedTest5(Integer limit, String name) {
    System.out.println(new Study(limit, name));
  }

  @DisplayName("스터디 만들기")
  @ParameterizedTest(name = "{index} {displayName} name = {0}")
  @CsvSource({"10, '자바스터디'", "20, 스프링"})
  void parameterizedTest6(@AggregateWith(StudyAggregator.class) Study study) {
    System.out.println("study = " + study);
  }

  static class StudyAggregator implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
      return new Study(accessor.getInteger(0), accessor.getString(1));
    }
  }

  static class StudyConverter extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
      assertThat(targetType).isSameAs(Study.class);
      return new Study(Integer.parseInt(source.toString()));
    }
  }

}