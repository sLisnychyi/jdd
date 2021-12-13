package academy.kovalevskyi.algorithms.week0.day3;

import academy.kovalevskyi.algorithms.week0.day0.Sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort implements Sort {

  public static void main(String[] args) {
    Integer[] arr = {1, 7, 9, 2, 3, 9, 1, 4};
    new QuickSort().sort(arr, Comparator.naturalOrder());
    System.out.println(Arrays.toString(arr));
  }

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
      return;
    }
    int pivotal = ThreadLocalRandom.current().nextInt(left, right);
    int i = left;
    int j = right;
    while (i < j) {
      if (comparator.compare(target[i], target[pivotal]) < 0) {
        i++;
      } else if (comparator.compare(target[j], target[pivotal]) > 0) {
        j--;
      } else {
        swap(target, i, j);
        i++;
        j--;
      }
    }
    sort(target, left, j, comparator);
    sort(target, i, right, comparator);
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
    return "N^2";
  }

  @Override
  public String spaceComplexityWorst() {
    return "N";
  }
}
