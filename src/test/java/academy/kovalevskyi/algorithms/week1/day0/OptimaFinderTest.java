//package academy.kovalevskyi.algorithms.week1.day0;
//
//import com.google.common.truth.Truth;
//import org.junit.jupiter.api.Test;
//
//import java.util.function.Function;
//
//
//class OptimaFinderTest {
//    private final Function<Double, Double> absFunction = (x) -> {
//        return Math.abs(x + 1.0D) + 1.0D;
//    };
//    private final Function<Double, Double> invertedAbsFunction = (x) -> {
//        return -(Math.abs(x + 1.0D) + 1.0D);
//    };
//    private final Function<Double, Double> flatLeftAndUp = (x) -> {
//        return x < -1.0D ? 1.0D + -x * 2.0E-6D : x;
//    };
//    private final Function<Double, Double> flatLeftAndDown = (x) -> {
//        return x < 0.0D ? 1.0D + -x * 2.0E-6D : -x;
//    };
//    private final Function<Double, Double> upAndFlatRight = (x) -> {
//        return x < -1.0D ? -x : 1.0D + x * 2.0E-6D;
//    };
//    private final Function<Double, Double> downAndFlatRight = (x) -> {
//        return x < 0.0D ? x : 1.0D + x * 2.0E-6D;
//    };
//    private final Function<Double, Double> flatLineUp = (x) -> {
//        return 5.0D * x + 2.0D;
//    };
//    private final Function<Double, Double> flatLineDown = (x) -> {
//        return -(5.0D * x + 2.0D);
//    };
//
//    @Test
//    public void findOptimaWithSimpleAbs() {
//        double answer = OptimaFinder.findOptima(this.absFunction, -10.0D, 6.0D, 0.1D);
//        checkAnswerWithingRange(answer, -10.0D, 6.0D);
//        Truth.assertWithMessage("checking findOptima with simple abs function Math.abs(x + 1) + 1").that(answer).isLessThan(-0.89D);
//        Truth.assertWithMessage("checking findOptima with simple abs function Math.abs(x + 1) + 1").that(answer).isGreaterThan(-1.11D);
//    }
//
//    @Test
//    public void findOptimaWithFlatLineDown() {
//        double answer = OptimaFinder.findOptima(this.flatLineDown, 6.0D, 99.0D, 0.5D);
//        checkAnswerWithingRange(answer, 6.0D, 99.0D);
//        Truth.assertWithMessage("checking findOptima with line going down").that(answer).isAtMost(99);
//        Truth.assertWithMessage("checking findOptima with line going down").that(answer).isGreaterThan(98.49D);
//    }
//
//    private static void checkAnswerWithingRange(double answer, double min, double max) {
//        Truth.assertWithMessage(String.format("asserting that answer %f is >=%f and <=%f", answer, min, max)).that(answer).isAtMost(max);
//        Truth.assertWithMessage(String.format("asserting that answer %f is >=%f and <=%f", answer, min, max)).that(answer).isAtLeast(min);
//    }
//
//}