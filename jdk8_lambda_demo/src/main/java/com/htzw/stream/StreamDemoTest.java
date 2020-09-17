package com.htzw.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.*;

/**
 * 在 Java 8 中增加了一个新的抽象接口 Stream API，它支持声明式的处理数据。使用 Stream 操作集合似于使用
 * SQL 语句数据库查找数据类似，提供直观的方法进行操作。 同时 Stream API 让开发者能够快速写出干净、简洁的
 * 代码，提高开发者的开发效率。
 * <p>
 * Stream 将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道传输过程中对流进行处理， 比如筛选、
 * 排序、聚合等操作。在经过一系列中间操作后形成最终的管道，得到处理的结果。
 *
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/9/14 14:18
 */
public class StreamDemoTest {


    /**
     * 各类数据转Stream
     * 一般我们需要获取 `Stream` 对象后才能对其进行操作，
     * 下面列出了一些数据转换为 `Stream` 的常用方法
     */
    @Test
    public void stream001() {
        // 多个数据直接转换为 Stream
        Stream stream1 = Stream.of("a", "b", "c");

        // 数组转换为 Stream
        String[] strArrays = new String[]{"a", "b"};
        Stream stream2 = Arrays.stream(strArrays);

        // list 列表转换为 Stream
        List<String> strList = new ArrayList<>();
        strList.add("a");
        strList.add("b");
        Stream stream3 = strList.stream();

        // Set 集合转换为 Stream
        Set<String> strSet = new HashSet<>();
        strSet.add("a");
        strSet.add("b");
        Stream stream4 = strSet.stream();

        // Map 集合转换为 Stream
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 100);
        map.put("b", 200);
        Stream stream5 = map.entrySet().stream();
    }

    /**
     * ## Stream 转换得到指定类型数据
     * <p>
     * ​既然可以把集合或者数组转换成流，那么也就可以把流转换回去，使用 `collect()` 方法
     * 就能实现，不过一般还需要配合 `Collectors` 工具类一起使用，`Collectors` 类中
     * 内置了一系列收集器实现，如下：
     * <p>
     * - **toList()：** 将元素收集到一个新的 List 集合中；
     * - **toSet()：** 将元素收集到一个新的 Set 集合中；
     * - **toCollection()：** 将元素收集到一个新的 ArrayList 集合中；
     * - **joining()：** 将元素收集到一个可以用分隔符指定的字符串中；
     * <p>
     * 下面再介绍下 Stream 转换指定类型的常用示例：
     */
    @Test
    public void stream002() {
        // 创建 Stream，将 Stream 转换为数组
        Stream stream = Stream.of("a", "b", "c");
        Object[] objectArray = stream.toArray();

        // 创建 Stream，将 Stream 转换为字符串
        Stream stream2 = Stream.of("a", "b", "c");
        String str = stream2.collect(Collectors.joining()).toString();
        System.out.println(str);

        // 创建 Stream，将 Stream 转换为 List 列表
        Stream stream3 = Stream.of("a", "b", "c");
        List<String> strList = (List<String>) stream3.collect(Collectors.toList());

        // 创建 Stream，将 Stream 转换为 Set 集合
        Stream stream4 = Stream.of("a", "b", "c");
        Set<String> strSet = (Set<String>) stream4.collect(Collectors.toSet());
    }

    /**
     * Stream 对于基本类型的封装
     * <p>
     * ​因为 `Java` 的范型不支持基本类型，所以我们无法用 `Stream<int>` 这样的类型，如果写了类似代码在编译器中会提示编译错
     * 误。为了存储 `int`，只能使用 `Stream<Integer>`，但这样会产生频繁的装箱、拆箱操作。为了提高效率，对基本类型数据
     * `int`、`long`、`double` 进行了封装，分别为 `IntSteam`、`LongStream`、`DoubleSteam`，使用方法和 `Stream` 类似
     */
    @Test
    public void stream003() {
        // IntSteam
        IntStream.of(new int[]{10, 20, 30}).forEach(System.out::println);
        IntStream.range(1, 5).forEach(System.out::println);
        IntStream.rangeClosed(1, 5).forEach(System.out::println);

        // LongStream
        LongStream.of(new long[]{1L, 2L, 3L}).forEach(System.out::println);
        LongStream.range(1, 5).forEach(System.out::println);
        LongStream.rangeClosed(1, 5).forEach(System.out::println);

        // DoubleSteam
        DoubleStream.of(1.11, 2.23, 3.14).forEach(System.out::println);
    }

    /**
     * Stream 的并行介绍
     *
     *在 `Stream` 中，最明显的特点就是存在并行操作，不过如果使用默认方式执行中间与终端操作，那么整个执行过程其实是个串行操作。
     * 如果想让 `Stream` 并行处理数据，那么需要 `Stream` 中调用 `parallel()` 或者集合中调用 `parallelStream()` 方法来开启并行执行。
     *
     * 其中 `Stream` 底层使用的是 `ForkJoinTask` 实现 `Stream` 的并行处理，充分利用 `CPU` 的多核能力，`Stream` 的 `API`
     * 将底层复杂实现完全屏蔽了，开发者仅需调用一个方法即可实现并行计算。
     *
     * 从下面的运行结果来看，可以看到使用并行执行所花费的时间远低于串行所花费的时间，不过在使用并行执行时一定要先考虑好使用情况，考虑执行
     * 数据是否需要顺序执行，是否涉及线程安全，是否涉及使用网络等，并不是全部情况都能使用并行执行完成处理逻辑的，
     * 所以在使用之前一定要慎重，使用不好很可能会带来性能的不升反降。
     *
     * 一般情况下，如机器学习和数据处理等比较适合使用并行处理数据任务，其它方便需要使用者自己衡量进行测试，是否该使用并行执行任务。
     */
    @Test
    public void stream04(){
        // 并行计算
        long startTime = System.currentTimeMillis();
        long sumResult1 = LongStream.rangeClosed(1, 100000000000L).parallel().sum();
        System.out.println("并行执行耗时：" + (System.currentTimeMillis() - startTime));

        // 串行计算
        startTime = System.currentTimeMillis();
        long sumResult2 = LongStream.rangeClosed(1, 100000000000L).sum();
        System.out.println("串行执行耗时：" + (System.currentTimeMillis() - startTime));

        // 输出汇总结果
        System.out.println("汇总结果1 = " + sumResult1 + "，汇总结果2 = " + sumResult2);
    }


    /**
     * =======================================Stream 中间操作（有状态）常用 API=========================================
     * distinct
     *
     * 保证输出的流中包含唯一的元素，通常用于数据去重。
     *
     * - 接口定义：
     *   - Stream<T> distinct();
     * - 方法描述：
     *   - 在 `distinct` 接口定义中不接收任何参数，该方法的作用是根据 `hashCode()` 和 `equals()` 方法来获取不同的元素，因此我们的元素
     *   必须实现这两个方法。如果 `distinct()` 正在处理有序流，那么 对于重复元素将保留处理元素时的顺序。而在处理无序流的情况下，则不一定保证
     *   元素的顺序。在有序流的并行执行情况下，保持 `distinct()` 的顺序性是需要高昂的缓冲开销。如果我们在处理元素时，不需要保证元素的顺序性，
     *   那么我们可以使用 `unordered()` 方法实现无序流。
     */
    @Test
    public void stream05() {
        // 创建 List 集合
        List<Integer> strList = new ArrayList<>();
        strList.add(1);
        strList.add(2);
        strList.add(2);
        strList.add(1);
        strList.add(3);

        // 执行 distinct 操作
        strList.stream().distinct().forEach(System.out::println);
    }
    /**
     *  sorted
     *
     * 对数据进行排序，不过对于有序流，排序是稳定的，而对于非有序流，不保证排序稳定。
     *
     * - 接口定义：
     *   - Stream<T> sorted();
     *   - Stream<T> sorted(Comparator<? super T> comparator);
     * - 方法描述：
     *   - 使用 `Stream<T> sorted();` 方法时，它会将 `Stream` 中的元素按照 `自然排序` 方式对元素进行排序。等到将全部元素处理完成后，将元
     *   素组成新的 `Stream` 返回。
     *   - 使用 `Stream<T> sorted(Comparator<? super T> comparator);` 方法时，它接收的是一个 `Comparator` 类型参数，在 `Lambda`
     *   表达式中 `Comparator<T>` 一般是用于比较两个参数，设置一个算法逻辑，执行完后返回一个整数，可以是负数、零、正整数，它的的不同的值表
     *   示两个值比较的不同，一般排序会按这个比较结果进行排序。等到将全部元素处理完成后，将元素组成新的 `Stream` 返回。
     */
    @Test
    public void stream06() {
        // 自然排序
        List<String> strList1 = new ArrayList<>();
        strList1.add("a");
        strList1.add("b");
        strList1.add("c");
        strList1.stream().sorted().forEach(System.out::println);
        System.out.println("-------------------");
        // 指定规则排序
        List<Integer> strList2 = new ArrayList<>();
        strList2.add(30);
        strList2.add(10);
        strList2.add(20);
        strList2.stream().sorted((o1, o2) -> o1-o2).forEach(System.out::println);
    }
    /**
     * skip
     *
     * 根据指定数值，从指定位置跳过流中某些元素。
     *
     * - 接口定义：
     *   - Stream<T> skip(long n);
     * - 方法描述：
     *   - 在 `skip` 接口定义中是接收 `long` 类型参数，指定要跳过前 `n` 个元素，将第 `n` 个后的元素组成新的流返回。
     */
    @Test
    public void stream07() {
        // 创建 List 集合
        List<Integer> strList = new ArrayList<>();
        strList.add(1);
        strList.add(2);
        strList.add(3);
        strList.add(4);
        strList.add(5);

        // 执行 peek 操作,跳过第一个，从第二个开始
        strList.stream().skip(1).forEach(System.out::println);
    }
    /**
     * limit
     *
     * 根据指定数值，限制只能访问流中最大访问的个数。这是一个有状态的、短路方法。
     *
     * - 接口定义：
     *   - Stream<T> limit(long maxSize);
     * - 方法描述：
     *   - 在 `limit` 接口定义中是接收 `long` 类型参数，指定限制 `maxSize` 个元素，即将 `maxSize` 和它之前的元素组成新的流返回。
     */
    @Test
    public void stream08(){
        // 创建 List 集合
        List<Integer> strList = new ArrayList<>();
        strList.add(1);
        strList.add(2);
        strList.add(3);
        strList.add(4);
        strList.add(5);

        // 执行 limit 操作
        strList.stream().limit(2).forEach(System.out::println);
    }

    /**
     * ===================================Stream 中间操作（无状态）常用 API=========================================
     *  map
     *
     * 对流中的元素进行处理，然后返回，返回值的类型可以和原来的元素的类型不同。
     *
     * - 接口定义：
     *   - Stream<R> map(Function<? super T, ? extends R> com.htzw.plus.mapper);
     * - 方法描述：
     *   - 在 `map` 接口定义中是接收 `Function` 类型参数，了解 `Lambda` 表达式就可以知道 `Function<T,R>` 是接收一个 `T`
     *   返回处理后的值 `R`。所以，这里 `map` 方法就是对流中的元素进行处理，然后返回一个新的元素。等到将全部元素处理完成后将
     *   元素组成新的流返回。
     */
    @Test
    public void stream09(){
        // 创建 List 集合
        List<String> strList = new ArrayList<>();
        strList.add("zhangsan");
        strList.add("lisi");
        strList.add("wangwu");
        strList.add("zhaoliu");
        strList.add("sunqi");

        // 执行 map 操作
        strList = strList.stream().map(x -> "测试：" + x).collect(Collectors.toList());
        strList.forEach(System.out::println);
    }

    /**
     *### peek
     *
     * 对流中的每个元素进行操作处理，返回的流和原来的流保存一样的元素。
     *
     * - 接口定义：
     *   - Stream<T> peek(Consumer<? super T> action);
     * - 方法描述：
     *   - 在 `peek` 接口定义中是接收 `Consumer` 类型参数，了解 `Lambda` 表达式就可以知道
     *   `Consumer<T>` 是接收一个 `T` 返回处理后不返回值。所以，这里 `peek` 方法就是对流中
     *   的元素进行处理而不返回值。等到将全部元素处理完成后将元素组成新的流返回。
     */
    @Test
    public void stream10(){
        List<String> strList = new ArrayList<>();
        strList.add("zhangsan");
        strList.add("lisi");
        strList.add("wangwu");
        strList.add("zhaoliu");
        strList.add("sunqi");

        // 执行 peek 操作
        strList.stream().peek(x -> System.out.println("forEach 1：" + x))
                .peek(x -> System.out.println("forEach 2：" + x))
                .peek(x-> System.out.println("forEach 3:"+ x))
                .forEach(System.out::println);
    }

    /**
     *filter
     *
     * 主要用于数据过滤，过滤不符合 predicate 条件的元素，保留过滤后的元素。
     *
     * - 接口定义：
     *   - Stream<T> filter(Predicate<? super T> predicate);
     * - 方法描述：
     *   - 在 `filter` 接口定义中是接收 `Predicate` 类型参数，了解 `Lambda` 表达式就可以知
     *   道 `Predicate<T>` 是接收一个 `T` 进行验证，返回布尔值。所以，这里 `filter` 方法就是设
     *   置验证条件，对流中的元素进行验证，返回符合条件的全部元素组成新的流。
     */
    @Test
    public void stream11(){
        List<String> strList = new ArrayList<>();
        strList.add("zhangsan");
        strList.add("lisi");
        strList.add("wangwu");
        strList.add("zhaoliu");
        strList.add("sunqi");

        // 执行 filter 操作
        strList = strList.stream().filter(x -> x.length() > 5).collect(Collectors.toList());
        strList.forEach(System.out::println);
    }

    /**
     *  mapToInt
     *
     * 主要用于对流中元素进行一对一转换为 int 整数，然后可以进行一些求和、平均值、最大最小值等处理。
     *
     * - 接口定义：
     *   - IntStream mapToInt(ToIntFunction<? super T> com.htzw.plus.mapper);
     * - 方法描述：
     *   - 在 `mapToInt` 接口定义中可知，它接收 `ToIntFunctio` 类型参数，在 `Lambda` 中 `ToIntFunction<T>` 函数的作用为
     *   接受一个输入参数，返回一个 `int` 类型结果，根据这点很容易了解到 `mapToInt` 方法就是将 `Stream` 中原有的元素类型转换
     *   为 `int` 类型到新的 `Stream` 中。
     */
    @Test
    public void stream12(){
        // 获取 Stream 对象
        Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // 获取元素最大值
        int max = stream1.mapToInt(x -> x).summaryStatistics().getMax();
        // 输出
        System.out.println(max);
    }

    /**
     * mapToDouble
     *
     * 主要用于对流中元素进行一对一转换为 double 双精度浮点型，然后可以进行一些求和、平均值、最大最小值等处理。
     *
     * - 接口定义：
     *   - IntStream mapToInt(ToDoubleFunction<? super T> com.htzw.plus.mapper);
     * - 方法描述：
     *   - 在 `mapToDouble` 接口定义中可知，它接收 `ToDoubleFunction` 类型参数，
     *   在 `Lambda` 中 `ToDoubleFunction<T>` 函数的作用为接受一个输入参数，返回一个 `int` 类型结果，
     *   根据这点很容易了解到 `ToDoubleFunction` 方法就是将 `Stream` 中原有的元素类型转换为 `double` 类型到新的 `Stream` 中。
     */
    @Test
    public void stream13(){
        // 获取 Stream 对象
        Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // 获取元素最大值
        double max = stream1.mapToDouble(x -> x).summaryStatistics().getMax();
        // 输出
        System.out.println(max);
    }
    /**
     * ===================================Stream 终端操作（非短路操作）常用 API============================
     * max
     *
     * 返回流中所有元素的最大值。
     *
     * - 接口定义：
     *   - Optional<T> max(Comparator<? super T> comparator);
     * - 方法描述：
     *   - 在 `max` 接口定义中是接收 Comparator 类型参数，`Lambda` 常用函数的 `Consumer<T>`
     *   一般是用于比较两个参数，设置一个算法逻辑，执行完后返回一个整数，可以是负数、零、正整数，根据返回的值结果进行排序筛选出最大值。
     */
    @Test
    public void stream14(){
        // 获取 Stream 对象
        Stream<Integer> stream = Stream.of(1, 3, 5, 8);
        // 求 Stream 元素中最大值
        Optional optional = stream.max((o1, o2) -> o1-o2);
        // 判断 Optional 中值是否为空，不为空就输出
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }
    }

    /**
     * min
     *
     * 返回流中所有元素的最小值。
     *
     * - 接口定义：
     *   - Optional<T> min(Comparator<? super T> comparator);
     * - 方法描述：
     *   - 在 `max` 接口定义中是接收 `Comparator` 类型参数，`Lambda` 常用函数的 `Consumer<T>`
     *   一般是用于比较两个参数，设置一个算法逻辑，执行完后返回一个整数，可以是负数、零、正整数，根
     *   据返回的值结果进行排序筛选出最小值。
     */
    @Test
    public void stream15(){
        // 获取 Stream 对象
        Stream<Integer> stream = Stream.of(1, 3, 5, 8);
        // 求 Stream 元素中最小值
        Optional optional = stream.min((x, y) -> x - y);
        // 判断 Optional 中值是否为空，不为空就输出
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }
    }
    /**
     * count
     *
     * 统计流中所有元素的数目。
     *
     * - 接口定义：
     *   - long count();
     * - 方法描述：
     *   - 该方法用于统计 `Stream` 中元素个数，返回 `long` 类型结果。
     */
    @Test
    public void stream16(){
        // 获取 Stream 对象
        Stream<Integer> stream = Stream.of(1, 3, 5, 8);
        System.out.println(stream.count());
    }
}
