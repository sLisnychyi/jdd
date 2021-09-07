package academy.kovalevskyi.algorithms.week0.day1;

import academy.kovalevskyi.algorithms.week0.day0.Sort;
import java.util.Comparator;

public class InsertionSort implements Sort {

  @Override
  public <T> void sort(T[] target, Comparator<T> comparator) {
    for (int i = 1; i < target.length; i++) {
      int index = i;
      while (index > 0 && comparator.compare(target[index], target[index - 1]) < 0) {
        T tmp = target[index];
        target[index] = target[index - 1];
        target[index - 1] = tmp;
        index--;
      }
    }
  }
}
