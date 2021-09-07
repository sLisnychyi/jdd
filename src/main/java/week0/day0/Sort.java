package week0.day0;

import java.util.Arrays;
import java.util.Comparator;

public interface Sort {
  default <T extends Comparable<? super T>> void sort(final T[] target) {
    sort(target, T::compareTo);
  }

  default <T> void sort(final T[] target, final Comparator<T> comparator) {
    for (int i = 0; i < target.length; i++) {
      for (int j = i + 1; j < target.length; j++) {
        if (comparator.compare(target[i], target[j]) > 0) {
          T tmp = target[i];
          target[i] = target[j];
          target[j] = tmp;
        }
      }
    }
  }

  default <T> T[] createSortedArray(final T[] target, final Comparator<T> comparator) {
    T[] copy = Arrays.copyOf(target, target.length);
    sort(copy, comparator);
    return copy;
  }

  default String complexityBest() {
    return "N";
  }

  default String complexityAverage() {
    return "N^2";
  }

  default String complexityWorst() {
    return "N^2";
  }

  default String spaceComplexityWorst() {
    return "N";
  }
}
