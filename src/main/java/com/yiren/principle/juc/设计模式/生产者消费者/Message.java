package com.yiren.principle.juc.设计模式.生产者消费者;

public final class Message<T> {
  public Message(Integer id, T information) {
    this.id = id;
    this.information = information;
  }

  private final Integer id;
  private final T information;

  public Integer getId() {
    return id;
  }

  public T getInformation() {
    return information;
  }

  @Override
  public String toString() {
    return "Message{" +
      "id=" + id +
      ", information=" + information +
      '}';
  }
}