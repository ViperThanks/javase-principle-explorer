package com.yiren.principle.guava.io.source;

import com.google.common.base.Splitter;
import com.google.common.io.BaseEncoding;
import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.core.expand.BaseExplorer;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 31/1/2024
 */
public class EncodingExplorer extends BaseExplorer {


    /**
     * 探索 方法
     */
    @Override
    public void explore() throws Exception {
        String jwtString = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5YTkyODM0NGY5NzY0Mzc0YTJlZmQ5OWVjN2IyZTBiMSIsInN1YiI6IjEyMzQiLCJpc3MiOiJzZyIsImlhdCI6MTY3NDA0MDQ4MiwiZXhwIjoxNjc0MDQ0MDgyfQ.7AGeaAwFpy1Op-3Q-EGzZZFXxj1_-pcm1xaE7bNxer8";
        BaseEncoding base64 = BaseEncoding.base64();
        Splitter.on('.')
                .splitToStream(jwtString)
                .map(subjwt -> new String(base64.decode(subjwt)))
                .forEach(log::info);

    }

    public static void main(String[] args) {
        Executor.executeMyself();
    }


}