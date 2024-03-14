package com.yiren.tmp;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;
import com.yiren.practice.PrintSequenceFrom1To100;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 29/1/2024
 */
public class tmp12 extends BaseExplorer {

    public static void main(String[] args) {
        Executor.executeMyself();
    }

    private static final LoadingCache<String, String> LOADING_CACHE =
            CacheBuilder.newBuilder().
                    build(new CacheLoader<String, String>() {
                        @Nonnull
                        @Override
                        public String load(@Nonnull String key) throws Exception {
                            return key + 1;
                        }
                    });

    @Override
    public void explore() throws Exception {
        log.info(String.valueOf(60_000 * 365 * 25 * 0.15));
    }
}
