package academy.kovalevskyi.algorithms.week0.day2;

import java.util.OptionalInt;

public class Tasks {

  public static OptionalInt findIndex(int[] sortedArray, int target) {
    int i = -1;
    int j = sortedArray.length;
    while (j - i > 1) {
      int middleIndex = (i + j) / 2;
      int middle = sortedArray[middleIndex];
      if (target == middle) return OptionalInt.of(middleIndex);
      if (target > middle) {
        i = middleIndex;
      } else {
        j = middleIndex;
      }
    }
    return OptionalInt.empty();
  }

}
