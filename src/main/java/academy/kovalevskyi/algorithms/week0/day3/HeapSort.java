package academy.kovalevskyi.algorithms.week0.day3;

import academy.kovalevskyi.algorithms.week0.day0.Sort;

import java.util.*;

public class HeapSort implements Sort {

  public static void main(String[] args) {
    System.out.println(new Heap(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
  }

  @Override
  public void sort(char[] target, Comparator<Character> comparator) {

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

class Heap {
  private final int[] array;

  Heap(int[] array) {
    this.array = array;
    maxHeapify(array, 0);
  }

  void maxHeapify(int[] arr,  int i){

  }

  record Node(int value, Node left, Node right) {}
}
