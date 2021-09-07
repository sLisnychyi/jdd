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
      next = moveByTwo(next);
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
      for (int i = 0; i < 2; i++) {
        if (next != null) {
          next = next.next;
        }
      }
    }
    return false;
  }

  private static Node moveByTwo(Node next) {
    next = next.next;
    if (next != null) {
      next = next.next;
    }
    return next;
  }
}
