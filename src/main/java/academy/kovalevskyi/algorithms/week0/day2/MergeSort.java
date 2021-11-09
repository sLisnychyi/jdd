package academy.kovalevskyi.algorithms.week0.day2;

import academy.kovalevskyi.algorithms.week0.day0.Sort;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSort implements Sort {

  @Override
  public <T> void sort(T[] target, Comparator<T> comparator) {
    T[] sortedArray = createSortedArray(target, comparator);
    System.arraycopy(sortedArray, 0, target, 0, target.length);
  }

  @Override
  public <T> T[] createSortedArray(T[] target, Comparator<T> comparator) {
    if (target == null) throw new NullPointerException();
    if (target.length == 1) {
      return Arrays.copyOf(target, 1);
    }
    T[] left = createSortedArray(Arrays.copyOfRange(target, 0, target.length / 2), comparator);
    T[] right =
        createSortedArray(Arrays.copyOfRange(target, target.length / 2, target.length), comparator);
    return merge(left, right, comparator);
  }

  private <T> T[] merge(T[] left, T[] right, Comparator<T> comparator) {
    T[] result = Arrays.copyOf(left, left.length + right.length);
    int i = 0;
    int j = 0;
    while (i + j != left.length + right.length) {
      if (i >= left.length) {
        result[i + j] = right[j++];
      } else if (j >= right.length) {
        result[i + j] = left[i++];
      } else {
        if (comparator.compare(left[i], right[j]) > 0) {
          result[i + j] = right[j++];
        } else {
          result[i + j] = left[i++];
        }
      }
    }
    return result;
  }
}
