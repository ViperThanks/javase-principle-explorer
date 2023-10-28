package com.yiren.MyRedis.test;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.MyRedis.ICache.ICache;
import com.yiren.MyRedis.ICache.impl.CacheBuilder;
import com.yiren.MyRedis.ICache.impl.CacheEvicts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 10/13/2023
 */
public class FixedCacheExplorer implements Explorer {

  private static final Logger LOGGER = LoggerFactory.getLogger(FixedCacheExplorer.class);

  @Override
  public void explore() throws Exception {
    ICache<Object, Object> cache = CacheBuilder
        .builder()
        .size(3)
        .map(new HashMap<>())
        .evict(CacheEvicts.fifo())
        .build();
    cache.put("hello", "adslf");
    //业务
    cache.put("why", "java");
    cache.put("yes", "www");
    LOGGER.info("entry set : {}",cache.entrySet());
    cache.put("new","genshi");
    LOGGER.info("entry set : {}",cache.entrySet());

  }

  public static void main(String[] args) {
    Executor.executeExplorer(FixedCacheExplorer.class);
  }
}
