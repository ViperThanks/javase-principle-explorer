package com.yiren.tmp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 2023-10-19
 */
@RequiredArgsConstructor
@Data
public class Test123 {

  private final String name;

  private boolean hello;

  List<Double> scores = new ArrayList<>();

  public String getName() {
    return name;
  }

  public boolean isHello() {
    return hello;
  }

  public void setHello(boolean hello) {
    this.hello = hello;
  }

  public List<Double> getScores() {
    return Collections.unmodifiableList(scores);
  }

  public void setScores(List<Double> scores) {
    this.scores = scores;
  }
}
