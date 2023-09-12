package com.yiren.principle.juc.设计模式.保护性暂停;

import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 12/9/2023
 */
public class Downloader {

  private static final String baiduUrl = "https://www.baidu.com";



  public static List<String> download() throws IOException{
    URL url = new URL(baiduUrl);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    List<String> lines = new ArrayList<>();
    try(BufferedReader reader =
      new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
      String line;
      while (Objects.nonNull(line = reader.readLine()))
        lines.add(line);
    }
    return lines;
  }

}
