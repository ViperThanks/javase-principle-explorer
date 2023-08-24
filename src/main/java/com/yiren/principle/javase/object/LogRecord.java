package com.yiren.principle.javase.object;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class LogRecord {
  private String businessName;
  private String methodName;
  private String url;
  private String method;
  private String ip;
  private LocalDateTime operateTime;
}