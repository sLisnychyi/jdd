package academy.kovalevskyi.algorithms.week1.day0;

public record GraphBinaryNode<T>(
                                 GraphBinaryNode<T> left,
                                 GraphBinaryNode<T> right,
                                 T value) {
}
