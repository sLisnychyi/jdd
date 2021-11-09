package academy.kovalevskyi.algorithms.week0.day0;

public class Tasks {
  private static final Sort SORT = new Sort() {};

  public static boolean sameCharactersSorting(String left, String right) {
    if (left.length() != right.length()) {
      return false;
    }
    char[] leftArr = left.toCharArray();
    char[] rightArr = right.toCharArray();
    SORT.sort(leftArr, rightArr);
    for (int i = 0; i < left.length(); i++) {
      if (leftArr[i] != rightArr[i]) {
        return false;
      }
    }
    return true;
  }

  public static boolean sameCharactersO1(String left, String right) {
    if (left.length() != right.length()) {
      return false;
    }
    char[] result = new char[256];
    for (char value : left.toCharArray()) {
      result[value]++;
    }
    for (char value : right.toCharArray()) {
      char c = result[value];
      if (c > 1) {
        result[value]--;
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
