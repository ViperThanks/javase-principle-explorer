package com.yiren.redis;

import com.yiren.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 7/6/2024
 */
public class SortedSetTerminal {


    public String doCommand(String clientCommand) {
        if (StringUtils.isBlank(clientCommand)) {
            return "invalid command";
        }
        return doCommand(clientCommand.split(" "));
    }

    /**
     * zadd setName key1 score1 key2 score2
     * @param clientCommand
     * @return
     */
    private String doCommand(String[] clientCommand) {
        String event = clientCommand[0];
        String containerKey = clientCommand[1];
        final SortedSet sortedSet = CommonUtils.defaultIfNull(SortedSet.get(containerKey), () -> SortedSet.create(containerKey));
        Object invoke = null;
        for (int i = 2; i < clientCommand.length - 1; i += 2) {
            String key = clientCommand[i];
            double score = Double.parseDouble(clientCommand[i + 1]);
            try {
                Method zaddMethod = getMethod(event, sortedSet, key, score);
                invoke = zaddMethod.invoke(null, sortedSet, key, score);
            } catch (Exception ignored) {

            }
        }
        return String.valueOf(invoke);
    }

    private Method getMethod(String methodName, Object... params) {
        Class<?>[] paramTypes = Arrays.stream(params)
                .map(Object::getClass)
                .toArray(Class<?>[]::new);
        try {
            return SortedSet.class.getMethod(methodName, paramTypes);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Method not found: " + methodName, e);
        }
    }
}
