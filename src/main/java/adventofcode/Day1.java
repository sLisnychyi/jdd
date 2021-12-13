package adventofcode;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static academy.kovalevskyi.javadeepdive.week1.day2.HttpMethod.GET;

public class Day1 {
  public static void main(String[] args) throws IOException, InterruptedException {

    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create("https://adventofcode.com/2021/day/4/input"))
            .header(
                "cookie",
                "session=53616c7465645f5f3cb3a7d2e3f82552f01bd644ba3af467af795160a70030d96b1f032cf40be7971ec365ea1d8e8f45")
            .build();
    HttpResponse<Stream<String>> send =
        HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofLines());

    Stream<String> body = send.body();
    //        Stream<String> body =
    // Arrays.stream(("7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1\n" +
    //                "\n" +
    //                "22 13 17 11  0\n" +
    //                " 8  2 23  4 24\n" +
    //                "21  9 14 16  7\n" +
    //                " 6 10  3 18  5\n" +
    //                " 1 12 20 15 19\n" +
    //                "\n" +
    //                " 3 15  0  2 22\n" +
    //                " 9 18 13 17  5\n" +
    //                "19  8  7 25 23\n" +
    //                "20 11 10 24  4\n" +
    //                "14 21 16 12  6\n" +
    //                "\n" +
    //                "14 21 17 24  4\n" +
    //                "10 16 15  9 19\n" +
    //                "18  8 23 26 20\n" +
    //                "22 11 13  6  5\n" +
    //                " 2  0 12  3  7").split("\n"));

    List<String> collect = body.collect(Collectors.toList());

    String[] elems = collect.get(0).split(",");

    List<Map<Integer, String>> tables = new ArrayList<>();
    List<int[][]> grids = new ArrayList<>();

    for (int i = 1; i < collect.size(); i++) {
      Map<Integer, String> valueWithPositions = new HashMap<>();
      String line = collect.get(i);
      if (line.equals("")) {
        int[][] tmpGrid = new int[5][5];
        for (int j = i + 1; j < i + 1 + 5; j++) {
          List<String> rowElems =
              Arrays.stream(collect.get(j).split(" ")).filter(e -> e.length() > 0).toList();
          for (int k = 0; k < rowElems.size(); k++) {
            int key = Integer.parseInt(rowElems.get(k));
            valueWithPositions.put(key, (j - i - 1) + "" + k);
            tmpGrid[(j - i - 1)][k] = key;
          }
        }
        tables.add(valueWithPositions);
        grids.add(tmpGrid);
      }
    }

    int result = execute(elems, tables, grids);

    System.out.println(result);
  }

  private static int execute(
      String[] elems, List<Map<Integer, String>> tables, List<int[][]> grids) {
    Set<Integer> visited =
        new HashSet<>(
            Set.of(
                Integer.parseInt(elems[0]),
                Integer.parseInt(elems[1]),
                Integer.parseInt(elems[2]),
                Integer.parseInt(elems[3])));

    Set<Integer> winBoard = new LinkedHashSet<>();
    int lastBoard = -1;
    int lastElem = -1;
    for (int i = 4; i < elems.length && winBoard.size() != grids.size(); i++) {
      int elem = Integer.parseInt(elems[i]);
      visited.add(elem);
      for (int j = 0; j < tables.size(); j++) {
        Map<Integer, String> map = tables.get(j);
        int[][] grid = grids.get(j);
        String s = map.get(elem);
        if (s == null || winBoard.contains(j)) continue;
        String[] rowCol = s.split("");
        boolean valid = true;
        for (int k = 0; k < 5; k++) {
          int row = Integer.parseInt(rowCol[0]);
          int val = grid[row][k];
          if (!visited.contains(val)) {
            valid = false;
            break;
          }
        }
        if (valid) {
          winBoard.add(j);
          if (winBoard.size() == grids.size()) {
            lastBoard = j;
            lastElem = elem;
            break;
          }
        }
        //                if (valid && (winBoard.size() == grids.size() - 1 || i == elems.length -
        // 1)) {
        //                    return map.keySet().stream().filter(e ->
        // !visited.contains(e)).mapToInt(e -> e).sum() * elem;
        //                } else if (valid) {
        //                    winBoard.add(j);
        //                    lastBoard = j;
        //                    lastElem = elem;
        //                    break;
        //                }
        valid = true;
        for (int k = 0; k < 5; k++) {
          int col = Integer.parseInt(rowCol[1]);
          int val = grid[k][col];
          if (!visited.contains(val)) {
            valid = false;
            break;
          }
        }
        if (valid) {
          winBoard.add(j);
          if (winBoard.size() == grids.size()) {
            lastBoard = j;
            lastElem = elem;
            break;
          }
          //                    break;
        }
        //                if (valid && (winBoard.size() == grids.size() - 1 || i == elems.length -
        // 1)) {
        //                    return map.keySet().stream().filter(e ->
        // !visited.contains(e)).mapToInt(e -> e).sum() * elem;
        //                } else if (valid) {
        //                    winBoard.add(j);
        //                    lastBoard = j;
        //                    lastElem = elem;
        //                    break;
        //                }
      }
    }
    return tables.get(lastBoard).keySet().stream()
            .filter(e -> !visited.contains(e))
            .mapToInt(e -> e)
            .sum()
        * lastElem;
  }
}
