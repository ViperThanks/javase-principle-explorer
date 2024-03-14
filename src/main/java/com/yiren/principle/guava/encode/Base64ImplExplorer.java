package com.yiren.principle.guava.encode;

import com.google.common.io.BaseEncoding;
import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;

import java.util.Base64;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 24/2/2024
 */
public class Base64ImplExplorer extends BaseExplorer {

    /**
     * 探索 方法
     *
     * @throws Exception
     */
    @Override
    public void explore() throws Exception {
        String encode = BaseEncoding.base64().encode(new byte[]{'a'});
        log.info(encode);
    }

    public static void main(String[] args) {
        Executor.executeMyself();
    }
}
