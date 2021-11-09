package academy.kovalevskyi.algorithms.week1.day1;

import academy.kovalevskyi.algorithms.week1.day0.GraphBinaryNode;
import java.util.LinkedList;
import java.util.Queue;

public class GraphHelper2 {
  public static boolean includesDepthFirstSearch(GraphBinaryNode<?> root, Object value) {
    if (root == null) {
      return false;
    }
    if (root.value().equals(value)) return true;
    return includesDepthFirstSearch(root.left(), value)
        || includesDepthFirstSearch(root.right(), value);
  }

  public static boolean includesBreathFirstSearch(GraphBinaryNode<?> root, Object value) {
    Queue<GraphBinaryNode<?>> queue = new LinkedList<>();
    queue.add(root);
    while (queue.size() != 0) {
      GraphBinaryNode<?> node = queue.poll();
      if (node.value() == value) return true;
      if (node.left() != null) queue.add(node.left());
      if (node.right() != null) queue.add(node.right());
    }
    return false;
  }
}
