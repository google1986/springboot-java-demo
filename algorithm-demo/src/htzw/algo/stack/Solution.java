package htzw.algo.stack;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/7/10 14:03
 */
public class Solution {
    @Test
    public void main() {
        Solution solution = new Solution();
        System.out.println(solution.isValid2("(()){}"));
    }
    // Hash table that takes care of the mappings.
    private static HashMap<Character, Character> mappings;

    // Initialize hash map with mappings. This simply makes the code easier to read.
//    public Solution() {
//        this.mappings = new HashMap<>();
//        this.mappings.put(')', '(');
//        this.mappings.put('}', '{');
//        this.mappings.put(']', '[');
//    }

    static {
        mappings = new HashMap<>();
        mappings.put(')','(');
        mappings.put(']','[');
        mappings.put('}','{');

    }

    private boolean isValid2(String s){
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (mappings.containsKey(aChar)) {
                char topElement = stack.empty() ? '#' : stack.pop();
                if (topElement !=mappings.get(aChar)) {
                    return false;
                }
            }else {
                stack.push(aChar);
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid(String s) {

        // Initialize a stack to be used in the algorithm.
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the current character is a closing bracket.
            if (mappings.containsKey(c)) {

                // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
                char topElement = stack.empty() ? '#' : stack.pop();

                // If the mapping for this bracket doesn't match the stack's top element, return false.
                if (topElement != mappings.get(c)) {
                    return false;
                }
            } else {
                // If it was an opening bracket, push to the stack.
                stack.push(c);
            }
        }

        // If the stack still contains elements, then it is an invalid expression.
        return stack.isEmpty();
    }
}
