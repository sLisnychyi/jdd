package academy.kovalevskyi.algorithms.week1.day1;

import java.util.Arrays;
import java.util.stream.IntStream;

public class XoBoard {
  private XoFigure[][] board;

  public XoBoard() {
    board = new XoFigure[3][3];
  }

  public XoBoard(XoBoard copy) {
    this.board = copy.board;
  }

  public XoFigure getFigure(int x, int y) {
    return board[x][y];
  }

  public void setFigure(int x, int y, XoFigure figure) {
    board[x][y] = figure;
  }

  public XoFigure hasWinner() {
    return XoFigure.figureO;
  }

  public boolean tie() {
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    XoBoard xoBoard = (XoBoard) o;
    return Arrays.equals(board, xoBoard.board);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(board);
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
