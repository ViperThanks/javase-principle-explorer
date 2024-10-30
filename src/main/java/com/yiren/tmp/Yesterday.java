package com.yiren.tmp;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableMap;
import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;
import com.yiren.principle.utils.PrincipleUtil;
import com.yiren.utils.CallerUtils;
import com.yiren.utils.CommonUtils;
import com.yiren.utils.Generator;
import com.yiren.utils.LambadaUtils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * desc:
 *
 * @author Viper Thanks
 * @since 25/3/2024
 */
public class Yesterday extends BaseExplorer {

    public static void main(String[] args) {
        Executor.executeMyselfWithTime();
    }

    private static final Object present = PrincipleUtil.getStaticFiled(HashSet.class, "PRESENT", Object.class).get();

    /**
     * 探索 方法
     *
     * @throws Exception e
     */
    @Override
    public void explore() throws Exception {
        Stopwatch started = Stopwatch.createStarted();
        Map<String, Double> collect = StringGenerator.list(10).stream().collect(Collectors.toMap(Function.identity(), (k) -> CommonUtils.getRandomDouble(60, 100), (a1, a2) -> a2));
        pws(collect,"map");
        ImmutableMap.Builder<String, Double> builder = ImmutableMap.builderWithExpectedSize(10);
        for (int i = 0; i < 10; i++) {
            builder.put(CommonUtils.getRandomString(), CommonUtils.getRandomDouble(60, 100));
        }
        Map<String, Double> userNameScoreMap = builder.buildKeepingLast();
        pws(userNameScoreMap, "map");
        pws(PrincipleUtil.getStaticFiled(HashSet.class, "PRESENT", Object.class));

        //
        List<String> list = StringGenerator.list(50);
        List<Double> list1 = DoubleGenerator.list(50);
        Iterator<Double> iterator = list1.iterator();
        Map<String, Double> map = list.stream().collect(Collectors.toMap(Function.identity(), (k) -> iterator.next(), LambadaUtils.keepingLast()));
        pws(map);

        pws("调用者 cnm " + CallerUtils.getCallerClassName() + "听到没有？");
        pws(started.stop(),"耗时");

        Generator.newBuilder().generator(null).predicate(null).build();
    }

    public void test(){
        pws(CallerUtils.getCallerClass());
    }
}