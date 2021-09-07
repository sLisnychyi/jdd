package academy.kovalevskyi.algorithms.week0.day0;

import java.util.Arrays;

public class Tasks {
  public static boolean sameCharactersSorting(String left, String right) {
    if (left.length() != right.length()) {
      return false;
    }
    char[] first = left.toCharArray();
    char[] second = right.toCharArray();
    Arrays.sort(first);
    Arrays.sort(second);
    for (int i = 0; i < left.length(); i++) {
      if (first[i] != second[i]) {
        return false;
      }
    }
    return true;
  }

  public static boolean sameCharactersO1(String left, String right) {
    if (left.length() != right.length()) {
      return false;
    }
    char[] result = new char[26];
    for (char value : left.toCharArray()) {
      result[value - 97] = 1;
    }
    for (char value : right.toCharArray()) {
      char c = result[value - 97];
      if (c == 1) {
        result[value - 97] = 0;
      } else {
        return false;
      }
    }
    for (char c : result) {
      if (c != 0) {
        return false;
      }
    }
    return true;
  }
}
