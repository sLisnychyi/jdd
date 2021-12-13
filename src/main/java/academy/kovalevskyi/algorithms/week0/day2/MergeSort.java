package academy.kovalevskyi.algorithms.week0.day2;

import academy.kovalevskyi.algorithms.week0.day0.Sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.copyOfRange;

public class MergeSort implements Sort {

  @Override
  public <T> void sort(T[] target, Comparator<T> comparator) {
    if (target.length > 0) sort(target, 0, target.length - 1, comparator);
  }

  private <T> void sort(T[] target, int left, int right, Comparator<T> comparator) {
    if (right - left == 0) return;
    if (right - left == 1) {
      if (comparator.compare(target[left], target[right]) > 0) {
        swap(target, left, right);
      }
    }
    int mid = (right + left) / 2;
    sort(target, left, mid, comparator);
    sort(target, mid + 1, right, comparator);
    for (int i = left; i < right; i++) {
      if (comparator.compare(target[i], target[i + 1]) > 0) {
        int k = i;
        while (k >= 0 && comparator.compare(target[k], target[k + 1]) > 0) {
          swap(target, k, k + 1);
          k--;
        }
      }
    }
  }

  @Override
  public <T> T[] createSortedArray(T[] target, Comparator<T> comparator) {
    T[] result = copyOf(target, target.length);
    sort(result, comparator);
    return result;
  }

  @Override
  public String complexityBest() {
    return "N*log(N)";
  }

  @Override
  public String complexityAverage() {
    return "N*log(N)";
  }

  @Override
  public String complexityWorst() {
    return "N*log(N)";
  }

  @Override
  public String spaceComplexityWorst() {
    return "N";
  }
}
