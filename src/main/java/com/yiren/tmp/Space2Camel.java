package com.yiren.tmp;

import com.yiren.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * desc: for example ：execute explorerClass
 *
 * @author Viper Thanks
 * @since 25/4/2024
 */
public class Space2Camel {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private static final String fatherFolderPath = "D:/company/工作/ETL-国际化/";
    private static final PrintStream CN_PRINT;
    private static final PrintStream EN_PRINT;

    static {
        try {
            CN_PRINT = new PrintStream(new FileOutputStream(fatherFolderPath + "ScriptExecution-cn.json", true));
            EN_PRINT = new PrintStream(new FileOutputStream(fatherFolderPath + "ScriptExecution-en.json", true));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String space = " ";

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.print("输入: ");
            String param = READER.readLine();
            if (param == null || param.isEmpty()) {
                continue;
            }
            String[] words = param.trim().split(space);
            StringBuilder pascalCase = new StringBuilder();
            for (String word : words) {
                if (!word.isEmpty()) {
                    // 将每个词的第一个字母转换为大写，其余部分保持小写
                    pascalCase.append(word.substring(0, 1).toUpperCase());
                    if (word.length() > 1) {
                        pascalCase.append(word.substring(1));
                    }
                }
            }
            System.out.println(pascalCase);
            CN_PRINT.printf("  \"%s\": ", pascalCase);
            EN_PRINT.printf("  \"%s\": ", pascalCase);
            System.out.print("cn : ");
            String param1 = READER.readLine();
            System.out.print("en : ");
            String param2 = READER.readLine();
            System.out.print("ensure ? ");
            String param3 = READER.readLine();
            if (StringUtils.equalsAnyIgnoreCase(param3, "n", "no")) {
                continue;
            }
            CN_PRINT.printf("\"%s\",%n",param1);
            EN_PRINT.printf("\"%s\",%n", CommonUtils.defaultIfEmpty(param2,() -> param));

            EN_PRINT.flush();
            CN_PRINT.flush();
            System.out.println("ok");
        }
    }
}
