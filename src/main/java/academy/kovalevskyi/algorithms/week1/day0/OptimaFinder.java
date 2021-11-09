package academy.kovalevskyi.algorithms.week1.day0;

import java.util.function.Function;

public class OptimaFinder {

  public static double findOptima(
      Function<Double, Double> f, double start, double end, double precision) {
    while (getRange(start, end) > precision) {
      double mid = (getRange(start, end) / 2) + start;
      Double midV = f.apply(mid);
      Double leftV = f.apply(mid - precision);
      if (midV < leftV) {
        start = mid;
      } else {
        end = mid;
      }
    }
    return start;
  }

  private static double getRange(double start, double end) {
    if (start < 0) {
      return start * -1 + end;
    } else {
      if (end < 0) {
        return end * -1 + start;
      }
      return end > start ? end - start : start - end;
    }
  }
}
