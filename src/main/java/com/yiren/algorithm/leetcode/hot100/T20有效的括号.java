package com.yiren.algorithm.leetcode.hot100;

import ch.qos.logback.classic.filter.LevelFilter;
import com.yiren.tmp.Main;

import java.util.*;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 13/9/2023
 */
public class T20有效的括号 {


  public static void main(String[] args) {
    boolean valid = new Solution().isValid("{[]}");
    System.out.println(valid);

  }

}

class Solution1 {
  char[] left = {'{', '(', '['};
  char[] right = {'}', ')', ']'};

  public boolean isValid(String s) {
    ArrayDeque<Character> stack = new ArrayDeque<>(3);
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (isLeft(c)) {
        stack.push(c);
      }else {
        //if (stack.isEmpty() || stack.pop() != )
      }
    }

    return true;
  }

  private boolean isLeft(char x) {
    for (char c : left) {
      if (c == x) {
        return true;
      }
    }
    return false;
  }
}

class Solution {

  static final Stack<Character> stack = new Stack<>();
  static final Map<Character, Character> map = new HashMap<>(5);

  static {
    map.put('{', '}');
    map.put('(', ')');
    map.put('[', ']');
  }

  public boolean isValid(String s) {
    for (int i = 0; i < s.length(); i++) {
      char currentChar = s.charAt(i);
      if (isLeft(currentChar)) {
        stack.add(currentChar);
      } else {
        if (stack.isEmpty() || map.get(stack.pop()) != currentChar) {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }


  private boolean isLeft(char x) {
    return map.containsKey(x);
  }
}
