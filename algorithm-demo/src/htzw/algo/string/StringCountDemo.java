package htzw.algo.string;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 输入两个字符串a和b，统计字符串a中每个字符，在字符串b中出现的次数
 *      * 例如：输入字符串abdfsg和dfhstaaeggbdsag，
 *      * 统计结果为：a:3	b:1	s:2	d:2	f:1	g:3
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/8/24 9:10
 */
public class StringCountDemo {
    public static void main(String[] args) {
        System.out.println(totalNum("abdfsg","dfhstaaeggbdsag"));
    }

    /**
     * 输入两个字符串a和b，统计字符串a中每个字符，在字符串b中出现的次数
     * 例如：输入字符串abdfsg和dfhstaaeggbdsag，
     * 统计结果为：a:3	b:1	s:2	d:2	f:1	g:3
     *
     * @param a 字符串a
     * @param b 字符串b
     * @return
     */
    private static String totalNum(String a , String b){
        StringBuilder res = new StringBuilder();
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        Map<Character, Integer> map = new HashMap<>(aChars.length);
        for (char aChar : aChars) {
            map.put(aChar,0);
        }
        for (char bChar : bChars) {
            if (map.containsKey(bChar)){
                map.put(bChar,map.get(bChar)+1);
            }
        }
        Set<Map.Entry<Character, Integer>> entries = map.entrySet();

        for (Map.Entry<Character, Integer> next : entries) {
            res.append(next.getKey()).append(":").append(next.getValue()).append("\t");
        }
        return res.toString();
    }
}
