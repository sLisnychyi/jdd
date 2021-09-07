package academy.kovalevskyi.algorithms.week0.day1;

public class Tasks {
  public static class Node {
    public Node next;
    public int value;
  }

  public static int findMiddleInOneGo(Node start) {
    Node cur = start;
    Node next = start.next;
    while (next != null) {
      cur = cur.next;
      next = next.next;
      if (next != null) {
        next = next.next;
      }
    }
    return cur.value;
  }

  public static boolean hasCycle(Node start) {
    Node next = start.next;
    while (next != null) {
      if (start == next) {
        return true;
      }
      start = start.next;
      next = next.next;
      if (next != null) {
        next = next.next;
      }
    }
    return false;
  }

}
