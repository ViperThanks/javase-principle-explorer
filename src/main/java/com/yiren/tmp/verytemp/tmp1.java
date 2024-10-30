package com.yiren.tmp.verytemp;

import com.yiren.core.Executor;
import com.yiren.core.Explore;
import com.yiren.core.enums.ExplorerType;
import com.yiren.core.expand.BaseExplorer;
import com.yiren.enums.IndexDesc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 25/6/2024
 */
@Explore(value = ExplorerType.JAVA_SE, desc = "测试")
public class tmp1 extends BaseExplorer implements IndexDesc {

    public static void main(String[] args) {
        Executor.executeMyselfWithTime();
    }


    private static final String HDHM_REGEX = "(ADSLD\\d+|\\d{8}D)";
    private static final Pattern HDHM_PATTERN = Pattern.compile(HDHM_REGEX);
    /**
     * 探索 方法
     *
     * @throws Exception e
     */
    @Override
    public void explore() throws Exception {
        int needPutWidth2 = 0,needPutHeight2 = 0;
        String backgroundUrl = "http://182.43.189.184:8390/scrm/static/POST_QRCODE.png";
        try {
            BufferedImage image = getImageFromUrl(backgroundUrl);
            if (image != null) {
                needPutWidth2 = image.getWidth();
                needPutHeight2 = image.getHeight();
                log.info("根据 url : {} 获取图片 width : {}, height : {}", backgroundUrl, needPutWidth2, needPutHeight2);
            } else {
                log.error("根据url : {}获取图片像素失败", backgroundUrl);
            }
        } catch (IOException e) {
            log.error("根据url : {}获取图片像素失败", backgroundUrl);
        }
    }

    public static BufferedImage getImageFromUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        return ImageIO.read(url);
    }

    public static String getName(String filePath) {
        if (null == filePath) {
            return null;
        }
        int len = filePath.length();
        if (0 == len) {
            return filePath;
        }
        if (isFileSeparator(filePath.charAt(len - 1))) {
            // 以分隔符结尾的去掉结尾分隔符
            len--;
        }

        int begin = 0;
        char c;
        for (int i = len - 1; i > -1; i--) {
            c = filePath.charAt(i);
            if (isFileSeparator(c)) {
                // 查找最后一个路径分隔符（/或者\）
                begin = i + 1;
                break;
            }
        }

        return filePath.substring(begin, len);
    }

    /**
     * 是否为Windows或者Linux（Unix）文件分隔符<br>
     * Windows平台下分隔符为\，Linux（Unix）为/
     *
     * @param c 字符
     * @return 是否为Windows或者Linux（Unix）文件分隔符
     */
    public static boolean isFileSeparator(char c) {
        return SLASH == c || BACKSLASH == c;
    }

    /**
     * 字符常量：斜杠 {@code '/'}
     */
    public static final char SLASH = '/';

    /**
     * 字符常量：反斜杠 {@code '\\'}
     */
    public static final char BACKSLASH = '\\';

    public static String extractPath(String urlString) {
        try {
            URL url = new URL(urlString);
            return url.getPath();
        } catch (MalformedURLException e) {
            // 处理 URL 格式错误的情况
            System.err.println("URL 格式错误: " + e.getMessage());
            return null;
        }
    }


    /**
     * @return
     */
    @Override
    public Integer getIndex() {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public String getDesc() {
        return "";
    }

    public enum test1 implements IndexDesc{
        ;

        /**
         * @return
         */
        @Override
        public Integer getIndex() {
            return 0;
        }

        /**
         * @return
         */
        @Override
        public String getDesc() {
            return "";
        }
    }
}
