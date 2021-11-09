package academy.kovalevskyi.algorithms.week1.day0;

public class IntGraphHelper {
  public static GraphBinaryNode<Integer> createNode(Integer value) {
    return new GraphBinaryNode<>(null, null, value);
  }

  public static GraphBinaryNode<Integer> addNode(GraphBinaryNode<Integer> root, Integer value) {
    if (root == null) return null;
    GraphBinaryNode<Integer> left = root.left();
    GraphBinaryNode<Integer> right = root.right();
    if (value < root.value()) {
      left = left == null ? createNode(value) : addNode(left, value);
    } else {
      right = right == null ? createNode(value) : addNode(right, value);
    }
    return new GraphBinaryNode<>(left, right, root.value());
  }

  public static boolean contains(GraphBinaryNode<Integer> root, Integer value) {
    if (root == null) return false;
    if (root.value().equals(value)) return true;
    return root.value() > value ? contains(root.right(), value) : contains(root.left(), value);
  }
}
