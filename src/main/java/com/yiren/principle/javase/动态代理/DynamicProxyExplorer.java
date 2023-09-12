package com.yiren.principle.javase.动态代理;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 7/9/2023
 */
public class DynamicProxyExplorer implements Explorer {

  private static final Logger log = LoggerFactory.getLogger(DynamicProxyExplorer.class);

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    List<Integer> instance = (List<Integer>) new JdkDynamicProxy().getInstance(new ArrayList<Integer>());
    instance.add(1);
    instance.add(2);
    log.info(instance.toString());
    SecurityManager securityManager = System.getSecurityManager();
  }

  public static void main(String[] args) {
    Executor.executeExplorer(DynamicProxyExplorer.class);
  }


}